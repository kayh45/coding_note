package dongari;
import java.util.Scanner;

public class Week4_3 {
	public static void main(String[] args){
		
		System.out.println("=== ������ ===");

		
		for(int i = 2; i <= 9; i++) {
			System.out.println("===" + i + "��===");
			for(int j = 1; j <= 9; j++){
				System.out.println(i + " X " + j + " = " + i*j);
			}				
		}
	}
}
