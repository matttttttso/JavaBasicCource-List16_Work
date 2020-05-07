import java.util.InputMismatchException;
import java.util.Scanner;

public class BigOrSmall {
//フィールドメンバ	ここから↓
	int gameCount;
	static Chip chip;
	Deck deck;
	Card cardPrev;
	Card cardNew;
	int inputBetChip;
	int getChip;
	int choiceBigOrSmall;
	int choiceContinueBigOrSmall;
	int choiceContinueGame;
	String bigOrSmall;
	String winOrLose;
	Scanner scanner = new Scanner(System.in);
//フィールドメンバ	ここまで↑

//プログラム記述		ここから↓
	BigOrSmall(){
		chip = new Chip(10, 0);
		while(true) {
			while(true) {
				initialize();
				initialPrint();
				checkInputBet();
				chip.decreaseChipNum(inputBetChip);
				printChoiceBigOrSmall();
				checkChoiceBigOrSmall();
				judge();
				printResult();
				if(winOrLose == "Win!!") {
					while(true) {
						win();
						choiceContinueBIgOrSmall();
						checkChoiceContinueBigOrSmall();
						if(choiceContinueBigOrSmall == 0) {
							System.out.println("連続BET!!");
							inputBetChip = getChip;
							gameCount++;
							cardPrev = new Card(deck.deckToInt(gameCount));
							cardNew = new Card(deck.deckToInt(gameCount + 1));
							printChoiceBigOrSmall();
							checkChoiceBigOrSmall();
							judge();
							printResult();
							if(winOrLose == "Win!!") {
								continue;
							}else if(winOrLose == "Lose...") {
								break;
							}
						}else if(choiceContinueBigOrSmall == 1) {
							System.out.println("チップを回収します");
							chip.increaseChipNum(getChip);
							winOrLose = "Lose...";
							break;
						}
					}
				}
				if(winOrLose == "Lose...") {
					break;
				}
			}
			if(chip.getScore() == 0) {
				System.out.println("チップが底をつきました");
				break;
			}
			printContinueGame();
			checkChoiceContinueGame();
			if(choiceContinueGame == 0){
				System.out.println("ゲーム続行");
				continue;
			}else if(choiceContinueGame == 1) {
				break;
			}
		}
		scanner.close();
		System.out.println("");
		System.out.println("END");
	}
//プログラム記述		ここまで↑

//メソッドメンバゾーン　ここから↓
	void initialize() {
		gameCount = 0;					//呼び出す配列の位置 ->0からスタート
		deck = new Deck();				//デッキ初期化
		deck.setDeck();					//デッキを整理
		deck.shuffle();					//デッキのシャッフル
		cardPrev = new Card(deck.deckToInt(gameCount));		//デッキからカードを1枚引く
		cardNew = new Card(deck.deckToInt(gameCount + 1));	//次のカードも決めとく
	}
	//初期の表示
	void initialPrint() {
		System.out.println("デッキを初期化しました");
		System.out.println("\n********チップ枚数とカード********");
		System.out.println(chip.toString());
		System.out.println("現在のカード： " + cardPrev.toString());
		System.out.println("****************************");
	}
	//BET数入力表示
	void printBet() {
		System.out.println("\n" + "■BET枚数入力");
		System.out.println("BETするチップ数を入力してください（最低1～最大20）");
	}
	//選択表示(Big or Small)
	void printChoiceBigOrSmall() {
		System.out.println("\n" + "■Big or Small選択");
		System.out.println("現在のカード： " + cardPrev.toString());
		System.out.println("[Big or Small]" + "\n0:Big 1:Small");
	}
	//結果表示
	void printResult() {
		System.out.println("\n" + "<<<<<<<<Big or Small>>>>>>>>");
		System.out.println(chip.toString());
		System.out.println("BET数： " + inputBetChip);
		choiceToString(choiceBigOrSmall);
		System.out.println("引いたカード： " + cardNew.toString());
		System.out.println(cardNew.toString() + " は " + cardPrev.toString() +" より " + bigOrSmall);
		System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
		System.out.println("\n" + winOrLose);
	}
	//チップ数表示
	void printContinueGame() {
		System.out.println("\n" + "--------現在のチップ枚数--------");
		System.out.println(chip.toString());
		System.out.println("----------------------------");
		if(chip.getScore() == 0) {
			System.out.println("チップが底をつきました");
		}
		else if(chip.getScore() > 0) {
			System.out.println("[ゲームを続けますか?]"+ "\n0:Yes 1:No");
		}
	}
	//勝敗判定
	void judge() {
		switch(choiceBigOrSmall) {
			case 0:	//Big
				if(cardPrev.getCardInt() < cardNew.getCardInt()) {
					bigOrSmall = "Big";
					winOrLose = "Win!!";
					break;
				}else {
					bigOrSmall = "Small";
					winOrLose = "Lose...";
					break;
				}
			case 1:	//Small
				if(cardPrev.getCardInt() < cardNew.getCardInt()) {
					bigOrSmall = "Big";
					winOrLose = "Lose...";
					break;
				}else {
					bigOrSmall = "Small";
					winOrLose = "Win!!";
					break;
				}
		}
	}
	//勝ったとき
	void win() {
		getChip = inputBetChip * 2;
		System.out.println("チップ" + getChip + "枚を獲得しました");
	}
	//連続でBig or Smallを続けるか確認
	void choiceContinueBIgOrSmall() {
		System.out.println("\n" + "[獲得したチップ" + getChip + "枚でBig or Smallを続けますか?] 0:Yes 1:No");
	}
	//Big or Small で選んだ方を文章化
	void choiceToString(int num) {
		if(num == 0) {
			System.out.println("あなたの選択： Big");
		}else if(num == 1) {
			System.out.println("あなたの選択： Small");
		}
	}
//メソッドメンバゾーン	ここまで↑

//例外処理ゾーン	ここから↓
	//BET数の例外処理
	void checkInputBet() {
		while(true) {
			try {
				printBet();
				inputBetChip = scanner.nextInt();
				checkChipBetNum(inputBetChip);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字で入力してください。");
			}
		}
	}
	public static void checkChipBetNum(int input) throws NumberOutOfBoundException {
		if (input < 1 || 20 < input) {
			throw new NumberOutOfBoundException("チップポイントは半角数字の1〜20を入力してください ");
		}
		if (chip.getScore() < input) {
			throw new NumberOutOfBoundException("BETするチップポイントは総計のチップポイント以下で入力してください ");
		}
	}
	//0or1の例外処理
	void checkChoiceBigOrSmall() {
		while(true) {
			try {
				choiceBigOrSmall = scanner.nextInt();
				checkChoice(choiceBigOrSmall);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字の0あるいは1のみ入力してください");
			}
		}
	}
	void checkChoiceContinueBigOrSmall() {
		while(true) {
			try {
				choiceContinueBigOrSmall = scanner.nextInt();
				checkChoice(choiceContinueBigOrSmall);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字の0あるいは1のみ入力してください");
			}
		}
	}
	void checkChoiceContinueGame() {
		while(true) {
			try {
				choiceContinueGame = scanner.nextInt();
				checkChoice(choiceContinueGame);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字の0あるいは1のみ入力してください");
			}
		}
	}
	public static void checkChoice(int input) throws NumberOutOfBoundException {
		if (input != 0 && input != 1) {
			throw new NumberOutOfBoundException("半角数字の0あるいは1のみ入力してください");
		}
	}
//例外処理ゾーン	ここまで↑
}
