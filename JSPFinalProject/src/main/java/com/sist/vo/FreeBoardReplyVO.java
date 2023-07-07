package com.sist.vo;
import java.util.*;
/*
 *   NO         NOT NULL NUMBER       
BNO                 NUMBER       
ID                  VARCHAR2(20) 
NAME       NOT NULL VARCHAR2(51) 
MSG        NOT NULL CLOB         
REGDATE             DATE         
GROUP_ID            NUMBER    ==> 답변끼리 모아주는 역할     
GROUP_STEP          NUMBER    ==> 답변출력 순서 
GROUP_TAB           NUMBER    ==> 어느 댓글에 대한 표시
--------------------------------------------------- 댓글  
ROOT                NUMBER    ==> 상위 댓글번호  
DEPTH               NUMBER    ==> 댓글 갯수 
--------------------------------------------------- 댓글 삭제 
 */
public class FreeBoardReplyVO {
    private int no,bno,group_id,group_step,group_tab,root,depth;
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
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
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
