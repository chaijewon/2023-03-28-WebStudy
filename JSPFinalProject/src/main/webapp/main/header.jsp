<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">Food & Recipe</a></h1>
    </div>
    <div class="fl_right">
     <c:if test="${sessionScope.id==null }">
       <ul class="inline">
        <li><i class="fa fa-phone"></i><input type=text name=id class="input-sm" size=10></li>
        <li><i class="fa fa-envelope-o"></i> <input type=password name=pwd class="input-sm" size=10></li>
         <li><input type=button value="로그인" class="btn btn-sm btn-danger"></li>
       </ul>
      </c:if>
      
      <c:if test="${sessionScope.id!=null }">
       <ul class="inline">
        <li>${seesionScope.name }(${sessionScope.admin=='y'?"관리자":"일반사용자" }) 님 로그인중입니다</li>
         <li><input type=button value="로그아웃" class="btn btn-sm btn-danger"></li>
       </ul>
      </c:if>
    </div>
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">홈</a></li>
     <c:if test="${sessionScope.id==null }"><%-- 로그인이 안된 상태 --%>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
        </ul>
      </li>
      </c:if>
      <c:if test="${sessionScope.id!=null }"><%-- 로그인된 상태 --%>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="pages/gallery.html">회원수정</a></li>
          <li><a href="pages/full-width.html">회원탈퇴</a></li>
        </ul>
      </li>
      </c:if>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="pages/gallery.html">지역별 맛집찾기</a></li>
         <%--
                sessionScope.id
                session.getAttribute("id")
                
                ${name} 
                request.getAttribute("name")
          --%>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/full-width.html">맛집 예약</a></li>
          <li><a href="pages/sidebar-left.html">맛집 추천</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="pages/gallery.html">레시피</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/sidebar-left.html">레시피만들기</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">서울여행</a>
        <ul>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/full-width.html">자연 & 관광</a></li>
          <li><a href="pages/sidebar-left.html">쇼핑</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/sidebar-left.html">코스</a></li>
         </c:if>
        </ul>
      </li>
      <li><a href="#">레시피 스토어</a></li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="pages/gallery.html">공지사항</a></li>
          <li><a href="pages/full-width.html">자유게시판</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/sidebar-left.html">묻고답하기</a></li>
         </c:if>
        </ul>
      </li>
      <c:if test="${sessionScope.id!=null }">
        <c:if test="${sessionScope.admin=='n' }">
          <li><a href="#">마이페이지</a></li>
        </c:if>
        <c:if test="${sessionScope.admin=='y' }">
          <li><a href="#">관리자페이지</a></li>
        </c:if>
      </c:if>
      
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->

</body>
</html>