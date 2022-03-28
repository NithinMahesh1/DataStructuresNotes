package structures;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

	/**
	 * {@inheritDoc}
	 */
	private LLNode<T> head = null; 
	
	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if(head == null) {
			throw new StackUnderflowException();
		}
		else {
			T temp = head.getData();
			head = head.getNext(); 
			return temp;
		}
		//return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if(head == null) {
			throw new StackUnderflowException();
		}
		else {
			return head.getData();
		}
		//return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head == null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
		LLNode<T> curr = this.head;
		while(!isEmpty()) {
			count++; 
			curr = curr.getNext();
		}
		return count;
		//return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		// TODO Auto-generated method stub
		LLNode<T> newNode = new LLNode<T>(elem, null);

		newNode.setNext(head);
		head = newNode;
	}

}
