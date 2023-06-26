<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('div > h1 ~ span').css("color","red")
})
</script>
</head>
<body>
  <div>
    <h1>Hello Jquery!!</h1>
    <span>Hello Selector</span>
    <span>Hello Selector</span>
  </div>
</body>
</html>