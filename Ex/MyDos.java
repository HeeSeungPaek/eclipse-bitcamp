import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MyDos {
	static String defaultPath = "C:" + File.separator;
	static String path = defaultPath;

	public static void main(String[] args) {
		// CD 현재 디렉터리 이름을 보여주거나 바꿉니다.1
		// DIR 디렉터리에 있는 파일과 하위 디렉터리 목록을 보여줍니다. 2
		// MD 디렉터리를 만듭니다. 3
		// MKDIR 디렉터리를 만듭니다.
		// RD 디렉터리를 지웁니다. 4
		// RMDIR 디렉터리를 지웁니다.
		// REN 파일 이름을 바꿉니다. 5
		// RENAME파일 이름을 바꿉니다.
		// TYPE 텍스트 파일의 내용을 보여줍니다. 6
		// EXIT 프로그램을 종료합니다.

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
			default:
			}

		}

	}

	static void searchDirectory() { // Cal, SDF, ArrayList, File 집합체
		Calendar date = Calendar.getInstance(); // searchDirectory만 사용한다면, 메소드에 넣자
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

	static void changeDirectory(String[] input) { // 이 놈이 문제

		if (input.length == 1) {
			if (input[0].equalsIgnoreCase("cd..")) { // 현재 문자열이 C:\면 그대로, 아니라면 디렉토리를 자른다.
				if (path.equals(defaultPath)) { // 현재 C:\라면
					path = defaultPath;
				} else {
					String goal = path.replace(defaultPath, "");
					path = path.replace(goal, "");
				}
			} else if (input[0].equalsIgnoreCase("cd")) {
				System.out.println(path);
			}
			return;
		}

		String str = input[1].contains(defaultPath) ? input[1] : path + input[1]; // 입력한 경로명에 C:\가 있다면 그대로, 없으면 C:\ 붙인다.

		String str2 = input[1].replace(defaultPath, ""); // 비교연산에 쓸 문자열을 하나 더 만들었다

		// if (!input[1].equalsIgnoreCase(defaultPath) &&
		// str2.lastIndexOf(File.separator) == -1)
		// cd 경로명에 C:\가 포함되어 있지 않고, C:\를 제거한 경로명에 구분자(\)가 없다면 구분자를 붙여준다
		// str += File.separator;

		File file = new File(str);

		if (!file.exists() || !file.isDirectory()) {
			System.out.println("Invalid Directory");
			return;
		}
		path = str;
	}

	static void makeDirectory(String[] input) {
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

	static void removeDirectory(String[] input) {
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

	static void renameDirectory(String[] input) {
		if (input.length <= 2) {
			if (input.length == 1)
				System.out.println("명령 구문이 올바르지 않습니다.");
			else if (input[1].equals("/?")) {
				System.out.println("RENAME [드라이브:][경로]파일이름1 파일이름2.");
				System.out.println("REN [드라이브:][경로]파일이름1 파일이름2.");
				System.out.println("\n대상 파일로 새 드라이브나 경로를 지정할 수 없음을 주의하십시오.");
			}
		} else {

		}

	}

	static void readTextFile(String[] input) {

	}

	static void displayHelp() {

	}
}