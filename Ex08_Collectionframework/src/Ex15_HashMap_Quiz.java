import java.util.HashMap;
import java.util.Set;

//Thread  할때 .....
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
		return "문제:" + this.question + " 쓴답:" + this.answer + " 채점:" + this.result;
	}
}

public class Ex15_HashMap_Quiz {

	public static void main(String[] args) {
		HashMap<String, String> quiz = new HashMap<>();
		quiz.put("1+1", "2");
		quiz.put("1+2", "3");
		quiz.put("1+3", "4");

		for (int i = 0; i < quiz.size(); i++) {
			Set set = quiz.keySet(); // keySet() : Set을 구현하고 있는 객체의 주소를 리턴
			System.out.println(set.toArray());
			Object[] obj = set.toArray(); // http://asuraiv.blogspot.com/2015/06/java-list-toarray.html
			// System.out.println(obj.length);
			System.out.println("문제:" + obj[i]);
			System.out.println(quiz.keySet().toArray()[i]);

			System.out.println(quiz.get(quiz.keySet().toArray()[i]));
			//quiz.keySet().toArray()[i] > quiz맵의 키값들을 내부적으로 new Set하여 키 객체들을 Set에
			//넣고, 그 키들을 배열로 변환해 [i]번 방의 키값들에 대해
			// quiz.get(키) >> 인자 자리의 키에 해당하는 Value를 볼 수 있게된다.
		}

	}

}
