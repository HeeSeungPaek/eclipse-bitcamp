/*
*@Class : UserManager
*@Date : 2020. 09. 12
*@Author : ¹®Áö¿¬, ÀÓ¼ÒÈñ, ¹éÈñ½Â
*@Desc : È¸¿ø ¹× °ü¸®ÀÚÀÇ ·Î±×ÀÎ ÈÄ ManageSystem °´Ã¼ÀÇ °¢°¢ÀÇ ÇÔ¼ö·Î ÀÌµ¿
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

	public void startProcess(){	// ÇÁ·Î±×·¥ÀÇ Ã³À½ ½ÃÀÛÁöÁ¡ startProcess()
		while (true){
			
			try{
				
				System.out.println("¦®¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¯");
				System.out.println("*     ¸¶ ÇÔ ºô¸®Á¶ ÀÚÀü°Å ÇÁ·Î±×·¥¿¡ ¿À½Å °ÍÀ» È¯¿µÇÕ´Ï´Ù       *");
				System.out.println("¦±¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦°");
				System.out.println("==============È¸¿ø ±¸ºÐÀ» ¼±ÅÃÇØ ÁÖ¼¼¿ä.==============");
				System.out.println("1.È¸¿ø°¡ÀÔ      2.·Î±×ÀÎ     3.°ü¸®ÀÚ ·Î±×ÀÎ    4.È¸¿ø ºñ¹Ð¹øÈ£ º¯°æ    5.ÇÁ·Î±×·¥ Á¾·áÇÏ±â");
				System.out.print("¸Þ´º¼±ÅÃ>>");
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
					System.out.println("ÇÁ·Î±×·¥À» Á¾·áÇÕ´Ï´Ù.");
					return;
				default:
					System.out.println("¿Ã¹Ù¸¥ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä");
				}
			}catch (IOException e){
				
				System.out.println(e + " ¿À·ù¹ß»ý");
			}catch (Exception e){
				
				System.out.println(e + " ¿À·ù¹ß»ý");
			}
		}
	} // startProcess()


	private void regist() throws IOException{ // È¸¿ø°¡ÀÔ regist()
		if (!fd.exists())fd.mkdir();

		System.out.println("¾ÆÀÌµð´Â ¿µ¹®À¸·Î ½ÃÀÛÇÏ¸ç, ¿µ¹®°ú ¼ýÀÚ·Î ÀÌ·¯¿ìÁø 5-12ÀÚ¸®ÀÇ Çü½ÄÀÌ¾î¾ß ÇÕ´Ï´Ù.");
		System.out.println("¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		System.out.print("¾ÆÀÌµð : ");
		String id = scan.nextLine().trim().toLowerCase();
		if (isCheckRegex("id", id)){
			
			System.out.println();
			System.out.println("ºñ¹Ð¹øÈ£´Â ¿µ¹® ¼Ò,´ë¹®ÀÚ,¼ýÀÚ¸¦ ÃÖ¼Ò ÇÑ °³¾¿ Æ÷ÇÔÇÑ 8-20ÀÚ¸®ÀÇ Çü½ÄÀÌ¾î¾ß ÇÕ´Ï´Ù.");
			System.out.println("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä");
			System.out.print("ºñ¹Ð¹øÈ£ : ");
			String password = scan.nextLine().trim();
			if (isCheckRegex("password", password)){
				
				System.out.println();
				System.out.println("ÀÌ¸§Àº ÇÑ±Û 2-4ÀÚÀÇ Çü½ÄÀÌ¾î¾ß ÇÕ´Ï´Ù.");
				System.out.print("ÀÌ¸§ : ");
				String userName = scan.nextLine().trim();
				if (isCheckRegex("username", userName)){
					
					System.out.println();
					System.out.println("»ý³â¿ùÀÏÀº yymmddÀÇ Çü½ÄÀÌ¾î¾ß ÇÕ´Ï´Ù. ex.930814");
					System.out.print("»ý³â¿ùÀÏ : ");
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
							System.out.println(userName + "´ÔÀÇ È¸¿øÁ¤º¸°¡ ÀúÀåµÇ¾ú½À´Ï´Ù.");
							showResult(id, userName, birth);
						} else
						{
							System.out.println("ÀÔ·ÂÇÏ½Å [" + id + "] ¾ÆÀÌµð´Â Á¸ÀçÇÕ´Ï´Ù.");
							System.out.println("ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
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
		System.out.println("\t¾ÆÀÌµð : " + id + ", \tÀÌ¸§ : " + username + ",\t »ý³â¿ùÀÏ : " + birth);
		System.out.println("*****************************************************************");
		System.out.println();
	}
	
	/*
	* @method Name : isCheckRegex
	* @date : 2020. 09. 12
	* @author : ¹®Áö¿¬, ¹éÈñ½Â
	* @description : °¢ ÀÔ·Â°ª(id, password, userName, birth)À» Á¤±ÔÇ¥Çö½ÄÀ» ÅëÇØ °ËÁõÇÑ´Ù. 
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
			regex = "^[°¡-ÆR]{2,4}$";
			break;
		case "birth":
			regex = "^[0-9]{2}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}$";
			break;
		}
		
		boolean isMatch = Pattern.matches(regex, info);
		if (!isMatch){
			
			System.out.println("¿Ã¹Ù¸£Áö ¾ÊÀº Çü½ÄÀÇ " + type + "ÀÔ´Ï´Ù. ´Ù½Ã ½ÃµµÇØÁÖ¼¼¿ä.");
			System.out.println();
			check = false;
		}
		
		return check;
	}

	private void signIn() throws IOException{ // È¸¿ø ·Î±×ÀÎ signIn()

		FileReader fr = null;
		BufferedReader br = null;
		String cmp_data;	
		String[] txt_data;	// txtÆÄÀÏ¿¡ ¾²¿©Áø UserÀÇ Á¤º¸¸¦ ÀÐ¾î °¢ °ªÀ» split()À» ÅëÇØ ºñ±³ÇÒ °ªÀ» ÇÒ´ç
		File file;			// idÀÌ¸§°ú µ¿ÀÏÇÑ ÆÄÀÏ °´Ã¼ ¼±¾ð

		System.out.println("·Î±×ÀÎÀ» ÇÏ±â À§ÇÑ ¾ÆÀÌµð / ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		System.out.println("¾ÆÀÌµð:");
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
				
				System.out.println("ºñ¹Ð¹øÈ£:");
				String password = scan.nextLine().trim();
				System.out.println();

				if (txt_data[0].equals(password)){
					String userName = txt_data[1];
					String birth = txt_data[2];
					System.out.println(userName + "´Ô ·Î±×ÀÎ µÇ¾ú½À´Ï´Ù.");
					manageSystem.showMenuRental(id);
					return;
					
				}else{
					if (cnt > 1){
						System.out.println("ÀÔ·ÂÇÏ½Å ¾ÆÀÌµð¿Í ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
						System.out.print((cnt - 1) + "¹øÀÇ ±âÈ¸°¡ ³²¾Ò½À´Ï´Ù.\n ");
						
					} else{
						
						System.out.println("ÀÔ·ÂÇÏ½Å ¾ÆÀÌµð¿Í ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
						System.out.println("ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
						System.out.println();
					}
				}
			}
		}
	} // signIn()

	private void signInAsAdmin() throws IOException{	
		Scanner sc = new Scanner(System.in);
		System.out.println("°ü¸®ÀÚ ·Î±×ÀÎÀ» ¿øÇÏ½Ã¸é ¾ÆÀÌµð/ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		System.out.println("¾ÆÀÌµð:");
		String id = sc.nextLine().trim().toLowerCase();
		System.out.println("ºñ¹Ð¹øÈ£:");
		String password = sc.nextLine().trim();

		Admin admin = Admin.getInstance();
		if ((id.equals(admin.getId())) && (password.equals(admin.getPwd()))){
			System.out.println("°ü¸®ÀÚ´Ô ·Î±×ÀÎ µÇ¾ú½À´Ï´Ù.");
			manageSystem.showMenuManage();
			
		}else{
			
			System.out.println("¾ÆÀÌµð¿Í ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
			System.out.println("ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			return;
		}
	}

	private void changePwd() throws IOException{ // getUserInfo()·ÎºÎÅÍ User°´Ã¼¸¦ ¹Þ¾Æ ÇØ´ç °´Ã¼ Á¤º¸ º¯°æ
		User user = getUserInfo();
		File file;
		String password;

		if (user != null){
			System.out.println("¦®¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¯");
			System.out.println("*\t\t" + user.getId() + "´ÔÀÇ ºñ¹Ð¹øÈ£´Â´Â " + user.getPassword() + " ÀÔ´Ï´Ù. *");
			System.out.println("¦±¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦¬¦°");
			System.out.println("\t\tº¯°æÇÒ ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇÏ¿© ÁÖ½Ê½Ã¿ä.");
			System.out.println("ºñ¹Ð¹øÈ£´Â ¿µ¹® ¼Ò,´ë¹®ÀÚ,¼ýÀÚ¸¦ ÃÖ¼Ò ÇÑ °³¾¿ Æ÷ÇÔÇÑ 8-20ÀÚ¸®ÀÇ Çü½Ä");
			System.out.print("\t\tº¯°æÇÒ ºñ¹Ð¹øÈ£ : ");
			password = scan.nextLine().trim();
			Pattern pwdPattern2 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
			Matcher matcher5 = pwdPattern2.matcher(password);
			
			if (!matcher5.matches()){
				System.out.println("¿Ã¹Ù¸£Áö ¾ÊÀº Çü½ÄÀÇ ºñ¹Ð¹øÈ£ÀÔ´Ï´Ù. ´Ù½Ã ½ÃµµÇØÁÖ¼¼¿ä.");
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

		System.out.println(user.getUserName() + "´ÔÀÇ º¯°æµÈ ºñ¹Ð¹øÈ£´Â" + password + "ÀÔ´Ï´Ù. ´Ù½Ã ·Î±×ÀÎ ÇØ ÁÖ¼¼¿ä!");

	}

	private User getUserInfo() throws IOException{	// id¸¦ °Ë»öÇÏ¿© ÇØ´ç id°¡ Á¸ÀçÇÏ¸é return User
		User user;
		File file;

		FileReader fr = null;
		BufferedReader br = null;
		String cmp_data;

		System.out.println("°Ë»öÇÒ ¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		System.out.print("¾ÆÀÌµð : ");
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

				System.out.println("±âÁ¸ ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				String str_password = scan.nextLine().trim(); 
				System.out.println();
				if (txt_data[0].equals(str_password)){
					String str_userName = txt_data[1];
					String str_birth = txt_data[2];
					user = new User(str_id, str_password, str_userName, str_birth);
					return user;
					
				} else{
					
					if (cnt > 1){
						System.out.println("ÀÔ·ÂÇÏ½Å ºñ¹Ð¹øÈ£°¡ ¾ÆÀÌµð¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
						System.out.print((cnt - 1) + "¹øÀÇ ±âÈ¸°¡ ³²¾Ò½À´Ï´Ù. ");
						
					} else{
						
						System.out.println("ÀÔ·ÂÇÏ½Å ºñ¹Ð¹øÈ£°¡ ¾ÆÀÌµð¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
						System.out.println("ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
						System.out.println("======================================");
						System.out.println();
					}
				}
			}
		} else{
			
			System.out.println("ÀÔ·ÂÇØÁÖ½Å ¾ÆÀÌµð" + str_id + "ÀÌ(°¡) Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}
		return null;
	}
}
