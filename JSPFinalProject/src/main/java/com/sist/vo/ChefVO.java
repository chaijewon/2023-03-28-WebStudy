package com.sist.vo;
/*
 *   CHEF      NOT NULL VARCHAR2(500) 
POSTER    NOT NULL VARCHAR2(260) 
MEM_CONT1          VARCHAR2(100) 
MEM_CONT2          VARCHAR2(100) 
MEM_CONT3          VARCHAR2(100) 
MEM_CONT4          VARCHAR2(100) 
 */
public class ChefVO {
    private String chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7;

	public String getChef() {
		return chef;
	}

	public void setChef(String chef) {
		this.chef = chef;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getMem_cont1() {
		return mem_cont1;
	}

	public void setMem_cont1(String mem_cont1) {
		this.mem_cont1 = mem_cont1;
	}

	public String getMem_cont2() {
		return mem_cont2;
	}

	public void setMem_cont2(String mem_cont2) {
		this.mem_cont2 = mem_cont2;
	}

	public String getMem_cont3() {
		return mem_cont3;
	}

	public void setMem_cont3(String mem_cont3) {
		this.mem_cont3 = mem_cont3;
	}

	public String getMem_cont7() {
		return mem_cont7;
	}

	public void setMem_cont7(String mem_cont7) {
		this.mem_cont7 = mem_cont7;
	}
    
}
