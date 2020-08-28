package kr.or.bit;

// 자료구조 만들기 (Stack)
// 스택을 만들기 위해서는 : 저장공간이 확보되어야한다.(Array : Object[] stackarr)
// 위치 정보 : 저장되는 값이 있는 위치 ( index )
// 기능 : push, pop, empty, full

public class MyStack {
	private Object[] stackarr; // 저장소확보
	private int maxsize; // 옵션이지만, 나는 최대 크기를 여기에 담아보겠다.
	private int top; // 배열의 index값으로 삼고싶다.(위치정보)

	public MyStack(int maxsize) {
		this.maxsize = maxsize; // 난 파라미터로 입력받은 값을 최대 크기로 지정하겠다
		stackarr = new Object[maxsize]; // maxsize크기의 오브젝트타입 배열을 만들겠다.
		this.top = -1;
	}

	public boolean isEmpty() {
		return (top == -1); // 비어있으면 true 값을, 아니면 false
	}

	public boolean isFull() {
		return (top == maxsize - 1); // 방번호와 비교하기 위해서는 maxsize -1이어야 한다. -> true or false
	}

	public void push(Object object) {
		if (isFull()) {
			System.out.println("Stack is already full");
		} else {
			stackarr[++top] = object; // 처음 top = -1로 만들었기에, 0번쨰방을 가리키며 시작한다!, 또 push하면 ++이니 1번째방으로....
		}
	}

	public Object pop() {
		Object value = null;
		if (isEmpty()) {
			System.out.println("Stack is Empty");
		} else {
			value = stackarr[top];
			top--;
		}
		return value;
	}

	
	
}
