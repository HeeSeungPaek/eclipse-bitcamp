package kr.or.bit_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class ManageSystem {

	private HashMap<String, Bicycle> bicycle = new HashMap<String, Bicycle>();
	private int index = 1000;
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	ObjectInputStream ois = null;
	String file;
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;

	public void showMenuManage(String id) {	// ������ ���α׷� ���� �޴� -> �������� ������
		// ������ ���α׷� �Դϴ� ��� �Ͻðڽ��ϱ�?
		// 1�� ������ȸ 2�� ������ �߰� 3�� ������ ����.....
	}

	private void totalIncome() {	// ������ ��� - ������ȸ
		// �Ѹ��� ��ȸ
		// ���� �ý��ۿ��� ����� ������ �ҷ����� (�����б�)
	}

	private void addBicycle() {		// ������ ��� - ������ �߰�
		// ������ �߰�
		bicycle.put(Integer.toString(index), new Bicycle());
		Bicycle.count++; // ���⼭ ī��Ʈ�� �������� ������� ������� ���
		index++;

	}

	private void removeBicycle() {	// ������ ��� - ������ ����
		// ������ ���� ������ ���� �ɷ��̴�´ٸ� �ٸ�������� ��
		bicycle.remove(Integer.toString(index));
		Bicycle.count--;
	}

	public void showMember() {		// ������ ��� - ȸ������ ��ȸ
		// ȸ������ ��ȸ
		// user���� ȸ������ �Ҷ� ��ϵ� ������ �о� ���� //Member field ������ȭ
		try {
			file = "/User/sunwoo/Desktop/userInfo.txt";
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			Object users = null;

			while ((users = ois.readObject()) != null) {
				System.out.println(((UserInfo) users).toString()); // ĳ����

			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} catch (EOFException eofe) {
			System.out.println("��" + eofe.getMessage());
			eofe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("������ ���� �� �����ϴ�.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		} finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void notice() {		// ������ ��� - �Ű���� ����
		// �Ű���ġ ���� ����
	}
	// �ı���ȸ�� Rental �� ���� �ƴϸ� �׳� ���⼭ �� ������

	
	////////////////////////////////////////////////////////////// Rental////////////////////

	public void showMenuRental(String id) {	// ����� ���α׷� ���� �޴�
		// case ���� ����� �� �ִ� �޼ҵ� ���� �ؿ� ����
		while(true) {
		System.out.println("=========================");
		System.out.println(" �� �� �� �� �� �� �� �� �� �� ");
		System.out.println("=========================");
		nowBicycle();
		System.out.println("=========================");
		System.out.println("1.�뿩 2.�ݳ� 3.�ı⺸�� ");
		System.out.println("=========================");
		rental(id);
		}
	}

	private void nowBicycle() {		// ����� ��� - �뿩������ ������ ��ȸ
		// ������ ��ȸ - ����
		// String id = ���� id ;
		Set set = bicycle.keySet();
		for (int i = 0; i < bicycle.size(); i++) {
			System.out.printf("[%s]", set.contains(Integer.toString(i)) ? index + "�� ������" : "�뿩��");
		}

	}

	private void rental(String id) {	// ����� ��� - ������ �뿩
		// ������ �뿩 -����
		// ��ȣ ������
		System.out.println("�뿩������ ������ ��ȣ�� ������ �ֽʽÿ�.");
		String number = AccountManager.scan.nextLine();
		Set set = bicycle.keySet();
		for (int i = 0; i < bicycle.size(); i++) {
			if (set.contains(number)) {
				bicycle.replace(id, bicycle.get(number));
			}
		}
		int key = 1;
		pay(key);
		// index ��ȣ�� ��ġ�ϸ� �뿩�� �ٲٱ�
		// �αױ�� �ۼ�
		file = "/Users/sunwoo/Desktop/log.txt";
	}

	private int returnBicycle() {	// ����� ��� - ������ �ݳ�
		// ������ �ݳ� -����
		return 0; // index ��ȣ�� ��ġ�ϸ� �뿩�������� �ٲٱ�
		// �αױ�� �ۼ�
	}

	// �ı� �ۼ� ��ȸ�� ����Ʈ�� ����ͼ�

	private void report() throws Exception {	// ����� ��� - �ݳ� �� ���� �Ű�
		// ����Ű� - ���� ���� ����
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyy��MM��dd��HH��mm��");
		String record = date.format(cal.getTime());
		// boolean fileExist;
		File asFolder = new File("C://����Ű�//");
		System.out.println("���峭 �������� ��ȣ�� �����ּ���: ");
		String Bicyclenum = AccountManager.scan.nextLine();
		File asFile = new File("C://����Ű�//" + Bicyclenum + "_" + record + ".txt");
		// if (!asFile.exists()) {
		System.out.println("������ �����ּ���: ");
		String symptom = AccountManager.scan.nextLine();
		System.out.println("����Ű� ���������� ó���Ǿ����ϴ�.");

		if (!asFolder.exists())
			asFolder.mkdir();
		FileWriter asFileWriter = new FileWriter(asFile);
		BufferedWriter bw = new BufferedWriter(asFileWriter);
		bw.write(record + "\n" + "�����Ź�ȣ:  " + Bicyclenum + "_" + "���峻��: " + symptom);

		bw.close();
		// }
	}

	private void calculate(int number) {	
		int price = 1000;
		System.out.println(number + "�ð����� " + (number * price) + "���Դϴ�.");
		System.out.println("�ݾ��� ������ �ֽʽÿ�");
		int pay = Integer.parseInt(AccountManager.scan.nextLine());
		while (pay < (number * price)) {
			System.out.println("�ܾ��� �����մϴ�.\n" + ((number * price) - pay) + "���� �� ������ �ּ���");
			pay += Integer.parseInt(AccountManager.scan.nextLine());
		}
		System.out.println("�Ž����� : " + (pay - (number * price)));
	}

	private void pay(int key) {
		// ���� - ����
		// �뿩�� �ݳ��̳�? ������ �޶����
		// �ŷ���Ͽ� �ۼ��ϱ�.
		if (key == 1) {
			System.out.println("============����ǥ============");
			System.out.println(" 1.1�ð��� 2.3�ð��� 3.6�ð��� ");
			int choice = Integer.parseInt(AccountManager.scan.nextLine());
			int number = 0;
			while (true) {
				if (choice == 1) {
					number = 1;
					calculate(number);
					break;
				} else if (choice == 2) {
					number = 3;
					calculate(number);
					break;
				} else if (choice == 3) {
					number = 6;
					calculate(number);
					break;
				} else {
					System.out.println("�ٽ� �Է��� �ּ���.");
					System.out.println(" 1.1�ð��� 2.3�ð��� 3.6�ð��� ");
					choice = Integer.parseInt(AccountManager.scan.nextLine());
				}
			}
		} else {
			// �ݳ�
		}

	}
}
