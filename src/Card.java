
public class Card {
	//フィールドメンバ
	String suit;	//4つのマーク（スペード、ハート、ダイヤ、クラブ）
	int numCard;		//数字（1〜13）

	//コンストラクタ
	Card(String mark, int num){
		this.suit = mark;
		this.numCard = num;
	}

	//メソッドメンバ
	//カードの表示
	String showCard() {
		return "現在のカード： " + this.suit + " の " + this.numCard;
	}
}
