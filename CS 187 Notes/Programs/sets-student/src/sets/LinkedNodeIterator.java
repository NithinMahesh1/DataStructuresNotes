package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
	private LinkedNode<E> head;

  // TODO (1) choose appropriate attributes


  // Constructors
  public LinkedNodeIterator(LinkedNode<E> node) {
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
  public E next() {
    // TODO (4)
	  E item;
	 if(hasNext()) {
		  head = head.getNext();
		  item = head.getData();
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
