
public class Chip {
	//フィールドメンバ
	private int numChip10 = 10;	//[10]単位のチップの枚数
	private int numChip1 = 0;	//[1]単位のチップの枚数
	private int score;

	public int getScore() {
		return score;
	}

	//コンストラクタ
	Chip(int num10, int num1){
		//[10]のチップ数
		this.numChip10 = num10;
		//[1]のチップ数
		if(num1 < 10) {
			this.numChip1 = num1;
		}else if(10 <= num1){
			this.numChip1 = num1 % 10;
			numChip10 += (int)Math.ceil(num1 / 10);
		}

		score = (num10 * 10) + (num1 * 1);
	}

	//メソッドメンバ
	//チップ枚数とスコアの表示
	public String toString() {
		return "所持チップ総数： "+ score + "\n([10]:" + numChip10 + "枚, [1]:" + numChip1 + "枚)";
	}
	//チップを増やすメソッド
	public void increaseChipNum(int num) {
		this.numChip10 += (int)Math.floor(num / 10);
		int tmp = this.numChip1 + (num % 10);
		if(tmp < 10) {
			this.numChip1 = tmp;
		}else if(10 <= tmp){
			this.numChip1 = tmp % 10;
			numChip10 += (int)Math.ceil(tmp / 10);
		}
		this.score = (numChip10 * 10) + (numChip1 * 1);
	}
	//チップを減らすメソッド
	public void decreaseChipNum(int num) {
		this.numChip10 -= (int)Math.floor(num / 10);
		int tmp = this.numChip1 - (num % 10);
		if(0 <= tmp) {
			this.numChip1 = tmp;
		}else if(tmp < 0){
			numChip10 -= 1;
			this.numChip1 = 10 + tmp;
		}
		this.score = (numChip10 * 10) + (numChip1 * 1);
	}
}
