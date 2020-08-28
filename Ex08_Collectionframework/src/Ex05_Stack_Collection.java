import java.util.Stack;

import kr.or.bit.Coin;
import kr.or.bit.MyStack;

public class Ex05_Stack_Collection {

	public static void main(String[] args) {
		//Java API는 Stack 자료구조를 제공
		
		Stack s = new Stack();
		s.push("A");
		s.push("B");
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		//System.out.println(s.pop());  java.util.EmptyStackException예외가 발생하게 된다.
		System.out.println("============");
		//직접 우리가 만든 스택
		
		MyStack my = new MyStack(3);
		System.out.println(my.isFull());
		
		my.push(10);
		my.push(20);
		my.push(30);
		System.out.println("지금 꽉 찻니?" + my.isFull());
		System.out.println(my.pop());
		System.out.println(my.pop());
		System.out.println(my.pop());
		
		//동전 박스
		Stack<Coin> coinbox = new Stack<Coin>();
		
		coinbox.add(new Coin(100));
		coinbox.add(new Coin(50));
		coinbox.add(new Coin(500));
		coinbox.add(new Coin(10));
		
		while(!coinbox.isEmpty()) {	//isEmpty = false > !isEmpty = true 실행
			Coin coin = coinbox.pop();
			System.out.println("동전 : " + coin.getValue() + "원");
		}

		
		
	}

}
