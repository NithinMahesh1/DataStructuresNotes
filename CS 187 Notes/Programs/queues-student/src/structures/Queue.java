package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
    protected LLNode<T> front;
    protected LLNode<T> rear;
    protected int size;
	
	public Queue() {
		// TODO 1
		front = null;
		rear = null;
		size = 0;
	}
	
	public Queue(Queue<T> other) {
		// TODO 2
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		LLNode<T> newNode = other.front;
		for(int i = 0; i<other.size(); i++) {
			this.enqueue(newNode.getData());
			newNode = newNode.getNext();
		}	
	}
	
	@Override
	public boolean isEmpty() {
		// TODO 3
		return (size == 0);
	}

	@Override
	public int size() {
		// TODO 4
		return size;
		//return -1;
	}

	@Override
	public void enqueue(T element) {
		// TODO 5;
		LLNode<T> newNode = new LLNode<T>(element, null);
		if(isEmpty()) 
			front = newNode;
		else {
			rear.setNext(newNode);
		}
		rear = newNode;
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// TODO 6;
		if(isEmpty()) 
			throw new NoSuchElementException();
		if(this.size() == 1) {
			T temp = front.getData();
			front = front.getNext();
			front = null;
			rear = null;
			size--;
			return temp;
		}
			T temp2 = front.getData();
			front = front.getNext();
			size--;
			return temp2;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO 7
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.getData();
		//return null;
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		LinkedStack<T> copyStack = new LinkedStack<T>();
		Queue<T> copyQueue = new Queue<T>();
		
		while(!isEmpty()) {
			copyStack.push(dequeue());
		}
		
		while(!copyStack.isEmpty()) {
			copyQueue.enqueue(copyStack.pop());
		}
		
		return copyQueue;
	}
}
