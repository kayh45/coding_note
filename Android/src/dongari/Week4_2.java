package dongari;
import java.util.Scanner;

public class Week4_2 {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("���� �� �ϳ��� �Է����ּ���");
		System.out.println("(1. ���ǹ�, 2. �ݺ���, 3. �迭)");
		String slt = input.next();
		
//		switch(slt) {
//		case "���ǹ�" : System.out.println("���ǹ����� if���� switch���� �ֽ��ϴ�."); break;
//		case "�ݺ���" : System.out.println("�ݺ������� for���� while���� �ֽ��ϴ�."); break;
//		case "�迭" : System.out.println("�迭�� []�� ����Ͽ� ǥ���մϴ�."); break;
//		default : System.out.println("�߸��� �Է��Դϴ�.");
//		}
		
		if(slt.equals("���ǹ�")) {
			System.out.println("���ǹ����� if���� switch���� �ֽ��ϴ�.");
		}else if(slt.equals("�ݺ���")) {
			System.out.println("�ݺ������� for���� while���� �ֽ��ϴ�.");
		}else if(slt.equals("�迭")) {
			System.out.println("�迭�� []�� ����Ͽ� ǥ���մϴ�."); 
		}else {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
		
				
		input.close();
		
	}

}
