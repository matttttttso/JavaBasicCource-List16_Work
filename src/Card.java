
public class Card {
	//フィールドメンバ
	String suit;	//4つのマーク（スペード、ハート、ダイヤ、クラブ）
	int number;		//数字（1〜13）

	//コンストラクタ
	Card(String suit, int number){
		this.suit = suit;
		this.number = number;
	}

	//メソッドメンバ
	//カードの表示
	void showCard() {
		System.out.println(this.suit + " の " + this.number);
	}
}
