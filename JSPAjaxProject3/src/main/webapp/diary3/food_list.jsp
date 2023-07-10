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
	$('.food_select').css("cursor","pointer")
	$('.food_select').click(function(){
		let img=$(this).attr("data-poster");
		let name=$(this).attr("data-name");
		let fno=$(this).attr("data-fno");
		
		$('#food_img').attr("src",img);
		$('#food_name').text(name);
		
		$.ajax({
			type:'post',
			url:'diary.do',
			data:{"fno":fno},
			success:function(result)
			{
				$('#reserve_day').html(result);
			}
		})
	})
})
</script>
</head>
<body>
  <table class="table">
    <tr>
     <th class="text-center"></th>
     <th class="text-center">업체명</th>
     <th class="text-center">전화</th>
    </tr>
    <c:forEach var="vo" items="${list }">
     <tr class="food_select" data-poster="${vo.poster }" 
        data-name="${vo.name }" data-fno="${vo.fno }">
      <td class="text-center">
        <img src="${vo.poster }" style="width: 30px;height: 30px">
      </td>
      <td>${vo.name }</td>
      <td class="text-center">${vo.phone }</td>
     </tr>
    </c:forEach>
  </table>
</body>
</html>