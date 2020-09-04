import java.util.ArrayList;
import java.util.List;

import kr.or.bit.CopyEmp;
import kr.or.bit.Emp;

class Product {
	int price;
	int bonuspoint;

	// Overloaded Constructor only
	Product(int price) {
		this.price = price;
		this.bonuspoint = (int) (this.price / 10.0);
	}
}

class KtTv extends Product {
	KtTv() {
		super(500);
	}
	// KtTv(int price){ super(price);}

	@Override
	public String toString() {
		return "KtTv";
	}
}

class Audio extends Product {
	Audio() {
		super(100);
	}

	@Override
	public String toString() {
		return "Audio";
	}
}

class NoteBook extends Product {
	NoteBook() {
		super(150);
	}

	@Override
	public String toString() {
		return "NoteBook";
	}
}

public class Ex07_Generic_Quiz {
	public static void main(String[] args) {
		// 1. Array 배열을 사용해서 cart 만들고 제품을 담으세요 (tv , audio , notebook)
		Product[] cart = new Product[3];	
		cart[0] = new KtTv();
		cart[1] = new Audio();
		cart[2] = new NoteBook();
		
		// 2. ArrayList 를 사용해서 cart 만들고 제품을 담으세요
		ArrayList<Product> pcart = new ArrayList<Product>();
		pcart.add(new KtTv());
		pcart.add(new KtTv());
		pcart.add(new KtTv());
		pcart.add(new NoteBook());

		for (Product product : pcart) {
			System.out.println(product);
		}

		// 사원 3명을 만드세요
		ArrayList<Emp> emplist = new ArrayList<Emp>();
		emplist.add(new Emp(1000, "김씨", "IT"));
		emplist.add(new Emp(2000, "박씨", "SALES"));
		emplist.add(new Emp(1000, "이씨", "MANAGER"));

		// 사원의 정보 (사번 , 이름 , 직종) 출력하세요
		// toString() (x)
		for (Emp emp : emplist) {	//Emp타입의 emp참조변수는 emplist의 모든 Emp타입을 바라본다.
									// 따라서, 참조변수 emp는 이 foreach문에서 emplist 속 emp들의 정보들을 확인 가능
			System.out.println(emp.getEmpno() + " / " + emp.getEname() + " / " + emp.getJob());
		}

		// 사원의 정보 (사번 , 이름 , 직종) 출력하세요 (일반 for문)
		// toString() (x)
		for (int i = 0; i < emplist.size(); i++) {
			System.out.println(emplist.get(i).getEmpno() + " / " + emplist.get(i).getEname());
		}

		ArrayList<CopyEmp> clist = new ArrayList<CopyEmp>();
		clist.add(new CopyEmp(100, "김", 1000));
		clist.add(new CopyEmp(200, "이", 2000));
		clist.add(new CopyEmp(300, "박", 3000));

		// 1. 200번 사원의 급여를 5000 으로 수정하세요 ( 일반 for 문안에서) getter , setter
		for (int i = 0; i < clist.size(); i++) {
			if (clist.get(i).getEmpno() == 200) {
				clist.get(i).setSal(5000);
				System.out.println(clist.get(i).toString());
			}
		}

		// 2. 300번 사원의 이름을 "궁금해" 수정해서 출력하세요 (개선된 for)
		for (CopyEmp emp : clist) {
			if (emp.getEmpno() == 300) {
				emp.setEname("궁금해");
				System.out.println(emp.toString());
			}
		}

	}

}
