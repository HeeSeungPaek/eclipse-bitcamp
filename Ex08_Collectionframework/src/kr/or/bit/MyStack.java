package kr.or.bit;

// �ڷᱸ�� ����� (Stack)
// ������ ����� ���ؼ��� : ��������� Ȯ���Ǿ���Ѵ�.(Array : Object[] stackarr)
// ��ġ ���� : ����Ǵ� ���� �ִ� ��ġ ( index )
// ��� : push, pop, empty, full

public class MyStack {
	private Object[] stackarr; // �����Ȯ��
	private int maxsize; // �ɼ�������, ���� �ִ� ũ�⸦ ���⿡ ��ƺ��ڴ�.
	private int top; // �迭�� index������ ���ʹ�.(��ġ����)

	public MyStack(int maxsize) {
		this.maxsize = maxsize; // �� �Ķ���ͷ� �Է¹��� ���� �ִ� ũ��� �����ϰڴ�
		stackarr = new Object[maxsize]; // maxsizeũ���� ������ƮŸ�� �迭�� ����ڴ�.
		this.top = -1;
	}

	public boolean isEmpty() {
		return (top == -1); // ��������� true ����, �ƴϸ� false
	}

	public boolean isFull() {
		return (top == maxsize - 1); // ���ȣ�� ���ϱ� ���ؼ��� maxsize -1�̾�� �Ѵ�. -> true or false
	}

	public void push(Object object) {
		if (isFull()) {
			System.out.println("Stack is already full");
		} else {
			stackarr[++top] = object; // ó�� top = -1�� ������⿡, 0�������� ����Ű�� �����Ѵ�!, �� push�ϸ� ++�̴� 1��°������....
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
