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
	$('#findBtn').on("click",function(){
		let fd=$('#fd').val();
		if(fd.trim()=="")
		{
			$('#fd').focus();
			return;
		}
		$('#locFrm').submit();
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">지역별 맞집검색</h2>
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">
           <form method="post" action="../food/location_find.do" class="inline" id="locFrm">
            <input type=text name=fd id=fd size=20 class="input-sm" value="${fd }">
            <input type=button value="검색" class="btn btn-sm btn-danger"
            id="findBtn">
            </form>
          </header>
          <ul class="nospace clear">
           <c:forEach var="vo" items="${list }" varStatus="s">
              <li class="one_quarter ${s.index%4==0?'first':'' }"><a href="#"><img src="${vo.poster }" title="${vo.name }"></a></li>
           </c:forEach> 
           
            
        </figure>
      </div>
      <!--  class=current -->
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
         <%-- startPage : 1 , 11 , 21 , 31... --%>
         <c:if test="${startPage>1 }">
          <li><a href="../food/location_find.do?page=${startPage-1 }&fd=${fd}">&laquo; Previous</a></li>
         </c:if>
         
         <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <li ${curpage==i?"class=current":"" }><a href="../food/location_find.do?page=${i }&fd=${fd}">${i }</a></li>
         </c:forEach>
         
          
         <c:if test="${endPage<totalpage }">
          <li><a href="../food/location_find.do?page=${endPage+1 }&fd=${fd}">Next &raquo;</a></li>
         </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>