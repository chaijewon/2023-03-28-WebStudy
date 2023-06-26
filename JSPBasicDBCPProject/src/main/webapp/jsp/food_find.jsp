<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%--
      293page DBCP => DataBaseConnectionPool
      1. 연결 / 해제 반복 
         => 시간 소모가 많다 (오라클 연결시에 시간이 많이 소모)
            ------------- 연결시 시간 소모를 절약 
         => 미리 Connection객체를 생성해서 저장후에 사용 
         => 일반적으로 웹프로그램에서는 일반화 
         => Connection객체 생성을 제한 
         => 사용후에는 재사용이 가능 
      2. Connection을 미리 생성하기 때문에 => Connection객체 관리가 쉽다 
      3. 쉽게 서버가 다운되지 않는다 
      4. 라이브러리가 만들어져 있다 (commons-dbcp,commons-pool)
      
      ==> 280page 
          목적 : 웹 사용자는 응답 시간 (빠르게) 
                -----------------
                 DB연결 (원할) => BackEnd가 빠르다 (FrontEnd)
                 효율적으로 데이터베이스 연동 
          DBCP 
          ----
          방법 => 1. Connection 객체 생성 (maxActive:최대,maxIdle:풀)
                 2. POOL영역에 저장 
                 3. 사용자가 요청시에 Connection의 주소를 얻어온다 
                 4. 사용자 요청에 따라 수행 
                 5. 반드시 POOL안에 Connection객체를 반환 
                ----------------------------------------------
                 1. server.xml => Connection객체 생성 
                 2. 코딩 방법 
                    -------
                    1) getConnection() : 미리 생성된 Connection 객체 얻기
                    2) disConnection() : 반환 
                    3) 기능 => 이전하고 동일 
                    *** 보안이 좋다 
                    
                    
                 
 --%>
<%
    //0 한글 변환 (디코딩)
    request.setCharacterEncoding("UTF-8");
    //1. 사용자가 전송한 데이터를 받는다 (page) 
    String fd=request.getParameter("addr");
    if(fd==null)
    	fd="마포";
    String strPage=request.getParameter("page");
    //2. 실행과 동시에 페이지 전송이 불가능 => 첫페이지는 default 설정 (1)
    if(strPage==null)
    	strPage="1";
    //3. 현재 페이지 지정
    int curpage=Integer.parseInt(strPage);
    //4. 현재페이에 대한 데이터 읽기 (DAO=>오라클)
    FoodDAO dao=FoodDAO.newInstance();
    List<FoodBean> list=dao.foodFindData(curpage, fd);
    //5. 총페이지 읽기 
    int totalpage=dao.foodTotalpage(fd);
    //6. 블록별 처리 
    final int BLOCK=10;
    // [1] ~  [10]  ,  [11] ~ [20]
    //7. 시작위치
    /*   curpage 1~10 startPage 1
    */
    int startPage=((curpage-1)/BLOCK*BLOCK)+1;// 1 11 21....
    //8. 끝 위치
    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// 10 20 30....
    if(endPage>totalpage)
    {
    	endPage=totalpage;
    }
    
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
  width:960px;
}
</style>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').click(function(){
		let addr=$('#addr').val();
		if(addr.trim()==="")
		{
			$('#addr').focus();
			return;
		}
		$('#frm').submit(); // 유효성 검사 (강제 입력)
	})
})
</script>
<body>
  <div class="container">
    <div class="row">
     <table class="table">
       <tr>
        <td>
         <form method="post" action="food_find.jsp" id="frm">
          <input type=text id="addr" name=addr size=20 class="input-sm" value="<%=fd%>">
          <input type="button" id="findBtn" value="검색" class="btn btn-sm btn-danger">
         </form>
        </td>
       </tr>
     </table>
    </div>
    <div class="row">
      <%
         for(FoodBean vo:list)
         {
      %>
             <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster() %>"  style="width:100%">
			        <div class="caption">
			          <p><%=vo.getName() %></p>
			          <p><%=vo.getAddress() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
      <%
         }
      %>
    </div>
    <div class="row">
      <div class="text-center">
         <ul class="pagination">
          <%
              if(startPage>1) // 1 11 21...
              {
          %>
                <li><a href="food_find.jsp?page=<%=startPage-1%>&addr=<%=fd%>">&lt;</a></li>
          <%
              }
          %>
          <%
             for(int i=startPage;i<=endPage;i++)
             {
          %>
		  <li <%=i==curpage?"class=active":"" %>><a href="food_find.jsp?page=<%=i%>&addr=<%=fd%>"><%=i %></a></li>
		  <%
             }
		  %>
		  <%
		      if(endPage<totalpage)
		      {
		  %>
		         <li><a href="food_find.jsp?page=<%=endPage+1%>&addr=<%=fd%>">&gt;</a></li>
		  <%
		      }
		  %>
		</ul>
      </div>
    </div>
  </div>
</body>
</html>