package com.sist.vo;
/*
 *  NO                                        NOT NULL NUMBER
    BNO                                                NUMBER
    ID                                                 VARCHAR2(20)
    NAME                                      NOT NULL VARCHAR2(51)
    MSG                                       NOT NULL CLOB
    REDATE                                             DATE
 */
import java.util.*;
/*
 *   자바빈즈 => 변수 (읽기/쓰기) => getter/setter
 */
public class ReplyVO {
    private int no,bno;
    private String id,name,msg,dbday;
    private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
    
}
