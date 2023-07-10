<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 960px;
}
h1{
    text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'food_list.do',
		success:function(result)
		{
			$('#food_list').html(result);
		}
	})// 처음 실행시 => 한식을 먼저 출력 
	$('.foods').click(function(){
		let type=$(this).text();
		$.ajax({
			type:'post',
			url:'food_list.do',
			data:{"type":type},
			success:function(result)
			{
				$('#food_list').html(result);
			}
		})
	})
})
</script>
</head>
<body>
  <div class="container">
   <h1>맛집 예약</h1>
   <div class="row">
    <table class="table" height=700>
      <tr>
        <td width=20% height="580" class="danger">
          <table class="table">
           <caption><h3>맛집 정보</h3></caption>
           <tr>
             <td>
              <span class="btn btn-xs btn-danger foods">한식</span>
              <span class="btn btn-xs btn-warning foods">양식</span>
              <span class="btn btn-xs btn-success foods">중식</span>
              <span class="btn btn-xs btn-info foods">일식</span>
              <span class="btn btn-xs btn-primary foods">기타</span>
             </td>
           </tr>
           <tr>
             <td>
              <div id="food_list" style="height: 450px;overflow-y:scroll"></div>
             </td>
           </tr>
          </table>
        </td>
        <td width=45% height="580" class="success">
         <table class="table">
           <caption><h3>예약일 정보</h3></caption>
           <tr>
            <td id="reserve_day"></td>
           </tr>
          </table>
        </td>
        <td width=35% rowspan="2" class="info">
          <table class="table">
           <caption><h3>예약 정보</h3></caption>
           <tr>
             <td colspan="2" class="text-center">
              <img src="default.png" style="width: 200px;height: 220px" id="food_img">
             </td>
           </tr>
           <tr>
             <td colspan="2">
              <h4 id="food_name"></h4>
             </td>
           </tr>
           <tr>
             <td colspan="2">
              <span style="color:gray;display:none" id="fd">예약일:</span><span id="food_day"></span>
             </td>
           </tr>
           <tr>
             <td colspan="2">
              <span style="color:gray;display:none" id="ft">예약시간:</span><span id="food_t"></span>
             </td>
           </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td width=35% height="120" class="warning">
          <table class="table">
           <caption><h3>예약시간 정보</h3></caption>
           <tr>
             <td id="food_time"></td>
           </tr>
          </table>
        </td>
        <td width=30% height="120" class="default">
          <table class="table">
           <caption><h3>인원 정보</h3></caption>
           <tr>
             <td id="food_inwon"></td>
           </tr>
          </table>
        </td>
      </tr>
    </table>
   </div>
  </div>
</body>
</html>