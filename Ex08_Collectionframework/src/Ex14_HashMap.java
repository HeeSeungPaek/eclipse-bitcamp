import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
	HashMap<K, V>
		HashMap<String, String>
		HashMap<Integer, String>
		HashMap<String, Student>  *********
		>> put("kim",new Student()) ********
*/
class Student {
	int kor;
	int math;
	int eng;
	String name;

	public Student(int kor, int math, int eng, String name) {
		super();
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.name = name;
	}
	
}

class ArrayTest {
	ArrayList<String[]> al = new ArrayList<String[]>();
}

public class Ex14_HashMap {
	public static void main(String[] args) {
		HashMap<String, Student> sts = new HashMap<String, Student>();	//선우형이 얘기한게 이거구나, 제네릭 두쌍(키,밸류타입)
		sts.put("hong", new Student(100, 99, 98, "홍길동"));
		sts.put("kim", new Student(50, 40, 60, "김유신"));
		
		Student std = sts.get("hong");	// 와우 HashMap타입의 sts참조변수에 HashMap메소드 .get([key]);
										// >> value type(=Student)를 리턴하니, Student std에 넣어줄 수 있구나
		System.out.println(std.kor);
		System.out.println(std.name);

		// Tip 1
		// Map 안에 key , value >> key + "=" + value >>
		Set set = sts.entrySet(); // hong=Student@15db9742
		Iterator it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// Tip .. 분리된 key , value 획득 ... 2
		// class Entry { Object key , Object value}
		// Map { Entry[] elements } >> Map.Entry 인터페이스 ....
		for (Map.Entry m : sts.entrySet()) {
			System.out.println(m.getKey() + " / " + ((Student) m.getValue()).name);
		}

		// Tip ..3 ^^!	-> ㄷㄷ ... Generic Type = < String array > 
		ArrayTest at = new ArrayTest();
		at.al.add(new String[] { "1", "2", "3", "4" });	//al -> ArrayList<String[]>();
		at.al.add(new String[] { "5", "6", "7", "9" });	//so that we can use a method about ArrayList!
		String[] str = at.al.get(0);
		for (String s : str) {
			System.out.println(s);
		}

	}

}
