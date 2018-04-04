package java_2016_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class CreateField {
	private static int fieldRow; // �����ʵ��� �� ũ��
	private static int fieldCol; // �����ʵ��� �� ũ��
	private static String level; // ������ ����
	static int[][] mineList = new int[2][]; // ������ ��ǥ
	public static String[][] gameField; // �����ʵ� �迭
	static int numberOfMine; // ������ ����
	CreateField(int row, int col, String level) throws IOException { // ���� �ʵ��� ũ�⸦ �������ݴϴ�.
		gameField = new String[row][col]; // �����ʵ�迭�� ���ڿ� �迭�� ������ݴϴ�.
		fieldRow = row; fieldCol = col; // �Է¹��� �࿭�� ũ�⸦ �ʵ忡 �־��ݴϴ�.
		CreateField.level = level;
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j< gameField[i].length;j++) {
				if (i == (row - 1) && j == (col - 1)) gameField[i][j] = " �� "; // ��������ǥ��
				else gameField[i][j] = " �� ";
			}
		}
		gameField[0][0] = " �� ";
	}
	private boolean boolArray(int a,int[][] arr) { // ��ǥ�� ��� �ٸ��� ������ִ� �޼ҵ�
		boolean b1 = false, b2 = false;
		for (int i =1; i <= a; i++) {
			b1 = ( arr[0][a] == arr[0][a-i] );
			b2 = ( arr[1][a] == arr[1][a-i] );
			if (b1 && b2) return true;
			else continue;
		}	
	return false;
	}
	static void printField() { // ���� �ʵ带 ����ȭ �����ݴϴ�.
		for (int i = 0; i <80;i++) { // �ܼ� ȭ���� �����ϰ� ������ֱ� ���� �ݺ���
			System.out.println();
		}
		System.out.print("���� ��");
		for (int col = 0; col < (fieldCol+1); col++) {
			switch (level) {
			case "easy" : System.out.print("�� "); break;
			case "normal" : System.out.print("����"); break;
			case "hard" : System.out.print("����"); break;
			}
		}
		System.out.print("�� ����");
		for (int row = 0; row < fieldRow; row++) {
			System.out.println();
			System.out.print("�� ");
			for (int col = 0; col < fieldCol; col++) {
				System.out.print(gameField[row][col]);
				if (col ==  (fieldCol -1) ) System.out.print(" ��");
			}
		}
		System.out.println();
		System.out.print("���� ��");
		for (int col = 0; col < (fieldCol+1); col++) {
			switch (level) {
			case "easy" : System.out.print("�� "); break;
			case "normal" : System.out.print("����"); break;
			case "hard" : System.out.print("����"); break;
			}
		}
		System.out.println("�� ����");
	}
	void modifiedField(PlayGame obj, int row, int col) throws IOException { //����� �ʵ��� ������ �迭�� �ٽ� �־��ִ� �޼ҵ��Դϴ�.
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j< gameField[i].length;j++) {
				if (i == (row - 1) && j == (col - 1)) gameField[i][j] = " �� ";
				else gameField[i][j] = obj.iconSet(i, j);

				
			}
		}
	}
	void createMine(int l, int row, int col) { // ������ ��ǥ�� �������ִ� �޼ҵ�
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
			} else { // ���� �Էµ� ���� ���մϴ�.
				do {
				mineList[0][a] = mineRandomPoint.nextInt(row);
				mineList[1][a] = mineRandomPoint.nextInt(col);
				}
				while(boolArray(a,mineList) || ((mineList[0][a] == 0&& mineList[1][a] == 0) || (mineList[0][a] == row - 1 && mineList[1][a] == col - 1))); 
			} 
		}
		
	}
	static void inputMineToField() { // �ʿ��� ��� ����Ͽ� ȣ��
		for (int a = 0; a < mineList[0].length;a++) {
			int rowM = mineList[0][a], colM = mineList[1][a];
			gameField[rowM][colM] = " �� ";
		}
	}
}

