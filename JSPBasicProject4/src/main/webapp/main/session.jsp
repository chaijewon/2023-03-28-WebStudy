<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   request / response / session => 웹 개발의 핵심 
   -------------------- ------- 프로그램의 실행하는 중 
   session은 서버에 저장 : 사용자의 정보를 지속적으로 관리 
   -------- 1) 장바구니 , 결재 ,예약 , 추천 ...
            2) session에 저장이 되면 모든 JSP에서 사용이 가능 (전역변수)
   클래스명 => HttpSession 
             클라이언트마다 1개  생성 => id가 부여 (구분자) 
                                    --- sessionId => 채팅 , 상담..
   주요메소드 
           String getId() : 세션마다 저장 구분자 
           setMaxinactiveInterval() => 저장 기간을 설정 
            => 기본 default => 1800 (초단위 : 30분)
            => 경매 
           isNew() : ID가 할당이 된것이지 여부 확인 
             => 장바구니 
           invalidate() : session에 저장된 모든 내용을 지운다 
                          로그아웃
           setAttribute():session에 정보 저장 
           getAttribute() : 저장된 데이터 읽기
           removeAttribute() : 저장된 데이터 일부를 지울 사용 
   
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>HttpSession(session):177page</h1>
  <table class="table">
    <tr>
      <td width=20% class="text-center">클래스명</td>
      <td width=80%>HttpSession(request로 생성이 가능:request.getSession())
        <br>
        <sub>request: session/cookie</sub>
      </td>
    </tr>
    <tr>
      <td width=20% class="text-center">주요기능</td>
      <td width=80%>
        <ul>
          <li>서버에서 클라이언트의 정보 저장</li>
          <li>저장된 정보를 지속적으로 관리</li>
          <li>전역변수로 사용이 가능 (모든 JSP에서 사용이 가능)</li>
          <li>사용처:예약 , 장바구니 , 구매 ..</li>
        </ul>
      </td>
    </tr>
    <tr>
      <td width=20% class="text-center">주요메소드</td>
      <td width=80%>
        <ul>
         <li>***저장:setAttribute(String key , Object obj)</li>
         <li>***저장 데이터 읽기: Object getAttribute(String key):사용시 형변환</li>
         <li>***전체 데이터 해제: invalidate()</li>
         <li>***일부 정보 삭제: removeAttribute(String key)</li>
         <li>저장기간설정:setMaxinactiveInterval(int time):1/1000=>default 1800</li>
         <li>저장되는 기간 : 30분</li>
         <li>생성여부를 확인: isNew()</li>
         <li>클라이언트당 1개의 session 공간:getId():websocket</li>
         <li>해당 세션이 생성된 시간:getCreateTime()</li>
        </ul>
      </td>
    </tr>
  </table>
</body>
</html>







