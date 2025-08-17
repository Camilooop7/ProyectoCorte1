package co.edu.unbosque.util.structure;

import java.io.Serializable;


public class LinkedList<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Node<E> first;

	public LinkedList() {
		this.first = null;
	}

	public Node<E> getFirst() {
		return first;
	}

	public void setFirst(Node<E> first) {
		this.first = first;
	}

	public boolean isEmpty() {
		return (this.first == null);
	}

	public void insert(E info, Node<E> previous) {
		if (previous != null) {
			Node<E> newNode = new Node<>(info);
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}
	}

	public void addLastR(E info) {
		if (this.isEmpty()) {
			this.first = new Node<>(info);
		} else {
			addLastR(this.first, info);
		}
	}

	private void addLastR(Node<E> current, E info) {
		if (current.getNext() == null) {
			current.setNext(new Node<>(info));
		} else {
			addLastR(current.getNext(), info);
		}
	}

	public Node<E> getLastNodeR() {
		if (this.isEmpty()) {
			return null;
		}
		return getLastNodeR(this.first);
	}

	private Node<E> getLastNodeR(Node<E> current) {
		if (current.getNext() == null) {
			return current;
		} else {
			return getLastNodeR(current.getNext());
		}
	}

	public E extract(Node<E> previous) {
		E data = null;
		if (previous != null && previous.getNext() != null) {
			data = previous.getNext().getInfo();
			previous.setNext(previous.getNext().getNext());
		}
		return data;
	}

	@Override
	public String toString() {
		return toStringR(this.first);
	}

	private String toStringR(Node<E> current) {
		if (current == null) {
			return "";
		}
		return current.getInfo().toString() + (current.getNext() != null ? "\n" + toStringR(current.getNext()) : "");
	}

	public Node<E> find(E info) {
		return getR(this.first, info);
	}

	private Node<E> getR(Node<E> current, E info) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().equals(info)) {
			return current;
		} else {
			return getR(current.getNext(), info);
		}
	}

	public boolean deleteByInfo(E info) {
		if (isEmpty()) {
			return false;
		}

		// Caso especial: eliminar el primer nodo
		if (first.getInfo().equals(info)) {
			first = first.getNext();
			return true;
		}

		// Caso general: buscar recursivamente
		return deleteByInfoR(first, info);
	}

	private boolean deleteByInfoR(Node<E> previous, E info) {
		if (previous == null || previous.getNext() == null) {
			return false; // No encontrado
		}

		if (previous.getNext().getInfo().equals(info)) {
			return extract(previous) != null; // Eliminación segura
		}

		return deleteByInfoR(previous.getNext(), info);
	}

	public Node<E> get(int n) {
		if (n < 0 || this.first == null) {
			return null;
		}
		if (n == 0) {
			return this.first;
		}
		LinkedList<E> subList = new LinkedList<>();
		subList.first = this.first.getNext();
		return subList.get(n - 1);
	}
	
	public int size() {
	    return sizeRecursive(first);
	}

	private int sizeRecursive(Node<E> nodo) {
	    if (nodo == null) {
	        return 0;  // caso base: lista vacía o llegamos al final
	    }
	    return 1 + sizeRecursive(nodo.getNext()); // cuenta este nodo y sigue con el siguiente
	}


	public String print() {
		return this.toString();
	}
}
