package java_2018_1;
import java.util.Scanner;

public class SmartPhone {

	Scanner input = new Scanner(System.in);
	String phone_number;
	String telecom;
	double battery_rate;
	int memory_remain;
	double screen_bright;

	// ��� ������ ����ϴ� �޼ҵ�
	public void viewDetail() { 
		System.out.println("�� ��ȣ : " + phone_number);
		System.out.println("��Ż� : " + telecom);
		System.out.println("���͸� ���� ���� : " + (int) battery_rate + "%");
		System.out.println("�޸� �ܷ� : " + memory_remain + "MB");
		System.out.println("ȭ�� ��� : " + (screen_bright * 10));
	}

	// ȭ�� ��⸦ �����ϴ� �޼ҵ�
	public void setBright() {

		System.out.println("ȭ�� ��⸦ �����ϼ���(1~10)");
		int bright = input.nextInt();

		if (bright >= 1 || bright <= 10) {
			System.out.println("ȭ�� ��Ⱑ " + bright + "�� �����Ǿ����ϴ�.");
			screen_bright = bright * 0.1;
		} else {
			System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
		}
	}

	// ��Ż縦 �ٲٴ� �޼ҵ�
	public void telecomChange() {

		System.out.println("��Ż縦 �������ּ���(SKT, KT, LGT �� ����)");

		String changedTelecom = input.next();
		String beforeTelecom = telecom;
		if (changedTelecom.equals(telecom)) {
			System.out.println("�̹� " + telecom + " ��Ż縦 �̿��ϰ� �ֽ��ϴ�.");

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
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
			}
			System.out.println(beforeTelecom + "->" + changedTelecom);
		}
	}

	// ���͸��� �����ϴ� �޼ҵ�
	public void batteryCharge() {

		System.out.println("�����ð��� �Է����ּ���");

		double time = input.nextDouble();
		double charging_rate = time * 30;

		System.out.print(battery_rate + "% -> ");
		battery_rate += charging_rate;

		if (battery_rate > 100) {
			battery_rate = 100;
		}

		System.out.println(battery_rate + "%");

	}

	// ������ ��� �޼ҵ�
	public void takePicture() {
		System.out.println("������ ������ϴ�.");
		int after_memory = memory_remain - 5;
		if (after_memory < 0) {
			System.out.println("�뷮�� �����մϴ�.");
			System.out.println("���� �뷮: " + memory_remain + "MB");
		} else {
			memory_remain -= 5;
			System.out.println("���� �뷮: " + memory_remain + "MB");
		}
	}


	// ������ �����ϴ� �޼ҵ�
	public void deletePicture() {
		System.out.println("������ �������ϴ�.");
		memory_remain += 5;
		System.out.println("���� �뷮: " + memory_remain + "MB");
	}

	// ���θ޼ҵ�
	public static void main(String[] args) {

		SmartPhone myPhone = new SmartPhone(); // myPhome �ν��Ͻ��� ����
		int selected = 0; // ��� ���ÿ� ����ϴ� ���� ���� �� �ʱ�ȭ

		// myPhone�� ��� �ν��ϼ� ������ �ʱ�ȭ
		myPhone.phone_number = "010-1234-5678";
		myPhone.telecom = "SKT";
		myPhone.battery_rate = 12.0;
		myPhone.memory_remain = 13;
		myPhone.screen_bright = 0.5;

		myPhone.viewDetail(); // ��� ������ ���
		System.out.println("===============================");

		// ����� �����ϴ� �ݺ���, 0�� �Է��ϸ� ����
		for (;;) {

			System.out.println("����� �����ϼ���(0~5), ���� ���͸� �ܷ�: " + (int)myPhone.battery_rate + "%");
			System.out.println("1. ��Ż� ����");
			System.out.println("2. ���͸� ����");
			System.out.println("3. ���� �Կ�");
			System.out.println("4. ���� ����");
			System.out.println("5. ȭ�� ��� ����");
			System.out.println("0. ����");

			// ��� ������ �Է¹޴� scanner ����
			selected = myPhone.input.nextInt();
			System.out.println("===============================");

			// ���õ� ������� �б��Ͽ� �� ����� �޼ҵ� ȣ��
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
				
			default: // ���� ���� ���� �Է¹����� ������ ����ϰ� ����
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
				break;
			}

			// ȭ�� ��⿡ ����Ͽ� ����� ������ ������ ���͸� �ܷ��� �Ҹ�
			
			myPhone.battery_rate -= (10 * myPhone.screen_bright);
			if(myPhone.battery_rate <= 0) {
				myPhone.battery_rate = 0;
				System.out.println("===============================");
				System.out.println("���͸��� �����Ͽ� �ý����� �����մϴ�."); 
				System.out.println("===============================");
				break;
			}
			
			if (selected == 0) { // �Է¹��� ���� 0�̸� �ݺ��� ����
				break;
			}

			System.out.println("===============================");

		}

		// ���������� ��� ������ ���
		myPhone.viewDetail();
	}
}
