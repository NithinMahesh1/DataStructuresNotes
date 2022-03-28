package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	//private StackBasedHanoiPeg fromPeg;
	//private StackBasedHanoiPeg toPeg;
	//private StackBasedHanoiPeg one;
	//private StackBasedHanoiPeg two;
	//private StackBasedHanoiPeg three;
	private StackBasedHanoiPeg[] container;
	
	public ArrayBasedHanoiBoard(int n) {
		//fromPeg = new StackBasedHanoiPeg();
		//toPeg = new StackBasedHanoiPeg();
		//one = new StackBasedHanoiPeg();
		//two = new StackBasedHanoiPeg();
		//three = new StackBasedHanoiPeg();
		container = new StackBasedHanoiPeg[n];
		for(int i=0; i<n; i++) {
			container[i] = new StackBasedHanoiPeg();
		}
	}
	

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}
		int fromPeg = move.getFromPeg();
		int toPeg = move.getToPeg();
		
		container[toPeg].addRing(container[fromPeg].getTopRing());
		container[fromPeg].remove();
	}

	@Override
	public boolean isSolved() {
		if(!container[0].hasRings() && !container[1].hasRings()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLegalMove(HanoiMove move) {
		int fromPeg = move.getFromPeg();
		int toPeg = move.getToPeg();
		if((fromPeg > 2 || toPeg > 2) && (fromPeg < 0 || toPeg < 0)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}
		if(fromPeg == toPeg) {
			return false;
		}
		if(!container[fromPeg].hasRings()) {
			return false;
		}
		if(container[fromPeg].getTopRing().getSize() > container[toPeg].getTopRing().getSize()) {
			return false;
		}
		return true;
	}
	
}
