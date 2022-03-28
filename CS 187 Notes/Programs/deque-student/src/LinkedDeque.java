
public class LinkedDeque<T> implements Deque<T> {

	private DLNode<T> head;
	private DLNode<T> tail;
	
	@Override
	public void addToFront(T element) {
		head = new DLNode<T>(element, head, null);
		if(head.getNext() == null) // this is the only element
			tail = head;
		else
			head.getNext().setPrev(head);
	}

	@Override
	public T removeFront() throws DequeUnderflowException {
		if(isEmpty()) throw new DequeUnderflowException();
		T data = head.getData();
		if(head.getNext() != null)
			head.getNext().setPrev(null);
		else
			tail = null;
		head = head.getNext();
		return data;
	}

	@Override
	public T first() throws DequeUnderflowException {
		if(isEmpty()) throw new DequeUnderflowException();
		return head.getData();
	}

	@Override
	public void addToRear(T element) {
		// TODO(1)
		tail = new DLNode<T>(element, tail, null);
		if(tail.getPrev() ==  null) 
			tail.setData(element);
		else 
			tail.getPrev().setNext(tail);
			
	}

	@Override
	public T removeRear() throws DequeUnderflowException {
		// TODO(2)
		//return null; //cross this out
		if(isEmpty()) throw new DequeUnderflowException();
		T temp = tail.getData();
		if(tail.getPrev() != null) 
			tail.getPrev().setNext(null);
		else 
			tail = tail.getPrev();
			return temp;
				
	}

	@Override
	public T last() throws DequeUnderflowException {
		// TODO(3)
		//return null; //cross this out
		if(isEmpty()) throw new DequeUnderflowException();
		return tail.getData();
		
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

}
