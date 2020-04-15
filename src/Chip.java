
public class Chip {
	//フィールドメンバ
	int numChip10;	//[10]単位のチップの枚数
	int numChip1;	//[1]単位のチップの枚数
	int score;

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
