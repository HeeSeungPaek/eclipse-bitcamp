import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Today Point
// List의 ArrayList 함수
/*
.add()		:
.size()		:
.get()		:
.toString()	:
.contains()	:
.clear()	:
.isEmpty()	:
.remove()	:
*/

public class Ex02_ArrayList {

	public static void main(String[] args) {
		// List인터페이스를 구현하고 있는 클래스 -> 순서가 있는, 중복을 허용하는 데이터의 집합을 다룰 때 사용하는 클래스

		ArrayList arraylist = new ArrayList(); // Object 타입을 가지는 정적 배열이 힙메모리에 생성된다

		// ArrayList의 함수들
		arraylist.add(100); // arraylist의 방에 순서대로 값을 넣어준다.
		arraylist.add(200);
		arraylist.add(300);

		for (int i = 0; i < arraylist.size(); i++) {
			System.out.println(arraylist.get(i)); // .get() 함수 외우기!
		}

		System.out.println(arraylist.toString()); // List의 class들은 toString을 재정의 했다.

		System.out.println(arraylist.get(0));

		// add()함수 : 순차적으로 데이터를 넣어주는 함수
		arraylist.add(0, 111); // 특정 방에 값을 넣으면 **이동**한다.
		System.out.println(arraylist); // [111, 100, 200, 300] -> 이동이 많아 안좋음
										// ArrayList는 중간에 데이터를 삽입하는 것에 대해 굉장히 안 좋다.
										// 첨삭의 단점을 보완한 클래스 = LinkedList
										// == ArrayList는 뒤에다 삽입하고, 뒤부터 지우는 게 좋다.
		arraylist.add(4, 444);
		// **비 순차적인 (특정 위치에 끼워넣는) 데이터를 추가하거나 삭제하는 작업은 ArrayList한테는 적합한 작업이 아니다.
		// >> ArrayList는 [순서가 있는 데이터의 집합]을 좋아한다.
		// **순차적 (뒤에서부터 차곡차곡 쌓는) 데이터의 추가, 삭제를 다룰 때 유리하다.

		// arraylist.remove(200); //indexOutOfBounds Exception 문제
		System.out.println(arraylist);

		System.out.println(arraylist.contains(200)); // contains() 값을 읽어 return boolean.
		System.out.println(arraylist.contains(2000));

		arraylist.clear(); // 데이터는 다 지우고, 공간은 남아있다.
		System.out.println(arraylist.size());
		System.out.println(arraylist.isEmpty()); // 데이터가없니?

		arraylist.add(101);
		arraylist.add(102);
		arraylist.add(103);
		System.out.println(arraylist.isEmpty()); // false

		System.out.println("삭제 전 :" + arraylist.size());
		Object value = arraylist.remove(0); // 0번째 방의 값을 삭제하는데, 그 값을 리턴한다.
		System.out.println("삭제된 데이터 : " + value);
		System.out.println("삭제 후 :" + arraylist.size());
		System.out.println(arraylist.toString());

		// Today Point's Point
		// 개발자가 실제로 사용하는 코드
		// = 다형성을 좋아한다. = 다형성을 쓰면 확장성도 좋고, 유연성도 확보된다. -> 다형성코드를 습관적으로 개발자들이 좋아한다.
		List li = new ArrayList(); // 부모는 자식의 주소값을 가질 수 있다. -> 다형성 ( 인터페이스도 하나의 타입이다! > 부모)
		li.add("가");
		li.add("나");
		li.add("다");
		li.add("라");

		List li2 = li.subList(0, 2); // subList() 만들어 주는 데이터 순서가 있는 데이터 집합
		System.out.println(li2.toString());

		ArrayList alist = new ArrayList();
		alist.add(50);
		alist.add(1);
		alist.add(7);
		alist.add(40);
		alist.add(46);
		alist.add(3);
		alist.add(15);

		System.out.println(alist.toString()); // [50, 1, 7, 40, 46, 3, 15]
		Collections.sort(alist);
		System.out.println(alist.toString()); // [1, 3, 7, 15, 40, 46, 50] 초급자 쓰지 마세.. 중급자부터
												// 오름차순
		Collections.reverse(alist); // 이거도 쓰지말자
		System.out.println(alist.toString());

		//ArrayList abc = new ArrayList();  //java.lang.IndexOutOfBoundsException
		//abc.add(4, 111);					//ArrayList는 다음방, 사이값을 제외하면 삽입 금지 > 약속
		//System.out.println(abc.get(1));
		//System.out.println(abc.toString());
		
	}
}
