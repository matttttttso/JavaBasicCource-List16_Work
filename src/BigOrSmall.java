import java.util.Scanner;

public class BigOrSmall {
	int gameCount;
	Chip chip;
	Deck deck;
	Card cardPrev;
	Card cardNew;
	int inputBetNum;
	int choiceBigOrSmall;
	int choiceContinueBet;
	int choiceContinueGame;
	String bigOrSmall;
	String winOrLose = "Win!!";

	BigOrSmall(){
		chip = new Chip(10, 0);	//チップ数初期化
		while(choiceContinueGame == 0) {
			while(winOrLose == "Win!!") {
				//↓-----ゲーム数（初期化）------------------------------------------------------------↓
				gameCount = 0;			//呼び出す配列の位置 ->0からスタート
				deck = new Deck();		//デッキ初期化
				deck.setDeck();			//デッキを整理
	//			deck.shuffle();			//デッキのシャッフル
				cardPrev = new Card(deck.deckToInt(gameCount));		//デッキからカードを1枚引く
				cardNew = new Card(deck.deckToInt(gameCount + 1));	//次のカードも決めとく
				initialPrint();			//初期チップ数、初期カード表示、ベット数入力依頼
				//↑-----ゲーム数（初期化）------------------------------------------------------------↑
				//↓-----BET------------------------------------------------------------↓
				inputBetNum = new Scanner(System.in).nextInt();		//入力待機（BET数）
				chip.decreaseChipNum(inputBetNum);
				System.out.println(chip.toString());
				/*
				 * 後で例外を処理するように修正
				 * 4-1	入力したチップポイントが、もし半角数字の1〜20以外の文字が入力された場合、
				 * 		エラーメッセージを表示させて（表示例、チップポイントは半角数字の1〜20を入力してください）、
				 * 		上記の3に戻る。
				 * 4-2	入力したチップポイントが、もし総計のチップポイントより多く入力（BET）した場合、
				 * 		エラーメッセーを表示させて（表示例、BETするチップポイントは総計のチップポイント以下で入力してください）、
				 * 		上記の3に戻る（例、現在のチップポイントの総計が15の場合、BETできるチップポイントは15までになる）。
				 */
				chip.getScore();		//チップの数値によって場合分け
				//↑-----BET------------------------------------------------------------↑
				//↓-----BigOrSmall選択------------------------------------------------------------↓
				printChoiceBigOrSmall();	//BigOrSmallの選択依頼
				choiceBigOrSmall = new Scanner(System.in).nextInt();	//入力待機0:Big 1:Small
				/*
				 * 後で例外を処理するように修正
				 * 6	もし半角数字の0あるいは1以外の文字が入力された場合、
				 * 		エラーメッセージを表示させて（表示例、半角数字の0あるいは1のみ入力してください）、上記の5に戻る。
				 */
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
				choiceContinueBet = new Scanner(System.in).nextInt();
				//↓↓----BET続行しない=>ループ脱出---------------------------------------------------------↓↓
				if(choiceContinueBet == 1) {
					System.out.println("BETを一旦やめます");
					break;
				//↑↑----BET続行しない=>ループ脱出---------------------------------------------------------↑↑
				//↓↓----BET続行-----------------------------------------------------------↓↓
				}else if(choiceContinueBet == 0) {
					System.out.println("BET続けます");
				}
				while(choiceContinueBet == 0) {
					gameCount++;	//ゲーム数を+1
					cardPrev = new Card(deck.deckToInt(gameCount));
					cardNew = new Card(deck.deckToInt(gameCount + 1));
					//↓-----BigOrSmall選択------------------------------------------------------------↓
					printChoiceBigOrSmall();
					choiceBigOrSmall = new Scanner(System.in).nextInt();
					//例外処理
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
					choiceContinueBet = new Scanner(System.in).nextInt();
					//↓↓----BET続行しない=>ゲームを続けるか選択---------------------------------------------------------↓↓
					if(choiceContinueBet == 1) {
						System.out.println("BETを一旦やめます");
						//↓-----ゲームを続けるか選択-------------------------------------------↓
						printContinueGame();
						choiceContinueGame = new Scanner(System.in).nextInt();
						if(choiceContinueGame == 1) {
							System.out.println("ゲームをやめます");
							break;
						}else if(choiceContinueGame == 0){
							System.out.println("ゲームを続けます");
						}
						//↑-----ゲームを続けるか選択-------------------------------------------↑
					//↑↑----BET続行しない=>ループ脱出---------------------------------------------------------↑↑
					//↑↑----BET続行-----------------------------------------------------------↑↑
					}else if(choiceContinueBet == 0) {
						System.out.println("BET続けます");
					}
				}
				if(winOrLose == "Lose...") {
					break;
				}
				if(choiceContinueGame == 1) {
					System.out.println("while(win) 終了");
					break;
				}else if(choiceContinueGame == 0){
					System.out.println("ゲームを続けます");
				}
				//↑-----勝ったらループ続行------------------------------------------------------------↑
			}
			printContinueGame();
			choiceContinueGame = new Scanner(System.in).nextInt();
			if(choiceContinueGame == 1) {
				System.out.println("while(Yes) 終了");
				break;
			}else if(choiceContinueGame == 0){
				System.out.println("ゲームを続けます");
				winOrLose = "Win!!";
			}
		}
		//Noの場合
		//プログラム終了
		System.out.println("");
		System.out.println("END");
	}

	void initialPrint() {
		System.out.println("********チップ枚数とカード********");
		System.out.println(chip.toString());
		System.out.println("現在のカード： " + cardPrev.toString());
		System.out.println("****************************");
		System.out.println("\n" + "■BET枚数選択");
		System.out.println("BETするチップ数を入力してください（最低1～最大20）");
	}

	void printChoiceBigOrSmall() {
		System.out.println("\n" + "■Big or Small選択");
		System.out.println("現在のカード： " + cardPrev.toString());
		System.out.println("[Big or Small] 0:Big 1:Small");
	}

	void printResult() {
		System.out.println("********Big or Small********");
		System.out.println("BET数： " + inputBetNum);
		choiceToString(choiceBigOrSmall);
		System.out.println("引いたカード： " + cardNew.toString());
		System.out.println(cardNew.toString() + " は " + cardPrev.toString() +" より " + bigOrSmall);
		System.out.println("****************************");
		System.out.println(winOrLose);
	}

	void printContinueGame() {
		System.out.println("********現在のチップ枚数********");
		System.out.println(chip.toString());
		System.out.println("****************************");
		System.out.println("[ゲームを続けますか?] 0:Yes 1:No");
	}

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
					chip.decreaseChipNum(inputBetNum);
					break;
				}
			case 1:	//Small
				if(cardPrev.getCardInt() < cardNew.getCardInt()) {
					bigOrSmall = "Big";
					winOrLose = "Lose...";
					chip.decreaseChipNum(inputBetNum);
					break;
				}else {
					bigOrSmall = "Small";
					winOrLose = "Win!!";
					break;
				}
		}
	}

	void win() {
		chip.increaseChipNum(inputBetNum*2);
		System.out.println("チップ" + inputBetNum*2 + "枚を獲得しました");
		System.out.println("\n" + "[獲得したチップ" + inputBetNum*2 + "枚でBig or Smallを続けますか?] 0:Yes 1:No");
//		if()
	}

	void choiceToString(int num) {
		if(num == 0) {
			System.out.println("あなたの選択： Big");
		}else if(num == 1) {
			System.out.println("あなたの選択： Small");
		}
	}

}
