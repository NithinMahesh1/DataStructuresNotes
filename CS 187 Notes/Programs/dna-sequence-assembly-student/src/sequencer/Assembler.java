package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
	
	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list
	 * will not be modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */
	
	List<Fragment> frag = new ArrayList<Fragment>();
	
	public Assembler(List<Fragment> fragments) {
		this.frag = fragments;
	}
	
	/**
	 * Returns the current list of fragments this assembler contains.
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return frag;
	}
	
	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was performed.
	 * 
	 * This method chooses the best assembly possible, that is, 
	 * it merges the two fragments with the largest overlap, breaking ties
	 * between merged fragments by choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted into
	 * the list of fragments in this assembler, and the two original fragments are removed
	 * from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {
		Fragment assemble;
		
		for(int i=0; i<frag.size(); i++) {
			for(int j=0; j<this.toString().length(); j++) {
				if(i == j) {
					continue;
				}
				if(calculateOverlap(frag)) {
					
					assemble = frag.get(i).mergedWith(this.frag);
				}
			}
		}
		return false;
	}
	
	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {
	}
}
