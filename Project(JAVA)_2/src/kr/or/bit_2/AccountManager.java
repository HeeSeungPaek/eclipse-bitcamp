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

	public void showMenu() { // 메뉴 출력
		System.out.println("==========마 함 빌리조 : 자전거 대여 프로그램===========");
		System.out.println("회원 구분을 선택해 주세요.");
		System.out.println("1.회원가입    2. 로그인    3. 회원정보 로드   4. 프로그램 종료");
	}

	public void startLogIn() { // 로그인 시작
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
				System.out.println("유효하지 않은 선택입니다. 다시 입력해 주세요.");
			}
		}
	}

	public void makeAccount() { // 회원가입 > 조건 충족 시 회원가입 완료 + UserInfo.txt
		// 회원가입 전, 파일에서 회원정보를 불러와 memberList에 할당

		System.out.println("회원가입을 선택하셨습니다.");

		System.out.println("사용자 이름 : ");
		String userName = scan.nextLine();

		System.out.println("사용자 생년월일 : ");
		String birth = scan.nextLine();

		System.out.println("사용자 아이디 : ");
		String id = scan.nextLine().trim().toLowerCase();

		while (true) {
			if (set.contains(id)) {
				System.out.printf("이미 존재하는 아이디 입니다./n 다시 작성해 주세요.");
				id = scan.nextLine().trim().toLowerCase();
			} else {
				break;
			}
		}

		System.out.println("사용자 비밀번호 : ");
		String password = scan.nextLine();

		// 아이디, 생년월일, 비밀번호 등 정규표현식 조건 확인 삽입 예정

		// 조건에 부합하다면.

		memberList.put(id, new UserInfo(id, password, userName, birth));
		// 직렬화인지? 그냥 map을 쓰는 건지? 만약 여기서 이런식으로 진행을 한다면, startLogin() 에서 써논걸 읽어와서 맵에다가
		// 다시넣어줘야함.
		saveFile(); // id,회원정보를 가진 map을 직렬화하여 파일로 저장 -> UserInfo.txt
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void logIn() {
		System.out.println("로그인");
		System.out.print("아이디 > ");
		String id = scan.nextLine().trim().toLowerCase();
		System.out.print("비밀번호 > ");
		String password = scan.nextLine();

		if (admin.getId().equalsIgnoreCase(id) && admin.getPassword().equals(password)) {
			System.out.println("관리자 로그인 완료");
			// 관리자 함수 시작
		} else if (!set.contains(id)) {
			System.out.println("존재하지 않는 아이디 입니다. 다시 입력해 주세요.");
		} else {
			if (memberList.get(id).getPassword().equals(password)) {
				System.out.println("로그인 성공!");
				ms.showMenuRental(id);
			} else {
				System.out.println("패스워드가 다릅니다.");
			}
		}

	}

	private void saveFile() { // 저장
		try {
			fos = new FileOutputStream(file, true); // 추가하여 저장한다? append
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(memberList); // 우리가 만든 memberList를 파일로 직렬화하여 저장한다. 언제? 회원가입이 완료 시

		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
			e.printStackTrace();
		} catch (IOException e2) {
			System.out.println("파일 저장 중 예외 발생");
			e2.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println("파일 저장 후 닫기 중 예외 발생");
				e.printStackTrace();
			}
		}
	}

	private void loadFile() { // 로드 미완성 >> 로그인 시에 읽어오기 맞죠~
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
