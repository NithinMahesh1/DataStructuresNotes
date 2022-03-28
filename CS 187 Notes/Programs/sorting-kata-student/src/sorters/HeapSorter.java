package sorters;

import java.util.Comparator;
import structures.SwapList;

public class HeapSorter<T> extends AbstractSorter<T> {

	public HeapSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		for(int i=list.size()/2-1; i>=0; i--) { // heapify
		     bubbleDown(i, list.size()-1);
		   }
		   for(int i= list.size()-1;  i>=1; i--) {
		     list.swap(0, i);
		     bubbleDown(0, i-1);
		   }
		return list;
	}
	
	protected void bubbleDown(int index, int last) {
		// TODO Auto-generated method stub
		int largerChild, left = (index*2)+1, right = (index*2)+2;;
		while (left <= last) { // at least 1 child
		    if (right <= last && list.compare(left, right, comparator) < 0) {
		    	largerChild = right;
		    } else {  
		      largerChild = left;
		    }
		    if (list.compare(index, largerChild, comparator) >= 0) {
		      break;
		    }
		    list.swap(index, largerChild);
		    index = largerChild;
		    left = (index*2)+1;
		    right = (index*2)+2;
		  }
	}
}
