package com.sist.dao;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        	for(int i=1;i<=10;i++)
        	{
        		try {
        		  int a=(int)(Math.random()*3);
        		  int res=i/a;
        		  System.out.println("i="+i+",a="+a+",res="+res);
        		}catch(Exception ex) {System.out.println("0발생");}
        	}
        }catch(Exception ex) {}
	}

}
