package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		heap.iterator();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLeftChildOf(int index) {
		// TODO Auto-generated method stub
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return (2*index+1);
		}
		//return 0;
	}

	@Override
	protected int getRightChildOf(int index) {
		// TODO Auto-generated method stub
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return (2*index+2);
		}
		//return 0;
	}

	@Override
	protected int getParentOf(int index) {
		// TODO Auto-generated method stub
		if(index < 1) {
			throw new IndexOutOfBoundsException();
		} else {
			return (index-1)/2;
		}
		//return 0;
	}
	
	public Iterator<Entry <P, V>> iteratorHelper() {
		return heap.iterator();
	} 

	@Override
	protected void bubbleUp(int index) {
		// TODO Auto-generated method stub
		int hole = index; 
		int parent = (hole-1)/2;
		Entry<P, V> temp = heap.get(hole);
		while((hole > 0) && getComparator().compare(temp.getPriority(), heap.get(parent).getPriority()) > 0) {  
			heap.set(hole, heap.get(parent));
			hole = parent; 
			parent = (parent-1)/2;
		}
		heap.set(hole, temp);
	}

	@Override
	protected void bubbleDown(int index) {
		// TODO Auto-generated method stub
		int largerChild, left = 1, right = 2;
		Entry<P, V> temp = heap.get(index);
		while (left <= size()-1) { // at least 1 child
		    if (right <= size()-1 && getComparator().compare(heap.get(left).getPriority(), heap.get(right).getPriority()) < 0) {
		    	largerChild = right;
		    } else {  
		      largerChild = left;
		    }
		    if (getComparator().compare(heap.get(index).getPriority(), heap.get(largerChild).getPriority()) >= 0) {
		      break;
		    }
		    heap.set(index, heap.get(largerChild));
		    index = largerChild;
		    left = (index*2)+1;
		    right = (index*2)+2;
		  }
		  heap.set(index, temp);
	}
}
