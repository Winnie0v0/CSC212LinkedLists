package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.*;

/**
 * A Doubly-Linked List is a list based on nodes that know of their successor and predecessor.
 * @author jfoley
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends ListADT<T> {
	/**
	 * This is a reference to the first node in this list.
	 */
	Node<T> start;
	/**
	 * This is a reference to the last node in this list.
	 */
	Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public void addFront(T item) {
		if (start == null) {
		    start = end = new Node<T>(item);
		  } else {
		    Node<T> second = start;
		    start = new Node<T>(item);
		    start.after = second;
		    second.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		throw new TODOErr();
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
		    return n.value;
				}
			}
		throw new BadIndexError(index);
	}
	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		throw new TODOErr();
	}

	@Override
	public int size() {
		throw new TODOErr();
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
}
