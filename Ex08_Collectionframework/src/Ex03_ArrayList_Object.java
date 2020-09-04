import java.util.ArrayList;

import kr.or.bit.Emp;

public class Ex03_ArrayList_Object {

	public static void main(String[] args) {
		// ��� �Ѹ��� ���弼��
		Emp emp = new Emp(100, "������", "¡��");
		System.out.println(emp.toString());

		// ��� 2�� ������ �迭��
		Emp[] emplist = { new Emp(100, "���", "�Ϲ�"), new Emp(200, "�ھ�", "�л�") };
		for (Emp e : emplist) {
			System.out.println(e.toString());
		}

		// 1�� �� �Ի縦 �߾��, �߰��ϼ��� -> ���� �迭�� ũ�Ⱑ �� �� �־����� ���� �Ұ����ϴ�
		// -> ArrayList�� ����ϸ� �����ϴ�!
		ArrayList elist = new ArrayList();
		elist.add(new Emp(1, "��", "IT"));
		elist.add(new Emp(2, "��", "IT"));
		// 1�� �� �Ի��޾��, �߰��ϼ���
		elist.add(new Emp(3, "��", "IT"));
		System.out.println(elist.size());
		System.out.println(elist.get(0).toString());
		
		// toString�� ����Ͽ� ����ϴ� ���� �� �� �ִ�.
		for(int i = 0; i < elist.size(); i++) {	//elist.toString�� ����.
			System.out.println(elist.get(i));
		}
		
		//toString�� ������� ���� ����� ���, �̸�, ������ ����غ�����.
		for(int i = 0; i < elist.size(); i++) {
			Object obj = elist.get(i);			// ArrayList�� ���׸� X-> ObjectŸ���̴�. �׷��� �ٿ�ĳ������ �ʿ�
			Emp e = (Emp)obj;					//�ٿ�ĳ���� �ؾ� ���δ�.
			System.out.println(e.getEmpno());
			System.out.println(e.getEname());
			System.out.println(e.getJob());
		}
		for(Object obj : elist) {
			Emp e1 = (Emp)obj;
		}
		// ���� ���� ���� �ڵ�� �����ڰ� ¥������...
		// �ٽ� �ٿ� ĳ����... �ݺ��� ���� ��...
		// Object ���� �ʰ� �� �� �ִ� ��� >> ���ʸ�
		// ���ʸ� : ��ü ������ �� Ÿ���� ������ �� �ִ�.
		// �������� ����� �ڵ�� 90%�� [���ʸ�]..
		ArrayList<Emp> list2 = new ArrayList<Emp>();	//EmpŸ���� ������ ArrayList��ü list2�� �����!
		list2.add(new Emp(1, "A", "IT"));
		
		System.out.println("-----------");
		for(Emp e : list2) {
			System.out.println(e.getEmpno());
		}
	}

}
