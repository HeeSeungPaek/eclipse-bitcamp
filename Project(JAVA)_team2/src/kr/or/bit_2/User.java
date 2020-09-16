/*
*@Class : User
*@Date : 2020. 09. 11
*@Author : �����, ������
*@Desc : ȸ������ �� �����Ǵ� ȸ�� User ��ü
*/

package kr.or.bit_2;

public class User{	// User ȸ�� ���� ��ü

	private String userName;
	private String id;
	private String password;
	private String birth;

	public User(String id, String password, String userName, String birth){	// User ��ü ������ 
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
		return "ȸ������ [���̵�" + id + ", �̸� :" + userName + ", ������� : " + birth + "]";
	}

}
