import java.util.HashSet;
import java.util.Iterator;

class Myclass {
	private int id;
	private String name;

	public Myclass() {
	}

	public Myclass(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void displayInfo() {
		System.out.println("학번 : " + this.id);
		System.out.println("이름 : " + this.name);
	}

	public static void display(Myclass c) {
		c.displayInfo();
	}

	@Override
	public String toString() {
		return "Myclass [학번=" + id + ", 이름=" + name + "]";
	}

}

public class Ex10_Set_Object {
	public static void main(String[] args) {
		HashSet<Myclass> hset = new HashSet<Myclass>();

		Myclass mc1 = new Myclass(1, "Abc");
		Myclass mc2 = new Myclass(2, "Def");
		Myclass mc3 = new Myclass(3, "Asdf");

		hset.add(mc1);
		hset.add(mc2);
		hset.add(mc3);

		System.out.println("개선된  for");
		for (Myclass m : hset) {
			m.displayInfo();
		}

		// iterator 출력
		Iterator<Myclass> itr = hset.iterator();
		while (itr.hasNext()) {
			// System.out.println(itr.next().toString());
			Myclass mc = itr.next();
			System.out.println(mc.getId() + " / " + mc.getName());
		}

	}

}
