package com.sist.vo;
import java.util.*;
/*
 *  CREATE TABLE project_member(
   id VARCHAR2(20),
   pwd VARCHAR2(10) CONSTRAINT pm_pwd_nn NOT NULL,
   name VARCHAR2(51) CONSTRAINT pm_name_nn NOT NULL,
   sex VARCHAR2(10),
   birthday VARCHAR2(20) CONSTRAINT pm_bday_nn NOT NULL,
   email VARCHAR2(120),
   post VARCHAR2(10) CONSTRAINT pm_post_nn NOT NULL,
   addr1 VARCHAR2(300) CONSTRAINT pm_addr1_nn NOT NULL,
   addr2 VARCHAR2(300),
   phone VARCHAR2(30) ,
   content CLOB,
   admin CHAR(1) DEFAULT 'n',
   regdate DATE DEFAULT SYSDATE,
   CONSTRAINT pm_id_pk PRIMARY KEY(id),
   CONSTRAINT pm_sex_ck CHECK(sex IN('남자','여자')),
   CONSTRAINT pm_email_uk UNIQUE(email),
   CONSTRAINT pm_phone_uk UNIQUE(phone)
)

 */
public class MemberVO {
    private String id,pwd,name,sex,birthday,email,post,addr1,addr2,phone,
                 content,admin,msg;
    private Date regdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	  
}
