package hanoi;

import structures.LinkedStack;

/**
 * A {@link StackBasedHanoiPeg} is an implementation of {@link HanoiPeg}.
 * 
 * @author jcollard
 */
public class StackBasedHanoiPeg implements HanoiPeg {

	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	private final LinkedStack<HanoiRing> newPeg;
	
	public StackBasedHanoiPeg() {
		newPeg = new LinkedStack<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if(ring == null) {
			throw new NullPointerException();
		}
		if(newPeg.isEmpty()) {
			newPeg.push(ring);
		}
		else if(newPeg.getSize() < ring.getSize()) {
			newPeg.push(ring);
		} else {
			throw new IllegalHanoiMoveException("Ring being pushed is bigger than top ring");
		}
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if(newPeg.isEmpty()) {
			throw new IllegalHanoiMoveException("No rings in stack");
		}
		return newPeg.pop();
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if(newPeg.isEmpty()) {
			throw new IllegalHanoiMoveException("No rings in stack");
		}
		return newPeg.peek();
		//return null;
	}

	@Override
	public boolean hasRings() {
		if(newPeg.isEmpty()) {
			return false;
		}
		return true;
	}
}
