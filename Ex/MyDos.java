import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MyDos {

	public static void main(String[] args) {
		
		System.out.println("Microsoft Windows DOS ��ɾ� ����� [Version 1.0]");
		System.out.println("(c) 2020 Microsoft Corporation. All rights reserved\n");

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

	static void changeDirectory(String[] input) { // cd ���� : cd.. �Է� �� ������ C:\�� �����Ѵ�. ��ġ��
		String temp = "";
		String inputPath = ""; // cd��ɾ� ���������� �Է��� ���ڿ� > input

		if (input.length > 1) // cd ..�� ���, if���� ��ź��.
			inputPath = input[1];

		if (input[0].equalsIgnoreCase("cd..")) {
			inputPath = input[0].substring(2, 4);
		}

		if (inputPath.equals("..")) { // cd.. or cd ..
			File currentPath = new File(path);
			if (currentPath.getParentFile() != null)
				path = currentPath.getParentFile().getAbsolutePath();

		} else {
			if (inputPath.contains("./")) { // cd�� �۵� ��� ����
				if (inputPath.contains(defaultPath)) { // cd ./C:\�� ���� ó��
					System.out.println("���� �̸�, ���丮 �̸� �Ǵ� ���� ���̺� ������ �߸��Ǿ����ϴ�.");
					return;
				}
				temp = path + File.separator + inputPath.replace("./", ""); // ./��θ� �� �Է��� ��쿡, ���� �Է��� ���� �����η� �����.

			} else if (inputPath.contains(defaultPath)) { // �����θ� �Է��� ���
				temp = inputPath; // C:\�� ������ ���
			} else { // ���� ����� ���� ��θ� �Է��� ���
				temp = path + File.separator + inputPath;
			}

			File file = new File(temp); // ����

			if (file.exists() && file.isDirectory()) {
				path = file.getAbsolutePath();
			} else {
				System.out.println("������ ��θ� ã�� �� �����ϴ�.");
			}
		}

	}

	static void makeDirectory(String[] input) { // md
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

	static void removeDirectory(String[] input) { // rd
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

	static void renameDirectory(String[] input) { // rename
		if (input.length <= 2) {
			if (input[1].equals("/?")) {
				System.out.println("RENAME [����̺�:][���]�����̸�1 �����̸�2.");
				System.out.println("REN [����̺�:][���]�����̸�1 �����̸�2.");
				System.out.println("\n��� ���Ϸ� �� ����̺곪 ��θ� ������ �� ������ �����Ͻʽÿ�.");
			} else {
				System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
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
				System.out.println("������ ������ ã�� �� �����ϴ�.");
			} else { // ���� �ٲٰ��� �ϴ� ������ ������ ���...
				File newFile = new File(path + input[2]);
				if (!newFile.exists()) {
					file.renameTo(newFile);
				} else { // ������ �̸��� �����ϹǷ�...
					System.out.println("���� �̸� �ߺ� : ��� ������ �ùٸ��� �ʽ��ϴ�.");
				}
			}
		}
	}

	static void readTextFile(String[] input) {

		if (input.length == 1) { // type ��ɾ �ۼ��Ͽ��� �� ���.
			System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
			return;
		}

		if (input.length >= 2) { // type ���� ������� text������ ���� �� �Ʒ� ����
			FileReader fr = null;
			BufferedReader br = null;

			try { // ���� �б� �۾�
				for (int i = 1; i < input.length; i++) { // input[1]���� txt������ �����ϱ⿡ i = 1 ���� ����.

					if (input[i].contains(".txt")) {
						fr = new FileReader(new File(path + File.separator + input[i]));
						br = new BufferedReader(fr);

						String line = "";
						System.out.println("���� �� : " + input[i]); // ���� �̸� ���
						for (int j = 0; (line = br.readLine()) != null; j++) {
							System.out.println(line);
						}
						System.out.println();
					} else {
						System.out.println("������ ���� [" + input[i] + "]�� ã�� �� �����ϴ�.");
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
		System.out.println("DIR         ���͸��� �ִ� ���ϰ� ���� ���͸� ����� �����ݴϴ�.");
		System.out.println("CD          ���� ���͸� �̸��� �����ְų� �ٲߴϴ�.");
		System.out.println("CD..        ���� ���丮�� �̵��մϴ�.");
		System.out.println("MD(MKDIR)   ���丮�� ����ϴ�.");
		System.out.println("RD(RMDIR)   ���丮�� ����ϴ�.");
		System.out.println("REN(RENAME) ���� �̸��� �ٲߴϴ�.");
		System.out.println("TYPE        �ؽ�Ʈ ������ ������ �����ݴϴ�.");
		System.out.println("EXIT        ���α׷��� �����մϴ�");
	}

}
