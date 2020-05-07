import java.util.Random;

public class Deck {
	private int[] deck = new int[52];	//52個の行列作成

	//行列に1~52の数字を順に入れていく
	public void setDeck() {
		for(int i = 0; i < deck.length; i++) {
			deck[i] = i + 1;
		}
	}

	//シャッフルする
	public void shuffle() {
		for(int i = 0; i < deck.length; i++) {
			Random random = new Random();
			int randNum = random.nextInt(52);
			int tmp = deck[i];
			deck[i] = deck[randNum];
			deck[randNum] = tmp;
		}
	}

	//デッキ内容表示（数字1~52で）
	public void showDeck() {
		int count = 0;
		for(int i = 0; i < deck.length; i++) {
			System.out.print(deck[i] + "/");
			count++;
			if(count % 10 == 0) {
				System.out.println("");
			}
		}
	}

	public int deckToInt(int i) {
		return deck[i];
	}
}
