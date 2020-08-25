import java.util.ArrayList;

class Empdata {
	private String name;
	private int[] numbers;
	private ArrayList elist;

	Empdata() {
		this.name = "아무개";
		
		// 사용자 정의 타입, Array, ArrayList > 초기화 : 처음 값을 가지는 것 >> 메모리를 가지는 것 >> new 통해 힙에 있는
		// 메모리에..
		this.numbers = new int[10];
		elist = new ArrayList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public ArrayList getElist() {
		return elist;
	}

	public void setElist(ArrayList elist) {
		this.elist = elist;
	}

}

public class Ex04_ArrayList {

	public static void main(String[] args) {
		Empdata empdata = new Empdata();
		System.out.println(empdata.toString());	//toString 재정의 안해서 주소값 나옴
		System.out.println(empdata.getElist().toString());

		ArrayList li = new ArrayList();
		li.add(100);
		li.add(200);
		
		empdata.setElist(li);
		System.out.println(empdata.getElist().toString());
	}

}
