<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:860px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//$('#fd').prop("disabled",true);
	  let sel=$('#sel').val();
      if(sel==='all')
	  {
			$('#fd').prop("disabled",true);
	  }
	  else
	  {
			$('#fd').prop("disabled",false);
	  }
	$('#sel').change(function(){
		let sel=$(this).val();
		if(sel==='all')
		{
			$('#fd').prop("disabled",true);
		}
		else
		{
			$('#fd').prop("disabled",false);
		}
	})
	$('#findBtn').on("click",function(){
		let sel=$('#sel').val();
		if(sel==='all')
		{
		   $('#findFrm').submit();	
		}
		else
		{
			let fd=$('#fd').val();
			if(fd==='')
			{
				$('#fd').focus();
				return;
			}
			$('#findFrm').submit();
		}
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">${chef }님 레시피목록</h2>
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">
           <form method="post" action="../recipe/chef_list.do" class="inline" id="findFrm">
	           <select name="sel" class="input-sm" id="sel">
	            <option value="all" ${sel=='all'?"selected":"" }>전체</option>
	            <option value="find" ${sel=='find'?"selected":"" }>검색</option>
	           </select>
	           <input type=hidden name=chef value="${chef }">
	           <input type=text name=fd size=20 class="input-sm" id="fd" value=${sel!='all'?fd:"" }>
	           <input type="button" value="검색" class="btn btn-sm btn-danger" id="findBtn">
	           </form>
          </header>
          <ul class="nospace clear">
           <c:forEach var="vo" items="${list }" varStatus="s">
              <li class="one_quarter ${s.index%4==0?'first':'' }"><a href="#"><img src="${vo.poster }" title="${vo.title }"></a></li>
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
          <li><a href="../recipe/chef_list.do?page=${startPage-1 }&chef=${chef}">&laquo; Previous</a></li>
         </c:if>
         
         <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <li ${curpage==i?"class=current":"" }><a href="../recipe/chef_list.do?page=${i }&chef=${chef}">${i }</a></li>
         </c:forEach>
         
          
         <c:if test="${endPage<totalpage }">
          <li><a href="../recipe/chef_list.do?page=${endPage+1 }&chef=${chef}">Next &raquo;</a></li>
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