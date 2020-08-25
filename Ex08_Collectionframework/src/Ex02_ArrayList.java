import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Today Point
// List�� ArrayList �Լ�
/*
.add()		:
.size()		:
.get()		:
.toString()	:
.contains()	:
.clear()	:
.isEmpty()	:
.remove()	:
*/

public class Ex02_ArrayList {

	public static void main(String[] args) {
		// List�������̽��� �����ϰ� �ִ� Ŭ���� -> ������ �ִ�, �ߺ��� ����ϴ� �������� ������ �ٷ� �� ����ϴ� Ŭ����

		ArrayList arraylist = new ArrayList(); // Object Ÿ���� ������ ���� �迭�� ���޸𸮿� �����ȴ�

		// ArrayList�� �Լ���
		arraylist.add(100); // arraylist�� �濡 ������� ���� �־��ش�.
		arraylist.add(200);
		arraylist.add(300);

		for (int i = 0; i < arraylist.size(); i++) {
			System.out.println(arraylist.get(i)); // .get() �Լ� �ܿ��!
		}

		System.out.println(arraylist.toString()); // List�� class���� toString�� ������ �ߴ�.

		System.out.println(arraylist.get(0));

		// add()�Լ� : ���������� �����͸� �־��ִ� �Լ�
		arraylist.add(0, 111); // Ư�� �濡 ���� ������ **�̵�**�Ѵ�.
		System.out.println(arraylist); // [111, 100, 200, 300] -> �̵��� ���� ������
										// ArrayList�� �߰��� �����͸� �����ϴ� �Ϳ� ���� ������ �� ����.
										// ÷���� ������ ������ Ŭ���� = LinkedList
										// == ArrayList�� �ڿ��� �����ϰ�, �ں��� ����� �� ����.
		arraylist.add(4, 444);
		// **�� �������� (Ư�� ��ġ�� �����ִ�) �����͸� �߰��ϰų� �����ϴ� �۾��� ArrayList���״� ������ �۾��� �ƴϴ�.
		// >> ArrayList�� [������ �ִ� �������� ����]�� �����Ѵ�.
		// **������ (�ڿ������� �������� �״�) �������� �߰�, ������ �ٷ� �� �����ϴ�.

		// arraylist.remove(200); //indexOutOfBounds Exception ����
		System.out.println(arraylist);

		System.out.println(arraylist.contains(200)); // contains() ���� �о� return boolean.
		System.out.println(arraylist.contains(2000));

		arraylist.clear(); // �����ʹ� �� �����, ������ �����ִ�.
		System.out.println(arraylist.size());
		System.out.println(arraylist.isEmpty()); // �����Ͱ�����?

		arraylist.add(101);
		arraylist.add(102);
		arraylist.add(103);
		System.out.println(arraylist.isEmpty()); // false

		System.out.println("���� �� :" + arraylist.size());
		Object value = arraylist.remove(0); // 0��° ���� ���� �����ϴµ�, �� ���� �����Ѵ�.
		System.out.println("������ ������ : " + value);
		System.out.println("���� �� :" + arraylist.size());
		System.out.println(arraylist.toString());

		// Today Point's Point
		// �����ڰ� ������ ����ϴ� �ڵ�
		// = �������� �����Ѵ�. = �������� ���� Ȯ�强�� ����, �������� Ȯ���ȴ�. -> �������ڵ带 ���������� �����ڵ��� �����Ѵ�.
		List li = new ArrayList(); // �θ�� �ڽ��� �ּҰ��� ���� �� �ִ�. -> ������ ( �������̽��� �ϳ��� Ÿ���̴�! > �θ�)
		li.add("��");
		li.add("��");
		li.add("��");
		li.add("��");

		List li2 = li.subList(0, 2); // subList() ����� �ִ� ������ ������ �ִ� ������ ����
		System.out.println(li2.toString());

		ArrayList alist = new ArrayList();
		alist.add(50);
		alist.add(1);
		alist.add(7);
		alist.add(40);
		alist.add(46);
		alist.add(3);
		alist.add(15);

		System.out.println(alist.toString()); // [50, 1, 7, 40, 46, 3, 15]
		Collections.sort(alist);
		System.out.println(alist.toString()); // [1, 3, 7, 15, 40, 46, 50] �ʱ��� ���� ����.. �߱��ں���
												// ��������
		Collections.reverse(alist); // �̰ŵ� ��������
		System.out.println(alist.toString());

		//ArrayList abc = new ArrayList();  //java.lang.IndexOutOfBoundsException
		//abc.add(4, 111);					//ArrayList�� ������, ���̰��� �����ϸ� ���� ���� > ���
		//System.out.println(abc.get(1));
		//System.out.println(abc.toString());
		
	}
}
