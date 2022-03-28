package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities {
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */

	
	public static <E> Set<E> union(Set<E> s, Set<E> t) {	
		Set<E> finalSet = new HashSet<E>(s);
		finalSet.addAll(t);	
		return finalSet;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t) {
		Set<E> newSet = new HashSet<E>(s);
		newSet.retainAll(t);
		return newSet;
	}

	/**
	 * Returns a new set representing the set difference s and t,
	 * that is, s \ t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t) {
		Set<E> newHashSet = new HashSet<E>(s);
		newHashSet.removeAll(t);
		return newHashSet;
	}
	
	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as the size of the intersection of the sets, 
	 * divided by the size of the union of the sets.
	 * 
	 * It is defined as 1 if both sets are empty.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	public static <E> double jaccardIndex(Set<E> s, Set<E> t) {
		if(s.isEmpty() && t.isEmpty()) {
			return 1.0;
		}
		Set <E> intersection = new HashSet<E>(s);
		intersection.retainAll(t);
		Set <E> union = new HashSet<E>(s);
		union.addAll(t);
		double a = 1.0;
		a = (double) intersection.size() / union.size();
		return a;	
	}
}
