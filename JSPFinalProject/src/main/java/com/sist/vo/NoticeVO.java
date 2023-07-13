package com.sist.vo;
/*
 *   CREATE TABLE project_notice(
	  no NUMBER,
	  id VARCHAR2(20),
	  name VARCHAR2(51) CONSTRAINT pn_name_nn NOT NULL,
	  type NUMBER,
	  subject VARCHAR2(1000) CONSTRAINT pn_subject_nn NOT NULL,
	  content CLOB CONSTRAINT pn_cont_nn NOT NULL,
	  regdate DATE DEFAULT SYSDATE,
	  hit NUMBER DEFAULT 0,
	  CONSTRAINT pn_no_pk PRIMARY KEY(no),
	  CONSTRAINT pn_id_fk FOREIGN KEY(id)
	  REFERENCES project_member(id),
	  CONSTRAINT pn_type_ck CHECK(type IN(1,2,3))
	);
 */
import java.util.*;
public class NoticeVO {
    private int no,type,hit;
    private String id,name,subject,content;
    private Date regdate;
    private String dbday,notice_type;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public String getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
	   
}
