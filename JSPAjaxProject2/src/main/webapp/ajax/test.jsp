<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
function sendData()
{
	ajaxConfig("GET","my.jsp",success);
}
function success(result) // success:function(result)
{
	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200)
	{
	     	let span=document.querySelector("#print");
	     	span.innerHTML=xmlHttpRequest.responseText;
	}
}
</script>
</head>
<body>
  <input type="button" value="전송" onclick="sendData()"><br>
  <span id="print"></span>
</body>
</html>