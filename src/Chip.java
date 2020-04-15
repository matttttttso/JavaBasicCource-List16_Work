
public class Chip {
	//フィールドメンバ
	private int numChip10;	//[10]単位のチップの枚数
	private int numChip1;	//[1]単位のチップの枚数
	private int score;

	public int getNumChip10() {
		return numChip10;
	}
	public void setNumChip10(int numChip10) {
		this.numChip10 = numChip10;
	}
	public int getNumChip1() {
		return numChip1;
	}
	public void setNumChip1(int numChip1) {
		this.numChip1 = numChip1;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	//コンストラクタ
	Chip(int num10, int num1){
		this.numChip10 = num10;
		this.numChip1 = num1;
		score = (num10 * 10) + (num1 * 1);
	}

	//メソッドメンバ
	String showChipNum() {
		return "総計： "+ score + " ([10]:" + numChip10 + "枚, [1]:" + numChip1 + "枚)";
	}
}
