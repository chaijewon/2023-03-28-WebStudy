package com.sist.vo;
/*
 *   NO     NOT NULL NUMBER         
		TITLE  NOT NULL VARCHAR2(1000) 
		POSTER NOT NULL VARCHAR2(260)  
		CHEF   NOT NULL VARCHAR2(300)  
		LINK            VARCHAR2(260)  
		HIT             NUMBER   
 */
public class RecipeVO {
    private int no,hit;
    private String title,poster,chef,link;
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
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
    
}
