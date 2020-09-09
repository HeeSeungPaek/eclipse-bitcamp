package kr.or.bit_2;

public class Admin {

	private final String id;
	private final String password;
	private Admin admin;

	private Admin() {
		this.id = "admin";
		this.password = "1234";
	}

	public Admin getInstance() {
		if (this.admin == null) {
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
