import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MyDos {
	static String defaultPath = "C:" + File.separator;
	static String path = defaultPath;

	public static void main(String[] args) {
		// CD ���� ���͸� �̸��� �����ְų� �ٲߴϴ�.1
		// DIR ���͸��� �ִ� ���ϰ� ���� ���͸� ����� �����ݴϴ�. 2
		// MD ���͸��� ����ϴ�. 3
		// MKDIR ���͸��� ����ϴ�.
		// RD ���͸��� ����ϴ�. 4
		// RMDIR ���͸��� ����ϴ�.
		// REN ���� �̸��� �ٲߴϴ�. 5
		// RENAME���� �̸��� �ٲߴϴ�.
		// TYPE �ؽ�Ʈ ������ ������ �����ݴϴ�. 6
		// EXIT ���α׷��� �����մϴ�.

		Scanner sc = new Scanner(System.in);

		String[] input = null;

		while (true) {
			System.out.print(path + ">");
			input = sc.nextLine().trim().split(" "); // input�迭�� ���� �������� �� �濡 ����, ù ��° �� ���� ���� ����

			if (input[0].equalsIgnoreCase("exit")) { // ���� exit�θ� Dos ���� ����
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

	static void searchDirectory() { // Cal, SDF, ArrayList, File ����ü
		Calendar date = Calendar.getInstance(); // searchDirectory�� ����Ѵٸ�, �޼ҵ忡 ����
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

		File file = new File(path);

		System.out.println("[ Path : " + file.getAbsolutePath() + " ]");

		ArrayList<Integer> dir = new ArrayList<Integer>(); // dir����
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

		System.out.println("\t\t[���� ���� ����] : " + (allFiles.length - dir.size()));
		System.out.println("\t\t[�� ���� �뷮] : " + totalFileSize + " ����Ʈ");
		System.out.println("\t\t[���� ���丮 ��] : " + dir.size());
	}

	static void changeDirectory(String[] input) { // �� ���� ����

		if (input.length == 1) {
			if (input[0].equalsIgnoreCase("cd..")) { // ���� ���ڿ��� C:\�� �״��, �ƴ϶�� ���丮�� �ڸ���.
				if (path.equals(defaultPath)) { // ���� C:\���
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

		String str = input[1].contains(defaultPath) ? input[1] : path + input[1]; // �Է��� ��θ� C:\�� �ִٸ� �״��, ������ C:\ ���δ�.

		String str2 = input[1].replace(defaultPath, ""); // �񱳿��꿡 �� ���ڿ��� �ϳ� �� �������

		// if (!input[1].equalsIgnoreCase(defaultPath) &&
		// str2.lastIndexOf(File.separator) == -1)
		// cd ��θ� C:\�� ���ԵǾ� ���� �ʰ�, C:\�� ������ ��θ� ������(\)�� ���ٸ� �����ڸ� �ٿ��ش�
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
			System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
		} else {
			File dir = new File(path + input[1]);

			if (!dir.exists()) {
				dir.mkdir();
			} else if (dir.exists()) {
				System.out.println("���� ���͸� �Ǵ� ����" + input[1] + "��(��) �̹� �ֽ��ϴ�.");
			}
		}
	}

	static void removeDirectory(String[] input) {
		if (input.length == 1)
			System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
		else {
			File dir = new File(path + input[1]);

			if (!dir.exists())
				System.out.println("������ ������ ã�� �� �����ϴ�.");

			else if (dir.exists() && !dir.isDirectory())
				System.out.println("���͸� �̸��� �ùٸ��� �ʽ��ϴ�.");

			else if (dir.exists() && dir.isDirectory()) {
				File[] subdir = dir.listFiles();
				if (subdir.length >= 1)
					System.out.println(input[1] + " ���͸��� ��� ���� �ʽ��ϴ�.");
				else
					dir.delete();
			}
		}
	}

	static void renameDirectory(String[] input) {
		if (input.length <= 2) {
			if (input.length == 1)
				System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
			else if (input[1].equals("/?")) {
				System.out.println("RENAME [����̺�:][���]�����̸�1 �����̸�2.");
				System.out.println("REN [����̺�:][���]�����̸�1 �����̸�2.");
				System.out.println("\n��� ���Ϸ� �� ����̺곪 ��θ� ������ �� ������ �����Ͻʽÿ�.");
			}
		} else {

		}

	}

	static void readTextFile(String[] input) {

	}

	static void displayHelp() {

	}
}