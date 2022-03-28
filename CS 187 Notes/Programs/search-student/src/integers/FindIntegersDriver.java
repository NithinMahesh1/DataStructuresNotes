package integers;

import java.util.List;

import search.SearchProblem;
import search.Solver;

public class FindIntegersDriver {

	public static void main(String[] args) {
		SearchProblem<Integer> problem = new FindIntegersProblem(7, -4, true);
		Solver<Integer> solver = new Solver<Integer>(problem);
		List<Integer> solution = solver.solve();
		for (Integer i: solution) {
			System.out.println(i);
		}
		System.out.println(solution.size() + " states in solution");
	}

}
