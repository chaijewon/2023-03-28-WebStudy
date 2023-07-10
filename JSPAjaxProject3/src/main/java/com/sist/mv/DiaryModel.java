package com.sist.mv;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.text.*;
public class DiaryModel {
   public void diaryData(HttpServletRequest request)
   {
	    String strYear=request.getParameter("year");
	    String strMonth=request.getParameter("month");
	    Date date=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	    String today=sdf.format(date);
	    StringTokenizer st=new StringTokenizer(today,"-");
	    String sy=st.nextToken();
	    String sm=st.nextToken();
	    String sd=st.nextToken();
	    
	    if(strYear==null)
	    	strYear=sy;
	    
	    if(strMonth==null)
	    	strMonth=sm;
	    
	    int year=Integer.parseInt(strYear);
	    int month=Integer.parseInt(strMonth);
	    int day=Integer.parseInt(sd);
	    // 요일 
	    String[] strWeek={"일","월","화","수","목","금","토"};
	    
	    Calendar cal=Calendar.getInstance();
	    cal.set(Calendar.YEAR,year);
	    cal.set(Calendar.MONTH,month-1);
	    cal.set(Calendar.DATE,1); // 1일자 
	    
	    int week=cal.get(Calendar.DAY_OF_WEEK);// 요일 구하기
	    int lastday=cal.getActualMaximum(Calendar.DATE);// 각달의 마지막 일 
	    
	    week=week-1;
	    
	    request.setAttribute("year", year);
	    request.setAttribute("month", month);
	    request.setAttribute("day", day);
	    request.setAttribute("week", week);
	    request.setAttribute("lastday", lastday);
	    request.setAttribute("strWeek", strWeek);
   }
}
