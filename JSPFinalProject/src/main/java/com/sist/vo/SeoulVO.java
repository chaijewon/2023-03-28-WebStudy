package com.sist.vo;
/*
 *   NO                                        NOT NULL NUMBER
 TITLE                                     NOT NULL VARCHAR2(1000)
 POSTER                                    NOT NULL VARCHAR2(1000)
 MSG                                                CLOB
 ADDRESS                                   NOT NULL VARCHAR2(500)
 */
public class SeoulVO {
  private int no,hit;
  private String title,poster,msg,address;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	  
}
