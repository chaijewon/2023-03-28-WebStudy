package com.sist.vo;
/*
 *   CREATE TABLE reserve_info(
	   no NUMBER,
	   id VARCHAR2(20),
	   fno NUMBER,
	   rday VARCHAR2(30) CONSTRAINT ri_day_nn NOT NULL,
	   rtime VARCHAR2(30) CONSTRAINT ri_time_nn NOT NULL,
	   inwon VARCHAR2(30) CONSTRAINT ri_inwon_nn NOT NULL,
	   rok CHAR(1) DEFAULT 'n',
	   regdate DATE DEFAULT SYSDATE,
	   CONSTRAINT ri_no_pk PRIMARY KEY(no),
	   CONSTRAINT ri_id_fk FOREIGN KEY(id)
	   REFERENCES project_member(id),
	   CONSTRAINT ri_fno_fk FOREIGN KEY(fno)
	   REFERENCES food_house(fno)
	);
	seq => ri_no_seq
 */
import java.util.*;
public class ReserveVO {
    private int no,fno;
    private String id,rday,rtime,inwon,rok;
    private Date regdate;
    private String poster,name,tel,dbday;// subquery => function
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRday() {
		return rday;
	}
	public void setRday(String rday) {
		this.rday = rday;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getInwon() {
		return inwon;
	}
	public void setInwon(String inwon) {
		this.inwon = inwon;
	}
	public String getRok() {
		return rok;
	}
	public void setRok(String rok) {
		this.rok = rok;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	  
}
