import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ex12_Map_Interface {

	public static void main(String[] args) {
		// Map 인터페이스 구현
		// Map >> (키 , 값) 쌍의 구조를 가지는 배열
		// ex) 지역번호 (02,서울) , (031,경기)
		// (key , value)
		// key :중복(x)
		// value: 중복(0)

		// generic 지원
		// Map 인터페이스 구현
		// 구버전 : HashTable (동기화)
		// 신버전 : HashMap (동기화 강제하지 않아요) : 성능고려
		// 동기화 (Thread 학습 : 여러개의 stack 동시 ....)

		HashMap map = new HashMap();
		map.put("Tiger", "1004");
		map.put("scott", "1004");
		map.put("superman", "1004");

		System.out.println(map.containsKey("tiger")); // 대소문자 구별 (false)
		System.out.println(map.containsKey("scott"));
		System.out.println(map.containsValue("1004"));

		System.out.println(map.get("Tiger")); // key >> value >> get()
		System.out.println(map.get("hong")); // key >> 없으면 >> null
		
		map.put("Tiger", "1007"); // 같은 key값의 value replace
		System.out.println(map.get("Tiger")); // 1007
		System.out.println(map.size()); // 3

		Object value = map.remove("superman");		// 여기 물어보자
		System.out.println("value : " + value);
		System.out.println(map.toString());

		// 응용 (이해) ^^!
		Set set = map.keySet(); // keySet() : 는 Set을 구현하고 있는 객체의 주소를 리턴
		// 순서(x) , 중복(x)
		Iterator it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		Collection clist = map.values();			//여기도?
		System.out.println(clist.toString());

	}

}
