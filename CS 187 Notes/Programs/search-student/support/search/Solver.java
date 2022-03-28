package search;

import java.util.List;

/**
 * A solver for generic search problems.
 * 
 * The solver provides solutions based upon (possibly multiple) search implementations.
 * 
 * @author liberato
 *
 * @param <T>
 *            the type of each state in the search problem
 */
public class Solver<T> {
	private final SearchProblem<T> problem;

	public Solver(SearchProblem<T> problem) {
		this.problem = problem;
	}

	/**
	 * Finds and returns a valid solution to the problem using a Searcher.
	 * 
	 * The solution will either be a list of states from the initial state to a
	 * goal state, or the empty list (if no solution was found).
	 * 
	 * @return a solution to the problem
	 */
	public List<T> solve() {
		Searcher<T> searcher = new Searcher<T>(problem);
		return searcher.findSolution();
	}
}