class SelectLevel { // ������ ���̵��� �����մϴ�.
	CreateField newGame; PlayGame nowPlay; // ��ü�� ����
	int row = 0, col = 0; // ���̵��� ���� �����ʵ� ũ��
	SelectLevel() throws java.io.IOException {	// ������ �޼ҵ�. ���̵��� �����մϴ�.	
		String levelString = null;				
		int level;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("���̵��� �������ּ���(���ڷ�)"); 		
		while (true){
			try {
		level = 0;
				while (level >= 0 || level <= 4) { // ���̵� ������ �߸��Ǿ��� �� �ݺ��մϴ�.
					System.out.println("1. easy   2. normal   3. hard");
					level = Integer.parseInt(input.readLine()); // ���̵� ������ �ٽ� �Է¹޽��ϴ�.
					break;
				}
				switch (level) {
				case 1 : {levelString = "easy"; row = 3; col = 4;} break;
				case 2 : {levelString = "normal"; row = 4; col = 7;} break;
				case 3 : {levelString = "hard"; row = 6; col = 7;} break;		
				}
				newGame = new CreateField(row,col,levelString); // ���� ȭ���� ����� ���� Ŭ������ �ҷ��ɴϴ�.
				nowPlay =  new PlayGame();
				newGame.createMine(level, row, col);
				CreateField.inputMineToField();
				CreateField.printField(); // ����ȭ���� ����մϴ�.
				break;
			}
			catch (Exception e) { // ���ڰ� �ƴ� ���� �Է¹޾��� �� �����޼����� ����մϴ�.
				System.out.println("���ڸ� �Է��ϼ���. 1, 2, 3 �߿� �ϳ��� �Է��ϼ���.");
			}		
		}
	}
}

class PlayGame {
	int playerCoordinateRow, rowTemp; // ��: �Էµ������������� ��: �ݺ����� ���� �ٲ�� ��
	int playerCoordinateCol, colTemp; // ��: �Էµ������������� ��: �ݺ����� ���� �ٲ�� ��
	String way; // �̵��� ����
	int moveDistance; // �̵��� �Ÿ�
	int life = 1; int score = 0; // ����� ����
	static String msg = ""; // ����� �޼���
	protected void modifyingPCoord(int md, String w) { //�̵� �� ��� �÷��̾��� ��ǥ�� �������ݴϴ�.
		rowTemp = playerCoordinateRow;
		colTemp = playerCoordinateCol;
		switch (w) {
			case "N" : playerCoordinateRow -= md; break;
			case "W" : playerCoordinateCol -= md; break;
			case "S" : playerCoordinateRow += md; break;
			case "E" : playerCoordinateCol += md; break;
		}				
	}
	
