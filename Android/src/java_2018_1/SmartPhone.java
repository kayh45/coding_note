package java_2018_1;
import java.util.Scanner;

public class SmartPhone {

	Scanner input = new Scanner(System.in);
	String phone_number;
	String telecom;
	double battery_rate;
	int memory_remain;
	double screen_bright;

	// 모든 정보를 출력하는 메소드
	public void viewDetail() { 
		System.out.println("폰 번호 : " + phone_number);
		System.out.println("통신사 : " + telecom);
		System.out.println("배터리 충전 정도 : " + (int) battery_rate + "%");
		System.out.println("메모리 잔량 : " + memory_remain + "MB");
		System.out.println("화면 밝기 : " + (screen_bright * 10));
	}

	// 화면 밝기를 설정하는 메소드
	public void setBright() {

		System.out.println("화면 밝기를 선택하세요(1~10)");
		int bright = input.nextInt();

		if (bright >= 1 || bright <= 10) {
			System.out.println("화면 밝기가 " + bright + "로 설정되었습니다.");
			screen_bright = bright * 0.1;
		} else {
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
	}

	// 통신사를 바꾸는 메소드
	public void telecomChange() {

		System.out.println("통신사를 선택해주세요(SKT, KT, LGT 중 선택)");

		String changedTelecom = input.next();
		String beforeTelecom = telecom;
		if (changedTelecom.equals(telecom)) {
			System.out.println("이미 " + telecom + " 통신사를 이용하고 있습니다.");

		} else {
			switch (changedTelecom) {
			case "SKT":
				telecom = "SKT";
				break;
			case "KT":
				telecom = "KT";
				break;
			case "LGT":
				telecom = "LGT";
				break;
			default:
				System.out.println("잘못된 값을 입력하셨습니다.");
			}
			System.out.println(beforeTelecom + "->" + changedTelecom);
		}
	}

	// 배터리를 충전하는 메소드
	public void batteryCharge() {

		System.out.println("충전시간을 입력해주세요");

		double time = input.nextDouble();
		double charging_rate = time * 30;

		System.out.print(battery_rate + "% -> ");
		battery_rate += charging_rate;

		if (battery_rate > 100) {
			battery_rate = 100;
		}

		System.out.println(battery_rate + "%");

	}

	// 사진을 찍는 메소드
	public void takePicture() {
		System.out.println("사진을 찍었습니다.");
		int after_memory = memory_remain - 5;
		if (after_memory < 0) {
			System.out.println("용량이 부족합니다.");
			System.out.println("남은 용량: " + memory_remain + "MB");
		} else {
			memory_remain -= 5;
			System.out.println("남은 용량: " + memory_remain + "MB");
		}
	}


	// 사진을 삭제하는 메소드
	public void deletePicture() {
		System.out.println("사진을 지웠습니다.");
		memory_remain += 5;
		System.out.println("남은 용량: " + memory_remain + "MB");
	}

	// 메인메소드
	public static void main(String[] args) {

		SmartPhone myPhone = new SmartPhone(); // myPhome 인스턴스를 생성
		int selected = 0; // 기능 선택에 사용하는 변수 선언 및 초기화

		// myPhone의 모든 인스턴수 변수를 초기화
		myPhone.phone_number = "010-1234-5678";
		myPhone.telecom = "SKT";
		myPhone.battery_rate = 12.0;
		myPhone.memory_remain = 13;
		myPhone.screen_bright = 0.5;

		myPhone.viewDetail(); // 모든 정보를 출력
		System.out.println("===============================");

		// 기능을 선택하는 반복문, 0을 입력하면 종료
		for (;;) {

			System.out.println("기능을 선택하세요(0~5), 현재 배터리 잔량: " + (int)myPhone.battery_rate + "%");
			System.out.println("1. 통신사 변경");
			System.out.println("2. 배터리 충전");
			System.out.println("3. 사진 촬영");
			System.out.println("4. 사진 삭제");
			System.out.println("5. 화면 밝기 변경");
			System.out.println("0. 종료");

			// 기능 선택을 입력받는 scanner 실행
			selected = myPhone.input.nextInt();
			System.out.println("===============================");

			// 선택된 기능으로 분기하여 각 기능의 메소드 호출
			switch (selected) {
			case 1:
				myPhone.telecomChange();
				break;
			case 2:
				myPhone.batteryCharge();
				break;
			case 3:
				myPhone.takePicture();
				break;
			case 4:
				myPhone.deletePicture();
				break;
			case 5:
				myPhone.setBright();
				break;
			case 0:
				break;
				
			default: // 범위 밖의 값을 입력받으면 에러를 출력하고 종료
				System.out.println("잘못된 값을 입력하셨습니다.");
				break;
			}

			// 화면 밝기에 비례하여 기능을 실행할 때마다 배터리 잔량을 소모
			
			myPhone.battery_rate -= (10 * myPhone.screen_bright);
			if(myPhone.battery_rate <= 0) {
				myPhone.battery_rate = 0;
				System.out.println("===============================");
				System.out.println("배터리가 부족하여 시스템을 종료합니다."); 
				System.out.println("===============================");
				break;
			}
			
			if (selected == 0) { // 입력받은 값이 0이면 반복문 종료
				break;
			}

			System.out.println("===============================");

		}

		// 마지막으로 모든 정보를 출력
		myPhone.viewDetail();
	}
}
