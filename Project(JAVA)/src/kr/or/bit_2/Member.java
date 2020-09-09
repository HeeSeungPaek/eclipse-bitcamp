package kr.or.bit_2;

import java.io.Serializable;

public class Member implements Serializable{
	
	private String userName;
	private String id;
	private String password;
	private String birth;
	
	public Member(String userName, String birth, String id, String password) {
		this.userName = userName;
		this.birth = birth;
		this.id = id;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "회원정보 [아이디" + id + ", 이름 :" + userName + ", 생년월일 : " + birth + "]";
	}
	


}