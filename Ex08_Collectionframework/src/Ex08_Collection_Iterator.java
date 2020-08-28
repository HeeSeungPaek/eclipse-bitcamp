import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
/*
	Collection FrameWork (컬렉션 프레임워크)
	
	[나열된 자원에 대해 순차적으로 접근해서 값을 리턴하는 표준] 
	 
	 Iterator 인터페이스 
     >> hasNext() , Next() , remove()   추상함수들
     >> ArrayList >> Iterator 구현 ...
     >> JDK를 까 본 결과..
     	Iterable[Iterator()] > Collection[size()] > List > ...
     											  > Set	> SortedSet > NavigableSet
     											  > Queue > Dequeue
    ArrayList 가 iterator() 를 구현
    
     public Iterator iterator() {
    	return  new A implements Iterator 
    }
    https://cafe.naver.com/springbit/314
*/
public class Ex08_Collection_Iterator {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(100);
		list.add(200);
		list.add(300);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		// 표준화된 출력 방식 (나열된 자원에 대해서)
		// 시간 : bank 표준화된 출력 (Iterator)
		Iterator it = list.iterator(); // Iterator 인터페이스를 구현하고 있는 객체의 주소 >> 객체 > hasNext() ...
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		ArrayList<Integer> intlist = new ArrayList<Integer>();
		intlist.add(1);
		intlist.add(2);
		intlist.add(3);
		Iterator<Integer> it2 = intlist.iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
		System.out.println("**********************");
		System.out.println("사이즈 " + intlist.size());
		for (int i = intlist.size() - 1; i >= 0; i--) {
			System.out.println(intlist.get(i));
		}

		System.out.println("************");

		// Iterator 인터페이스
		// 역방향 조회 (조건 : 순방향 >> 역방향)
		ListIterator<Integer> it3 = intlist.listIterator();
		// 순방향
		while (it3.hasNext()) {
			System.out.println(it3.next());
		}

		// 역방향
		while (it3.hasPrevious()) {
			System.out.println(it3.previous());
		}
	}

}
