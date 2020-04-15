
public class TrumpGame {
	public static void main(String[] args) {
		Initialize();
	}

	static void Initialize() {
		//ゲーム開始時の動作
		//チップ初期化
		Chip chip = new Chip(10, 0);
		//カードを１枚配る
		Card card = new Card(2);
		//表示
		System.out.println("********チップ枚数とカード********");
		System.out.println(chip.showChipNum());
		System.out.println(card.showCard());
		System.out.println("****************************");
	}
}
