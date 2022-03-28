package simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A Java class to simulate the card game War. See assignment writeup for details.
 * 
 * @author liberato
 *
 */
public class War {
	/**
	 * Determines the winner of a game of War, returning 1 if player A wins, -1 if player B wins, 0 if a draw.
	 * 
	 * The rules of the game are defined in the assignment writeup.
	 * 
	 * @param deck
	 * @return 1 if player A wins, -1 if player B wins, 0 if a draw
	 */
	static int gameOutcome = 8; 
	static int battleCounter;
	static List<Integer> spoils = new ArrayList<>();
	static List<Integer> deckA = new ArrayList<>();
	static List<Integer> deckB = new ArrayList<>();
	static boolean gameOver;
	
	public static int findWinner(List<Integer> deck) {
		// TODO
		spoils = new ArrayList<Integer>();
		deckA = new ArrayList<>();
		deckB = new ArrayList<>();
		battleCounter = 0;
		gameOutcome = 0;
		gameOver = false;
		
		if (deck.size() == 1) {
			gameOutcome = 1;
			return gameOutcome;
		}
		if (deck.isEmpty()) {
			gameOutcome = 0;
			return gameOutcome;
		}
		
		for (int i = 0; i < deck.size() - 1; i += 2) {
			deckA.add(deck.get(i));
			deckB.add(deck.get(i + 1));
		}
		if (deck.size() % 2 != 0) {
			deckA.add(deck.get(deck.size() - 1));
		}
		while (!gameOver && battleCounter < 1000) {
			battle(deckA, deckB);
		}
		return gameOutcome;
	}
	
	
	public static void war(List<Integer> deckA, List<Integer> deckB) {
		if (!(battleCounter < 1000)) {
			gameOutcome = 0;
			gameOver = true;
			return;
		}
		if (deckA.size() < 3) {
			gameOutcome = -1;
			gameOver = true;
			return;
		} else if (deckB.size() < 3) {
			gameOutcome = 1;
			gameOver = true;
			return;
		}
		spoils.add(deckA.remove(0));
		spoils.add(deckA.remove(0));
		spoils.add(deckA.remove(0));
		
		spoils.add(deckB.remove(0));
		spoils.add(deckB.remove(0));
		spoils.add(deckB.remove(0));
		
		battle(deckA, deckB);
	}
	
	public static void battle(List<Integer> deckA, List<Integer> deckB) {
		if (!(battleCounter < 1000)) {
			gameOutcome = 0;
			gameOver = true;
			return;
		}
		battleCounter++;
		if (deckA.isEmpty() && deckB.isEmpty()) {
			gameOutcome = 0;
			gameOver = true;
			return;
		} else if (deckA.isEmpty()) {
			gameOutcome = -1;
			gameOver = true;
			return;
		} else if (deckB.isEmpty()) {
			gameOutcome = 1;
			gameOver = true;
			return;
		}
		
		spoils.add(deckA.get(0));
		spoils.add(deckB.get(0));
		
		if (deckA.get(0) > deckB.get(0)){
			deckA.remove(0);
			deckB.remove(0);
			deckA.addAll(spoils);
			spoils.clear();
			return;
		}
		if (deckA.get(0) < deckB.get(0)) {
			deckA.remove(0);
			deckB.remove(0);
			deckB.addAll(spoils);
			spoils.clear();
			return;
		}
		if (deckA.get(0) == deckB.get(0)) {
			deckA.remove(0);
			deckB.remove(0);
			war(deckA, deckB);
		}
		
		
	}
	
}
