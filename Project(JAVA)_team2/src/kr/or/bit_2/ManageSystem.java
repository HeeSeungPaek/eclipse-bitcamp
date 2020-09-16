/*
*@Class : ManageSystem
*@Date : 2020. 09. 13
*@Author : 임소희, 박선우, 임소희, 백희승
*@Desc : UserManager로부터 로그인 후 실행되는 함수를 가진 ManageSystem 객체
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
	private final int TEN_MINUTE = 600;	// 시간 계산 시 사용될 상수 TEN_MINUTE, ONE_HOUR, TO_SEC
	private final int ONE_HOUR = 60;
	private final int TO_SEC = 60000;	// currentMillis / TO_SEC 시 초 단위로 사용할 수 있다.
	
	private Scanner scan = new Scanner(System.in);
	private ArrayList<Transaction> translog = new ArrayList<Transaction>();		// 자전거 대여, 반납 시 발생하는 명세 translog
	private HashMap<String, Bicycle> bicycle = new HashMap<String, Bicycle>();	// 자전거 추가, 제거 시 HashMap을 통해 직렬화, 역직렬화
	private HashMap<Integer, String> review = new HashMap<Integer, String>();
	private HashMap<String, String> report = new HashMap<String, String>();

	private Transaction trans = null;
	private String Review;
	private String Report;
	private String choice;
	private int count = 1;
	private int index = 1000;
	private String temp = null;

	public void showMenuManage(){ // 관리자 기능을 가지고 있는 showMenuManage()
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("┃                  ADMIN MENU                 ┃");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("==============회원 구분을 선택해 주세요.==============");
			System.out.println("1.총 매출 조회  2.자전거 추가  3.자전거 삭제");
			System.out.println("4.전체 회원 조회");
			System.out.println("5.후기 조회  6.고장 신고 조회  7.고장 신고 조치 ");
			System.out.println("8.관리자 메뉴 종료");
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

				System.out.println("관리자 메뉴를 나갑니다.");
				return;

			default:
				System.out.println("유효하지 않은 선택입니다. 다시 입력해 주세요.");
			}
		}
	}

	private void totalIncome() { // 관리자 기능 - 매출조회
		loadTransaction();	// 직렬화된 ArrayList 명세를 역직렬화
		int total = 0;
		for (int i = 0; i < translog.size(); i++) {
			total += translog.get(i).getAmount();
			System.out.println(translog.get(i));
		}
		System.out.println("총 매출은 " + total + "원 입니다.");

	}

	private void loadBicycle() { // 자전거 bicycle.txt 역직렬화 하여 읽기
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

	private void writeBicycle() { // 자전거 추가
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

	private void loadTransaction() { // 거래 명세 읽기

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

	private void writeTransaction() { // 거래 기록쓰기
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

	private void addBicycle() { // 자전거 대수 추가하기 - 기본 10대 생성

		loadBicycle();

		Set set = bicycle.keySet();
		if (!set.contains(Integer.toString(index))) {
			for (int i = 0; i < 10; i++) {
				bicycle.put(Integer.toString(index), new Bicycle());
				bicycle.get(Integer.toString(index)).setIndex(index);
				index++;
			}
		} else {
			System.out.println("이미 자전거가 존재합니다.");
		}

		writeBicycle();

	}

	private void removeBicycle() { // 관리자 기능 - 자전거 삭제 (소희)

		File fileDic = new File("bicycle.txt");
		
		if (!fileDic.exists()) {
			System.out.println("제거 할 자전거가 없습니다.");
		}
		System.out.println("제거 할 자전거 번호를 입력해 주세요");
		String index = scan.nextLine();
		bicycle.remove(index);

		writeBicycle();
	}

	private void showMember() { 
		String userDirectory = "/Users/sunwoo/UserMgData/"; // String 폴더 경로
		File defaultPath = new File(userDirectory); // 경로를 가진 File객체

		File[] userFiles = defaultPath.listFiles(); // 경로 속 파일들을 가지는 File[] userFiles
		String[] userData = null; //

		FileReader fr = null;
		BufferedReader br = null;
		System.out.println("===================회원 정보를 조회합니다===================");
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
					System.out.printf("[%d] [ID : %s] [이름 : %s] [생년월일 : %s] [비밀번호 : %s]\n", (i + 1), id, userData[1],
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

	public void showMenuRental(String id) { // 사용자 프로그램 시작 메뉴
		// case 마다 실행될 수 있는 메소드 들이 밑에 있음
		while (true) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃            마 함 빌리조 자전거 대여 서비스                    ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("==============================================");
			System.out.println(id + "님 환영합니다~ 자전거 대여 메뉴입니다.");
			System.out.println("1.대여 가능한 자전거 조회  2.대여  3.반납  4.후기보기 5.나가기");
			System.out.println(">");
			String input = scan.nextLine();
			switch (input) {
			case "1": // 자전거 조회
				nowBicycle();
				break;
			case "2": // 자전거 대여
				rental(id);
				break;
			case "3": // 자전거 반납
				returnBicycle(id);
				break;
			case "4": // 후기보기
				System.out.println("대여 화면에서 나갑니다.");
				System.exit(0);
			case "5": // 나가기
				System.out.println("대여 화면에서 나갑니다.");
				return;
			default:
				System.out.println("유효하지 않은 선택입니다. 다시 입력해 주세요.");
			}
		}
	}

	private void nowBicycle() {
		// 자전거 조회 - 선우

		loadBicycle();
		Set<String> set = bicycle.keySet();

		for (String str : set) {
			System.out.println(bicycle.get(str));
		}

	}

	private void rental(String id) {

		Set<String> set = bicycle.keySet();
		if (set.contains(id)) {
			System.out.println("여러대의 자전거를 대여할 수 없습니다.");
			return;
		}
		System.out.println("대여하실 자전거 번호를 입력해 주세요.");
		String bNumber = scan.nextLine();
		temp = bNumber;

		if (set.contains(bNumber)) {
			bicycle.put(id, bicycle.get(bNumber));
			bicycle.remove(bNumber);
			bicycle.get(id).setStatus("대여중");

		} else {
			System.out.println("선택하신 자전거는 없는 자전거 입니다.");
			System.out.println("다시 선택해 주세요.");
			return;
		}

		writeBicycle();

		trans = new Transaction(id, "대여", 1000);
		translog.add(trans);
		payForRent(id);

		writeTransaction();

	}

	private void returnBicycle(String id) {

		Set<String> set = bicycle.keySet();
		if (set.contains(id)) {

			bicycle.put(temp, bicycle.get(id));
			bicycle.remove(id);
			bicycle.get(temp).setStatus("대여가능");
			System.out.println("반납이 완료 됐습니다.");

		}

		writeBicycle();
		payForReturn(id);
		
		returnMenu(id);

	}

	private void calculate(int price) {
		System.out.println("금액을 투입해 주십시오");
		int pay = Integer.parseInt(scan.nextLine()); // 내가 내는돈
		while (pay < (price)) {
			System.out.println("잔액이 부족합니다.\n" + ((price) - pay) + "원을 더 투입해 주세요");
			pay += Integer.parseInt(scan.nextLine());
		}

		System.out.println("거스름돈 : " + (pay - price));

	}

	private void payForRent(String id) {

		System.out.println("기본요금 (1시간) 1000원 입니다.");
		calculate(1000);
		System.out.println("연체시 10분당 100원이 추가 됩니다.");
		for (int i = translog.size() - 1; i >= 0; i--) {
			if (id.equals(translog.get(i).getId())) {
				System.out.println("대여하신 정보는 " + translog.get(i) + "입니다.");
				break;
			}
		}

	}

	private void payForReturn(String id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		long time = System.currentTimeMillis();
		int diffMin = 0;
		for (int i = translog.size() - 1; i >= 0; i--) {
			if (id.equals(translog.get(i).getId())) {
				System.out.println("대여하신 정보는 " + translog.get(i) + "입니다.");
				diffMin = (int) (time - translog.get(i).getTimeMili()) / TO_SEC;
				break;
			}
		}
		System.out.println("현재 시각은 " + dateFormat.format(time) + "입니다.");

		if (diffMin > ONE_HOUR) {
			System.out.println(diffMin + "분 사용 하셨습니다. 10분당 100원이 부과됩니다.");
			calculate(diffMin * TEN_MINUTE);
		}

		trans = new Transaction(id, "반납", diffMin * TEN_MINUTE);

		translog.add(trans);

		writeTransaction();

	}

	//////////////////////////////////////////////////////////
	private void returnMenu(String id) {
		while (true) {
			System.out.println("원하시는 메뉴를 선택해 주세요");
			System.out.println("1.후기 작성 2.고장 신고 3. 나가기 ");
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

		System.out.println("리뷰를 적어주세요: ");
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
			System.out.println("에러 발생");
			e.printStackTrace();
		}
		System.out.println("저장되었습니다.");

	}

	private void reportsave(String id) {
		File file = new File("report.txt");

		String num;
		num = Integer.toString(index);

		System.out.println("증상을 적어주세요: ");
		Report = id + "\t" + scan.nextLine();
		report.put(temp, Report);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(report);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
		System.out.println("저장되었습니다.");

	}

	private void reviewload() {

		File file = new File("review.txt");

		if (!file.exists()) {
			System.out.println("저장된 파일이 없습니다.");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);

			review = (HashMap) oos.readObject();
			String you = Review;

			Set<Integer> set = review.keySet();
			System.out.println("번호\t아이디\t내용");
			for (Integer number : set) {
				String message = review.get(number);

				System.out.printf("[%s]\t%s\n", number, message);
			}

			oos.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("불러오는데 실패하였습니다.");
			e.printStackTrace();
		}
	}

	private void reportload() {

		File file = new File("report.txt");

		if (!file.exists()) {
			System.out.println("저장된 파일이 없습니다.");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);

			report = (HashMap) oos.readObject();
			String you = Report;

			Set<String> set = report.keySet();
			System.out.println("자전거번호\t아이디\t내용");
			for (String number : set) {
				String message = report.get(number);

				System.out.printf("[%s]\t%s\n", number, message);
			}

			oos.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("불러오는데 실패하였습니다.");
			e.printStackTrace();
		}
	}
}