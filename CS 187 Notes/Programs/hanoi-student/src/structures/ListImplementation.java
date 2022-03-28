package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementation<T> implements ListInterface<T> {
	
	private int size;
	private Node<T> head;
	private Node<T> end;

	
	public ListImplementation() {
		size = 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
		//return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size  == 0) {
			return true;
		}
		return false;
	}

	@Override
	public T get(int n) throws NoSuchElementException {
		// TODO Auto-generated method stub
		iterator();
		Iterator<T> ite = iterator();
		if(n < 0 || n >= size) {
			throw new NoSuchElementException();
		}
		T temp = ite.next();
		for(int i = 0; i<n; i++) {
			temp = ite.next();
		}
		return temp;
	}

	@Override
	public ListInterface<T> append(T elem) { //Work with the end pointer 
		// TODO Auto-generated method stub
		Node<T> curr = new Node<T>(elem, null);
		if(elem == null) {
			throw new NullPointerException();
		}
		if(end == null) {
			head = curr;
		} else {
			end.setNext(curr);
		}
		end = curr;
		return this; 
		//return null;
	}
	

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedIterator<T>(head);
		//return null;
	}
	
}
