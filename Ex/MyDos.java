import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MyDos {

	public static void main(String[] args) {
		
		System.out.println("Microsoft Windows DOS 명령어 만들기 [Version 1.0]");
		System.out.println("(c) 2020 Microsoft Corporation. All rights reserved\n");

		Scanner sc = new Scanner(System.in);

		String[] input = null;

		while (true) {
			System.out.print(path + ">");
			input = sc.nextLine().trim().split(" "); // input배열에 띄어쓰기 기준으로 각 방에 저장, 첫 번째 방 전에 공란 방지

			if (input[0].equalsIgnoreCase("exit")) { // 오직 exit로만 Dos 종료 가능
				System.out.println("Exit");
				System.exit(0);
				return;
			}

			switch (input[0].toLowerCase()) {
			case "dir":
				searchDirectory();
				break;
			case "cd":
			case "cd..":
				changeDirectory(input);
				break;
			case "md":
			case "mkdir":
				makeDirectory(input);
				break;
			case "rd":
			case "rmdir":
				removeDirectory(input);
				break;
			case "ren":
			case "rename":
				renameDirectory(input);
				break;
			case "type":
				readTextFile(input);
				break;
			case "help":
				displayHelp();
				break;
			default:
			}
		}
	}
	
	static String defaultPath = "C:" + File.separator;
	static String path = defaultPath;
	
	static void searchDirectory() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

		File file = new File(path);

		System.out.println("[ Path : " + file.getAbsolutePath() + " ]");

		ArrayList<Integer> dir = new ArrayList<Integer>(); // dir개수
		File[] allFiles = file.listFiles();
		long totalFileSize = 0;

		for (int i = 0; i < allFiles.length; i++) {
			String fileName = allFiles[i].getName();

			if (allFiles[i].isDirectory()) {
				fileName = dataFormat.format(date.getTime()) + "  <DIR> [" + fileName + "]";
				dir.add(i);
			} else {
				fileName = dataFormat.format(date.getTime()) + "   \t\t" + fileName + " / " + allFiles[i].length()
						+ "Byte";
				totalFileSize += allFiles[i].length();
			}
			System.out.println(fileName);
		}

		System.out.println("\t\t[현재 파일 개수] : " + (allFiles.length - dir.size()));
		System.out.println("\t\t[총 파일 용량] : " + totalFileSize + " 바이트");
		System.out.println("\t\t[현재 디렉토리 수] : " + dir.size());
	}

	static void changeDirectory(String[] input) { // cd 문제 : cd.. 입력 시 무조건 C:\로 진입한다. 고치기
		String temp = "";
		String inputPath = ""; // cd명령어 다음부터의 입력한 문자열 > input

		if (input.length > 1) // cd ..일 경우, if문을 안탄다.
			inputPath = input[1];

		if (input[0].equalsIgnoreCase("cd..")) {
			inputPath = input[0].substring(2, 4);
		}

		if (inputPath.equals("..")) { // cd.. or cd ..
			File currentPath = new File(path);
			if (currentPath.getParentFile() != null)
				path = currentPath.getParentFile().getAbsolutePath();

		} else {
			if (inputPath.contains("./")) { // cd와 작동 방법 동일
				if (inputPath.contains(defaultPath)) { // cd ./C:\의 오류 처리
					System.out.println("파일 이름, 디렉토리 이름 또는 볼륨 레이블 구문이 잘못되었습니다.");
					return;
				}
				temp = path + File.separator + inputPath.replace("./", ""); // ./경로명 을 입력할 경우에, 내가 입력한 값을 절대경로로 만든다.

			} else if (inputPath.contains(defaultPath)) { // 절대경로를 입력할 경우
				temp = inputPath; // C:\를 제외한 경로
			} else { // 현재 경로의 하위 경로를 입력한 경우
				temp = path + File.separator + inputPath;
			}

			File file = new File(temp); // 검증

			if (file.exists() && file.isDirectory()) {
				path = file.getAbsolutePath();
			} else {
				System.out.println("지정된 경로를 찾을 수 없습니다.");
			}
		}

	}

	static void makeDirectory(String[] input) { // md
		if (input.length == 1) {
			System.out.println("명령 구문이 올바르지 않습니다.");
		} else {
			File dir = new File(path + input[1]);

			if (!dir.exists()) {
				dir.mkdir();
			} else if (dir.exists()) {
				System.out.println("하위 디렉터리 또는 파일" + input[1] + "이(가) 이미 있습니다.");
			}
		}
	}

	static void removeDirectory(String[] input) { // rd
		if (input.length == 1)
			System.out.println("명령 구문이 올바르지 않습니다.");
		else {
			File dir = new File(path + input[1]);

			if (!dir.exists())
				System.out.println("지정된 파일을 찾을 수 없습니다.");

			else if (dir.exists() && !dir.isDirectory())
				System.out.println("디렉터리 이름이 올바르지 않습니다.");

			else if (dir.exists() && dir.isDirectory()) {
				File[] subdir = dir.listFiles();
				if (subdir.length >= 1)
					System.out.println(input[1] + " 디렉터리가 비어 있지 않습니다.");
				else
					dir.delete();
			}
		}
	}

	static void renameDirectory(String[] input) { // rename
		if (input.length <= 2) {
			if (input[1].equals("/?")) {
				System.out.println("RENAME [드라이브:][경로]파일이름1 파일이름2.");
				System.out.println("REN [드라이브:][경로]파일이름1 파일이름2.");
				System.out.println("\n대상 파일로 새 드라이브나 경로를 지정할 수 없음을 주의하십시오.");
			} else {
				System.out.println("명령 구문이 올바르지 않습니다.");
			}

		} else if (input.length == 3) {
			String originFile = "";
			if (!input[1].contains(defaultPath)) {
				originFile = path + input[1];
			} else {
				originFile = input[1];
			}

			File file = new File(originFile);
			if (!file.exists()) {
				System.out.println("지정된 파일을 찾을 수 없습니다.");
			} else { // 실제 바꾸고자 하는 파일이 존재할 경우...
				File newFile = new File(path + input[2]);
				if (!newFile.exists()) {
					file.renameTo(newFile);
				} else { // 동일한 이름이 존재하므로...
					System.out.println("파일 이름 중복 : 명령 구문이 올바르지 않습니다.");
				}
			}
		}
	}

	static void readTextFile(String[] input) {

		if (input.length == 1) { // type 명령어만 작성하였을 때 출력.
			System.out.println("명령 구문이 올바르지 않습니다.");
			return;
		}

		if (input.length >= 2) { // type 이후 보고싶은 text파일을 선언 시 아래 로직
			FileReader fr = null;
			BufferedReader br = null;

			try { // 파일 읽기 작업
				for (int i = 1; i < input.length; i++) { // input[1]부터 txt파일이 시작하기에 i = 1 부터 시작.

					if (input[i].contains(".txt")) {
						fr = new FileReader(new File(path + File.separator + input[i]));
						br = new BufferedReader(fr);

						String line = "";
						System.out.println("파일 명 : " + input[i]); // 파일 이름 출력
						for (int j = 0; (line = br.readLine()) != null; j++) {
							System.out.println(line);
						}
						System.out.println();
					} else {
						System.out.println("지정된 파일 [" + input[i] + "]를 찾을 수 없습니다.");
					}
				}
			} catch (Exception e) {
				e.getMessage();
			} finally {
				try {
					br.close();
					fr.close();
				} catch (Exception e2) {
				}
			}

		}

	}

	static void displayHelp() {
		System.out.println("DIR         디렉터리에 있는 파일과 하위 디렉터리 목록을 보여줍니다.");
		System.out.println("CD          현재 디렉터리 이름을 보여주거나 바꿉니다.");
		System.out.println("CD..        하위 디렉토리로 이동합니다.");
		System.out.println("MD(MKDIR)   디렉토리를 만듭니다.");
		System.out.println("RD(RMDIR)   디렉토리를 지웁니다.");
		System.out.println("REN(RENAME) 파일 이름을 바꿉니다.");
		System.out.println("TYPE        텍스트 파일의 내용을 보여줍니다.");
		System.out.println("EXIT        프로그램을 종료합니다");
	}

}
