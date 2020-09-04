import java.util.Vector;

/*
Collection Framework의 시작~
[다수의 데이터]를 다루는 표준화된 [인터페이스]를 [구현하고 있는 클래스들의 집합]

Collection 인터페이스 -> List인터페이스가 상속 -> ArrayList... (약속을 구현하고 있는 클래스들)
				   -> Set이 상속 -> HashSet, TreeSet (구현)
Map 인터페이스(key와 value를 pair) -> HashMap... (구현) 

1.List (줄을 서세요) : 순서가 있다, 중복을 허용한다. (번호표, 홍길동 10명 와도 구분 가능)
	>> 내부적으로 데이터(자료, 객체)들을 ["배열"]로 관리 - [홍][홍][홍]
1.1 Vector (구버전)		-> 동기화를 보장 (멀티 스레드 환경에서) > Lock장치로 데이터 보호 > 성능은 비교적 떨어진다.(나머지는 대기해야..)
1.2 ArrayList (신버전)	-> 동기화 보장 (멀-스) > Lock장치를 필요에따라 OorX(디폴트=x) > 성능을 우선시하겠다. > 한강 비빔밥

기존엔 여러개의 같은 타입의 데이터를 관리할 때는 Array(정적 배열)을 사용했다.
-- 방의 개수가 한번 정해지면, 변경 불가.
int[] arr = new int[5]; // 방의 개수가 한번 정해지면 변경 불가 -> 유연성 x
<Array> 
1.크기가 고정, 2.접근 = index로(0~n) = 막방index = length-1, 3. 초기 설정 불가
 
<Collection interface - List를 구현하는> (Vector, ArrayList)
1.배열 크기 동적으로 축소,확장 - [fact : 배열의 재할당]이용
2.순서 유지(내부적으로 배열 사용한다=index), 중복값 허용
3.데이터 저장 공간 : Array 

*/
public class Ex01_Vector {

	public static void main(String[] args) {
		Vector v = new Vector();	// == Obj[] obj = new Obj[10];	== 오브젝트를 담을 수 있는 그릇
		System.out.println("초기 default 용량 : " + v.capacity());	// Vector의 capacity : 초기 방 개수 : 10개 : null
		System.out.println("size : " + v.size());
		v.add("AA");
		v.add("AA");
		v.add("AA");
		v.add(100);
		System.out.println("size : " + v.size());
		System.out.println(v.toString());	//[AA, AA, AA, 100]를 출력하도록 Vertor의 toString은 재정의가 되어있구나!
		//Array에서 length
		//Collection에선 size!!
		for(int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));	//이거 외우자. [i]가 아니라 .get(index)! == 정적배열 arr[index]와 같다.
		}
		
		for(Object obj : v) {	//Vector 안에는 타입이 각 다르다...? >> Object로 본다
			System.out.println(obj);
		}
		// 무지 큰그릇 (Object)에 겨우 문자열을 담아 본다...? >> 나중에 배우긴할텐데
		
		// 그 떄 하는 게 제너릭 : 숫자 하나 담는데 뭐하러 오브젝트 쓰냐? >> 타입을 강제>>
		Vector<String> v2 = new Vector<String>();	// <타입>을 담을 수 있는 == String을 담을 수 있는 그릇
		v2.add("hello");
		v2.add("world");
		v2.add("king");
		for(String str : v2) {
			System.out.println(str);
		}
		////////////////////////////////////////////////////////////////
		
		Vector v3 = new Vector();	//10개짜리 방
		System.out.println(v3.capacity());

		v3.add("A");
		v3.add("A");
		v3.add("A");
		v3.add("A");
		v3.add("A");
		
		v3.add("A");
		v3.add("A");
		v3.add("A");
		v3.add("A");
		v3.add("A");	//10개 add
		System.out.println(v3.capacity());
		
		v3.add("A");	//11번 쨰 -> 알아서 방 늘린다.
		System.out.println(v3.capacity()); // -> 하나를 추가로 넣기 위해 방을 두배로 넓힌다 : capacity = 20;
		System.out.println(v3.size());	// size는 정적배열의 length와 같이, 데이터의 개수를 의미한다~~
		
		
		
	}

}
