package dongari;
import java.util.Scanner;

public class Week4_1 {
	public static void main(String[] args) {
		int age = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.println("나이를 입력하세요");
		age = input.nextInt();
		
		if(age == 60) {
			System.out.println("환갑입니다.");
		} else if(age >= 20) {
			System.out.println("성인입니다.");
		} else {
			System.out.println("미성년자입니다.");
		}
		
		input.close();
	}
}
