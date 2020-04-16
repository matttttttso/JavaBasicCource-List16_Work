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
	int choiceContinueBet;
	int choiceContinueGame;
	String bigOrSmall;
	String winOrLose = "Win!!";
//フィールドメンバ	ここまで↑

//プログラム記述		ここから↓
	BigOrSmall(){
		chip = new Chip(10, 0);					//チップ数初期化
		while(choiceContinueGame == 0) {
			while(winOrLose == "Win!!") {
			//↓-----ゲーム数（初期化）------------------------------------------------------------↓
				gameCount = 0;					//呼び出す配列の位置 ->0からスタート
				deck = new Deck();				//デッキ初期化
				deck.setDeck();					//デッキを整理
				deck.shuffle();					//デッキのシャッフル
				cardPrev = new Card(deck.deckToInt(gameCount));		//デッキからカードを1枚引く
				cardNew = new Card(deck.deckToInt(gameCount + 1));	//次のカードも決めとく
				initialPrint();					//初期チップ数、初期カード表示、BET数入力依頼
			//↑-----ゲーム数（初期化）------------------------------------------------------------↑
			//↓-----BET------------------------------------------------------------↓
				checkInputBet();				//BET数入力、チェック
				chip.decreaseChipNum(inputBetChip);					//BETした分減らす
				System.out.println("BET数： " + inputBetChip);
				System.out.println(chip.toString());
			//↑-----BET------------------------------------------------------------↑
			//↓-----BigOrSmall選択------------------------------------------------------------↓
				printChoiceBigOrSmall();	//BigOrSmallの選択依頼
				checkChoiceBigOrSmall();	//入力待機0:Big 1:Small
			//↑-----BigOrSmall選択------------------------------------------------------------↑
			//↓-----勝敗判定------------------------------------------------------------↓
				judge();		//勝敗判定
				printResult();	//結果表示
			//↓-----勝敗判定------------------------------------------------------------↑
			//↓-----負けたらループ脱出=>ゲームを続けるか選択-------------------------------------------↓
				if(winOrLose == "Lose...") {
					break;
				}
			//↑-----負けたらループ脱出=>ゲームを続けるか選択-------------------------------------------↑
			//↓-----勝ったらループ続行------------------------------------------------------------↓
				win();
			//↓↓----BET続行確認-----------------------------------------------------------↓↓
				checkChoiceContinueBet();
			//↓↓----BET続行しない=>ループ脱出---------------------------------------------------------↓↓
				if(choiceContinueBet == 1) {
					System.out.println("BETを一旦やめます");
					chip.increaseChipNum(getChip);
					break;
			//↑↑----BET続行しない=>ループ脱出---------------------------------------------------------↑↑
			//↓↓----BET続行-----------------------------------------------------------↓↓
				}else if(choiceContinueBet == 0) {
					System.out.println("BET続けます");
					inputBetChip = getChip;
				}
				while(choiceContinueBet == 0) {
					gameCount++;	//ゲーム数を+1
					cardPrev = new Card(deck.deckToInt(gameCount));
					cardNew = new Card(deck.deckToInt(gameCount + 1));
				//↓-----BigOrSmall選択------------------------------------------------------------↓
					printChoiceBigOrSmall();
					checkChoiceBigOrSmall();
				//↑-----BigOrSmall選択------------------------------------------------------------↑
				//↓-----勝敗判定------------------------------------------------------------↓
					judge();
					printResult();
				//↓-----負けたらループ脱出=>ゲームを続けるか選択-------------------------------------------↓
					if(winOrLose == "Lose...") {
						break;
					}
				//↑-----負けたらループ脱出=>ゲームを続けるか選択-------------------------------------------↑
					win();
				//↓↓----BET続行確認-----------------------------------------------------------↓↓
					checkChoiceContinueBet();
				//↓↓----BET続行しない=>ゲームを続けるか選択---------------------------------------------------------↓↓
					if(choiceContinueBet == 1) {
						System.out.println("BETを一旦やめます");
						chip.increaseChipNum(getChip);
					//↓-----ゲームを続けるか選択-------------------------------------------↓
						printContinueGame();
						checkChoiceContinueGame();
						if(choiceContinueGame == 1) {
							System.out.println("ゲームをやめます");
							break;
						}else if(choiceContinueGame == 0){
							System.out.println("ゲームを続けます");
						}
					//↑-----ゲームを続けるか選択-------------------------------------------↑
				//↑↑----BET続行しない=>ループ脱出---------------------------------------------------------↑↑
					}else if(choiceContinueBet == 0) {
						System.out.println("BET続けます");
						inputBetChip = getChip;
					}
				//↑↑----BET続行-----------------------------------------------------------↑↑
				}
				//↑-----勝ったらループ続行------------------------------------------------------------↑
				//↓-----負けたらループ脱出=>ゲームを続けるか選択-------------------------------------------↓
				if(winOrLose == "Lose...") {
					break;
				}
				//↑-----負けたらループ脱出=>ゲームを続けるか選択------------------------------------------------------------↑
			}
			//↓-----ゲームを続けるか選択-------------------------------------------↓
			printContinueGame();
			checkChoiceContinueGame();
			if(choiceContinueGame == 1) {
				break;
			}else if(choiceContinueGame == 0){
				System.out.println("ゲームを続けます");
				winOrLose = "Win!!";
			}
			//↑-----ゲームを続けるか選択-------------------------------------------↑
		}
		//プログラム終了
		System.out.println("");
		System.out.println("END");
	}
//プログラム記述		ここまで↑

//メソッドメンバゾーン　ここから↓
	//初期の表示
	void initialPrint() {
		System.out.println("********チップ枚数とカード********");
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
		System.out.println("[Big or Small] 0:Big 1:Small");
	}
	//結果表示
	void printResult() {
		System.out.println("********Big or Small********");
		System.out.println(chip.toString());
		System.out.println("BET数： " + inputBetChip);
		choiceToString(choiceBigOrSmall);
		System.out.println("引いたカード： " + cardNew.toString());
		System.out.println(cardNew.toString() + " は " + cardPrev.toString() +" より " + bigOrSmall);
		System.out.println("****************************");
		System.out.println(winOrLose);
	}
	//チップ数表示
	void printContinueGame() {
		System.out.println("********現在のチップ枚数********");
		System.out.println(chip.toString());
		System.out.println("****************************");
		System.out.println("[ゲームを続けますか?] 0:Yes 1:No");
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
				inputBetChip = new Scanner(System.in).nextInt();
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
				choiceBigOrSmall = new Scanner(System.in).nextInt();
				checkChoice(choiceBigOrSmall);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字の0あるいは1のみ入力してください");
			}
		}
	}
	void checkChoiceContinueBet() {
		while(true) {
			try {
				choiceContinueBet = new Scanner(System.in).nextInt();
				checkChoice(choiceContinueBet);
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
				choiceContinueGame = new Scanner(System.in).nextInt();
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
