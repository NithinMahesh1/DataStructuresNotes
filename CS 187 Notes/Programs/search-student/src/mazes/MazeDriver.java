package mazes;

import java.util.List;

import search.Solver;

public class MazeDriver {
	public static void main(String[] args) {
		MazeGenerator mg = new MazeGenerator(24, 8, 0);
		Maze maze = mg.generateDfs();
		System.out.println(maze.toString());
		Solver<Cell> solver = new Solver<Cell>(maze);
		List<Cell> solution = solver.solve();
		for (Cell cell : solution) {
			System.out.println(cell);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
