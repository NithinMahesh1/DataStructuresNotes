package list;

public class LinkedList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		// TODO
		int count = 0;
		Node<E> curr = head;
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}
	
	/**
	 * 
	 * @param e the element search for
	 * @return true iff the list contains an element of whose value equals that of e
	 */
	public boolean contains(E e) {
		// TODO
		Node<E> temp = head;
		while (temp != null) {
			if (temp.getValue().equals(e)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
	
	/**
	 * Appends the element e to the end of the list.
	 * 
	 * @param e the value to append
	 */
	public void append(E e) {
		// TODO
		Node<E> temp = new Node<E>(e);
		
		if (tail == null) {
			head = temp;
			tail = temp;
		}
		else {
			tail.setNext(temp);
			tail = tail.getNext();
		}
	}
	
	
	@Override
	public String toString() {
		// TODO
		return head.toString();
	}
}
