package structures;

import java.util.Comparator;
import java.util.Iterator;

import comparators.IntegerComparator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {

	IntegerComparator comparator = new IntegerComparator();
	StudentArrayHeap<Integer,V> heap = new StudentArrayHeap(comparator);
	
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		heap.add(priority, value);
		return this;
		//return null;
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NullPointerException();
		}
		return heap.remove();
		//return null;
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NullPointerException();
		}
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return heap.iteratorHelper();
		//return null;
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return comparator;
		//return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
		//return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return heap.isEmpty();
		//return false;
	}
}

