package co.edu.unbosque.util.structure;

import java.io.Serializable;

/**
 * Lista enlazada genérica con operaciones básicas y soporte para recursividad y
 * serialización.
 * 
 * @param <E> Tipo de los elementos almacenados.
 */
public class LinkedList<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Node<E> first;

	/** Constructor: crea una lista vacía. */
	public LinkedList() {
		this.first = null;
	}

	/** @return Primer nodo de la lista. */
	public Node<E> getFirst() {
		return first;
	}

	/** @param first Nuevo primer nodo de la lista. */
	public void setFirst(Node<E> first) {
		this.first = first;
	}

	/** @return true si la lista está vacía. */
	public boolean isEmpty() {
		return this.first == null;
	}

	/** Inserta un elemento después del nodo especificado. */
	public void insert(E info, Node<E> previous) {
		if (previous != null) {
			Node<E> newNode = new Node<>(info);
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}
	}

	/** Agrega un elemento al final de forma recursiva. */
	public void addLastR(E info) {
		if (this.isEmpty())
			this.first = new Node<>(info);
		else
			addLastR(this.first, info);
	}

	/** Método recursivo para agregar al final. */
	private void addLastR(Node<E> current, E info) {
		if (current.getNext() == null)
			current.setNext(new Node<>(info));
		else
			addLastR(current.getNext(), info);
	}

	/** @return Último nodo de la lista. */
	public Node<E> getLastNodeR() {
		if (this.isEmpty())
			return null;
		return getLastNodeR(this.first);
	}

	/** Método recursivo para obtener el último nodo. */
	private Node<E> getLastNodeR(Node<E> current) {
		return current.getNext() == null ? current : getLastNodeR(current.getNext());
	}

	/** Extrae el nodo siguiente al especificado. */
	public E extract(Node<E> previous) {
		if (previous == null || previous.getNext() == null)
			return null;
		E data = previous.getNext().getInfo();
		previous.setNext(previous.getNext().getNext());
		return data;
	}

	/** Limpia la lista. */
	public void clear() {
		this.first = null;
	}

	/** @return Representación en cadena de la lista. */
	@Override
	public String toString() {
		return toStringR(this.first);
	}

	/** Método recursivo para generar la cadena. */
	private String toStringR(Node<E> current) {
		return current == null ? ""
				: current.getInfo().toString() + (current.getNext() != null ? "\n" + toStringR(current.getNext()) : "");
	}

	/** @return Nodo que contiene la información especificada. */
	public Node<E> find(E info) {
		return getR(this.first, info);
	}

	/** Método recursivo para buscar un elemento. */
	private Node<E> getR(Node<E> current, E info) {
		return current == null ? null : current.getInfo().equals(info) ? current : getR(current.getNext(), info);
	}

	/** Elimina un nodo por su información. */
	public boolean deleteByInfo(E info) {
		if (isEmpty())
			return false;
		if (first.getInfo().equals(info)) {
			first = first.getNext();
			return true;
		}
		return deleteByInfoR(first, info);
	}

	/** Método recursivo para eliminar un nodo. */
	private boolean deleteByInfoR(Node<E> previous, E info) {
		if (previous == null || previous.getNext() == null)
			return false;
		if (previous.getNext().getInfo().equals(info)) {
			extract(previous);
			return true;
		}
		return deleteByInfoR(previous.getNext(), info);
	}

	/** @return Nodo en la posición especificada. */
	public Node<E> get(int n) {
		if (n < 0 || this.first == null)
			return null;
		if (n == 0)
			return this.first;
		LinkedList<E> subList = new LinkedList<>();
		subList.first = this.first.getNext();
		return subList.get(n - 1);
	}

	/** @return Tamaño de la lista. */
	public int size() {
		return sizeRecursive(first);
	}

	/** Método recursivo para calcular el tamaño. */
	private int sizeRecursive(Node<E> nodo) {
		return nodo == null ? 0 : 1 + sizeRecursive(nodo.getNext());
	}

	/** @return Cadena con los elementos de la lista. */
	public String print() {
		return this.toString();
	}
}
