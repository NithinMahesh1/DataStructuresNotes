package algorithms;

/**
 * A class that implements some basic mathematical functions using recursion.
 * 
 * YOU MUST USE RECURSION, NOT ITERATION, TO IMPLEMENT THESE FUNCTIONS!
 *
 * @author jcollard
 */
public class RecursiveMath {

	/**
	 * Returns {@code true} if {@code val} is even and {@code false} otherwise.
	 *
	 * @param val
	 * @return {@code true} if {@code val} is even and {@code false} otherwise.
	 */
	public boolean isEven(int val) {
		// Without recursion this could be: return val % 2 == 0;
		if(val == 0) {
			return true;
		}
		if(val == 1) {
			return false;
		}
		if(val < 0) {
			val = val*-1;
		}
		int check = val - 2;
		return isEven(check);
	}

	/**
	 * Returns {@code true} if {@code val} is odd and {@code false} otherwise.
	 * 
	 * @param val
	 * @return {@code true} if {@code val} is odd and {@code false} otherwise.
	 */
	public boolean isOdd(int val) {
		// Without recursion this could be: return val % 2 == 1;
		if(isEven(val)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the sum of all values between 0 and n.
	 * 
	 * @param n
	 * @return the sum of all values between 0 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int sumN(int n) {
		if(n < 0) {
			throw new IllegalArgumentException();
		}
		return helperSum(n, 0);
	}
	
	public int helperSum(int n, int sum) {
		int temp = n+sum;
		if(n == 0) {
			return temp;
		} 
		return helperSum(n-1, temp);
	}

	/**
	 * Returns the multiplication of all values between 1 and n.
	 * 
	 * @param n
	 * @return the multiplication of all values between 1 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int factorial(int n) {
		if(n < 0) {
			throw new IllegalArgumentException();
		}
		return factorialHelper(n, 1);
	}
	
	public int factorialHelper(int n, int mult) {
		int temp = n*mult;
		if(n == 1) {
			return temp;
		}
		return factorialHelper(n-1, temp);
	}

	/**
	 * Returns 2 to the nth power. (2^n)
	 * 
	 * @param n
	 * @return 2 to the nth power.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int biPower(int n) {
		if(n < 0) {
			throw new IllegalArgumentException();
		}
		if(n == 0) {
			return 1;
		}
		return(2*biPower(n-1));
	}

}
