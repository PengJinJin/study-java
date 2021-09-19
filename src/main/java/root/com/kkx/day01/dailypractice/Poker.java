package root.com.kkx.day01.dailypractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Poker {
	private static final String[] COLOR = {"黑桃", "红桃", "方块", "梅花"};
	private static final String[] CARD = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

	public static void main(String[] args) {
		List<String> card = createCard();
		sendCard(card);
		/*
		Iterator<String> it = card.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		//for (int i = 0; i < card.size(); i++) {
		//	System.out.println(card.get(i));

		//}
		*/
	}

	protected static List<String> createCard() {
		List<String> cards = new ArrayList<>();
		for (String color : COLOR) {
			for (String card : CARD) {
				cards.add(color + card);
			}
		}
		return cards;
	}

	protected static void sendCard(List<String> cards) {
		Collections.shuffle(cards);
		Iterator<String> iterator = cards.iterator();
		List<String> player1 = new ArrayList<>();
		List<String> player2 = new ArrayList<>();
		List<String> player3 = new ArrayList<>();
		List<String> underCards = new ArrayList<>();
		for (int i = 0; i < cards.size(); i++) {
			if (i >= 49) {
				underCards.add(cards.get(i));
			} else {
				if (i % 3 == 0) {
					player1.add(cards.get(i));
				} else if (i % 3 == 1) {
					player2.add(cards.get(i));
				} else {
					player3.add(cards.get(i));
				}
			}
		}
		System.out.println("玩家1的牌：");
		player1.forEach(p1 -> System.out.print(p1 + "\t"));
		System.out.println();
		System.out.println("玩家2的牌：");
		player2.forEach(p2 -> System.out.print(p2 + "\t"));
		System.out.println();
		System.out.println("玩家3的牌：");
		player3.forEach(p3 -> System.out.print(p3 + "\t"));
		System.out.println();
		System.out.println("底牌：");
		underCards.forEach(u -> System.out.print(u + "\t"));
		System.out.println();
	}

}
