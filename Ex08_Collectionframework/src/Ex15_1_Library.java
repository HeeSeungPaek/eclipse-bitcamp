import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class Book {
	// å ��ȣ�� �����̶� �̸��� ����ʵ���
	final String isbn;
	String title;
	int price;

	// �����ڷ� �̸� ��ȣ ���� �־�� ���� å �ϳ� ���� �� ���� �⺻ ������ ����
	public Book(String isbn, String title, int price) {
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}

	// å ��ȣ �����ϴ� �޼���
	public String ISBN() {
		return isbn;
	}

	// ������Ʈ Ŭ������ toString() ������
	@Override
	public String toString() {
		return String.format("ISBN:%s �̸�:%s ����:%d", isbn, title, price);
	}
}

//���� ������ Ŭ����
class BookManager {
	Scanner scan = new Scanner(System.in);
	// HashMap�� generic���� ���ڿ��� ��ü(å) ���� => �ϳ� put�Ҷ� ���ڿ�(key)=isbn�̶� å��ü(value)�� �־����
	// new Book(��ȣ, ����, ����)�̷���
	// å�̸� ������ ������ ���� �� ����, å ���� ������ ������ ���� �� ����, �ٵ� å ��ȣ�� ���������� �� ���� => key å��ȣ
	HashMap<String, Book> book_dic = new HashMap<String, Book>();

	// ���α׷� ����
	public void Run() {
		int key = 0;
		while ((key = selectMenu()) != 0) {
			switch (key) {
			case 1:
				addBook();
				break;
			case 2:
				removeBook();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				listBook();
				break;
			case 5:
				listISBN();
				break;
			default:
				System.out.println("�߸� �����Ͽ����ϴ�.");
				break;
			}
		}
		System.out.println("�����մϴ�...");
	}

	int selectMenu() {
		System.out.println("1:�߰� 2:���� 3:�˻� 4:���� ��� 5:ISBN ���0:����");
		int key = scan.nextInt();
		scan.nextLine();
		return key;
	}

	void addBook() {
		String isbn;
		System.out.print("�߰��� ���� ISBN:");
		isbn = scan.nextLine();
		// key������ �ִ� isbn�� �߿� �ߺ��Ǵ°� �ִ��� Ȯ���ϴ�
		if (book_dic.containsKey(isbn)) {
			System.out.println("�̹� �����ϴ� ISBN�Դϴ�.");
			return;
		}
		String title;
		int price;
		System.out.print("���� ����:");
		title = scan.nextLine();
		System.out.print("����:");
		price = scan.nextInt();
		scan.nextLine();
		Book book = new Book(isbn, title, price);
		book_dic.put(isbn, book);
		System.out.println(book.toString() + " �����Ͽ����ϴ�.");
	}

	void removeBook() {
		String isbn;
		System.out.print("������ ���� ISBN:");
		isbn = scan.nextLine();
		// key ������ ���� isbn�� �Է¹����Ŷ� �Ȱ������ִ��� Ȯ
		if (book_dic.containsKey(isbn)) {
			book_dic.remove(isbn);
			System.out.println("�����Ͽ����ϴ�.");
		} else {
			System.out.println("�������� �ʽ��ϴ�.");
		}
	}

	void searchBook() {
		String isbn;
		System.out.print("�˻��� ���� ISBN:");
		isbn = scan.nextLine();
		if (book_dic.containsKey(isbn)) {
			Book book = book_dic.get(isbn);
			System.out.println("�˻� ���>>" + book.toString());
		} else {
			System.out.println("�������� �ʽ��ϴ�.");
		}
	}

	// ���� ��� �ִ��� &
	// �ִ� å���� ���� (å�� ������ ��°�ü�� value�� �־������ value����ϸ��.)
	void listBook() {
		System.out.println("���� ���");
		int cnt = book_dic.size();
		System.out.println("���� ��:" + cnt);
		for (Book book : book_dic.values()) {
			System.out.println(book.toString());
		}
	}

	// ���� ��� �ִ��� &
	// �ִ� å���� ��ȣ�� ���� �ִ���?
	void listISBN() {
		System.out.println("ISBN ���");
		int cnt = book_dic.size();
		System.out.println("���� ��:" + cnt);
		// keySet()
		// keySet()�Լ��� set�� �����ϰ� �ִ� (key�� ����ִ�) ��ü�� �ּҸ� ����
		// ���⼭ key�� ����ִ� ��ü�� String
		Set set = book_dic.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// key���� �̹� ���ʸ����� String�� �� �����ϱ� Ÿ���� �����شٰ� ����
		for (String isbn : book_dic.keySet()) {
			System.out.println(isbn);
		}
	}
}

//ISBN�� Ű�� ���� �˻�(HashMap Ŭ���� �̿�)
class Ex_15_1_Library {
	public static void main(String[] args) {
		BookManager bm = new BookManager();
		bm.Run();
	}
}