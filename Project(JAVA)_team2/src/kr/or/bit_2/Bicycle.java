/*
*@Class : Bicycle
*@Date : 2020. 09. 13
*@Author : 박선우, 임소희
*@Desc : ManageSystem 클래스 메소드 addBicycle() 실행 시 생성되는 Bicycle 객체
*/

package kr.or.bit_2;

import java.io.Serializable;

public class Bicycle implements Serializable {
	private String status;
	private int index;

	Bicycle() {
		this.status = "대여가능";
		this.index = 1000;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "자전거 [번호 = " + index + "번, 상태 =" + status + "]";
	}
	
	
}