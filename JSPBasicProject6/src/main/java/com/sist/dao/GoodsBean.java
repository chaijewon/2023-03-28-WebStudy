package com.sist.dao;
/*
 *   NO                                        NOT NULL NUMBER
 GOODS_NAME                                NOT NULL VARCHAR2(1000)
 GOODS_SUB                                          VARCHAR2(1000)
 GOODS_PRICE                               NOT NULL VARCHAR2(50)
 GOODS_DISCOUNT                                     NUMBER
 GOODS_FIRST_PRICE                                  VARCHAR2(20)
 GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
 GOODS_POSTER                                       VARCHAR2(260)
 HIT                                                NUMBER
 */
public class GoodsBean {
    private int no,hit,discount;
    private String name,sub,price,delivery,poster,fp;
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
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	   
}
