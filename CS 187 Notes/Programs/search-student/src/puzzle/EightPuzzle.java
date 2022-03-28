package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	
	List<Integer> startingValues;
	List<Integer> currentState;
	private final List<Integer> solved = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
	
	public EightPuzzle(List<Integer> startingValues) {
		this.startingValues = startingValues;
	}
	
	private Integer[] swap(int a1, int b1, Integer[] current) {
		Integer store = current[a1];
		current[a1] = current[b1];
		current[b1] = store; 
		return current;
	}
	

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return startingValues;
	}

	private int getSpace(List<Integer> curr) {
		int index = 0;
		while (!curr.get(index).equals(0)) {
			index++;			
		}
		return index;
	}
	
	private int[] toDo(int index) {
		switch (index) {
		case 0: 
			return new int[]{1,3};
		case 1: 
			return new int[]{0,2,4};
		case 2: 
			return new int[]{1,5};
		case 3: 
			return new int[]{0,4,6};
		case 4: 
			return new int[]{1,3,5,7};
		case 5: 
			return new int[]{2,8,4};
		case 6: 
			return new int[]{7,3};
		case 7: 
			return new int[]{6,4,8};
		case 8: 
			return new int[]{5,7};
		default:
			return null;
		}
		
	}
	
	
	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		Integer[] current = new Integer[9];
		int index = getSpace(currentState);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int[] toDo = this.toDo(index);

		for (int i : toDo) {
			current = currentState.toArray(current);
			List<Integer> add = new ArrayList<Integer>();
			current = swap(index, i, current);
			
			for (Integer j : current) {
				add.add(j);
			}
		    result.add(add);
		    add = null;
		}
		return result;
	}


	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		return state.equals(solved);
	}

	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Solver<List<Integer>>(eightPuzzle).solve();
		for (List<Integer> state : solution) {
			System.out.println(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
