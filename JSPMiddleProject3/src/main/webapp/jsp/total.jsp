<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%--
      EL => <%= %> 대체 => 가급적이면 자바코딩 제거 
      --- 표현식 (화면 출력) => System.out.println()
      형식) 
            ${표현식} => View(태그형으로 프로그램 제작) 
      표현식)
             연산자
               산술 연산 (+ , - , * , /(div) , %(mod)) 
                 + : 순수하게 덧셈만 가능 ( 문자열 결합 += )
                 *** null을 연산처리 => 0으로 취급 
                 *** "1" => 자동으로 Integer.parseInt() => 정수 
                 / : 정수/정수=실수
               비교 연산 ( == (eq), != (ne), < (lt) , > (gt) , <= (le) , >= (ge) )
                 ${10 eq 10} => ${10==10}
                 *** 날짜,문자열을 포함한다 
                         ---- (한글 => 가나다라... , 알파벳 => ABCD.. abcd..)
               논리 연산 ( && (and) , || (or) )
                         && => 예약 가능 날짜 
                         || => 예약이 불가능 날짜 
               삼항연산자 : 페이지 , select , radio ...
                         ${sex=='남자'?"checked":""}
                         ${조건?"값":"값"}
             내장객체 (일반 JSP ==> 자바/HTML 분리) 
                     -------     ------------ => 모든 처리는 자바 : 결과값만 출력
                       |
                     자바/HTML혼합 
               = requestScope => 
                 기존의 request + 추가 데이터 설정 => 
                 request.setAttribute("key", 값) : 오라클에서 얻은 값을 추가 
                 request.getAttribute("key")
                     |
                 ${requestScope.key} => ${key}
               = sessionScope => 
                 session에 저장된 값을 읽기 
                 ${sessionScope.key} => ${key}  => 우선순위:request=>session
                 
               = param : ${param.key} => request.getParameter("key")
               = paramValues : ${paramValues.key} => request.getParameterValues()
               
               ${자바의 일반 변수 사용이 안된다}
               String name="";
               ${name} => 출력이 안됨 
               request.setAttribute("name","")
                     |
                   ${name}
                   
               session.setAttribute("name","")
                     |
                   ${name}
         => 제어 : JSTL 
            ----------
            core : 제어문 , URL관련 , 변수 설정 (request.setAttribute())
                   제어문 
                   조건문 
                   -----
                      <c:if test="조건식">
                         true일때 처리 문장 
                      </c:if> 
                      => 다중 조건문 , if~else가 없다 
                      => 자바
                         if(조건식)
                         {
                           
                         }
                   선택문
                   -----
                      <c:choose>
                        <c:when test="">처리문장</c:when>
                        <c:when test="">처리문장</c:when>
                        <c:when test="">처리문장</c:when>
                        <c:otherwise>default</c:otherwise>
                      </c:choose>
                       => switch , 다중 if 
                   반복문 
                     <c:forEach> => for
                       for(int i=0;i<=10;i++)
                                                        i<=10
                       => <c:forEach var="i" begin="0" end="10" step="1">
                                                       -------- 포함
                          *** 단점 
                          for(int i=10;i>=0;i--)
                          ---------------------- <c:forEach>가 없다 
                                                 => i++
                     <c:forEach> => 향상된 for (for-each)
                         for(BoardVO vo:list)
                         => <c:forEach var="vo" items="list"> (가장 많이 사용)
                     <c:forTokens> => StringTokenizer
                     
                     URL : 화면 이동 
                     -------------
                      response.sendRedirect(url) 
                       => <c:redirect url="url">
                      request.setAttribute("a",값)
                       => <c:set var="a" value="값">
                      out.println()
                       => <c:out value="">
                       => 자바스크립트에서 자바데이터를 받아서 출력 
                          jquery => $()
                                    ${}
            fmt : 변환 (날짜변환 , 숫자변환: DecimalFormat)
                       ------   -------     |
                       SimpleDateFormat   <fmt:formatNumber value="" pattern="999,999">
                             |
                         <fmt:formatDate value="" pattern="yyyy-MM-dd">
                       
            fn : String메소드 처리 
                 ${fn:length(문자열)}
                 ${fn:substring(문자열 , start, end)}
            ----------------------------------------
            sql : DAO
            xml : OXM 
                  => 자바 자체에서 처리 (사용빈도가 거의 없다)
            ----------------------------------------
 --%>
<%-- import --%>
<%-- 제어문 , URL ,변수선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- format --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- fnuction: fn --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
      prefix => 사용자 결정이 가능 
      prefix="c"
      
      <c:~>
      
      prefix="core"
      <core:~>
 --%>
<%
    // 데이터 설정 => 오라클 
    List<String> names=new ArrayList<String>();
    names.add("홍길동");
    names.add("심청이");
    names.add("이순신");
    names.add("박문수");
    names.add("강감찬");
    // EL은 사용할 수 없다 (EL ${} => request,session에 저장을 해야 사용이 가능)
    request.setAttribute("names", names);    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>자바를 이용한 for문</h1>
  <h3>이름 목록</h3>
  <ul>
    <%
     for(String name:names)
     {
    %>
        <li><%= name %></li>
    <%
     }
    %>
  </ul>
  <h3>JSTL출력</h3>
  <ul>
    <c:forEach var="name" items="${names }">
      <li>${name }</li>
    </c:forEach>
  </ul>
  <h3>자바 (StringTokenizer)</h3>
  <ul>
     <%
       String color="red,blue,green,yellow,black";
       StringTokenizer st=new StringTokenizer(color,",");
       while(st.hasMoreTokens())
       {
     %>
          <li><%=st.nextToken() %></li>
     <%
       }
     %>
  </ul>
  <h3>JSTL (forTokens)</h3>
  <ul>
    <%--
       var="color" : st.nextToken()
       items="red,blue,green,yellow,black"  : 문자열
       delims="," : 구분자
     --%>
    <c:forTokens var="color" items="red,blue,green,yellow,black" 
       delims=",">
       <li>${color }</li>
    </c:forTokens>
  </ul>
</body>
</html>