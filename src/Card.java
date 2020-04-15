
public class Card {
	//フィールドメンバ
	private String suit;	//4つのマーク（強さは、「スペード」 > 「ハート」 > 「ダイヤ」 > 「クラブ」の順）
	private int numCard;		//数字（1〜13）
	private int cardInt;	//数字1~52 (4*13)

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
	public int getCardInt() {
		return cardInt;
	}
	public void setCardInt(int cardInt) {
		if(0 < cardInt && cardInt < 53) {
			this.cardInt = cardInt;
		}else {
			this.cardInt = 0;
		}
	}

	//コンストラクタ
	Card(int cardInt){
		//数字の決定
		double cardDouble = cardInt;
		this.numCard = (int) Math.ceil(cardDouble / 4);
		//suitの決定
		int mod = cardInt % 4;
		switch(mod) {
			case 0:	this.suit = "♠";	break;
			case 3:	this.suit = "♥";	break;
			case 2:	this.suit = "♦";	break;
			case 1:	this.suit = "♣";	break;
		}
	}
	/*
	トランプの表し方
	 数	♣<♦<♥<♠
	A：	1,2,3,4
	2:	5,6,7,8
	 ...

	Q:	45,46,47,48
	K:	49,50,51,52

	4で割ったあまり
	 	1,2,3,0
	4で割ると
 	A:	0+	1/4,2/4,3/4,4/4	=>	0.xxx ~ 1.0
 	2:	1+	1/4,2/4,3/4,4/4	=>	1.xxx ~ 2.0
 	 ...

 	Q:	11+	1/4,2/4,3/4,4/4	=>	11.xxx ~ 12.0
 	K:	12+	1/4,2/4,3/4,4/4	=>	12.xxx ~ 13.0
	*/

	//メソッドメンバ
	//カードの表示
	String showCard() {
		return "現在のカード： " + this.suit + "/" + this.numCard;
	}
}
