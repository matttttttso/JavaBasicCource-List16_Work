import java.util.InputMismatchException;
import java.util.Scanner;

public class BigOrSmall {
//フィールドメンバ	ここから↓
	private static	Chip chip;
	private Deck	deck;
	private Card	cardPrev,
					cardNew;
	private int 	gameCount,
					inputBetChip,
					getChip,
					choiceBigOrSmall,
					choiceContinueBigOrSmall,
					choiceContinueGame;
	private String	bigOrSmall,
					winOrLose;
	private Scanner scanner = new Scanner(System.in);

	public final int	INITIAL_CHIP = 10,
						ZERO = 0,
						ONE = 1,
						DOUBLE = 2,
						BIG_INT = 0,
						SMALL_INT = 1,
						YES = 0,
						NO = 1,
						BET_CHIP_MIN = 1,
						BET_CHIP_MAX = 20,
						CHOICE_0 = 0,
						CHOICE_1 = 1;
	public final String WIN = "Win!!",
						LOSE = "Lose...",
						BIG_STR = "Big",
						SMALL_STR = "Small";
//フィールドメンバ	ここまで↑

//プログラム記述		ここから↓
	BigOrSmall(){
		chip = new Chip(INITIAL_CHIP, ZERO);
		CHIP_REMAIN_LOOP: while(chip.getScore() != ZERO) {
			choiceContinueGame = YES;
			CONTINUE_GAME_LOOP: while(choiceContinueGame == YES) {
				initialize();
				initialPrint();
				checkInputBet();
				chip.decreaseChipNum(inputBetChip);
				printChoiceBigOrSmall();
				checkChoiceBigOrSmall();
				judge();
				printResult();
				if(winOrLose.equals(WIN)) {
					CONSECUTIVE_WIN_LOOP: while(winOrLose.equals(WIN)) {
						win();
						choiceContinueBIgOrSmall();
						checkChoiceContinueBigOrSmall();
						if(choiceContinueBigOrSmall == YES) {
							System.out.println("連続BET!!");
							inputBetChip = getChip;
							gameCount++;
							cardPrev = new Card(deck.deckToInt(gameCount));
							cardNew = new Card(deck.deckToInt(gameCount + ONE));
							printChoiceBigOrSmall();
							checkChoiceBigOrSmall();
							judge();
							printResult();
							if(winOrLose.equals(WIN)) {
								continue CONSECUTIVE_WIN_LOOP;
							}else if(winOrLose.equals(LOSE)) {
								winOrLose = LOSE;
								break CONSECUTIVE_WIN_LOOP;
							}
						}else if(choiceContinueBigOrSmall == NO) {
							System.out.println("チップを回収します");
							chip.increaseChipNum(getChip);
							winOrLose = LOSE;
							break CONSECUTIVE_WIN_LOOP;
						}
					}
				}
				if(winOrLose.equals(LOSE)) {
					break CONTINUE_GAME_LOOP;
				}
			}
			if(chip.getScore() == ZERO) {
				System.out.println("チップが底をつきました");
				break CHIP_REMAIN_LOOP;
			}
			printContinueGame();
			checkChoiceContinueGame();
			if(choiceContinueGame == YES){
				System.out.println("ゲーム続行");
				continue CHIP_REMAIN_LOOP;
			}else if(choiceContinueGame == NO) {
				break CHIP_REMAIN_LOOP;
			}
		}
		scanner.close();
		System.out.println("");
		System.out.println("END");
	}
//プログラム記述		ここまで↑

//メソッドメンバゾーン　ここから↓
	void initialize() {
		gameCount = ZERO;					//呼び出す配列の位置 ->0からスタート
		deck = new Deck();				//デッキ初期化
		deck.setDeck();					//デッキを整理
//		deck.shuffle();					//デッキのシャッフル
		cardPrev = new Card(deck.deckToInt(gameCount));		//デッキからカードを1枚引く
		cardNew = new Card(deck.deckToInt(gameCount + ONE));	//次のカードも決めとく
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
		System.out.println("BETするチップ数を入力してください（最低" + BET_CHIP_MIN + "～最大" + BET_CHIP_MAX + "）");
	}
	//選択表示(Big or Small)
	void printChoiceBigOrSmall() {
		System.out.println("\n" + "■Big or Small選択");
		System.out.println("現在のカード： " + cardPrev.toString());
		System.out.println("[Big or Small]" + "\n" + BIG_INT + ":Big " + SMALL_INT + ":Small");
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
		if(chip.getScore() == ZERO) {
			System.out.println("チップが底をつきました");
		}
		else if(chip.getScore() > ZERO) {
			System.out.println("[ゲームを続けますか?]"+ "\n" + YES + ":Yes " + NO + ":No");
		}
	}
	//勝敗判定
	void judge() {
		switch(choiceBigOrSmall) {
			case BIG_INT:
				if(cardPrev.getCardInt() < cardNew.getCardInt()) {
					bigOrSmall = BIG_STR;
					winOrLose = WIN;
					break;
				}else {
					bigOrSmall = SMALL_STR;
					winOrLose = LOSE;
					break;
				}
			case SMALL_INT:
				if(cardPrev.getCardInt() < cardNew.getCardInt()) {
					bigOrSmall = BIG_STR;
					winOrLose = LOSE;
					break;
				}else {
					bigOrSmall = SMALL_STR;
					winOrLose = WIN;
					break;
				}
		}
	}
	//勝ったとき
	void win() {
		getChip = inputBetChip * DOUBLE;
		System.out.println("チップ" + getChip + "枚を獲得しました");
	}
	//連続でBig or Smallを続けるか確認
	void choiceContinueBIgOrSmall() {
		System.out.println("\n" + "[獲得したチップ" + getChip + "枚でBig or Smallを続けますか?] " + YES + ":Yes " + NO + ":No");
	}
	//Big or Small で選んだ方を文章化
	void choiceToString(int num) {
		if(num == BIG_INT) {
			System.out.println("あなたの選択： Big");
		}else if(num == SMALL_INT) {
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
	public void checkChipBetNum(int input) throws NumberOutOfBoundException {
		if (input < BET_CHIP_MIN || BET_CHIP_MAX < input) {
			throw new NumberOutOfBoundException("チップポイントは半角数字の" + BET_CHIP_MIN + "〜" + BET_CHIP_MAX + "を入力してください ");
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
				System.out.println("半角数字の" + CHOICE_0 + "あるいは" + CHOICE_1 + "のみ入力してください");
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
				System.out.println("半角数字の" + CHOICE_0 + "あるいは" + CHOICE_1 + "のみ入力してください");
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
				System.out.println("半角数字の" + CHOICE_0 + "あるいは" + CHOICE_1 + "のみ入力してください");
			}
		}
	}
	public void checkChoice(int input) throws NumberOutOfBoundException {
		if (input != CHOICE_0 && input != CHOICE_1) {
			throw new NumberOutOfBoundException("半角数字の" + CHOICE_0 + "あるいは" + CHOICE_1 + "のみ入力してください");
		}
	}
//例外処理ゾーン	ここまで↑
}
