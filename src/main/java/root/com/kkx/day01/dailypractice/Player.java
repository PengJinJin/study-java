package root.com.kkx.day01.dailypractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
	private int id;
	private String name;
	private Card[] cards;
	private List<Card> cardList;

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		this.cards = new Card[]{};
		this.cardList = new ArrayList<>();
	}

	public void addCards(Card c) {
		cards = Arrays.copyOf(cards, cards.length + 1);
		cards[cards.length - 1] = c;
		//cardList.add(c);
	}

	@Override
	public String toString() {
		Arrays.sort(cards);
		return "Player{name=" + name +
				",cards=" + Arrays.toString(cards) +
				'}';
	}
}
