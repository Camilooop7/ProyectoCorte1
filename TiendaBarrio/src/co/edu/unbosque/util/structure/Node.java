package co.edu.unbosque.util.structure;

import java.io.Serializable;

/**
 * Nodo genérico para listas enlazadas. Almacena información y referencia al
 * siguiente nodo.
 * 
 * @param <E> Tipo de la información almacenada.
 */
public class Node<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private E info;
	private Node<E> next;

	/** Constructor con información y siguiente nodo. */
	public Node(E info, Node<E> next) {
		this.info = info;
		this.next = next;
	}

	/** Constructor con solo información. */
	public Node(E info) {
		this(info, null);
	}

	/** Constructor por defecto. */
	public Node() {
		this(null, null);
	}

	/** @return Información almacenada en el nodo. */
	public E getInfo() {
		return info;
	}

	/** @param info Nueva información para el nodo. */
	public void setInfo(E info) {
		this.info = info;
	}

	/** @return Siguiente nodo en la lista. */
	public Node<E> getNext() {
		return next;
	}

	/** @param next Nuevo siguiente nodo. */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/** @return Representación en cadena de la información. */
	@Override
	public String toString() {
		return info != null ? info.toString() : null;
	}
}
