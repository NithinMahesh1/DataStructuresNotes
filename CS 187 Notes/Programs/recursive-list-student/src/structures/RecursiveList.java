package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

	protected int size = 0;
	protected Node<T> head = null;
	protected Node<T> prev = null;
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedIterator<T>(head);
		//return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
		//return 0;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		} 
		if(size() >= 1) {
			head = new Node<T>(elem, head);
			size++;
		}
		if(size() == 0) {
			head = new Node<T>(elem, null);
			size++;
		}
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		// TODO Auto-generated method stub
		//Node<T> pointer = null;
		//pointer = insertLastHelper(pointer); //connect the new node (lastNode) to the list
		//Node<T> lastNode = new Node<T>(elem, null);
		if(elem == null) {
			throw new NullPointerException();
		}
		insertAt(size(), elem);
		return this;
	}
	
	/*public Node<T> insertLastHelper(Node<T> pointer) {
		if(isEmpty()) {
			return pointer;
		}
		else if(pointer.getNext() != null) {
			pointer = pointer.getNext();
			insertLastHelper(pointer);
		}
		return pointer;
	}*/

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// TODO Auto-generated method stub
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(elem == null) {
			throw new NullPointerException();
		}
		
		if(index == 0) {
			insertFirst(elem);
		} 
		else {
			Node<T> curr = head;
			int count = 0;
			Node<T> newNode = new Node<T>(elem, null);
			Node<T> temp = getHelper(curr, count, index-1);
			Node<T> temp2 =  temp.getNext();
			temp.setNext(newNode);
			newNode.setNext(temp2);
			size++;
		}
		//Node<T> insert = insertAtHelper(0, index, head);
		//Node<T> newNode = new Node<T>(elem, null);
		//insert.setNext(newNode);
		
		return this;
	}
	
	/*public Node<T> insertAtHelper(int i, int index, Node<T> curr) {
		if(i < 0 || i > size()) {
			throw new IndexOutOfBoundsException();
		}
		if(i == index) {
			return curr;
		}
		curr = curr.getNext();
		i++;
		return insertAtHelper(i, index, curr);
	}*/

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		T temp = head.getData();
		head = head.getNext();
		size--;
		return temp;
	}
	
	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		return removeAt(size-1);
		//return null;
	}

	@Override
	public T removeAt(int i) {
		// TODO Auto-generated method stub
		if(i < 0 || i >= this.size()) {
			throw new IndexOutOfBoundsException();
		} 
		if ( i == 0){
			T result = head.getData();
			head = head.getNext();
			size--;
			return result;
		}
		int count = 0;
		Node<T> temp = getHelper(head, count, i);
		temp.setNext(temp.getNext().getNext());
		T data = temp.getNext().getData();
		size--;
		return data;
	}

	/*public T removeAtHelper(int count, int i, Node<T> curr) {
		if(count == i) {
			curr = curr.getNext();
			curr.setNext(curr.getNext().getNext());
			return curr.getData();
			//Node<T> temp = curr.getNext();
			//curr.setNext(curr.getNext().getNext());
			//return temp.getData();
		} else {
			curr = curr.getNext();
			count++;
			return removeAtHelper(count, i, curr);
		}
	}*/
	
	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		Node<T> newNode = this.head;
		return newNode.getData();
		//return null;
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		/*T curr = null;
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		Iterator<T> iter = iterator();
		getLastHelper(iter, curr);
		return curr;*/
		//return null;
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		return get(size-1);
	}
	
	/*public void getLastHelper(Iterator<T> iter, T curr) {
		if(iter.hasNext()) {
			curr = iter.next();
		}
		getLastHelper(iter, curr);
	}*/

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if(i < 0 || i >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> insert = getHelper(head, 0, i);
		return insert.getData();
		//return null;
	}
	
	public Node<T> getHelper(Node<T> curr, int count, int i) {
		if(count == i) {
			return curr;
		} 
		else {
			count++;
			curr = curr.getNext();
			return getHelper(curr, count, i);
		}
	}

	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		if(indexOf(elem)==-1) {
			return false;
		}
		else {
			removeAt(indexOf(elem));
			return true;
		}
	}
	
	/*public Node<T> removeHelper(Node<T> compare, Node<T> prev, T elem) {
		if(compare == null) {
			return null;
		}
		if(compare.getData().equals(elem)) {
			return prev;
		}
		prev = compare;
		compare = compare.getNext();
		return removeHelper(compare, prev, elem);
	}*/

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		int i = 0;
		Node<T> toCheck = head;
		if(elem == null) {
			throw new NullPointerException();
		}
		return indexOfHelper(i, toCheck, elem);
	}
	
	private int indexOfHelper(int i, Node<T> toCheck, T elem) {
		if(toCheck.getData() == elem) {
			return i; 
		}
		if(toCheck.getNext() == null) {
			return -1;
		} 
		else {
			return indexOfHelper(i++, toCheck.getNext(), elem);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
		//return false; 
	}

}
