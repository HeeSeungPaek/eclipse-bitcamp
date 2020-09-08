package kr.or.bit_2;

import java.util.HashMap;

public class RentalSystem {
	HashMap<Integer, Member> user;
	HashMap<Integer, Bicycle> bicycle;
	
	public void showMenu() {
		int menu = 0;
		System.out.println("1. 비회원 , 2. 로그인 , 3. 관리자");
		menu = User.sc.nextInt();
		switch(menu){
		case 1 : break;//회원가입 
		case 2 : break;//로그인 -> 대여시스템으로 가고 
		case 3 : break;//관리자 -> 관리시스템으로 가고 
		}
	}
	
	public void nowBicycle() {
		//자전거 조회 
	}
	
	public void rental() {
		//자전거 대여 
	}
	public void returnBicycle() {
		//자전거 반납 
	}
	//후기 작성 조회는 리포트꺼 끌어와서 
	public void report() {
		//고장신
	}
	
}
