package com.sist.vo;
/*
 *   CREATE TABLE reserve_day(
		   rno NUMBER,
		   rday NUMBER CONSTRAINT rd_rday_nn NOT NULL,
		   time VARCHAR2(200) CONSTRAINT rd_time_nn NOT NULL,
		   CONSTRAINT rd_rno_pk PRIMARY KEY(rno)
		);
 */
public class ReserveDayVO {
    private int rno,rday;
    private String time;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getRday() {
		return rday;
	}
	public void setRday(int rday) {
		this.rday = rday;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
}
