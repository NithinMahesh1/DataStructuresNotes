package sorters;

import java.util.Comparator;

import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		/*
		 * Note: When choosing a pivot, choose the element in the middle of
		 * the sub-array. That is,
		 * 
		 * pivotIndex = (firstIndex + lastIndex) / 2;
		 */
		quickSortHelper(0, list.size()-1);
		return list;
	}
	
	public void quickSortHelper(int low, int high) {
		  if(low < high) {
		    int p = partition(low, high); // p is split point
		    quickSortHelper(low, p-1);
		    quickSortHelper(p+1, high);
		} 
	}
	
	public int partition(int low, int high) {
		int pivot = high; // the last element as pivot 
		int storeIndex = low;
		int j;
		for(j=low; j<=high-1; j++) {
		    if(list.compare(j, pivot, comparator) <= 0) {
		      list.swap(j, storeIndex);
		      storeIndex++;
		} }
		  list.swap(storeIndex, high);
		  return storeIndex;
		}
}