package kr.or.bit_2;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	private String userName;
	private String id;
	private String password;
	private String birth;
	
	public UserInfo(String id,String password,String userName,String birth) {
		this.userName = userName;
		this.birth = birth;
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "ȸ������ [���̵�" + id + ", �̸� :" + userName + ", ������� : " + birth + "]";
	}

}
