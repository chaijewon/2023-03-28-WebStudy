package com.sist.vo;
/*
 *   CREATE TABLE food_like(
	   no NUMBER,
	   id VARCHAR2(20),
	   fno NUMBER,
	   CONSTRAINT fl_no_pk PRIMARY KEY(no),
	   CONSTRAINT fl_id_fk FOREIGN KEY(id)
	   REFERENCES project_member(id),
	   CONSTRAINT fl_fno_fk FOREIGN KEY(fno)
	   REFERENCES food_house(fno)
	);
 */
public class FoodLikeVO {
    private int no,fno;
    private String id;
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
