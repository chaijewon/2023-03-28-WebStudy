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
	$('.inwons').click(function(){
		let inwon=$(this).text();
		$('#fi').show();
		$('#food_i').text(inwon);
		$('#r_inwon').val(inwon);
		$('#ok').show()
	})
})
</script>
</head>
<body>
  <c:forEach var="i" begin="1" end="10">
    <span class="btn btn-xs btn-success inwons">${i }명</span>
  </c:forEach>
    <span class="btn btn-xs btn-success inwons">단체</span>
</body>
</html>