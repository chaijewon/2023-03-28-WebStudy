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
  width:960px;
}
</style>
</head>
<body>
  <div class="wrapper row3">
   <main class="container clear">
    <div class="row">
     <table class="table">
       <tr>
         <c:forTokens items="${vo.poster }" delims="^" var="img">
           <td>
            <img src="${img }" style="width: 100%">
           </td>
         </c:forTokens>
       </tr>
     </table>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <div class="col-sm-8">
        <%-- 맛집 정보 --%>
        <table class="table">
         <tr>
           <td colspan="2">
            <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
           </td>
         </tr>
         <tr>
          <th width=20% class="text-center">주소</th>
          <td width=80%>${addr1 }
            <br>
            <sub style="color:gray">지번:${addr2 }</sub>
          </td>
         </tr>
         <tr>
          <th width=20% class="text-center">전화</th>
          <td width=80%>${vo.phone }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">음식종류</th>
          <td width=80%>${vo.type }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">가격대</th>
          <td width=80%>${vo.price }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">주차</th>
          <td width=80%>${vo.parking }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">영업시간</th>
          <td width=80%>${vo.time }</td>
         </tr>
         <c:if test="${vo.menu!='no' }">
	         <tr>
	          <th width=20% class="text-center">메뉴</th>
	          <td width=80%>
	            <ul>
	              <c:forTokens items="${vo.menu }" delims="원" var="m">
	                <li>${m }</li>
	              </c:forTokens>
	            </ul>
	          </td>
	         </tr>
         </c:if>
         <tr>
          <td colspan="2" class="text-right">
            <a href="#" class="btn btn-xs btn-danger">찜하기</a>
            <a href="#" class="btn btn-xs btn-success">좋아요(0)</a>
            <a href="#" class="btn btn-xs btn-info">예약</a>
            <a href="../food/food_category_list.do?cno=${vo.cno }" class="btn btn-xs btn-warning">목록</a>
          </td>
         </tr>
        </table>
      </div>
      <%--
          1차 프로젝트 
          1) MVC 이해 
          2) DataBase 최대한 사용 
             JDBC => DBCP => ORM(MyBatis)
                             ------------- 스프링 
                     PS/SQL
          3) Front (AJAX)  => 2(Vue) , 3(React)
          --------------------------------------
          3차 => MSA(React) => FullStack
       --%>
      <div class="col-sm-4">
        <%--지도 , 인근 명소 --%>
        <div id="map" style="width:100%;height:350px;"></div>
        <script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${addr1}', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
      </div>
    </div>
   </main>
  </div>
</body>
</html>




