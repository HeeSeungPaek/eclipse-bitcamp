/*
*@Class : User
*@Date : 2020. 09. 11
*@Author : 백희승, 문지연
*@Desc : 회원가입 시 생성되는 회원 User 객체
*/

package kr.or.bit_2;

public class User{	// User 회원 정보 객체

	private String userName;
	private String id;
	private String password;
	private String birth;

	public User(String id, String password, String userName, String birth){	// User 객체 생성자 
		this.id = id;
		this.password = password;
		this.userName = userName;
		this.birth = birth;
	}

	public String getId(){
		return id;
	}

	public String getPassword(){
		return password;
	}

	public String getUserName(){
		return userName;
	}

	public String getBirth(){
		return birth;
	}

	@Override
	public String toString(){
		return "회원정보 [아이디" + id + ", 이름 :" + userName + ", 생년월일 : " + birth + "]";
	}

}
