package kr.or.bit;

public class CopyEmp {
	
	private int empno;		//CopyEmp의 필드
	private String ename;
	private int sal;

	public CopyEmp(int empno, String ename, int sal) {	// 오버로딩된 생성자만 사용가능
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}

	public int getEmpno() {	// 게터세터
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {	//toString 오버라이드
		return "CopyEmp [empno=" + empno + ", ename=" + ename + ", sal=" + sal + "]";
	}

}
