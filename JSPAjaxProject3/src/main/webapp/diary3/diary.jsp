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
	$('.rdays').css("cursor","pointer");
	$('.rdays').click(function(){
		let year=$(this).attr("data-year");
		let month=$(this).attr("data-month");
		let day=$(this).text();
		let rday=year+"년도 "+month+"월 "+day+"일";
		$('#fd').show();
		$('#food_day').text(rday)
		
		$.ajax({
			type:'post',
			url:'time.do',
			data:{"day":day},
			success:function(result)
			{
				$('#food_time').html(result)
			}
		})
	})
})
</script>
</head>
<body>
     <h1>${year }년도 ${month }월</h1>
     <table class="table">
      <tr>
        <td>
         <select name="year" id="year" class="input-sm">
           <c:forEach var="i" begin="2023" end="2030">
             <option ${i==year?"selected":"" }>${i }</option>
           </c:forEach>
         </select>년도&nbsp;
         <select name="month" id="month" class="input-sm">
           <c:forEach var="i" begin="1" end="12">
             <option ${i==month?"selected":"" }>${i }</option>
           </c:forEach>
         </select>월
        </td>
      </tr>
     </table>
     <table class="table">
       <tr class="success">
        <c:forEach var="s" items="${strWeek }" varStatus="ss">
          <c:choose>
            <c:when test="${ss.index==0 }">
             <c:set var="color" value="red"/>
            </c:when>
            <c:when test="${ss.index==6 }">
             <c:set var="color" value="blue"/>
            </c:when>
            <c:otherwise>
             <c:set var="color" value="black"/>
            </c:otherwise>
          </c:choose>
          <th class="text-center" height="40" style="color:${color}">${s }</th>
        </c:forEach>
       </tr>
       <c:set var="week" value="${week}"/>
       <c:forEach var="i" begin="1" end="${lastday }">
         <c:if test="${i==1 }">
           <tr>
           <c:forEach var="j" begin="1" end="${week }">
             <td height="40">&nbsp;</td>
           </c:forEach>
         </c:if>
         <c:if test="${rday[i]==0 }">
           <td class="text-center" height="40">${i }</td>
         </c:if>
         <c:if test="${rday[i]==1 }">
           <td class="text-center rdays" style="background-color:black;color:white;" height="40"
             data-year=${year } data-month=${month }
           ><b>${i }</b></td>
         </c:if>
         <c:set var="week" value="${week+1 }"/>
         <c:if test="${week>6 }">
           <c:set var="week" value="0"/>
           </tr>
           <tr>
         </c:if>
       </c:forEach>
       </tr>
     </table>
</body>
</html>
