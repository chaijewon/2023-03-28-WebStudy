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
	$('#btn').click(function(){
		$.ajax({
			type:'get',
			url:'my.jsp',
			success:function(res)
			{
				$('#print').text(res)
			}
		})
	})
})
</script>
</head>
<body>
   <input type=button value="전송" id="btn">
   <br>
   <span id="print"></span>
</body>
</html>