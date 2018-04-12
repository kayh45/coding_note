package dongari;
import java.util.Scanner;

public class Week4_2 {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("다음 중 하나를 입력해주세요");
		System.out.println("(1. 조건문, 2. 반복문, 3. 배열)");
		String slt = input.next();
		
//		switch(slt) {
//		case "조건문" : System.out.println("조건문에는 if문과 switch문이 있습니다."); break;
//		case "반복문" : System.out.println("반복문에는 for문과 while문이 있습니다."); break;
//		case "배열" : System.out.println("배열은 []를 사용하여 표현합니다."); break;
//		default : System.out.println("잘못된 입력입니다.");
//		}
		
		if(slt.equals("조건문")) {
			System.out.println("조건문에는 if문과 switch문이 있습니다.");
		}else if(slt.equals("반복문")) {
			System.out.println("반복문에는 for문과 while문이 있습니다.");
		}else if(slt.equals("배열")) {
			System.out.println("배열은 []를 사용하여 표현합니다."); 
		}else {
			System.out.println("잘못된 입력입니다.");
		}
		
				
		input.close();
		
	}

}
