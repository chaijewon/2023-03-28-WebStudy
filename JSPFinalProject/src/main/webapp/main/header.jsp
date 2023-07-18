<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logoutBtn').click(function(){
		location.href="../member/logout.do";
	})
	$('#logBtn').click(function(){
		// 유효성 검사 => 반드시 입력
		let id=$('#id').val();
		if(id.trim()=="")
		{
			$('#id').focus();
			return;
		}
		
		let pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
			$('#pwd').focus();
			return;
		}
		
		// 전송 => 실행결과를 가지고 온다 (자체 처리) (요청 = 응답 : Ajax,Vue,React)
		$.ajax({
			type:'post',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			success:function(result)//NOID,NOPWD,OK
			{
				let res=result.trim();
				if(res==='NOID')
				{
					alert("아이디가 존재하지 않습니다!");
					$('#id').val("");
					$('#pwd').val("");
					$('#id').focus();
				}
				else if(res==='NOPWD')
				{
					alert("비밀번호가 틀립니다!")
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else
				{
					location.href="../main/main.do"
				}
			}
		})
	})
})
</script>
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
        <li><i class="entypo-user"></i><input type=text name=id class="input-sm" size=10 id=id placeholder="아이디"></li>
        <li><i class="entypo-brush"> <input type=password name=pwd class="input-sm" size=10 id=pwd placeholder="비밀번호"></li>
         <li><input type=button value="로그인" class="btn btn-sm btn-danger" id="logBtn"></li>
       </ul>
      </c:if>
      
      <c:if test="${sessionScope.id!=null }">
       <ul class="inline">
        <li>${sessionScope.name }(${sessionScope.admin=='y'?"관리자":"일반사용자" }) 님 로그인중입니다</li>
         <li><input type=button value="로그아웃" class="btn btn-sm btn-danger" id="logoutBtn"></li>
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
          <li><a href="../member/idfind.do">아이디찾기</a></li>
          <li><a href="../member/paswordfind.do">비밀번호찾기</a></li>
        </ul>
      </li>
      </c:if>
      <c:if test="${sessionScope.id!=null }"><%-- 로그인된 상태 --%>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/member_update.do">회원수정</a></li>
          <li><a href="../member/member_delete.do">회원탈퇴</a></li>
        </ul>
      </li>
      </c:if>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/location_find.do">지역별 맛집찾기</a></li>
         <%--
                sessionScope.id
                session.getAttribute("id")
                
                ${name} 
                request.getAttribute("name")
          --%>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="../reserve/reserve_main.do">맛집 예약</a></li>
          <li><a href="pages/sidebar-left.html">맛집 추천</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_list.do">레시피</a></li>
          <li><a href="../recipe/chef.do">쉐프</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="../recipe/recipe_make.do">레시피만들기</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="drop" href="#">서울여행</a>
        <ul>
          <li><a href="../seoul/seoul_list.do">명소</a></li>
          <li><a href="../seoul/seoul_list.do?type=2">자연 & 관광</a></li>
          <li><a href="../seoul/seoul_list.do?type=3">쇼핑</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/sidebar-left.html">코스</a></li>
         </c:if>
        </ul>
      </li>
      <li><a href="../goods/goods_list.do">레시피 스토어</a></li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="../notice/notice_list.do">공지사항</a></li>
          <li><a href="../board/list.do">자유게시판</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="../replyboard/list.do">묻고답하기</a></li>
         </c:if>
        </ul>
      </li>
      <c:if test="${sessionScope.id!=null }">
        <c:if test="${sessionScope.admin=='n' }">
          <li><a href="../mypage/mypage_main.do">마이페이지</a></li>
        </c:if>
        <c:if test="${sessionScope.admin=='y' }">
          <li><a href="../adminpage/adminpage_main.do">관리자페이지</a></li>
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