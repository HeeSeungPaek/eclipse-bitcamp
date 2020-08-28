import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Set은 인터페이스 구현하고 있는  클래스
//Set  원안에 들어가세요 (순서 보장(x) , 중복(x)) : 순서가 없고 중복을 허락하지 않는 데이터 집합 : Set
//Set >> HashSet , TreeSet
public class Ex09_Set {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();

		hs.add(1);
		hs.add(100);
		boolean bo2 = hs.add(55);	// 이 두 줄 : HashSet hs에 55를 넣고, 중복이 있나요? = bo2에 저장 
		System.out.println(bo2);

		bo2 = hs.add(1); // 보장 : 중복값은 없어요
		System.out.println(bo2);

		System.out.println(hs.toString());

		hs.add(2);
		System.out.println(hs.toString()); // 출력 순서 보장 되지 않아요

		HashSet<String> hs2 = new HashSet<String>();
		hs2.add("b");
		hs2.add("A");
		hs2.add("F");
		hs2.add("c");
		hs2.add("z");
		hs2.add("A");
		hs2.add("A");
		hs2.add("A");

		System.out.println("HashSet hs2: "+hs2.toString());	// 엥 자동으로 오름차순 정렬 되있네? > 넣은 순서가 있어서..

		String[] obj = { "B", "C", "D", "B", "A", "A" };
		HashSet<String> hs3 = new HashSet<String>();
		for (int i = 0; i < obj.length; i++) {
			hs3.add(obj[i]);
		}
		System.out.println(hs3.toString());

		// Quiz
		// HashSet에 1~45 난수 6개 넣으세요
		/*
		 * int[] lotto = new int[6];
		 * 
		 * for(int i = 0 ; i < 6 ; i++) { lotto[i] = (int)(Math.random()*45 + 1);
		 * for(int j = 0 ; j < i ; j++) { //j < i (채워진 개수 만큼 비교) if(lotto[i] ==
		 * lotto[j]) { i--; //point 같은 방의 값을 바꾸어여 한다 break; } } }
		 */
		HashSet<Integer> lotto = new HashSet<Integer>();
		for (int i = 0; lotto.size() < 6; i++) { // lotto.size() < 6
			int num = (int) (Math.random() * 45 + 1);
			lotto.add(num);
			System.out.println("i : " + i + " num :" + num);
		}
		System.out.println(lotto);

		Set set2 = new HashSet(); // Set 부모 타입
		int index = 0;
		while (set2.size() < 6) {
			int num = (int) (Math.random() * 45 + 1);
			set2.add(num);
		}
		System.out.println("lotto : " + set2);

		
		HashSet<String> set3 = new HashSet<String>();
		set3.add("AA");
		set3.add("DD");
		set3.add("ABC");
		set3.add("FFF");
		System.out.println(set3.toString());

		
		Iterator<String> st = set3.iterator();
		while (st.hasNext()) {
			System.out.println(st.next());
		}
	}
}