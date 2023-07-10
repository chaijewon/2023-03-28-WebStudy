<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
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
         <td class="text-center" height="40">${i }</td>
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
