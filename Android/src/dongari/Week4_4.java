package dongari;
import java.util.Scanner;

public class Week4_4 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		final int CORRECT = (int)(Math.random() * 51);
		
		while(true) {
			System.out.println("���ڸ� �Է����ּ���(0~50)");
			int answer = input.nextInt();
			if(answer == CORRECT){
				System.out.println("***�����Դϴ�.***");
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
