package root.com.kkx.day01.dailypractice;

public class Card implements Comparable<Card> {
	public static final String[] SUIT_NAME = {"红桃", "方块", "梅花", "黑桃", ""};
	public static final String[] RANK_NAME = {"3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a", "2", "小王", "大王"};

	public static final int HEART = 0;
	public static final int DIAMOND = 1;
	public static final int CLUB = 2;
	public static final int SPADE = 3;
	public static final int JOKER = 4;


	public static final int THREE = 0;
	public static final int FOUR = 1;
	public static final int FIVE = 2;
	public static final int SIX = 3;
	public static final int SEVEN = 4;
	public static final int EIGHT = 5;
	public static final int NINE = 6;
	public static final int TEN = 7;
	public static final int JACK = 8;
	public static final int QUEEN = 9;
	public static final int KING = 10;
	public static final int ACE = 11;
	public static final int TWO = 12;
	public static final int BLACK = 13;
	public static final int COLOR = 14;
	private int suit;//花色
	private int rank; //点数

	public Card() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		if (suit != card.suit) return false;
		return rank == card.rank;
	}

	@Override
	public int hashCode() {
		int result = suit;
		result = 31 * result + rank;
		return result;
	}

	@Override
	public String toString() {
		return SUIT_NAME[suit] + RANK_NAME[rank];
	}

	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Card o) {
		return this.rank - o.rank;
	}
}
