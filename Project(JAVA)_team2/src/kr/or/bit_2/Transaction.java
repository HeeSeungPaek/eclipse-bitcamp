/*
*@Class : Transaction
*@Date : 2020. 09. 13
*@Author : 박선우, 임소희
*@Desc : 자전거 대여, 반납 시 생성되는 명세 Transaction 객체
*/

package kr.or.bit_2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction implements Serializable{
//거래정보 담기는 곳

	private String transactionTime;
	private String id;
	private String kind;
	private int amount;
	private Calendar cal;
	private long timeMili;	// 대여시간과 반납시간의 시간차를 구하기 위한 Instance Variable
	
	Transaction(String id, String kind, int amount){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
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
		return "ID = "+id+" | " + " 종류 =" + kind + " | 가격 =" + amount + "\n";
	}

}