package com.sist.vo;
/*
 *     CREATE TABLE food_jjim(
	   no NUMBER,
	   id VARCHAR2(20),
	   fno NUMBER,
	   CONSTRAINT fj_no_pk PRIMARY KEY(no),
	   CONSTRAINT fi_id_fk FOREIGN KEY(id)
	   REFERENCES project_member(id),
	   CONSTRAINT fi_fno_fk FOREIGN KEY(fno)
	   REFERENCES food_house(fno)
);
 */
public class FoodJJimVO {
    private int no,fno;
    private String id;
    private String name,poster,tel;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
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
   
}
