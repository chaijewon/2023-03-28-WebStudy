package com.sist.manager;
import java.util.*;
import java.net.*;
import java.io.*;
// https://www.kobis.or.kr/kobis/business/main/main.do
public class MovieManager {
   public static void main(String[] args) {
	   MovieManager m=new MovieManager();
	   String s=m.movieListData(1);
	   System.out.println(s);
  }
  public String movieListData(int no)
  {
	  String[] strUrl= {"","searchMainDailyBoxOffice.do",
			         "searchMainRealTicket.do",
			         "searchMainDailySeatTicket.do"};
	  String json="";
	  try
	  {
		  URL url=new URL("https://www.kobis.or.kr/kobis/business/main/"+strUrl[no]);
		  HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		  StringBuffer sb=new StringBuffer();
		  if(conn!=null)
		  {
			  BufferedReader in=
					 new BufferedReader(new InputStreamReader(
							 conn.getInputStream()));
			  while(true)
			  {
				  String s=in.readLine();
				  if(s==null) break;
				  sb.append(s);
			  }
			  
			  json=sb.toString();
		  }
	  }catch(Exception ex){}
	  return json;
  }
}
