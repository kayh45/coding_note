package java_2016_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class CreateField {
	private static int fieldRow; // 게임필드의 행 크기
	private static int fieldCol; // 게임필드의 열 크기
	private static String level; // 선택한 레벨
	static int[][] mineList = new int[2][]; // 지뢰의 좌표
	public static String[][] gameField; // 게임필드 배열
	static int numberOfMine; // 지뢰의 갯수
	CreateField(int row, int col, String level) throws IOException { // 게임 필드의 크기를 설정해줍니다.
		gameField = new String[row][col]; // 게임필드배열을 문자열 배열로 만들어줍니다.
		fieldRow = row; fieldCol = col; // 입력받은 행열의 크기를 필드에 넣어줍니다.
		CreateField.level = level;
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j< gameField[i].length;j++) {
				if (i == (row - 1) && j == (col - 1)) gameField[i][j] = " ⓖ "; // 골인지점표시
				else gameField[i][j] = " □ ";
			}
		}
		gameField[0][0] = " ＆ ";
	}
	private boolean boolArray(int a,int[][] arr) { // 좌표가 모두 다르게 만들어주는 메소드
		boolean b1 = false, b2 = false;
		for (int i =1; i <= a; i++) {
			b1 = ( arr[0][a] == arr[0][a-i] );
			b2 = ( arr[1][a] == arr[1][a-i] );
			if (b1 && b2) return true;
			else continue;
		}	
	return false;
	}
	static void printField() { // 게임 필드를 가시화 시켜줍니다.
		for (int i = 0; i <80;i++) { // 콘솔 화면을 깨끗하게 만들어주기 위한 반복문
			System.out.println();
		}
		System.out.print("┌─ ─");
		for (int col = 0; col < (fieldCol+1); col++) {
			switch (level) {
			case "easy" : System.out.print("─ "); break;
			case "normal" : System.out.print("──"); break;
			case "hard" : System.out.print("──"); break;
			}
		}
		System.out.print("─ ─┐");
		for (int row = 0; row < fieldRow; row++) {
			System.out.println();
			System.out.print("│ ");
			for (int col = 0; col < fieldCol; col++) {
				System.out.print(gameField[row][col]);
				if (col ==  (fieldCol -1) ) System.out.print(" │");
			}
		}
		System.out.println();
		System.out.print("└─ ─");
		for (int col = 0; col < (fieldCol+1); col++) {
			switch (level) {
			case "easy" : System.out.print("─ "); break;
			case "normal" : System.out.print("──"); break;
			case "hard" : System.out.print("──"); break;
			}
		}
		System.out.println("─ ─┘");
	}
	void modifiedField(PlayGame obj, int row, int col) throws IOException { //변경된 필드의 내용을 배열에 다시 넣어주는 메소드입니다.
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j< gameField[i].length;j++) {
				if (i == (row - 1) && j == (col - 1)) gameField[i][j] = " ⓖ ";
				else gameField[i][j] = obj.iconSet(i, j);

				
			}
		}
	}
	void createMine(int l, int row, int col) { // 지뢰의 좌표를 생성해주는 메소드
		Random mineRandomPoint = new Random();
		numberOfMine = l * 3;
		mineList = new int[2][numberOfMine];
		for (int a = 0; a < numberOfMine; a++) {
			if (a ==0) { 
			do {
				mineList[0][a] = mineRandomPoint.nextInt(row);
				mineList[1][a] = mineRandomPoint.nextInt(col);
				}
			while ((mineList[0][a] == 0&& mineList[1][a] == 0) || (mineList[0][a] == row - 1 && mineList[1][a] == col - 1));
			} else { // 전에 입력된 값과 비교합니다.
				do {
				mineList[0][a] = mineRandomPoint.nextInt(row);
				mineList[1][a] = mineRandomPoint.nextInt(col);
				}
				while(boolArray(a,mineList) || ((mineList[0][a] == 0&& mineList[1][a] == 0) || (mineList[0][a] == row - 1 && mineList[1][a] == col - 1))); 
			} 
		}
		
	}
	static void inputMineToField() { // 필요한 경우 사용하여 호출
		for (int a = 0; a < mineList[0].length;a++) {
			int rowM = mineList[0][a], colM = mineList[1][a];
			gameField[rowM][colM] = " ◆ ";
		}
	}
}

