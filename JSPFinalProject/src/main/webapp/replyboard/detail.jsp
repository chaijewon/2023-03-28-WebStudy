<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:850px;
}
</style>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
  <div class="wrapper row3">
   <main class="container clear">
    <h2 class="sectiontitle">내용 보기</h2>
    <div class="row">
     <table class="table">
      <tr>
       <th width=20% class="text-center">번호</th>
       <td width=30% class="text-center">${vo.no }</td>
       <th width=20% class="text-center">작성일</th>
       <td width=30% class="text-center">${vo.dbday }</td>
      </tr>
      <tr>
       <th width=20% class="text-center">이름</th>
       <td width=30% class="text-center">${vo.name }</td>
       <th width=20% class="text-center">조회수</th>
       <td width=30% class="text-center">${vo.hit }</td>
      </tr>
      <tr>
       <th width=20% class="text-center">제목</th>
       <td colspan="3">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan="4" valign="top" class="text-left" height="200">
         <pre style="white-space: pre-wrap;background-color: white;border:none">${vo.content }</pre>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
         <c:if test="${sessionScope.id==vo.id }">
          <a href="../replyboard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
          <a href="../replyboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
         </c:if>
         <a href="../replyboard/list.do" class="btn btn-xs btn-warning">목록</a>
        </td>
      </tr>
     </table>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
     <div id="piechart" style="width: 900px; height: 500px;"></div>
    </div>
   </main>
  </div>

</body>
</html>