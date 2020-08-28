import java.util.ArrayList;

/*
Today Point
Generic  jdk 1.5
c# , java  �ʼ� ���
Collection Ŭ���� >> ������ �⺻ ���� ���� : Object
1. Object  Ÿ�� ���� ... >> Ÿ�� ...����  >> Ÿ�� ���� (��ü ���� .... Ÿ��)  >> ���ʸ�
2. Ÿ�� ������ (Ÿ�� ����)
3. ���� ����ȯ (casting) :     (Car)object  (x) 
 Ŭ���� ����ÿ� ���ʸ� ���� �ڵ�
 */
class MyGen2<T> { // type parameter
	T obj; // if <T = String>, then T�� �� String���� ġȯ

	void add(T obj) {
		this.obj = obj;
	}

	T get() {
		return this.obj;
	}
}

class Person2 extends Object {
	int age = 100;
}

public class Ex06_Generic {
	public static void main(String[] args) {
		// MyGen2 Ŭ������ add�Լ��� �߰�, get�Լ��� �����Ͽ� ����. <String>
		MyGen2<String> mygen = new MyGen2<String>();
		mygen.add("���ڿ�");
		String str = mygen.get();
		System.out.println(str);

		ArrayList list = new ArrayList();
		// ���� ������ Ÿ�� : Object[] elements

		list.add(10);
		list.add(new Person2());
		list.add("ȫ�浿");
		// ���
		// ������ for

		for (Object obj : list) {
			// person ��ü�� ���� ����ϰ� �������� ���� ���
			// System.out.println(obj);
			// ��ü�ΰͰ� �ƴ� ��
			if (obj instanceof Person2) {
				Person2 p = (Person2) obj;
				System.out.println(p.age); // 100
			} else {
				System.out.println(obj); // 10, ȫ�浿�� ��������
			}
		}
		ArrayList<Person2> plist = new ArrayList<Person2>();
		plist.add(new Person2());
		plist.add(new Person2());
		for (Person2 p : plist) {
			System.out.println(p.age);
		}
	}
}