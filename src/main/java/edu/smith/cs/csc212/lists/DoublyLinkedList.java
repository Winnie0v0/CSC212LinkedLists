package edu.smith.cs.csc212.lists;

import edu.smith.cs.csc212.lists.SinglyLinkedList.Node;
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
		T firstValue = this.start.value;
		this.start = this.start.after;
		if (this.start == null) {
			this.end = null;
			return firstValue;
		} 
		this.start.before.after = null;
		this.start.before = null;
		return firstValue;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		T firstValue = this.end.value;
		this.end = this.end.before;
		if (this.end == null) {
			this.start = null;
			return firstValue;
		} 
		this.end.after.before = null;
		this.end.after = null;
		return firstValue;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		if (index == 0) {
			return removeFront();
			} 
		if (index == this.size()-1) {
			return removeBack();
			}
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				T thisValue = n.value;
				n.before.after = n.after;
				n.after.before = n.before;
				return thisValue;
			}
		}
		throw new BadIndexError(index);
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
		if (index == 0) {
			addFront(item);	
			return;
			} 
		if (index == this.size()) {
			addBack(item);
			return;
			}
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				Node<T> newNode = new Node<T>(item);
				Node<T> m = n.before;
				n.before = newNode;
				newNode.before = m;
				m.after = newNode;
				newNode.after = n;
				return;
				}
			}
		throw new BadIndexError(index);
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.end.value;
	}
	
	@Override
	public T getIndex(int index) {
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
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				n.value = value;
				return;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count ++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
}
