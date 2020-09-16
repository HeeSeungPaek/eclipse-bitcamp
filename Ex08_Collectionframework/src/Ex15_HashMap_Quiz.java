import java.util.HashMap;
import java.util.Set;

//Thread  �Ҷ� .....
class QuizInfo {
	String question;
	String answer;
	String result;

	public QuizInfo(String question, String answer, String result) {
		this.question = question;
		this.answer = answer;
		this.result = result;
	}

	public String toString() {
		return "����:" + this.question + " ����:" + this.answer + " ä��:" + this.result;
	}
}

public class Ex15_HashMap_Quiz {

	public static void main(String[] args) {
		HashMap<String, String> quiz = new HashMap<>();
		quiz.put("1+1", "2");
		quiz.put("1+2", "3");
		quiz.put("1+3", "4");

		for (int i = 0; i < quiz.size(); i++) {
			Set set = quiz.keySet(); // keySet() : Set�� �����ϰ� �ִ� ��ü�� �ּҸ� ����
			System.out.println(set.toArray());
			Object[] obj = set.toArray(); // http://asuraiv.blogspot.com/2015/06/java-list-toarray.html
			// System.out.println(obj.length);
			System.out.println("����:" + obj[i]);
			System.out.println(quiz.keySet().toArray()[i]);

			System.out.println(quiz.get(quiz.keySet().toArray()[i]));
			//quiz.keySet().toArray()[i] > quiz���� Ű������ ���������� new Set�Ͽ� Ű ��ü���� Set��
			//�ְ�, �� Ű���� �迭�� ��ȯ�� [i]�� ���� Ű���鿡 ����
			// quiz.get(Ű) >> ���� �ڸ��� Ű�� �ش��ϴ� Value�� �� �� �ְԵȴ�.
		}

	}

}
