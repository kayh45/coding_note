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
			
			if(count == 0) { // 처음 시작할 때, 괄호와 입력받은 값만 문자열에 저장
				formula += ("(" + num);
			}else if(num == 0) { // 입력이 끝나면 결과값을 구하고 문자열에 저장 후 반복문 종료
				result = sum/count;
				formula += (")/" + count + " = " + result);
				break;
			}else { // 그 전에는 계속 "+ n"형태로 문자열에 저장
				formula += ("+" + num);
			}			
			sum += num; // sum 변수에 입력받은 값을 계속 더함
			count++; // 반복문이 실행된 횟수
		}
		
		System.out.println(formula);
		input.close();

	}

}
