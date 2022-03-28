package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedIterator<T> implements Iterator<T> { 
	
		private Node<T> head;

	  // TODO (1) choose appropriate attributes
		
	  // Constructors
	  public LinkedIterator(Node<T> node) {
	    // TODO (2) choose appropriate parameters and do the initialization
		  head = node;
	  }

	  @Override
	  public boolean hasNext() {
	    // TODO (3)
		 if(head.getNext() != null) {
			 return true;
		 }	
		 return false;
	  }

	  @Override
	  public T next() {
	    // TODO (4)
		  T item;
		 if(hasNext()) {
			  item = head.getData();
			  head = head.getNext();
		 }
		 else {
			 throw new NoSuchElementException();
		 }
	    return item;
	  }

	  // Leave this one alone.
	  @Override
	  public void remove() {
	    throw new UnsupportedOperationException();
	  }
}

