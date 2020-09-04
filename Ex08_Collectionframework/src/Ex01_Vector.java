import java.util.Vector;

/*
Collection Framework�� ����~
[�ټ��� ������]�� �ٷ�� ǥ��ȭ�� [�������̽�]�� [�����ϰ� �ִ� Ŭ�������� ����]

Collection �������̽� -> List�������̽��� ��� -> ArrayList... (����� �����ϰ� �ִ� Ŭ������)
				   -> Set�� ��� -> HashSet, TreeSet (����)
Map �������̽�(key�� value�� pair) -> HashMap... (����) 

1.List (���� ������) : ������ �ִ�, �ߺ��� ����Ѵ�. (��ȣǥ, ȫ�浿 10�� �͵� ���� ����)
	>> ���������� ������(�ڷ�, ��ü)���� ["�迭"]�� ���� - [ȫ][ȫ][ȫ]
1.1 Vector (������)		-> ����ȭ�� ���� (��Ƽ ������ ȯ�濡��) > Lock��ġ�� ������ ��ȣ > ������ ���� ��������.(�������� ����ؾ�..)
1.2 ArrayList (�Ź���)	-> ����ȭ ���� (��-��) > Lock��ġ�� �ʿ信���� OorX(����Ʈ=x) > ������ �켱���ϰڴ�. > �Ѱ� �����

������ �������� ���� Ÿ���� �����͸� ������ ���� Array(���� �迭)�� ����ߴ�.
-- ���� ������ �ѹ� ��������, ���� �Ұ�.
int[] arr = new int[5]; // ���� ������ �ѹ� �������� ���� �Ұ� -> ������ x
<Array> 
1.ũ�Ⱑ ����, 2.���� = index��(0~n) = ����index = length-1, 3. �ʱ� ���� �Ұ�
 
<Collection interface - List�� �����ϴ�> (Vector, ArrayList)
1.�迭 ũ�� �������� ���,Ȯ�� - [fact : �迭�� ���Ҵ�]�̿�
2.���� ����(���������� �迭 ����Ѵ�=index), �ߺ��� ���
3.������ ���� ���� : Array 

*/
public class Ex01_Vector {

	public static void main(String[] args) {
		Vector v = new Vector();	// == Obj[] obj = new Obj[10];	== ������Ʈ�� ���� �� �ִ� �׸�
		System.out.println("�ʱ� default �뷮 : " + v.capacity());	// Vector�� capacity : �ʱ� �� ���� : 10�� : null
		System.out.println("size : " + v.size());
		v.add("AA");
		v.add("AA");
		v.add("AA");
		v.add(100);
		System.out.println("size : " + v.size());
		System.out.println(v.toString());	//[AA, AA, AA, 100]�� ����ϵ��� Vertor�� toString�� �����ǰ� �Ǿ��ֱ���!
		//Array���� length
		//Collection���� size!!
		for(int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));	//�̰� �ܿ���. [i]�� �ƴ϶� .get(index)! == �����迭 arr[index]�� ����.
		}
		
		for(Object obj : v) {	//Vector �ȿ��� Ÿ���� �� �ٸ���...? >> Object�� ����
			System.out.println(obj);
		}
		// ���� ū�׸� (Object)�� �ܿ� ���ڿ��� ��� ����...? >> ���߿� �������ٵ�
		
		// �� �� �ϴ� �� ���ʸ� : ���� �ϳ� ��µ� ���Ϸ� ������Ʈ ����? >> Ÿ���� ����>>
		Vector<String> v2 = new Vector<String>();	// <Ÿ��>�� ���� �� �ִ� == String�� ���� �� �ִ� �׸�
		v2.add("hello");
		v2.add("world");
		v2.add("king");
		for(String str : v2) {
			System.out.println(str);
		}
		////////////////////////////////////////////////////////////////
		
		Vector v3 = new Vector();	//10��¥�� ��
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
		v3.add("A");	//10�� add
		System.out.println(v3.capacity());
		
		v3.add("A");	//11�� �� -> �˾Ƽ� �� �ø���.
		System.out.println(v3.capacity()); // -> �ϳ��� �߰��� �ֱ� ���� ���� �ι�� ������ : capacity = 20;
		System.out.println(v3.size());	// size�� �����迭�� length�� ����, �������� ������ �ǹ��Ѵ�~~
		
		
		
	}

}
