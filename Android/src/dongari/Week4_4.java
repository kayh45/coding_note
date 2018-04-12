package dongari;
import java.util.Scanner;

public class Week4_4 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		final int CORRECT = (int)(Math.random() * 51);
		
		while(true) {
			System.out.println("숫자를 입력해주세요(0~50)");
			int answer = input.nextInt();
			if(answer == CORRECT){
				System.out.println("***정답입니다.***");
				break;
			}else if(answer > CORRECT){
				System.out.println("DOWN");
			}else if(answer < CORRECT){
				System.out.println("UP");
			}
		}
		
		input.close();
		
	}
}
