/**
 *	Doubly Circular Tailed Linked List
 * 	Developed by Abdul Rahman
 */

import java.util.*;

public class DCTLinkedList {
	public static void main(String[] args) {


		LinkedList<String> list = new LinkedList<String>();

		System.out.println("Part 1");
		list.addFirst("aaa");
		list.addFirst("bbb");
		list.addFirst("ccc");
		list.print();
		System.out.println("head: " + list.getHead() + " prev: " + list.getHead().getPrev() + " next: " + list.getHead().getNext());
		System.out.println("tail: " + list.getTail() + " prev: " + list.getTail().getPrev() + " next: " + list.getTail().getNext());
		
		ListNode<String> n = list.getHead();
		do {
			System.out.println("N: " + n + " Prev: " + n.getPrev() + " Next: " + n.getNext());
			n = n.getNext();
		} while (n != list.getTail().getNext());
		
		System.out.println("\nPart 2");
		list.addLast("xxx");
		list.print();
		System.out.println("head: " + list.getHead() + " prev: " + list.getHead().getPrev() + " next: " + list.getHead().getNext());
		System.out.println("tail: " + list.getTail() + " prev: " + list.getTail().getPrev() + " next: " + list.getTail().getNext());

		n = list.getHead();
		do {
			System.out.println("N: " + n + " Prev: " + n.getPrev() + " Next: " + n.getNext());
			n = n.getNext();
		} while (n != list.getTail().getNext());
		
		System.out.println("\nPart 3");
		list.removeAfter(null);
		list.print();
		System.out.println("head: " + list.getHead() + " prev: " + list.getHead().getPrev() + " next: " + list.getHead().getNext());
		System.out.println("tail: " + list.getTail() + " prev: " + list.getTail().getPrev() + " next: " + list.getTail().getNext());

		n = list.getHead();
		do {
			System.out.println("N: " + n + " Prev: " + n.getPrev() + " Next: " + n.getNext());
			n = n.getNext();
		} while (n != list.getTail().getNext());
	}
}

class LinkedList<E> {
	protected ListNode<E> head = null;
	protected ListNode<E> tail = null;
	protected int size = 0;

	public LinkedList() {}

	public int getSize() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	public E getFirst() throws NoSuchElementException {
		if (head == null) 
			throw new NoSuchElementException("can't get from an empty list");
		else return head.getElement();
	}

	public boolean contains(E item) {
		for (ListNode <E> n = head; n != null; n = n.getNext())
			if (n.getElement().equals(item)) return true;
		return false;
	}

	public ListNode<E> get(int num) {
		if (num < 1 && num > size) {
			return null;
		} else {
			ListNode<E> temp = head;
			while (num != 1) {
				temp = temp.getNext();
				num--;
			}
			return temp;
		}
	}

	public ListNode<E> get(E item) {
		ListNode<E> temp = head;

		do {
			if (temp.getElement().equals(item)) return temp;
			temp = temp.getNext();
		} while (temp != tail.getNext());
		return null;
	}

	public void addFirst(E item) {
		addAfter(item, null);
	}

	public void addLast(E item) {
		if (head != null) {    
			ListNode<E> newNode = new ListNode <E> (item, head, tail);
			tail.setNext(newNode);
			newNode.getNext().setPrev(newNode);
			tail = tail.getNext();
		} else {
			tail = new ListNode <E> (item, head, tail);
			head = tail;
		}
		size++;
	}

	public void addAfter(E item, ListNode<E> after) {
		if (after != null) {
			ListNode<E> newNode = new ListNode <E> (item, after.getNext(), after);
			after.setNext(newNode);
			if (newNode.getNext() != null)
				newNode.getNext().setPrev(newNode);
			if (after == tail)  
				tail = after.getNext();
		} else { 
				ListNode<E> newNode = new ListNode<E>(item, head, tail);
				head = newNode;
				if (newNode.getNext() != null)
					newNode.getNext().setPrev(head);
				if (tail == null) {
					tail = newNode;
				} else {
					newNode.getPrev().setNext(newNode);
				}
		}
		size++;
	}

	public E removeFirst() throws NoSuchElementException{
		return removeAfter(null);
	}

	public E removeAfter(ListNode<E> after) throws NoSuchElementException {
		if (after != null) {
			ListNode<E> next = after.getNext();
			if (next != null) {
				after.setNext(next.getNext());
				size--;
				if (next.getNext() == null) {
					after.setNext(head);
					head.setPrev(after);
					tail = after;
				} else {
					after.getNext().setPrev(after);
				}
				return next.getElement();
			} else throw new NoSuchElementException("This is the last node");
		} else {
			if (head == null)
				throw new NoSuchElementException("Linked List is empty");
			else {
				E item = head.getElement();
				head = head.getNext();
				size--;
				if (head == null) tail = null;
				if (tail != null) {
					tail.setNext(head);
					head.setPrev(tail);
				}
				return item;
			}
		}
	}

	public boolean remove(E item) {
		ListNode<E> temp = this.get(item);
		if (temp != null) {
			removeAfter(temp.getPrev());
			return true;
		}
		return false;
	}

	public boolean  updateNode(E element, ListNode<E> node) {
		ListNode<E> n = head;
		if (head == null)
			return false;
		do {
			if (n == node) {
				n.setElement(element); 
				return true;
			}
		} while (n != tail.getNext());
		return false;
	}

	public void print() throws NoSuchElementException {
		if (head == null)
			throw new NoSuchElementException("Linked List is empty");

		ListNode<E> n = head;
		do {
			System.out.print(n.getElement() + ", " );
			n = n.getNext();
		} while (n != tail.getNext());

		System.out.println("size: " + size);
	}
}

class ListNode<E> {
	protected E element;
	protected ListNode<E> next;
	protected ListNode<E> prev;

	public ListNode(E item) {
		this(item, null, null);
	}

	public ListNode(E item, ListNode<E> n, ListNode<E> p) {
		element = item;
		next = n;
		prev = p;
	}

	public ListNode<E> getNext() {
		return this.next;
	}

	public E getElement() {
		return this.element;
	}

	public ListNode<E> getPrev() {
		return this.prev;
	}

	public void setNext(ListNode<E> item) {
		this.next = item;
	}

	public void setElement(E item) {
		this.element = item;
	}

	public void setPrev(ListNode<E> item) {
		this.prev = item;
	}

	public String toString() {
		return String.valueOf(this.getElement());
	}
}
