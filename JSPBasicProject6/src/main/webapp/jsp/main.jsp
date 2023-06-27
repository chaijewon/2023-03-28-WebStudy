<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%--
       301page : Session VS Cookie
       ----------------------------
       Session/Cookie의 차이점 (GET/POST) => MVC 
       ---------------------
             Session      Cookie
   클래스명  (내장객체)       (필요시마다 생성)
           HttpSession    Cookie 
           => HttpSession session=request.getSession() => interface
           => Cookie cookie=new Cookie() => 일반 클래스 
           ---------------------------------------------------------
   저장값    자바에서 사용되는 모든 클래스  문자열만 저장이 가능 
            (Object)
   저장위치   서버안에 저장     클라이언트 (브라우저) 저장 
            => 구분(sessionId)
               getId() => WebSocket
   보안      보안(O)         보안(X)
   사용처    로그인 (사용자 일부 정보)    최근방문 
            장바구니 
   -----------------------------------------------------------------
   Cookie 
     1) Cookie 생성 
        Cookie cookie=new Cookie(String key,String value) 
                      -----------------------------------
     2) 저장 기간 설정 
        cookie.setMaxAge(초)  => 0이면 삭제 
               -------------
     3) 저장 path지정 
        cookie.setPath("/") 
               --------------
     4) 저장된 쿠키를 클라이언트 브라우저로 전송 
        response.addCookie(cookie)
        
     5) 쿠키 읽기 
        Cookie[] cookies=request.getCookies()
                         -------------------- 저장된 쿠키 전체 읽기
     6) key 읽기    : getName()
     7) value 읽기  : getValue()
     ---------------------------
     *** 쿠키/세션 => 상태를 지속적으로 유지 
                    ---- 값 변경 (react/vue => state)
                    데이터 관리 
     Session (HttpSession)
     ---------------------- 내장 객체 
     서블릿 : request.getSession()
     JSP : 내장 객체 (session)
     ------------------------ 서버에 저장 
     => 전역변수 (모든 JSP에서 사용)
     => 서버에 저장 (보안이 뛰어나다)
     
     ***1) 세션 저장 
        session.setAttribute(String key,Object obj)
     2) 세션 저장 기간 
        session.setMaxInactiveInterval(60*5) => 30분 (1800초) => default
     ***3) 세션 읽기
        Object session.getAttribute(String key)
        *** 형변황이 필요하다 
     ***4) 세션 일부 삭제 
        session.removeAttribute(String key)
     ***5) 세션 전체 삭제 
        session.invalidate()
     6) 생성여부 확인 
        session.isNew()
     7) 세션에 등록된 sessionId (각 클라이언트마다 1개 생성) => 모든 정보 
        session.getId()
    ------------------------------------------------------------
      저장방식 => Map방식 (key,value) 
                value는 중복이 가능 
                key는 중복이 불가능  ====> 덮어 쓴다 
                Web에서 사용 => Map방식 (request, response,session,application)                 
                ***getParameter(String key)
       
 --%>
<%
    // 출력할 데이터 읽기 (오라클) 
    
    // 1. 사용자 요청 
    String strPage=request.getParameter("page");
    // => 첫페이지 : default로 설정 
    if(strPage==null)
    	strPage="1";
    // 2. DAO연결 => 데이터 읽기 
    int curpage=Integer.parseInt(strPage);
    FoodDAO dao=FoodDAO.newInstance();
    List<FoodBean> list=dao.foodListData(curpage);
    int totalpage=dao.foodTotalPage();
    // ------------------------- 자바(Model)
    // 3. 읽은 데이터 출력 => HTML
    // ------------------------ HTML(View) 나눠서 작업 => MV구조
    // 쿠키 읽기 
    Cookie[] cookies=request.getCookies();
    List<FoodBean> cList=new ArrayList<FoodBean>();
    if(cookies!=null)// 쿠키가 존재하면 
    {
    	for(int i=cookies.length-1;i>=0;i--)//최신부터 읽기 시작 
    	{
    		if(cookies[i].getName().startsWith("food_"))
    		{
    			String fno=cookies[i].getValue();
    			FoodBean vo=dao.foodDetailData(Integer.parseInt(fno));
    			cList.add(vo);
    		}
    	}
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
<body>
  <div class="container">
    <div class="row">
     <%-- 맛집 목록 --%>
     <%
        for(FoodBean vo:list)
        {
     %>
             <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="detail_before.jsp?fno=<%=vo.getFno()%>">
			        <img src="<%=vo.getPoster() %>"  style="width:100%">
			        <div class="caption">
			          <p><%=vo.getName() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
     <%
        }
     %>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <%-- 페이지 --%>
      <div class="text-center">
       <a href="main.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-danger">이전</a>
        <%=curpage %> page / <%=totalpage %> pages
       <a href="main.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
      </div>
    </div>
    <div style="height: 20px"></div>
    <h3>최신 방문 맛집</h3>
    <a href="all_delete.jsp" class="btn btn-xs btn-warning">전체 삭제</a>
    <a href="all_cookie.jsp" class="btn btn-xs btn-warning">더보기</a>
    <hr>
    <div class="row">
      <%-- 최신 방문 --%>
     
      
      <%
         int i=0;
         if(cookies!=null && cList.size()>0)
         {
      %>
              <table class="table">
              <tr>
              
      <%
             for(FoodBean vo:cList)
             {
            	 if(i>9) break;
      %>
                <td>
                 <img src="<%=vo.getPoster() %>" style="width: 100px;height: 100px" title="<%=vo.getName()%>">
                  <p>
                  <a href="cookie_delete.jsp?fno=<%=vo.getFno() %>" class="btn btn-sm btn-danger">삭제</a>
                 </td>
      <%
                i++;
             }
      %>
            
            </tr>
            </table>
      <%
         }
         else
         {
      %>
           <h3>쿠키가 없습니다</h3>
      <%
         }
      %>
    </div>
  </div>
</body>
</html>




