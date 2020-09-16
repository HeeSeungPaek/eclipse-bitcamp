/*
*@Class : Admin
*@Date : 2020. 09. 11
*@Author : 백희승, 문지연
*@Desc : 관리자 Admin 객체는 단 하나만 존재
*/

package kr.or.bit_2;

public class Admin{
	private final String ID;
	private final String PASSWORD;
	private static Admin admin;

	private Admin(){
		this.ID = "admin";
		this.PASSWORD = "1234";
	}

	public static Admin getInstance(){
		if (admin == null){
			admin = new Admin();
			
		}
		return admin;
	}

	public String getId(){
		return ID;
	}

	public String getPwd(){
		return PASSWORD;
	}
}
