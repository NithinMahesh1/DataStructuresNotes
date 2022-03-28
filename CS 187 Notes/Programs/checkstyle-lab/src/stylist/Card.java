package stylist;

/**
 * A standard playing card.
 * 
 * Adapted from code Copyright © 2016 Allen B. Downey and Chris Mayfield.
 * 
 * Code used under Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 */
public class Card {

	public static final String[] RANKS = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private final int ro;
	private final int so;

	/**
	 * Constructs a card of the given rank and suit.
	 */
	public Card(int ro, int so) {
		this.ro = ro;
		this.so = so;
	}

	/**
	 * Returns a negative integer if this card comes before the given card, zero
	 * if the two cards are equal, or a positive integer if this card comes
	 * after the card.
	 */
	public int compareTo(Card that) {
		if (this.so < that.so) {
			return -1;
		}
		
		if (this.so > that.so) {
			return 1;
		}
		
		if (this.ro < that.ro) {
			return -1;
		}
		
		if (this.ro > that.ro) {
			return 1;
		}
		
		return 0;
	}

	/**
	 * Returns true if the given card has the same rank AND same suit; otherwise
	 * returns false.
	 */
	public boolean equals(Card that) {
		return this.ro == that.ro && this.so == that.so;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ro;
		result = prime * result + so;
		return result;
	}

	/**
	 * Gets the card's rank.
	 */
	public int getRank() {
		return this.ro;
	}

	/**
	 * Gets the card's suit.
	 */
	public int getSuit() {
		return this.so;
	}

	/**
	 * Returns the card's index in a sorted deck of 52 cards.
	 */
	public int position() {
		return this.so * 13 + this.ro - 1;
	}

	/**
	 * Returns a string representation of the card.
	 */
	public String toString() {
		return RANKS[this.ro] + "of" + SUITS[this.so];
	}

	public static void main(String[] args) {
		Card[] cards = new Card[52];
		int idx = 0;
		for (int r = 1; r < RANKS.length; r++) {
			{
				{
					for (int s = 0; s < SUITS.length; s++) {
						cards[idx++] = new Card(r, s);
					}
				}
			}
		}			
		for (Card c : cards) {
			System.out.println(c);
		}
	}
}