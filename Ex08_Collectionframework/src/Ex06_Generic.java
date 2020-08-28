import java.util.ArrayList;

/*
Today Point
Generic  jdk 1.5
c# , java  필수 기능
Collection 클래스 >> 데이터 기본 저장 공간 : Object
1. Object  타입 저항 ... >> 타입 ...문제  >> 타입 강제 (객체 생성 .... 타입)  >> 제너릭
2. 타입 안정성 (타입 강제)
3. 강제 형변환 (casting) :     (Car)object  (x) 
 클래스 설계시에 제너릭 적용 코드
 */
class MyGen2<T> { // type parameter
	T obj; // if <T = String>, then T는 다 String으로 치환

	void add(T obj) {
		this.obj = obj;
	}

	T get() {
		return this.obj;
	}
}

class Person2 extends Object {
	int age = 100;
}

public class Ex06_Generic {
	public static void main(String[] args) {
		// MyGen2 클래스의 add함수로 추가, get함수로 리턴하여 저장. <String>
		MyGen2<String> mygen = new MyGen2<String>();
		mygen.add("문자열");
		String str = mygen.get();
		System.out.println(str);

		ArrayList list = new ArrayList();
		// 저장 공간의 타입 : Object[] elements

		list.add(10);
		list.add(new Person2());
		list.add("홍길동");
		// 출력
		// 개선된 for

		for (Object obj : list) {
			// person 객체는 나이 출력하고 나머지는 값을 출력
			// System.out.println(obj);
			// 객체인것과 아닌 것
			if (obj instanceof Person2) {
				Person2 p = (Person2) obj;
				System.out.println(p.age); // 100
			} else {
				System.out.println(obj); // 10, 홍길동은 이쪽으로
			}
		}
		ArrayList<Person2> plist = new ArrayList<Person2>();
		plist.add(new Person2());
		plist.add(new Person2());
		for (Person2 p : plist) {
			System.out.println(p.age);
		}
	}
}