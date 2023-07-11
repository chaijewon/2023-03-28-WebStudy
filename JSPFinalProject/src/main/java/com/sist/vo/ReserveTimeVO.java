package com.sist.vo;
/*
 *    CREATE TABLE reserve_time(
		   tno NUMBER,
		   time VARCHAR2(20) CONSTRAINT rt_time_nn NOT NULL,
		   CONSTRAINT rt_tno_pk PRIMARY KEY(tno)
		);
 */
public class ReserveTimeVO {
    private int tno;
    private String time;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
}
