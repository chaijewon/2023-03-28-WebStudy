<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
      1) 사용자(동작,행위) => 요구사항분석 
         => 요청시에는 .do => <a> , <form>
                    ---- DispatcherServlet 
      2) 서버 
         => Front Controller (DispatcherServlet)
            사용자로부터 요청을 받아서 
            모델을 찾고 
            모델에서 request/session에 담아준 데이터를 JSP로 전송하는 역할 
            ----------------------------------------------------
              배달부 (Dispatcher)
         => 모델 : request가 JSP전송 
                  --------
                  request.setAttribute() : 데이터를 추가해서 전송할 목적 
         => JSP에서는 request에 들어가 있는 데이터를 출력하기 쉽게 
            ${} (EL) , 자바에서 제공하는 제어문 , 메소드...
                       ------------------------------
                        태그형으로 변경 (JSTL)
         => JSP는 스프링에서 그대로 사용 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:860px;
}
</style>
</head>
<body>
  <div class="wrapper row3">
   <main class="container clear">
    <div class="row">
     <table class="table">
       <tr>
         <td width=40% class="text-center" rowspan="8">
          <img src="${vo.goods_poster }" style="width: 100%">
         </td>
         <td width=60%><h3>${vo.goods_name }</h3></td>
       </tr>
       <tr>
        <td><span style="color:gray">${vo.goods_sub }</span></td>
       </tr>
       <tr>
        <td class="inline"><span style="color:red">${vo.goods_discount }%</span>
          &nbsp;<h3 style="display: inline;">${vo.goods_price }</h3>
        </td>
       </tr>
       <tr>
        <td><span style="color:green;font-size:8px">첫구매할인가</span>&nbsp;<span style="color:green;">${vo.goods_first_price}</span></td>
       </tr>
       <tr>
        <td>배송&nbsp;&nbsp;<span style="color:gray">${vo.goods_delivery }</span></td>
       </tr>
       <tr>
        <td class="inline">
         수량:<select name="account" class="input-sm" style="width:350px">
             <c:forEach var="i" begin="1" end="${vo.account }">
               <option value="${i }">${i }개</option>
             </c:forEach>
         </select>
        </td>
       </tr>
       <tr>
         <td class="text-right"></td>
       </tr>
       <tr>
        <td class="inline">
          <input type=button class="btn btn-lg btn-success" value="장바구니" style="width: 150px">
          <input type=button class="btn btn-lg btn-info" value="바로구매" style="width: 150px">
        </td>
       </tr>
     </table>
    </div>
   </main>
  </div>
</body>
</html>





