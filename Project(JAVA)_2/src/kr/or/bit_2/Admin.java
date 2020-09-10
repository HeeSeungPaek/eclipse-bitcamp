package kr.or.bit_2;

import java.io.Serializable;

public class Admin implements Serializable{

	private final String id;
	private final String password;
	private static Admin admin;

	private Admin() {
		this.id = "admin";
		this.password = "1234";
	}

	public static Admin getInstance() {
		if (admin == null) {
			admin = new Admin();
		}
		return admin;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}
