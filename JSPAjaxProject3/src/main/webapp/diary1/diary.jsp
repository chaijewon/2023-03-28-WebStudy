<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.text.*"%>
<%
    // Model 1 => JSP에서 처리 
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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 500px;
}
h1{
    text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#year').change(function(){
		$('#frm').submit()
	})
	$('#month').change(function(){
		$('#frm').submit()
	})
})
</script>
</head>
<body>
  <div class="container">
    <h1><%=year %>년도 <%=month %>월</h1>
    <div class="row">
    <form method="post" action="diary.jsp" id="frm">
    <table class="table">
     <tr>
       <td>
         <select class="input-sm" name="year" id="year">
        <%
           for(int i=2023;i<=2030;i++)
           {
        %>
             <option <%=year==i?"selected":"" %>><%=i %></option>
        <%
           }
        %>
        </select>년도&nbsp;
        <select class="input-sm" name="month" id="month">
        <%
           for(int i=1;i<=12;i++)
           {
        %>
             <option <%=month==i?"selected":"" %>><%=i %></option>월
        <%
           }
        %>
        </select>
       </td>
     </tr> 
    </table>
    </form>
     <table class="table">
       <tr>
        <%
           for(int i=0;i<7;i++)
           {
        	 String s="black";
        	 if(i==0)
        		 s="red";
        	 else if(i==6)
        		 s="blue";
        	 else 
        		 s="black";
        	 
        %>
             <th class="text-center" style="color:<%=s%>"><%=strWeek[i] %></th>
        <%
           }
        %>
       </tr>
       <%
           for(int i=1;i<=lastday;i++)
           {
        	   if(i==1)
        	   {
       %>
                  <tr>
       <%
        		   for(int j=0;j<week;j++)
        		   {
      %>
                      
                      <td class="text-center" height="35">&nbsp;</td>
      <%
        		   }
        	   }
        	   
       %>
              <td class="text-center" height="35"><%=i %></td>
       <%
              week++;
              if(week>6)
              {
            	  week=0;
       %>
                  </tr>
                  <tr>
       <%
              }
           }
       %>
       </tr>
     </table>
    </div>
  </div>
</body>
</html>