import java.util.ArrayList;

import kr.or.bit.Emp;

public class Ex03_ArrayList_Object {

	public static void main(String[] args) {
		// 사원 한명을 만드세요
		Emp emp = new Emp(100, "김유신", "징군");
		System.out.println(emp.toString());

		// 사원 2명 만들어라 배열로
		Emp[] emplist = { new Emp(100, "김시", "일반"), new Emp(200, "박씨", "학생") };
		for (Emp e : emplist) {
			System.out.println(e.toString());
		}

		// 1명 더 입사를 했어요, 추가하세요 -> 정적 배열은 크기가 한 번 주어지면 변경 불가능하다
		// -> ArrayList를 사용하면 가능하다!
		ArrayList elist = new ArrayList();
		elist.add(new Emp(1, "강", "IT"));
		elist.add(new Emp(2, "김", "IT"));
		// 1명 더 입사햇어요, 추가하세요
		elist.add(new Emp(3, "이", "IT"));
		System.out.println(elist.size());
		System.out.println(elist.get(0).toString());
		
		// toString을 사용하여 출력하는 것을 볼 수 있다.
		for(int i = 0; i < elist.size(); i++) {	//elist.toString을 했음.
			System.out.println(elist.get(i));
		}
		
		//toString을 사용하지 말고 사원의 사번, 이름, 직종을 출력해보세요.
		for(int i = 0; i < elist.size(); i++) {
			Object obj = elist.get(i);			// ArrayList에 제네릭 X-> Object타입이다. 그래서 다운캐스팅이 필요
			Emp e = (Emp)obj;					//다운캐스팅 해야 보인다.
			System.out.println(e.getEmpno());
			System.out.println(e.getEname());
			System.out.println(e.getJob());
		}
		for(Object obj : elist) {
			Emp e1 = (Emp)obj;
		}
		// 위와 같은 식의 코드는 개발자가 짜증낸다...
		// 다시 다운 캐스팅... 반복적 동작 등...
		// Object 쓰지 않고도 볼 수 있는 방법 >> 제너릭
		// 제너릭 : 객체 생성할 때 타입을 강제할 수 있다.
		// 현업에서 만드는 코드는 90%가 [제너릭]..
		ArrayList<Emp> list2 = new ArrayList<Emp>();	//Emp타입을 강제한 ArrayList객체 list2를 만든다!
		list2.add(new Emp(1, "A", "IT"));
		
		System.out.println("-----------");
		for(Emp e : list2) {
			System.out.println(e.getEmpno());
		}
	}

}
