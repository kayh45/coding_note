package java_2018_1;
import java.util.Scanner;

public class WhileSample {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int count = 0, num = 0, sum = 0, result = 0;
		String formula = "";
		
		System.out.print("input: ");
		
		while(true) {
			num = input.nextInt();
			
			if(count == 0) { // ó�� ������ ��, ��ȣ�� �Է¹��� ���� ���ڿ��� ����
				formula += ("(" + num);
			}else if(num == 0) { // �Է��� ������ ������� ���ϰ� ���ڿ��� ���� �� �ݺ��� ����
				result = sum/count;
				formula += (")/" + count + " = " + result);
				break;
			}else { // �� ������ ��� "+ n"���·� ���ڿ��� ����
				formula += ("+" + num);
			}			
			sum += num; // sum ������ �Է¹��� ���� ��� ����
			count++; // �ݺ����� ����� Ƚ��
		}
		
		System.out.println(formula);
		input.close();

	}

}