class SelectLevel { // 게임의 난이도를 설정합니다.
	CreateField newGame; PlayGame nowPlay; // 객체를 정의
	int row = 0, col = 0; // 난이도에 따른 게임필드 크기
	SelectLevel() throws java.io.IOException {	// 생성자 메소드. 난이도를 선택합니다.	
		String levelString = null;				
		int level;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("난이도를 선택해주세요(숫자로)"); 		
		while (true){
			try {
		level = 0;
				while (level >= 0 || level <= 4) { // 난이도 선택이 잘못되었을 시 반복합니다.
					System.out.println("1. easy   2. normal   3. hard");
					level = Integer.parseInt(input.readLine()); // 난이도 선택을 다시 입력받습니다.
					break;
				}
				switch (level) {
				case 1 : {levelString = "easy"; row = 3; col = 4;} break;
				case 2 : {levelString = "normal"; row = 4; col = 7;} break;
				case 3 : {levelString = "hard"; row = 6; col = 7;} break;		
				}
				newGame = new CreateField(row,col,levelString); // 게임 화면을 만들기 위한 클래스를 불러옵니다.
				nowPlay =  new PlayGame();
				newGame.createMine(level, row, col);
				CreateField.inputMineToField();
				CreateField.printField(); // 게임화면을 출력합니다.
				break;
			}
			catch (Exception e) { // 숫자가 아닌 값을 입력받았을 시 오류메세지를 출력합니다.
				System.out.println("숫자를 입력하세요. 1, 2, 3 중에 하나만 입력하세요.");
			}		
		}
	}
}

class PlayGame {
	int playerCoordinateRow, rowTemp; // 앞: 입력된최종도착지점 뒤: 반복문을 통해 바뀌는 값
	int playerCoordinateCol, colTemp; // 앞: 입력된최종도착지점 뒤: 반복문을 통해 바뀌는 값
	String way; // 이동할 방향
	int moveDistance; // 이동할 거리
	int life = 1; int score = 0; // 생명과 점수
	static String msg = ""; // 출력할 메세지
	protected void modifyingPCoord(int md, String w) { //이동 한 대로 플레이어의 좌표를 수정해줍니다.
		rowTemp = playerCoordinateRow;
		colTemp = playerCoordinateCol;
		switch (w) {
			case "N" : playerCoordinateRow -= md; break;
			case "W" : playerCoordinateCol -= md; break;
			case "S" : playerCoordinateRow += md; break;
			case "E" : playerCoordinateCol += md; break;
		}				
	}
	
