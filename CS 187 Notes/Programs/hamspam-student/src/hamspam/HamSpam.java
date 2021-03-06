package hamspam;

import java.util.ArrayList;

public class HamSpam {
	private final int hamNumber;
	private final int spamNumber;

	/**
	 * Construct an object that can compute hamspam values for a game of 
	 * Ham and Spam.
	 * 
	 * @param hamNumber the ham number; it must be greater than 1
	 * @param spamNumber the spam number; it must be greater 
	 * than 1 and not equal to the ham number 
	 */
	public HamSpam(int hamNumber, int spamNumber) {
		this.hamNumber = hamNumber;
		this.spamNumber = spamNumber;
		
		if(hamNumber < 1 && hamNumber == spamNumber) {
			throw new IllegalArgumentException("cannot be greater than 1");
		}
		
		else if(spamNumber < 1 && spamNumber == hamNumber) {
			throw new IllegalArgumentException("cannot be greater than 1 and not equal to the ham number");
		}
		
		else if(hamNumber == spamNumber) {
			throw new IllegalArgumentException("can't have the same ham and spam number");
		}
		
	}

	/**
	 * Returns the nth hamspam value (a number, ham, spam, or hamspam) 
	 * for this game of Ham and Spam.
	 * 
	 * For example, getValue(1) returns "1".
	 * 
	 * @param n
	 *            the number to consider; n > 0
	 * @return the hamspam value, as a String
	 */
	public String getValue(int n) {
		if(n % spamNumber == 0 && n % hamNumber == 0) {
			return "hamspam";
		}
		
		else if(n % hamNumber == 0){
			return "ham";
		} 
		
		else if(n % spamNumber == 0) {
			return "spam";
		}
		
		else {
			return Integer.toString(n);
		}
	}

	/**
	 * Returns an array of the hamspam values from start to end, inclusive, for
	 * this game of Ham and Spam.
	 * 
	 * For example, if the ham number is 3 and the spam number is 4,
	 * getValues(2,6) should return an array of Strings:
	 * 
	 * {"2", "ham", "spam", "5", "ham"}
	 * 
	 * @param start
	 *            the number to start from; start > 0
	 * @param end
	 *            the number to end at; end >= start
	 * @return the array of hamspam values
	 */
	public String[] getValues(int start, int end) {
	ArrayList<String> numbers = new ArrayList<String>((end - start)+1);
			
	for(int i = start; i <= end ; i++) {
		numbers.add(getValue(i));
		
	}
			
		return numbers.toArray(new String[numbers.size()]);
	}
}
