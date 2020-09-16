/*
*@Class : Transaction
*@Date : 2020. 09. 13
*@Author : �ڼ���, �Ӽ���
*@Desc : ������ �뿩, �ݳ� �� �����Ǵ� �� Transaction ��ü
*/

package kr.or.bit_2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction implements Serializable{
//�ŷ����� ���� ��

	private String transactionTime;
	private String id;
	private String kind;
	private int amount;
	private Calendar cal;
	private long timeMili;	// �뿩�ð��� �ݳ��ð��� �ð����� ���ϱ� ���� Instance Variable
	
	Transaction(String id, String kind, int amount){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd�� HH:mm:ss");
		cal = Calendar.getInstance();
		this.transactionTime = dateFormat.format(cal.getTime());
		this.timeMili = System.currentTimeMillis();	
		this.id = id;
		this.kind = kind;
		this.amount = amount;
	}

	public String getId(){
		return id;
	}

	public int getAmount(){
		return amount;
	}

	public long getTimeMili(){
		return timeMili;
	}

	@Override
	public String toString(){
		return "ID = "+id+" | " + " ���� =" + kind + " | ���� =" + amount + "\n";
	}

}