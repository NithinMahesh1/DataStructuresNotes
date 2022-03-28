package sorters;

import java.util.Comparator;

import structures.SwapList;

public class InsertionSorter<T> extends AbstractSorter<T> {

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		int out;
		int in = 0;
		for(out = 1; out<list.size(); out++) {
			in = out;
			while(in >= 1 && list.compare(in, in-1, comparator) < 0) {
				list.swap(in, in-1);
				in--;
			}
 		}
		return list;
	}
}
