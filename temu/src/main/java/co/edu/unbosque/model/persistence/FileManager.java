package co.edu.unbosque.model.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

/**
 * Utilidad para leer/escribir archivos de texto y serializados,
 * y para copiar archivos binarios. Guarda por defecto en <root del proyecto>/archivos
 * (carpeta hermana de src y pom.xml). Puede sobreescribirse con -Dapp.archivos.dir=...
 *
 * Además, si detecta despliegue Eclipse WTP, resuelve <workspace>/<proyecto>/archivos.
 */
public final class FileManager {

    /** Ruta base donde se almacenarán los archivos. */
    private static String RUTA_CARPETA;

    private FileManager() {}

    /** Crea la carpeta base (y padres) si no existen. */
    public static void crearCarpeta() {
        File base = new File(RUTA_CARPETA);
        if (!base.exists() && !base.mkdirs()) {
            throw new RuntimeException("No se pudo crear la carpeta: " + base.getAbsolutePath());
        }
        if (!base.isDirectory()) {
            throw new RuntimeException("La ruta no es una carpeta: " + base.getAbsolutePath());
        }
        System.out.println("RUTA_CARPETA = " + base.getAbsolutePath());
    }

    /* -------- Inicialización: VM arg -> WTP -> pom/.git -> user.dir -------- */
    static {
        try {
            String custom = System.getProperty("app.archivos.dir");
            if (custom != null && !custom.isBlank()) {
                RUTA_CARPETA = Paths.get(custom).toAbsolutePath().toString();
            } else {
                Path start = resolveStartPath();
                Path wtpRoot = resolveWtpProjectRoot(start); // <-- NUEVO: soporte WTP
                Path projectRoot = (wtpRoot != null) ? wtpRoot : resolveProjectRootFrom(start);
                RUTA_CARPETA = projectRoot.resolve("archivos").toString();
            }
            crearCarpeta();
        } catch (Exception e) {
            throw new RuntimeException("No se puede inicializar la ruta base de archivos", e);
        }
    }

    /**
     * Obtiene un punto de partida razonable: donde está cargada la clase o user.dir.
     */
    private static Path resolveStartPath() {
        try {
            Path start = Paths.get(FileManager.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());

            // Caso típico: .../target/classes
            if (start.getFileName() != null
                    && start.getFileName().toString().equalsIgnoreCase("classes")
                    && start.getParent() != null
                    && start.getParent().getFileName() != null
                    && start.getParent().getFileName().toString().equalsIgnoreCase("target")) {
                // Deja start como .../target/classes (sirve para detectar WTP también)
            }
            return start.toAbsolutePath().normalize();
        } catch (Exception ignore) {
            return Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        }
    }

    /**
     * Detecta un despliegue WTP:
     *   .../<workspace>/.metadata/.../wtpwebapps/<proyecto>/WEB-INF/classes
     * y devuelve <workspace>/<proyecto> si lo encuentra. Si no, retorna null.
     */
    private static Path resolveWtpProjectRoot(Path start) {
        String pathStr = start.toString().replace('\\', '/'); // normaliza separador
        int idxWtp = pathStr.indexOf("/wtpwebapps/");
        int idxMeta = pathStr.indexOf("/.metadata/");
        if (idxWtp == -1 || idxMeta == -1 || idxMeta > idxWtp) {
            return null; // no luce como WTP o no se puede deducir workspace
        }

        // Extrae <workspace> (antes de .metadata)
        String workspace = pathStr.substring(0, idxMeta);
        // Extrae <proyecto> inmediatamente después de "wtpwebapps/"
        int projStart = idxWtp + "/wtpwebapps/".length();
        int nextSlash = pathStr.indexOf('/', projStart);
        if (nextSlash == -1) return null;
        String projectName = pathStr.substring(projStart, nextSlash);

        if (projectName.isBlank()) return null;

        Path candidate = Paths.get(workspace, projectName).toAbsolutePath().normalize();
        // Por robustez, si existen src o pom.xml allí, mejor.
        if (Files.exists(candidate.resolve("pom.xml")) || Files.exists(candidate.resolve("src"))) {
            return candidate;
        }
        // Igual devuelve el candidato; suele ser correcto incluso sin pom.xml (proyectos no-Maven).
        return candidate;
    }