	protected void gameOver() { // 게임 오버를 수행할 메소드
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
		CreateField.inputMineToField();
		CreateField.printField();
		System.out.println(" ***Game Over*** ");
		System.out.println("*** 최종 점수: "+ score +" ***");
		System.exit(0);
	}
	protected String iconSet(int i, int j) { //modifyingField메소드에 알맞는 문자를 리턴합니다.
		String icon = null;
		if (i == playerCoordinateRow &&  j == playerCoordinateCol) icon = " & ";
		else {
			if (CreateField.gameField[i][j] == " ■ ") icon = " ■ ";
			icon = " □ ";
		}
		return icon;
	}
	void gameClear() { // 게임 클리어 시 출력할 메소드
		if (playerCoordinateRow == (CreateField.gameField.length - 1) 
		&& playerCoordinateCol == (CreateField.gameField[0].length - 1)) {
			CreateField.inputMineToField();
			CreateField.printField();
			System.out.println("*** 축하합니다. 목표지점에 도착하였습니다. ***");
			System.out.println("*** 최종 점수: "+ (score +300) +" ***");
			System.exit(0);
			
			
		}
	}
	protected void movePlayer(int rowL, int colL) throws IOException {  // 이동 값을 입력받는 메소드
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String errMsg = " 방향으로는 이동할 수 있는 공간이 없습니다.";
		int maxDistance;
		outFor:
		for (;;) {
			System.out.print("방향을 선택하십시오(N/S/W/E) = ");
			way = input.readLine();
			switch (way) {
				case "N" : if (playerCoordinateRow == 0) {
					System.out.println(way + errMsg); continue outFor;
					} else {maxDistance = playerCoordinateRow; break;}
				case "W" :if (playerCoordinateCol == 0) {
					System.out.println(way + errMsg); continue outFor;
					} else {maxDistance = playerCoordinateCol; break;}
				case "S" :if ((rowL - playerCoordinateRow) == 0) {
					System.out.println(way + errMsg); continue outFor;
					} else {maxDistance = (rowL - playerCoordinateRow - 1); break;}
				case "E" :if ((colL - playerCoordinateCol) == 0) {
					System.out.println(way + errMsg); continue outFor;
					} else {maxDistance = (colL - playerCoordinateCol - 1); break;}
				default : {
					System.out.println("N, S, W, E 중 하나만 입력하십시오.");
					System.out.println("(N)orth (S)outh (W)est (E)ast"); continue outFor; //잘못된 값 입력시 반복
				}
			}
			do { //정확한 값을 입력받기 위한 반복문을 시작합니다.
			System.out.print("이동거리를 입력하십시오. ( 1 ~ " + maxDistance + ") = ");
			moveDistance = Integer.parseInt(input.readLine());
			} while (moveDistance > maxDistance); // 입력값이 입력최대값보다 높으면 반복
			modifyingPCoord(moveDistance, way); break outFor;
		}
	}
	protected void footPrintIf(int rt, int mcr, int ct, int mcc, int loop) { // 점수를 주거나 감점을 하거나 게임오버를 시키는 메소드
		if (rt != mcr ||  ct != mcc) {
			if (CreateField.gameField[rt][ct] != " ■ ") {  // 이미 지나온 길이라면 점수를 주지 않습니다.
				score += (loop * 15 + 10); // 이동거리에 따라 점수를 줍니다.
				CreateField.gameField[rt][ct] = " ■ ";
			}
		} 
		else {
			if (life <= 0) {
				gameOver();
			} else {
				life--;
				score -= 100;
				msg = "** 지나가는 길에 지뢰를 밟았습니다. LIFE -1 **";
			}
		}
	}
	protected void footPrint() { // 지나온 길을 표시해주는 메소드
		int mineCheckRow = 0;
		int mineCheckCol = 0;
		for (int i = 0; i < moveDistance; i++) {
			switch (way) { // 이하 지나온 길에 지뢰가 있으면 라이프를 하나 차감하거나 게임오버하는 조건문
				case "N" : 
					for (int k = 0; k < CreateField.numberOfMine; k++) {
					mineCheckRow = CreateField.mineList[0][k];
					mineCheckCol = CreateField.mineList[1][k];
					int temp = rowTemp - i;
					footPrintIf(temp, mineCheckRow, colTemp,mineCheckCol,i);
				}break;					
				case "W" : 
					for (int k = 0; k < CreateField.numberOfMine; k++) {
						mineCheckRow = CreateField.mineList[0][k];
						mineCheckCol = CreateField.mineList[1][k];
						int temp = colTemp - i;
						footPrintIf(rowTemp, mineCheckRow, temp,mineCheckCol,i);
				}break;	
				case "S" : 
					for (int k = 0; k < CreateField.numberOfMine; k++) {
						mineCheckRow = CreateField.mineList[0][k];
						mineCheckCol = CreateField.mineList[1][k];
						int temp = rowTemp + i;
						footPrintIf(temp, mineCheckRow, colTemp,mineCheckCol,i);
				}break;	
				case "E" : 
					for (int k = 0; k < CreateField.numberOfMine; k++) {
						mineCheckRow = CreateField.mineList[0][k];
						mineCheckCol = CreateField.mineList[1][k];
						int temp = colTemp + i;
						footPrintIf(rowTemp, mineCheckRow, temp,mineCheckCol,i);
				}break;	
			}		
		}
	}
}

public class MineDodge {
	public static void main(String[] args) throws java.io.IOException { // 최종제어를 위한 메인메소드
		System.out.println("+----------------------------+");
		System.out.println("|   Mine Dodge Beta.   |");
		System.out.println("|   Created by. KAYH   |");
		System.out.println("+----------------------------+");
		System.out.println();
		SelectLevel newLevel = new SelectLevel(); // 게임 시작을 위한 난이도 선택
		while (true) { // 플레이어의 좌표를 움직이고 다시 출력하는 반복문
			newLevel.nowPlay.movePlayer(newLevel.row, newLevel.col); // 이동
			newLevel.newGame.modifiedField(newLevel.nowPlay,newLevel.row, newLevel.col); // 이동 한 값 입력
			newLevel.nowPlay.footPrint(); // 이동한 거리 표시
			CreateField.printField(); // 출력
			System.out.println(PlayGame.msg); // 메세지 출력
			System.out.print("LIFE: " + newLevel.nowPlay.life); // 남은 생명
			System.out.println("  Score: " + newLevel.nowPlay.score); // 점수
			newLevel.nowPlay.gameClear(); // 클리어 여부 확인
			PlayGame.msg = ""; // 메세지 초기화
		}
	}
}

/*TODO 
1. 지뢰의 위치 랜덤생성 (O)
2. 스코어보드 (O)
3. 클리어화면 구현(O)
4. 밟은 지뢰 다르게 표시(X)
 */