/*
*@Class : ManageSystem
*@Date : 2020. 09. 13
*@Author : �Ӽ���, �ڼ���, �Ӽ���, �����
*@Desc : UserManager�κ��� �α��� �� ����Ǵ� �Լ��� ���� ManageSystem ��ü
*/

package kr.or.bit_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ManageSystem {
	private final int TEN_MINUTE = 600;	// �ð� ��� �� ���� ��� TEN_MINUTE, ONE_HOUR, TO_SEC
	private final int ONE_HOUR = 60;
	private final int TO_SEC = 60000;	// currentMillis / TO_SEC �� �� ������ ����� �� �ִ�.
	
	private Scanner scan = new Scanner(System.in);
	private ArrayList<Transaction> translog = new ArrayList<Transaction>();		// ������ �뿩, �ݳ� �� �߻��ϴ� �� translog
	private HashMap<String, Bicycle> bicycle = new HashMap<String, Bicycle>();	// ������ �߰�, ���� �� HashMap�� ���� ����ȭ, ������ȭ
	private HashMap<Integer, String> review = new HashMap<Integer, String>();
	private HashMap<String, String> report = new HashMap<String, String>();

	private Transaction trans = null;
	private String Review;
	private String Report;
	private String choice;
	private int count = 1;
	private int index = 1000;
	private String temp = null;

	public void showMenuManage(){ // ������ ����� ������ �ִ� showMenuManage()
		while (true) {
			System.out.println("���������������������������");
			System.out.println("��                  ADMIN MENU                 ��");
			System.out.println("���������������������������");
			System.out.println("==============ȸ�� ������ ������ �ּ���.==============");
			System.out.println("1.�� ���� ��ȸ  2.������ �߰�  3.������ ����");
			System.out.println("4.��ü ȸ�� ��ȸ");
			System.out.println("5.�ı� ��ȸ  6.���� �Ű� ��ȸ  7.���� �Ű� ��ġ ");
			System.out.println("8.������ �޴� ����");
			System.out.print(">");
			String input = scan.nextLine();
			switch (input) {
			case "1":
				totalIncome();
				break;
			case "2":
				addBicycle();
				break;
			case "3":
				removeBicycle();
				break;
			case "4":
				showMember();
				break;
			case "5":
				reviewload();
				break;
			case "6":
				reportload();
				break;
			case "7":
				break;
			case "8":

				System.out.println("������ �޴��� �����ϴ�.");
				return;

			default:
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է��� �ּ���.");
			}
		}
	}

	private void totalIncome() { // ������ ��� - ������ȸ
		loadTransaction();	// ����ȭ�� ArrayList ���� ������ȭ
		int total = 0;
		for (int i = 0; i < translog.size(); i++) {
			total += translog.get(i).getAmount();
			System.out.println(translog.get(i));
		}
		System.out.println("�� ������ " + total + "�� �Դϴ�.");

	}

	private void loadBicycle() { // ������ bicycle.txt ������ȭ �Ͽ� �б�
		File fileDic = new File("bicycle.txt");;

		FileInputStream fis = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileDic);
			ois = new ObjectInputStream(fis);

			bicycle = (HashMap) ois.readObject();

			ois.close();
			fis.close();

		} catch (Exception e) {
			return;

		}
	}

	private void writeBicycle() { // ������ �߰�
		File fileDic = new File("bicycle.txt");
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileDic);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(bicycle);

			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadTransaction() { // �ŷ� �� �б�

		File fileDic = new File("Transaction.txt");

		try {
			FileInputStream fis = new FileInputStream(fileDic);
			ObjectInputStream oos = new ObjectInputStream(fis);

			translog = (ArrayList) oos.readObject();

			oos.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void writeTransaction() { // �ŷ� ��Ͼ���
		File fileDic = new File("Transaction.txt");

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileDic);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(translog);

			oos.close();
			// bos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addBicycle() { // ������ ��� �߰��ϱ� - �⺻ 10�� ����

		loadBicycle();

		Set set = bicycle.keySet();
		if (!set.contains(Integer.toString(index))) {
			for (int i = 0; i < 10; i++) {
				bicycle.put(Integer.toString(index), new Bicycle());
				bicycle.get(Integer.toString(index)).setIndex(index);
				index++;
			}
		} else {
			System.out.println("�̹� �����Ű� �����մϴ�.");
		}

		writeBicycle();

	}

	private void removeBicycle() { // ������ ��� - ������ ���� (����)

		File fileDic = new File("bicycle.txt");
		
		if (!fileDic.exists()) {
			System.out.println("���� �� �����Ű� �����ϴ�.");
		}
		System.out.println("���� �� ������ ��ȣ�� �Է��� �ּ���");
		String index = scan.nextLine();
		bicycle.remove(index);

		writeBicycle();
	}

	private void showMember() { 
		String userDirectory = "/Users/sunwoo/UserMgData/"; // String ���� ���
		File defaultPath = new File(userDirectory); // ��θ� ���� File��ü

		File[] userFiles = defaultPath.listFiles(); // ��� �� ���ϵ��� ������ File[] userFiles
		String[] userData = null; //

		FileReader fr = null;
		BufferedReader br = null;
		System.out.println("===================ȸ�� ������ ��ȸ�մϴ�===================");
		try {
			for (int i = 0; i < userFiles.length; i++) {
				fr = new FileReader(userFiles[i]);
				br = new BufferedReader(fr);
				if (!userFiles[i].isDirectory()) {
					File file = new File(userFiles[i].toString());

					String id = file.getName().replace(".txt", "");

					String line = "";
					try {
						for (int j = 0; (line = br.readLine()) != null; j++) {
							userData = line.split(":");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.printf("[%d] [ID : %s] [�̸� : %s] [������� : %s] [��й�ȣ : %s]\n", (i + 1), id, userData[1],
							userData[2], userData[0]);
				}
			}
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("====================================================");
	}

	////////////////////////////////////////////////////////////// Rental////////////////////

	public void showMenuRental(String id) { // ����� ���α׷� ���� �޴�
		// case ���� ����� �� �ִ� �޼ҵ� ���� �ؿ� ����
		while (true) {
			System.out.println("��������������������������������������������������������������������������������������������");
			System.out.println("��            �� �� ������ ������ �뿩 ����                    ��");
			System.out.println("��������������������������������������������������������������������������������������������");
			System.out.println("==============================================");
			System.out.println(id + "�� ȯ���մϴ�~ ������ �뿩 �޴��Դϴ�.");
			System.out.println("1.�뿩 ������ ������ ��ȸ  2.�뿩  3.�ݳ�  4.�ı⺸�� 5.������");
			System.out.println(">");
			String input = scan.nextLine();
			switch (input) {
			case "1": // ������ ��ȸ
				nowBicycle();
				break;
			case "2": // ������ �뿩
				rental(id);
				break;
			case "3": // ������ �ݳ�
				returnBicycle(id);
				break;
			case "4": // �ı⺸��
				System.out.println("�뿩 ȭ�鿡�� �����ϴ�.");
				System.exit(0);
			case "5": // ������
				System.out.println("�뿩 ȭ�鿡�� �����ϴ�.");
				return;
			default:
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է��� �ּ���.");
			}
		}
	}

	private void nowBicycle() {
		// ������ ��ȸ - ����

		loadBicycle();
		Set<String> set = bicycle.keySet();

		for (String str : set) {
			System.out.println(bicycle.get(str));
		}

	}

	private void rental(String id) {

		Set<String> set = bicycle.keySet();
		if (set.contains(id)) {
			System.out.println("�������� �����Ÿ� �뿩�� �� �����ϴ�.");
			return;
		}
		System.out.println("�뿩�Ͻ� ������ ��ȣ�� �Է��� �ּ���.");
		String bNumber = scan.nextLine();
		temp = bNumber;

		if (set.contains(bNumber)) {
			bicycle.put(id, bicycle.get(bNumber));
			bicycle.remove(bNumber);
			bicycle.get(id).setStatus("�뿩��");

		} else {
			System.out.println("�����Ͻ� �����Ŵ� ���� ������ �Դϴ�.");
			System.out.println("�ٽ� ������ �ּ���.");
			return;
		}

		writeBicycle();

		trans = new Transaction(id, "�뿩", 1000);
		translog.add(trans);
		payForRent(id);

		writeTransaction();

	}

	private void returnBicycle(String id) {

		Set<String> set = bicycle.keySet();
		if (set.contains(id)) {

			bicycle.put(temp, bicycle.get(id));
			bicycle.remove(id);
			bicycle.get(temp).setStatus("�뿩����");
			System.out.println("�ݳ��� �Ϸ� �ƽ��ϴ�.");

		}

		writeBicycle();
		payForReturn(id);
		
		returnMenu(id);

	}

	private void calculate(int price) {
		System.out.println("�ݾ��� ������ �ֽʽÿ�");
		int pay = Integer.parseInt(scan.nextLine()); // ���� ���µ�
		while (pay < (price)) {
			System.out.println("�ܾ��� �����մϴ�.\n" + ((price) - pay) + "���� �� ������ �ּ���");
			pay += Integer.parseInt(scan.nextLine());
		}

		System.out.println("�Ž����� : " + (pay - price));

	}

	private void payForRent(String id) {

		System.out.println("�⺻��� (1�ð�) 1000�� �Դϴ�.");
		calculate(1000);
		System.out.println("��ü�� 10�д� 100���� �߰� �˴ϴ�.");
		for (int i = translog.size() - 1; i >= 0; i--) {
			if (id.equals(translog.get(i).getId())) {
				System.out.println("�뿩�Ͻ� ������ " + translog.get(i) + "�Դϴ�.");
				break;
			}
		}

	}

	private void payForReturn(String id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd�� HH:mm:ss");
		long time = System.currentTimeMillis();
		int diffMin = 0;
		for (int i = translog.size() - 1; i >= 0; i--) {
			if (id.equals(translog.get(i).getId())) {
				System.out.println("�뿩�Ͻ� ������ " + translog.get(i) + "�Դϴ�.");
				diffMin = (int) (time - translog.get(i).getTimeMili()) / TO_SEC;
				break;
			}
		}
		System.out.println("���� �ð��� " + dateFormat.format(time) + "�Դϴ�.");

		if (diffMin > ONE_HOUR) {
			System.out.println(diffMin + "�� ��� �ϼ̽��ϴ�. 10�д� 100���� �ΰ��˴ϴ�.");
			calculate(diffMin * TEN_MINUTE);
		}

		trans = new Transaction(id, "�ݳ�", diffMin * TEN_MINUTE);

		translog.add(trans);

		writeTransaction();

	}

	//////////////////////////////////////////////////////////
	private void returnMenu(String id) {
		while (true) {
			System.out.println("���Ͻô� �޴��� ������ �ּ���");
			System.out.println("1.�ı� �ۼ� 2.���� �Ű� 3. ������ ");
			choice = scan.nextLine();
			switch (choice) {
			case "1":
				reviewsave(id);
				break;
			case "2":
				reportsave(id);
				break;
			default:
				System.exit(0);

			}
		}
	}

	private void reviewsave(String id) {

		File file = new File("review.txt");

		System.out.println("���並 �����ּ���: ");
		Review = id + "\t" + scan.nextLine();

		review.put(count, Review);

		count++;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(review);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("���� �߻�");
			e.printStackTrace();
		}
		System.out.println("����Ǿ����ϴ�.");

	}

	private void reportsave(String id) {
		File file = new File("report.txt");

		String num;
		num = Integer.toString(index);

		System.out.println("������ �����ּ���: ");
		Report = id + "\t" + scan.nextLine();
		report.put(temp, Report);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(report);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("���� �߻�");
			e.printStackTrace();
		}
		System.out.println("����Ǿ����ϴ�.");

	}

	private void reviewload() {

		File file = new File("review.txt");

		if (!file.exists()) {
			System.out.println("����� ������ �����ϴ�.");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);

			review = (HashMap) oos.readObject();
			String you = Review;

			Set<Integer> set = review.keySet();
			System.out.println("��ȣ\t���̵�\t����");
			for (Integer number : set) {
				String message = review.get(number);

				System.out.printf("[%s]\t%s\n", number, message);
			}

			oos.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("�ҷ����µ� �����Ͽ����ϴ�.");
			e.printStackTrace();
		}
	}

	private void reportload() {

		File file = new File("report.txt");

		if (!file.exists()) {
			System.out.println("����� ������ �����ϴ�.");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);

			report = (HashMap) oos.readObject();
			String you = Report;

			Set<String> set = report.keySet();
			System.out.println("�����Ź�ȣ\t���̵�\t����");
			for (String number : set) {
				String message = report.get(number);

				System.out.printf("[%s]\t%s\n", number, message);
			}

			oos.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("�ҷ����µ� �����Ͽ����ϴ�.");
			e.printStackTrace();
		}
	}
}