/*
*@Class : Bicycle
*@Date : 2020. 09. 13
*@Author : �ڼ���, �Ӽ���
*@Desc : ManageSystem Ŭ���� �޼ҵ� addBicycle() ���� �� �����Ǵ� Bicycle ��ü
*/

package kr.or.bit_2;

import java.io.Serializable;

public class Bicycle implements Serializable {
	private String status;
	private int index;

	Bicycle() {
		this.status = "�뿩����";
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
		return "������ [��ȣ = " + index + "��, ���� =" + status + "]";
	}
	
	
}