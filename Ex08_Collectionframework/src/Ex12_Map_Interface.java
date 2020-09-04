import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ex12_Map_Interface {

	public static void main(String[] args) {
		// Map �������̽� ����
		// Map >> (Ű , ��) ���� ������ ������ �迭
		// ex) ������ȣ (02,����) , (031,���)
		// (key , value)
		// key :�ߺ�(x)
		// value: �ߺ�(0)

		// generic ����
		// Map �������̽� ����
		// ������ : HashTable (����ȭ)
		// �Ź��� : HashMap (����ȭ �������� �ʾƿ�) : ���ɰ���
		// ����ȭ (Thread �н� : �������� stack ���� ....)

		HashMap map = new HashMap();
		map.put("Tiger", "1004");
		map.put("scott", "1004");
		map.put("superman", "1004");
		
		System.out.println(map.containsKey("tiger")); // ��ҹ��� ���� (false)
		System.out.println(map.containsKey("scott"));
		System.out.println(map.containsValue("1004"));

		System.out.println(map.get("Tiger")); // key >> value >> get()
		System.out.println(map.get("hong")); // key >> ������ >> null
		
		map.put("Tiger", "1007"); // ���� key���� value replace
		System.out.println(map.get("Tiger")); // 1007
		System.out.println(map.size()); // 3

		Object value = map.remove("superman");		// ���� �����
		System.out.println("value : " + value);
		System.out.println(map.toString());

		HashMap<String, String> strmap = new HashMap<String, String>();
		strmap.put("����", "1111");
		String v = strmap.remove("����");
		
		// ���� (����) ^^!
		Set set = map.keySet(); // keySet() : �� Set�� �����ϰ� �ִ� ��ü�� �ּҸ� ����
								// keySet() >> ���������� Set�� new�Ѵ�!
								// keySet() : key ��ü�� �� ��Ƽ� Set�� �ϳ� ����°�!
		System.out.println(set);
		// ����(x) , �ߺ�(x)
		Iterator it = set.iterator();	//���������� iterator�� �����ϴ� ��ü�� �����
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		Collection clist = map.values();			//���⵵?
		System.out.println(clist.toString());

	}

}