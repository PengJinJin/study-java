package root.com.kkx.day01.dailypractice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestCard {
	public static void main(String[] args) {
		//Card card = new Card(Card.CLUB, Card.ACE);
		//System.out.println(card);
		List<Card> cards = new ArrayList<>();
		for (int i = Card.THREE; i <= Card.TWO; i++) {
			for (int j = Card.HEART; j <= Card.SPADE; j++) {
				cards.add(new Card(j, i));
			}

		}
		cards.add(new Card(Card.JOKER, Card.BLACK));
		cards.add(new Card(Card.JOKER, Card.COLOR));
		System.out.println(cards);
		//洗牌
		Random random = new Random();
		for (int i = cards.size() - 1; i > 0; --i) {
			int index = random.nextInt(i);
			Card c = cards.get(index);
			Card temp = cards.get(i);
			cards.set(i, c);
			cards.set(index, temp);
		}
		//洗牌
		//Collections.shuffle(cards);
		System.out.println(cards);
		Player[] players = {new Player(1, "圈圈1"),
				new Player(2, "太饱"),
				new Player(3, "圈圈2")};

		//for (int i = 0	; i <cards.size()-3; i++) {
		//	players[i%players.length].addCards(cards.get(i));
		//}
		//第二种排序方法
		Iterator<Card> iterator = cards.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			players[count % players.length].addCards(iterator.next());
			count++;
			iterator.remove();
			if (cards.size() == 3) {
				break;
			}
		}
		System.out.println("底牌" + cards);
		System.out.println(players[0]);
		System.out.println(players[1]);
		System.out.println(players[2]);

	}
}
