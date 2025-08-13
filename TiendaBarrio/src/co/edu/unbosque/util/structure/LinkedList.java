package co.edu.unbosque.util.structure;

public class LinkedList<E> {

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
			Node<E> newNode = new Node<E>(info);
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}
	}

	public void addLastR(Node<E> current, E info) {

		if (current.getNext() == null) {
			current.setNext(new Node<E>(info));
		} else {
			current = current.getNext();
			addLastR(current, info);
		}
	}

	public Node<E> getLastNodeR(Node<E> current) {
		current = this.first;
		if (current == null || current.getNext() == null) {
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

	public String print() {
		return toString();
	}

	public String toStringR(Node<E> current) {
		current = this.first;
		if (current == null) {
			return "";
		}
		if (current.getNext() == null) {
			return current.getInfo().toString();
		} else {
			return current.getInfo().toString() + toStringR(current.getNext());
		}
	}

	public Node<E> getR(Node<E> current, E info) {
		current = this.first;
		if (current == null) {
			return null;
		}
		if (current.getInfo().equals(info)) {
			return current;
		} else {
			return getR(current.getNext(), info);
		}
	}
}
