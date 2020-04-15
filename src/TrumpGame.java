
public class TrumpGame {
	public static void main(String[] args) {
		//ゲーム数（初期化）
		int gameCount = 1;	//呼び出す配列の位置 ->0からスタート
		//チップ数初期化
		Chip chip = new Chip(1, 59);
		//デッキ初期化
		Deck deck = new Deck();
		deck.setDeck();	//デッキを整理
		deck.shuffle();	//デッキのシャッフル
//		deck.showDeck();	//デッキの内容表示

		//1枚目のカードを作成
		Card card1 = new Card(deck.deckToInt(gameCount - 1));

		//表示
		System.out.println("********チップ枚数とカード********");
		System.out.println(chip.showChipNum());
		System.out.println(card1.showCard());
		System.out.println("****************************");

		//ゲーム数を+1
		gameCount++;
	}
}
