package dongari;
import java.util.Scanner;

public class Week4_1 {
	public static void main(String[] args) {
		int age = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.println("���̸� �Է��ϼ���");
		age = input.nextInt();
		
		if(age == 60) {
			System.out.println("ȯ���Դϴ�.");
		} else if(age >= 20) {
			System.out.println("�����Դϴ�.");
		} else {
			System.out.println("�̼������Դϴ�.");
		}
		
		input.close();
	}
}
