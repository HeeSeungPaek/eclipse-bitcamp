import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class Book {
	// 책 번호랑 가격이랑 이름이 멤버필드임
	final String isbn;
	String title;
	int price;

	// 생성자로 이름 번호 가격 넣어야 새로 책 하나 만들 수 있음 기본 생성자 없음
	public Book(String isbn, String title, int price) {
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}

	// 책 번호 리턴하는 메서드
	public String ISBN() {
		return isbn;
	}

	// 오브젝트 클래스의 toString() 재정의
	@Override
	public String toString() {
		return String.format("ISBN:%s 이름:%s 가격:%d", isbn, title, price);
	}
}

//도서 관리자 클래스
class BookManager {
	Scanner scan = new Scanner(System.in);
	// HashMap의 generic으로 문자열과 객체(책) 설정 => 하나 put할때 문자열(key)=isbn이랑 책객체(value)를 넣어야함
	// new Book(번호, 제목, 가격)이렇게
	// 책이름 같은거 여러권 있을 수 있음, 책 가격 같은거 여러권 있을 수 있음, 근데 책 번호는 같은게있을 수 없음 => key 책번호
	HashMap<String, Book> book_dic = new HashMap<String, Book>();

	// 프로그램 실행
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
				System.out.println("잘못 선택하였습니다.");
				break;
			}
		}
		System.out.println("종료합니다...");
	}

	int selectMenu() {
		System.out.println("1:추가 2:삭제 3:검색 4:도서 목록 5:ISBN 목록0:종료");
		int key = scan.nextInt();
		scan.nextLine();
		return key;
	}

	void addBook() {
		String isbn;
		System.out.print("추가할 도서 ISBN:");
		isbn = scan.nextLine();
		// key값으로 있는 isbn들 중에 중복되는게 있는지 확인하는
		if (book_dic.containsKey(isbn)) {
			System.out.println("이미 존재하는 ISBN입니다.");
			return;
		}
		String title;
		int price;
		System.out.print("도서 제목:");
		title = scan.nextLine();
		System.out.print("가격:");
		price = scan.nextInt();
		scan.nextLine();
		Book book = new Book(isbn, title, price);
		book_dic.put(isbn, book);
		System.out.println(book.toString() + " 생성하였습니다.");
	}

	void removeBook() {
		String isbn;
		System.out.print("삭제할 도서 ISBN:");
		isbn = scan.nextLine();
		// key 값으로 받은 isbn이 입력받은거랑 똑같은게있는지 확
		if (book_dic.containsKey(isbn)) {
			book_dic.remove(isbn);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("존재하지 않습니다.");
		}
	}

	void searchBook() {
		String isbn;
		System.out.print("검색할 도서 ISBN:");
		isbn = scan.nextLine();
		if (book_dic.containsKey(isbn)) {
			Book book = book_dic.get(isbn);
			System.out.println("검색 결과>>" + book.toString());
		} else {
			System.out.println("존재하지 않습니다.");
		}
	}

	// 지금 몇권 있는지 &
	// 있는 책들은 뭔지 (책의 정보를 담는객체로 value에 넣어놨으니 value출력하면됨.)
	void listBook() {
		System.out.println("도서 목록");
		int cnt = book_dic.size();
		System.out.println("도서 수:" + cnt);
		for (Book book : book_dic.values()) {
			System.out.println(book.toString());
		}
	}

	// 지금 몇권 있는지 &
	// 있는 책들의 번호는 뭐가 있는지?
	void listISBN() {
		System.out.println("ISBN 목록");
		int cnt = book_dic.size();
		System.out.println("도서 수:" + cnt);
		// keySet()
		// keySet()함수는 set을 구현하고 있는 (key를 담고있는) 객체의 주소를 리턴
		// 여기서 key를 담고있는 객체는 String
		Set set = book_dic.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// key들을 이미 제너릭으로 String을 해 놨으니까 타입을 맞춰준다고 생각
		for (String isbn : book_dic.keySet()) {
			System.out.println(isbn);
		}
	}
}

//ISBN을 키로 도서 검색(HashMap 클래스 이용)
class Ex_15_1_Library {
	public static void main(String[] args) {
		BookManager bm = new BookManager();
		bm.Run();
	}
}