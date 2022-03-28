package sequencer;

public class Fragment {
	
	/**
	 * Creates a new Fragment based upon a String representing a sequence of nucleotides, 
	 * containing only the uppercase characters G, C, A and T.
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of nucleotides 
	 */
	
	private String nuc;
	
	public Fragment(String nucleotides) throws IllegalArgumentException {
		this.nuc = nucleotides;
		if(!nuc.matches("[GCAT]+")) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return nuc.length();
	}
	
	/**
	 * Returns a String representation of this fragment, exactly as was passed to the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return nuc;
	}
	
	/**
	 * Return true if and only if this fragment contains the same sequence of nucleotides
	 * as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Fragment)) {
			return false;
		}
		for(int i=0; i<nuc.length(); i++) {
			if(nuc.equals(o.toString())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment and
	 * the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an maximum overlap of 2.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int count = 0;
		String sub1;
		String sub2;
		if(nuc.equals(f.nuc)) {
			return nuc.length();
		}
		for(int i=nuc.length(); i>0; i--) {
			sub1 = this.toString().substring(i);
			for(int j=0; j<f.nuc.length(); j++) {
				sub2 = f.toString().substring(0, j);
				if(sub1.equals(sub2)) {
					count = sub2.length();
				}
				
			}
		}
		return count;
	}
	
	/**
	 * Returns a new fragment based upon merging this fragment with another fragment f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right; the
	 * fragments will be overlapped as much as possible during the merge.
	 *  
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment 
	 */
	public Fragment mergedWith(Fragment f) {
		Fragment combine = new Fragment(nuc + f.toString());
		String sub1;
		String sub2;
		if(this.equals(f)) {
			return this;
		}
		if(nuc.equals(f.toString())) {
			return this;
		}
		for(int i=nuc.length(); i>0; i--) {
			sub1 = this.toString().substring(i);
			for(int j=0; j<f.nuc.length(); j++) {
				sub2 = f.toString().substring(0, j);
				if(sub1.equals(sub2)) {
					combine.nuc = nuc.substring(0, i) + sub2 + f.nuc.substring(j);
				}
			}
		}
		return combine;
	}
}
