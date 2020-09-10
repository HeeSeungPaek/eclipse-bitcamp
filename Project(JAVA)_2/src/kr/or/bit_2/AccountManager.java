package kr.or.bit_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class AccountManager {

	private Admin admin;
	private HashMap<String, UserInfo> memberList;
	private Set<String> set;
	private ManageSystem ms;
	private String filename;
	private File file;

	private FileOutputStream fos;
	private FileInputStream fis;
	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private FileWriter fw;
	private BufferedWriter bw;

	static Scanner scan = new Scanner(System.in);

	public AccountManager() {
		this.memberList = new HashMap<String, UserInfo>();
		set = memberList.keySet();
		ms = new ManageSystem();
		admin = Admin.getInstance();
		this.filename = "userInfo.txt";
		this.file = new File(filename);

		this.fos = null;
		this.fis = null;
		this.bos = null;
		this.bis = null;
		this.ois = null;
		this.oos = null;
		this.fw = null;
		this.bw = null;
	}

	public void showMenu() { // �޴� ���
		System.out.println("==========�� �� ������ : ������ �뿩 ���α׷�===========");
		System.out.println("ȸ�� ������ ������ �ּ���.");
		System.out.println("1.ȸ������    2. �α���    3. ȸ������ �ε�   4. ���α׷� ����");
	}

	public void startLogIn() { // �α��� ����
		while (true) {
			showMenu();
			loadFile();
			String input = scan.nextLine();
			switch (input) {
			case "1":
				makeAccount();
				break;
			case "2":
				logIn();
				break;
			case "3":
				System.exit(0);
			default:
				System.out.println("��ȿ���� ���� �����Դϴ�. �ٽ� �Է��� �ּ���.");
			}
		}
	}

	public void makeAccount() { // ȸ������ > ���� ���� �� ȸ������ �Ϸ� + UserInfo.txt
		// ȸ������ ��, ���Ͽ��� ȸ�������� �ҷ��� memberList�� �Ҵ�

		System.out.println("ȸ�������� �����ϼ̽��ϴ�.");

		System.out.println("����� �̸� : ");
		String userName = scan.nextLine();

		System.out.println("����� ������� : ");
		String birth = scan.nextLine();

		System.out.println("����� ���̵� : ");
		String id = scan.nextLine().trim().toLowerCase();

		while (true) {
			if (set.contains(id)) {
				System.out.printf("�̹� �����ϴ� ���̵� �Դϴ�./n �ٽ� �ۼ��� �ּ���.");
				id = scan.nextLine().trim().toLowerCase();
			} else {
				break;
			}
		}

		System.out.println("����� ��й�ȣ : ");
		String password = scan.nextLine();

		// ���̵�, �������, ��й�ȣ �� ����ǥ���� ���� Ȯ�� ���� ����

		// ���ǿ� �����ϴٸ�.

		memberList.put(id, new UserInfo(id, password, userName, birth));
		// ����ȭ����? �׳� map�� ���� ����? ���� ���⼭ �̷������� ������ �Ѵٸ�, startLogin() ���� ���� �о�ͼ� �ʿ��ٰ�
		// �ٽó־������.
		saveFile(); // id,ȸ�������� ���� map�� ����ȭ�Ͽ� ���Ϸ� ���� -> UserInfo.txt
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}

	public void logIn() {
		System.out.println("�α���");
		System.out.print("���̵� > ");
		String id = scan.nextLine().trim().toLowerCase();
		System.out.print("��й�ȣ > ");
		String password = scan.nextLine();

		if (admin.getId().equalsIgnoreCase(id) && admin.getPassword().equals(password)) {
			System.out.println("������ �α��� �Ϸ�");
			// ������ �Լ� ����
		} else if (!set.contains(id)) {
			System.out.println("�������� �ʴ� ���̵� �Դϴ�. �ٽ� �Է��� �ּ���.");
		} else {
			if (memberList.get(id).getPassword().equals(password)) {
				System.out.println("�α��� ����!");
				ms.showMenuRental(id);
			} else {
				System.out.println("�н����尡 �ٸ��ϴ�.");
			}
		}

	}

	private void saveFile() { // ����
		try {
			fos = new FileOutputStream(file, true); // �߰��Ͽ� �����Ѵ�? append
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(memberList); // �츮�� ���� memberList�� ���Ϸ� ����ȭ�Ͽ� �����Ѵ�. ����? ȸ�������� �Ϸ� ��

		} catch (FileNotFoundException e) {
			System.out.println("���� ����");
			e.printStackTrace();
		} catch (IOException e2) {
			System.out.println("���� ���� �� ���� �߻�");
			e2.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println("���� ���� �� �ݱ� �� ���� �߻�");
				e.printStackTrace();
			}
		}
	}

	private void loadFile() { // �ε� �̿ϼ� >> �α��� �ÿ� �о���� ����~
		if(!file.exists()) {	// file = userInfo.txt
			try {
				fos = new FileOutputStream(file);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);

			memberList = (HashMap) ois.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				System.out.println("2." + e.getMessage());
				e.printStackTrace();
			}
			
		}

	}

}
