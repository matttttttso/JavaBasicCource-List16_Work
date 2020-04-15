
public class Card {
	//フィールドメンバ
	private String suit;	//4つのマーク（スペード、ハート、ダイヤ、クラブ）
	private int numCard;		//数字（1〜13）

	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getNumCard() {
		return numCard;
	}
	public void setNumCard(int numCard) {
		this.numCard = numCard;
	}

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
