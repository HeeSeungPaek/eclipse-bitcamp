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

	public void showMenuManage(String id) {	// 관리자 프로그램 시작 메뉴 -> 동연이형 만들어보기
		// 관리자 프로그램 입니다 어떤걸 하시겠습니까?
		// 1번 매출조회 2번 자전거 추가 3번 자전거 삭제.....
	}

	private void totalIncome() {	// 관리자 기능 - 매출조회
		// 총매출 조회
		// 결제 시스템에서 저장된 정보를 불러오기 (파일읽기)
	}

	private void addBicycle() {		// 관리자 기능 - 자전거 추가
		// 자전거 추가
		bicycle.put(Integer.toString(index), new Bicycle());
		Bicycle.count++; // 여기서 카운트는 자전거의 현재까지 만들어진 댓수
		index++;

	}

	private void removeBicycle() {	// 관리자 기능 - 자전거 삭제
		// 자전거 제거 끝에거 삭제 능력이닿는다면 다른방법으로 ㄲ
		bicycle.remove(Integer.toString(index));
		Bicycle.count--;
	}

	public void showMember() {		// 관리자 기능 - 회원정보 조회
		// 회원정보 조회
		// user에서 회원가입 할때 기록된 파일을 읽어 오기 //Member field 역직렬화
		try {
			file = "/User/sunwoo/Desktop/userInfo.txt";
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			Object users = null;

			while ((users = ois.readObject()) != null) {
				System.out.println(((UserInfo) users).toString()); // 캐스팅

			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (EOFException eofe) {
			System.out.println("끝" + eofe.getMessage());
			eofe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스가 존재하지 않습니다.");
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

	private void notice() {		// 관리자 기능 - 신고사항 고지
		// 신고조치 사항 고지
	}
	// 후기조회는 Rental 꺼 쓸지 아니면 그냥 여기서 또 만들지

	
	////////////////////////////////////////////////////////////// Rental////////////////////

	public void showMenuRental(String id) {	// 사용자 프로그램 시작 메뉴
		// case 마다 실행될 수 있는 메소드 들이 밑에 있음
		while(true) {
		System.out.println("=========================");
		System.out.println(" 자 전 거 대 여 반 납 서 비 스 ");
		System.out.println("=========================");
		nowBicycle();
		System.out.println("=========================");
		System.out.println("1.대여 2.반납 3.후기보기 ");
		System.out.println("=========================");
		rental(id);
		}
	}

	private void nowBicycle() {		// 사용자 기능 - 대여가능한 자전거 조회
		// 자전거 조회 - 선우
		// String id = ㄹㅇ id ;
		Set set = bicycle.keySet();
		for (int i = 0; i < bicycle.size(); i++) {
			System.out.printf("[%s]", set.contains(Integer.toString(i)) ? index + "번 자전거" : "대여중");
		}

	}

	private void rental(String id) {	// 사용자 기능 - 자전거 대여
		// 자전거 대여 -선우
		// 번호 던지기
		System.out.println("대여가능한 자전거 번호를 선택해 주십시오.");
		String number = AccountManager.scan.nextLine();
		Set set = bicycle.keySet();
		for (int i = 0; i < bicycle.size(); i++) {
			if (set.contains(number)) {
				bicycle.replace(id, bicycle.get(number));
			}
		}
		int key = 1;
		pay(key);
		// index 번호랑 일치하면 대여로 바꾸기
		// 로그기록 작성
		file = "/Users/sunwoo/Desktop/log.txt";
	}

	private int returnBicycle() {	// 사용자 기능 - 자전거 반납
		// 자전거 반납 -동연
		return 0; // index 번호랑 일치하면 대여가능으로 바꾸기
		// 로그기록 작성
	}

	// 후기 작성 조회는 리포트꺼 끌어와서

	private void report() throws Exception {	// 사용자 기능 - 반납 후 고장 신고
		// 고장신고 - 동연 파일 쓰기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년MM월dd일HH시mm분");
		String record = date.format(cal.getTime());
		// boolean fileExist;
		File asFolder = new File("C://고장신고//");
		System.out.println("고장난 자전거의 번호를 적어주세요: ");
		String Bicyclenum = AccountManager.scan.nextLine();
		File asFile = new File("C://고장신고//" + Bicyclenum + "_" + record + ".txt");
		// if (!asFile.exists()) {
		System.out.println("증상을 적어주세요: ");
		String symptom = AccountManager.scan.nextLine();
		System.out.println("고장신고가 정상적으로 처리되었습니다.");

		if (!asFolder.exists())
			asFolder.mkdir();
		FileWriter asFileWriter = new FileWriter(asFile);
		BufferedWriter bw = new BufferedWriter(asFileWriter);
		bw.write(record + "\n" + "자전거번호:  " + Bicyclenum + "_" + "고장내용: " + symptom);

		bw.close();
		// }
	}

	private void calculate(int number) {	
		int price = 1000;
		System.out.println(number + "시간권은 " + (number * price) + "원입니다.");
		System.out.println("금액을 투입해 주십시오");
		int pay = Integer.parseInt(AccountManager.scan.nextLine());
		while (pay < (number * price)) {
			System.out.println("잔액이 부족합니다.\n" + ((number * price) - pay) + "원을 더 투입해 주세요");
			pay += Integer.parseInt(AccountManager.scan.nextLine());
		}
		System.out.println("거스름돈 : " + (pay - (number * price)));
	}

	private void pay(int key) {
		// 결제 - 선우
		// 대여냐 반납이냐? 에따라 달라야함
		// 거래기록에 작성하기.
		if (key == 1) {
			System.out.println("============가격표============");
			System.out.println(" 1.1시간권 2.3시간권 3.6시간권 ");
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
					System.out.println("다시 입력해 주세요.");
					System.out.println(" 1.1시간권 2.3시간권 3.6시간권 ");
					choice = Integer.parseInt(AccountManager.scan.nextLine());
				}
			}
		} else {
			// 반납
		}

	}
}