	protected void gameOver() { // ���� ������ ������ �޼ҵ�
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
		CreateField.inputMineToField();
		CreateField.printField();
		System.out.println(" ***Game Over*** ");
		System.out.println("*** ���� ����: "+ score +" ***");
		System.exit(0);
	}
	protected String iconSet(int i, int j) { //modifyingField�޼ҵ忡 �˸´� ���ڸ� �����մϴ�.
		String icon = null;
		if (i == playerCoordinateRow &&  j == playerCoordinateCol) icon = " & ";
		else {
			if (CreateField.gameField[i][j] == " �� ") icon = " �� ";
			icon = " �� ";
		}
		return icon;
	}
	void gameClear() { // ���� Ŭ���� �� ����� �޼ҵ�
		if (playerCoordinateRow == (CreateField.gameField.length - 1) 
		&& playerCoordinateCol == (CreateField.gameField[0].length - 1)) {
			CreateField.inputMineToField();
			CreateField.printField();
			System.out.println("*** �����մϴ�. ��ǥ������ �����Ͽ����ϴ�. ***");
			System.out.println("*** ���� ����: "+ (score +300) +" ***");
			System.exit(0);
			
			
		}
	}
	protected void movePlayer(int rowL, int colL) throws IOException {  // �̵� ���� �Է¹޴� �޼ҵ�
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String errMsg = " �������δ� �̵��� �� �ִ� ������ �����ϴ�.";
		int maxDistance;
		outFor:
		for (;;) {
			System.out.print("������ �����Ͻʽÿ�(N/S/W/E) = ");
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
					System.out.println("N, S, W, E �� �ϳ��� �Է��Ͻʽÿ�.");
					System.out.println("(N)orth (S)outh (W)est (E)ast"); continue outFor; //�߸��� �� �Է½� �ݺ�
				}
			}
			do { //��Ȯ�� ���� �Է¹ޱ� ���� �ݺ����� �����մϴ�.
			System.out.print("�̵��Ÿ��� �Է��Ͻʽÿ�. ( 1 ~ " + maxDistance + ") = ");
			moveDistance = Integer.parseInt(input.readLine());
			} while (moveDistance > maxDistance); // �Է°��� �Է��ִ밪���� ������ �ݺ�
			modifyingPCoord(moveDistance, way); break outFor;
		}
	}
	protected void footPrintIf(int rt, int mcr, int ct, int mcc, int loop) { // ������ �ְų� ������ �ϰų� ���ӿ����� ��Ű�� �޼ҵ�
		if (rt != mcr ||  ct != mcc) {
			if (CreateField.gameField[rt][ct] != " �� ") {  // �̹� ������ ���̶�� ������ ���� �ʽ��ϴ�.
				score += (loop * 15 + 10); // �̵��Ÿ��� ���� ������ �ݴϴ�.
				CreateField.gameField[rt][ct] = " �� ";
			}
		} 
		else {
			if (life <= 0) {
				gameOver();
			} else {
				life--;
				score -= 100;
				msg = "** �������� �濡 ���ڸ� ��ҽ��ϴ�. LIFE -1 **";
			}
		}
	}
	protected void footPrint() { // ������ ���� ǥ�����ִ� �޼ҵ�
		int mineCheckRow = 0;
		int mineCheckCol = 0;
		for (int i = 0; i < moveDistance; i++) {
			switch (way) { // ���� ������ �濡 ���ڰ� ������ �������� �ϳ� �����ϰų� ���ӿ����ϴ� ���ǹ�
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
	public static void main(String[] args) throws java.io.IOException { // ������� ���� ���θ޼ҵ�
		System.out.println("+----------------------------+");
		System.out.println("|   Mine Dodge Beta.   |");
		System.out.println("|   Created by. KAYH   |");
		System.out.println("+----------------------------+");
		System.out.println();
		SelectLevel newLevel = new SelectLevel(); // ���� ������ ���� ���̵� ����
		while (true) { // �÷��̾��� ��ǥ�� �����̰� �ٽ� ����ϴ� �ݺ���
			newLevel.nowPlay.movePlayer(newLevel.row, newLevel.col); // �̵�
			newLevel.newGame.modifiedField(newLevel.nowPlay,newLevel.row, newLevel.col); // �̵� �� �� �Է�
			newLevel.nowPlay.footPrint(); // �̵��� �Ÿ� ǥ��
			CreateField.printField(); // ���
			System.out.println(PlayGame.msg); // �޼��� ���
			System.out.print("LIFE: " + newLevel.nowPlay.life); // ���� ����
			System.out.println("  Score: " + newLevel.nowPlay.score); // ����
			newLevel.nowPlay.gameClear(); // Ŭ���� ���� Ȯ��
			PlayGame.msg = ""; // �޼��� �ʱ�ȭ
		}
	}
}

/*TODO 
1. ������ ��ġ �������� (O)
2. ���ھ�� (O)
3. Ŭ����ȭ�� ����(O)
4. ���� ���� �ٸ��� ǥ��(X)
 */