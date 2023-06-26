package com.sist.dao;
/*
FNO                                       NOT NULL NUMBER
NAME                                      NOT NULL VARCHAR2(1000)
SCORE                                     NOT NULL NUMBER(2,1)
ADDRESS                                   NOT NULL VARCHAR2(1000)
TEL                                       NOT NULL VARCHAR2(20)
TYPE                                      NOT NULL VARCHAR2(50)
PRICE                                              VARCHAR2(60)
TIME                                               VARCHAR2(60)
PARKING                                            VARCHAR2(60)
MENU                                               VARCHAR2(1000)
HIT                                                NUMBER
POSTER                                             VARCHAR2(2000)
*/
public class FoodBean {
	private int fno,hit;
    private double score;
    private String name,address,tel,type,price,time,parking,menu,poster;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
  
}