    /**
     * Sube por los padres del "start" hasta encontrar la raíz del proyecto (pom.xml o .git).
     * Fallback: si arrancó en target/classes, sube dos niveles; si no, user.dir.
     */
    private static Path resolveProjectRootFrom(Path start) {
        try {
            Path p = start;

            // Si nos dieron .../WEB-INF/classes (app web), subimos dos niveles
            if (p.getFileName() != null && p.getFileName().toString().equalsIgnoreCase("classes")) {
                Path parent = p.getParent(); // WEB-INF
                if (parent != null && parent.getFileName() != null
                        && parent.getFileName().toString().equalsIgnoreCase("WEB-INF")) {
                    p = parent.getParent(); // <proyecto desplegado>
                } else {
                    // Caso típico Maven: .../target/classes -> sube a raíz del proyecto
                    p = (p.getParent() != null && p.getParent().getParent() != null)
                            ? p.getParent().getParent() : p;
                }
            }

            // Busca pom.xml o .git
            Path cursor = p;
            while (cursor != null
                    && !Files.exists(cursor.resolve("pom.xml"))
                    && !Files.exists(cursor.resolve(".git"))) {
                cursor = cursor.getParent();
            }
            if (cursor != null) return cursor.toAbsolutePath().normalize();

            // Último recurso: si era target/classes, intenta subir dos niveles
            if (p.getFileName() != null && p.getFileName().toString().equalsIgnoreCase("classes")) {
                Path up2 = (p.getParent() != null && p.getParent().getParent() != null)
                        ? p.getParent().getParent() : p;
                return up2.toAbsolutePath().normalize();
            }

            // Último-último: user.dir
            return Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        } catch (Exception e) {
            return Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        }
    }

    /* =======================  ARCHIVOS DE TEXTO  ======================= */

    /** Escribe texto en UTF-8. Crea archivo/carpeta si no existen. Sobrescribe. */
    public static void escribirArchivoTexto(String nombreArchivo, String contenido) {
        Path path = Paths.get(RUTA_CARPETA).resolve(nombreArchivo);
        try {
            Path parent = path.getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.writeString(
                path,
                contenido == null ? "" : contenido,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de texto: " + path.toAbsolutePath());
            e.printStackTrace();
        }
    }

    /** Lee texto UTF-8. Si no existe, lo crea vacío y retorna "" (nunca lanza). */
    public static String leerArchivoTexto(String nombreArchivo) {
        Path path = Paths.get(RUTA_CARPETA).resolve(nombreArchivo);
        try {
            if (!Files.exists(path)) {
                Path parent = path.getParent();
                if (parent != null) Files.createDirectories(parent);
                Files.createFile(path);
                return "";
            }
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de texto: " + path.toAbsolutePath());
            e.printStackTrace();
            return null;
        }
    }

    /* =======================  ARCHIVOS SERIALIZADOS  ======================= */

    /** Escribe un objeto serializable. Crea si no existe. Sobrescribe. */
    public static void escribirArchivoSerializado(String nombreArchivo, Object contenido) {
        Path path = Paths.get(RUTA_CARPETA).resolve(nombreArchivo);
        try {
            Path parent = path.getParent();
            if (parent != null) Files.createDirectories(parent);

            try (FileOutputStream fos = new FileOutputStream(path.toFile());
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(contenido);
                oos.flush();
            }
        } catch (NotSerializableException nse) {
            System.out.println("El objeto (o un campo) no implementa Serializable: " + nse.getMessage());
            nse.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problemas al escribir el archivo serializado: " + path.toAbsolutePath());
            e.printStackTrace();
        }
    }

    /** Lee un objeto serializado. Si el archivo no existe o está vacío, retorna null. */
    public static Object leerArchivoSerializado(String nombreArchivo) {
        Path path = Paths.get(RUTA_CARPETA).resolve(nombreArchivo);
        File f = path.toFile();
        if (!f.exists() || f.length() == 0) return null;

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (EOFException eof) {
            System.out.println("Archivo serializado vacío o incompleto: " + path.toAbsolutePath());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada al deserializar: " + path.toAbsolutePath());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo serializado: " + path.toAbsolutePath());
            e.printStackTrace();
            return null;
        }
    }

    /* =======================  COPIA BINARIOS  ======================= */

    /** Copia un archivo binario (ej. imagen). Reemplaza si existe. */
    public static void guardarImagen(File origen, File destino) throws IOException {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Origen y destino no pueden ser null");
        }
        Path src = origen.toPath();
        Path dst = destino.toPath();
        Path parent = dst.getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
    }

    /* =======================  UTILIDADES  ======================= */

    /** Retorna la ruta base actual. */
    public static Path getBaseDir() {
        return Paths.get(RUTA_CARPETA);
    }

    /** Permite cambiar la ruta base en runtime (útil para ruta compartida en red). */
    public static void setBaseDir(String nuevaRuta) {
        if (nuevaRuta == null || nuevaRuta.isBlank()) {
            throw new IllegalArgumentException("La nueva ruta no puede ser vacía");
        }
        RUTA_CARPETA = Paths.get(nuevaRuta).toAbsolutePath().toString();
        crearCarpeta();
    }
}
