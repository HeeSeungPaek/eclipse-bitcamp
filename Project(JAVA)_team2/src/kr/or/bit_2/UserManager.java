/*
*@Class : UserManager
*@Date : 2020. 09. 12
*@Author : ������, �Ӽ���, �����
*@Desc : ȸ�� �� �������� �α��� �� ManageSystem ��ü�� ������ �Լ��� �̵�
*/

package kr.or.bit_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager{

	private ManageSystem manageSystem;
	private File fd = new File("/Users/sunwoo/UserMgData/");
	private Scanner scan = new Scanner(System.in);

	public UserManager(){
		manageSystem = new ManageSystem();
	}

	public void startProcess(){	// ���α׷��� ó�� �������� startProcess()
		while (true){
			
			try{
				
				System.out.println("����������������������������������������������������������������������������������������������");
				System.out.println("*     �� �� ������ ������ ���α׷��� ���� ���� ȯ���մϴ�       *");
				System.out.println("����������������������������������������������������������������������������������������������");
				System.out.println("==============ȸ�� ������ ������ �ּ���.==============");
				System.out.println("1.ȸ������      2.�α���     3.������ �α���    4.ȸ�� ��й�ȣ ����    5.���α׷� �����ϱ�");
				System.out.print("�޴�����>>");
				String input = scan.nextLine();
				switch (input){
				case "1":
					regist();
					break;
				case "2":
					signIn();
					break;
				case "3":
					signInAsAdmin();
					break;
				case "4":
					changePwd();
					break;
				case "5":
					System.out.println("���α׷��� �����մϴ�.");
					return;
				default:
					System.out.println("�ùٸ� ���� �Է����ּ���");
				}
			}catch (IOException e){
				
				System.out.println(e + " �����߻�");
			}catch (Exception e){
				
				System.out.println(e + " �����߻�");
			}
		}
	} // startProcess()


	private void regist() throws IOException{ // ȸ������ regist()
		if (!fd.exists())fd.mkdir();

		System.out.println("���̵�� �������� �����ϸ�, ������ ���ڷ� �̷����� 5-12�ڸ��� �����̾�� �մϴ�.");
		System.out.println("���̵� �Է����ּ���.");
		System.out.print("���̵� : ");
		String id = scan.nextLine().trim().toLowerCase();
		if (isCheckRegex("id", id)){
			
			System.out.println();
			System.out.println("��й�ȣ�� ���� ��,�빮��,���ڸ� �ּ� �� ���� ������ 8-20�ڸ��� �����̾�� �մϴ�.");
			System.out.println("��й�ȣ�� �Է����ּ���");
			System.out.print("��й�ȣ : ");
			String password = scan.nextLine().trim();
			if (isCheckRegex("password", password)){
				
				System.out.println();
				System.out.println("�̸��� �ѱ� 2-4���� �����̾�� �մϴ�.");
				System.out.print("�̸� : ");
				String userName = scan.nextLine().trim();
				if (isCheckRegex("username", userName)){
					
					System.out.println();
					System.out.println("��������� yymmdd�� �����̾�� �մϴ�. ex.930814");
					System.out.print("������� : ");
					String birth = scan.nextLine().trim();
					if (isCheckRegex("birth", birth)){
						
						File f = new File(fd.getAbsolutePath() + "/" + id + ".txt");
						if (!f.exists()){
							
							FileWriter fw = new FileWriter(f);
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(password + ":");
							bw.write(userName + ":");
							bw.write(birth);

							bw.close();
							System.out.println(userName + "���� ȸ�������� ����Ǿ����ϴ�.");
							showResult(id, userName, birth);
						} else
						{
							System.out.println("�Է��Ͻ� [" + id + "] ���̵�� �����մϴ�.");
							System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
							System.out.println();
						}
					}
				}
			}
		}
	} // regist()

	private void showResult(String id, String username, String birth){
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("\t���̵� : " + id + ", \t�̸� : " + username + ",\t ������� : " + birth);
		System.out.println("*****************************************************************");
		System.out.println();
	}
	
	/*
	* @method Name : isCheckRegex
	* @date : 2020. 09. 12
	* @author : ������, �����
	* @description : �� �Է°�(id, password, userName, birth)�� ����ǥ������ ���� �����Ѵ�. 
	* @param spec : String type, String info
	* @return : boolean
	*/
	private boolean isCheckRegex(String type, String info){
		boolean check = true;
		String regex = "";
		switch (type){
		
		case "id":
			regex = "^[a-z]{1}([a-z][0-9]){4,11}$";
			break;
		case "password":
			regex = "^([0-9])([a-z])([A-Z]).{8,20}$";
			break;
		case "username":
			regex = "^[��-�R]{2,4}$";
			break;
		case "birth":
			regex = "^[0-9]{2}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}$";
			break;
		}
		
		boolean isMatch = Pattern.matches(regex, info);
		if (!isMatch){
			
			System.out.println("�ùٸ��� ���� ������ " + type + "�Դϴ�. �ٽ� �õ����ּ���.");
			System.out.println();
			check = false;
		}
		
		return check;
	}

	private void signIn() throws IOException{ // ȸ�� �α��� signIn()

		FileReader fr = null;
		BufferedReader br = null;
		String cmp_data;	
		String[] txt_data;	// txt���Ͽ� ������ User�� ������ �о� �� ���� split()�� ���� ���� ���� �Ҵ�
		File file;			// id�̸��� ������ ���� ��ü ����

		System.out.println("�α����� �ϱ� ���� ���̵� / ��й�ȣ�� �Է����ּ���.");
		System.out.println("���̵�:");
		String id = scan.nextLine().trim().toLowerCase();
		System.out.println();

		file = new File(fd.getAbsolutePath() + "/" + id + ".txt");

		if (file.exists()){
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			cmp_data = br.readLine();
			txt_data = cmp_data.split(":");

			br.close();

			for (int cnt = 3; cnt > 0; cnt--){
				
				System.out.println("��й�ȣ:");
				String password = scan.nextLine().trim();
				System.out.println();

				if (txt_data[0].equals(password)){
					String userName = txt_data[1];
					String birth = txt_data[2];
					System.out.println(userName + "�� �α��� �Ǿ����ϴ�.");
					manageSystem.showMenuRental(id);
					return;
					
				}else{
					if (cnt > 1){
						System.out.println("�Է��Ͻ� ���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						System.out.print((cnt - 1) + "���� ��ȸ�� ���ҽ��ϴ�.\n ");
						
					} else{
						
						System.out.println("�Է��Ͻ� ���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						System.out.println();
					}
				}
			}
		}
	} // signIn()

	private void signInAsAdmin() throws IOException{	
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �α����� ���Ͻø� ���̵�/��й�ȣ�� �Է����ּ���.");
		System.out.println("���̵�:");
		String id = sc.nextLine().trim().toLowerCase();
		System.out.println("��й�ȣ:");
		String password = sc.nextLine().trim();

		Admin admin = Admin.getInstance();
		if ((id.equals(admin.getId())) && (password.equals(admin.getPwd()))){
			System.out.println("�����ڴ� �α��� �Ǿ����ϴ�.");
			manageSystem.showMenuManage();
			
		}else{
			
			System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
			return;
		}
	}

	private void changePwd() throws IOException{ // getUserInfo()�κ��� User��ü�� �޾� �ش� ��ü ���� ����
		User user = getUserInfo();
		File file;
		String password;

		if (user != null){
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("*\t\t" + user.getId() + "���� ��й�ȣ�´� " + user.getPassword() + " �Դϴ�. *");
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("\t\t������ ��й�ȣ�� �Է��Ͽ� �ֽʽÿ�.");
			System.out.println("��й�ȣ�� ���� ��,�빮��,���ڸ� �ּ� �� ���� ������ 8-20�ڸ��� ����");
			System.out.print("\t\t������ ��й�ȣ : ");
			password = scan.nextLine().trim();
			Pattern pwdPattern2 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
			Matcher matcher5 = pwdPattern2.matcher(password);
			
			if (!matcher5.matches()){
				System.out.println("�ùٸ��� ���� ������ ��й�ȣ�Դϴ�. �ٽ� �õ����ּ���.");
				System.out.println();
				changePwd();
				
			} else if (matcher5.matches()){
				
				file = new File(fd.getAbsolutePath() + "/" + user.getId() + ".txt");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(password + ":");
				bw.write(user.getUserName() + ":");
				bw.write(user.getBirth());
				bw.close();
			}else{
				
				return;
			}
			System.out.println();

		} else{
			
			return;
		}

		System.out.println(user.getUserName() + "���� ����� ��й�ȣ��" + password + "�Դϴ�. �ٽ� �α��� �� �ּ���!");

	}

	private User getUserInfo() throws IOException{	// id�� �˻��Ͽ� �ش� id�� �����ϸ� return User
		User user;
		File file;

		FileReader fr = null;
		BufferedReader br = null;
		String cmp_data;

		System.out.println("�˻��� ���̵� �Է����ּ���.");
		System.out.print("���̵� : ");
		String str_id = scan.nextLine().trim().toLowerCase();
		System.out.println();

		file = new File(fd.getAbsolutePath() + "/" + str_id + ".txt");

		if (file.exists()) {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			cmp_data = br.readLine();
			String[] txt_data = cmp_data.split(":");
			br.close();

			for (int cnt = 3; cnt > 0; cnt--) {

				System.out.println("���� ��й�ȣ�� �Է����ּ���.");
				String str_password = scan.nextLine().trim(); 
				System.out.println();
				if (txt_data[0].equals(str_password)){
					String str_userName = txt_data[1];
					String str_birth = txt_data[2];
					user = new User(str_id, str_password, str_userName, str_birth);
					return user;
					
				} else{
					
					if (cnt > 1){
						System.out.println("�Է��Ͻ� ��й�ȣ�� ���̵�� ��ġ���� �ʽ��ϴ�.");
						System.out.print((cnt - 1) + "���� ��ȸ�� ���ҽ��ϴ�. ");
						
					} else{
						
						System.out.println("�Է��Ͻ� ��й�ȣ�� ���̵�� ��ġ���� �ʽ��ϴ�.");
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						System.out.println("======================================");
						System.out.println();
					}
				}
			}
		} else{
			
			System.out.println("�Է����ֽ� ���̵�" + str_id + "��(��) �������� �ʽ��ϴ�.");
		}
		return null;
	}
}
