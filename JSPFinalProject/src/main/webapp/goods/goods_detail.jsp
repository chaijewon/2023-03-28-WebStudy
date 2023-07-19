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
<script type="text/javascript" src="https://code.jquery.com/jquery.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
$(function(){
	$('#byBtn').click(function(){
		requestPay()
	})
	$('#amount_select').change(function(){
		let amount=$(this).val();
		let price=$('#total_price').attr("data-price");
		let total=amount*price;
		$('#total_price').html("<span style='color:green'>"+total+"</span>원")
	    $('#price').val(price);
		$('#amount').val(amount)
		$('#cartFrm').show();
	})
})
var IMP = window.IMP; // 생략 가능
IMP.init("imp68206770"); // 예: imp00000000
function requestPay() {
	console.log('clicked');
  // IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({
	    pg : 'html5_inicis', // version 1.1.0부터 지원.
	    
	        /*
	            'kakao':카카오페이,
	            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
	            'nice':나이스,
	            'jtnet':jtnet,
	            'uplus':LG유플러스
	        */
	    pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : $('#goods_name').text(),
	    amount : $('#byBtn').attr('data-price'),
	    buyer_email : 'iamport@siot.do',
	    buyer_name : '구매자이름',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456',
	    app_scheme : 'iamporttest' //in app browser결제에서만 사용 
	}, function(rsp) {
	    if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	});
}
</script>
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
         <td width=60%><h3 id="goods_name">${vo.goods_name }</h3></td>
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
         수량:<select name="account" class="input-sm" style="width:350px" id="amount_select">
             <c:forEach var="i" begin="1" end="${vo.account }">
               <option value="${i }">${i }개</option>
             </c:forEach>
         </select>
        </td>
       </tr>
       <tr>
         <td class="text-right" id="total_price" data-price="${price }"></td>
       </tr>
       <c:if test="${sessionScope.id!=null }">
	       <tr>
	        <td class="inline">
	          <form method="post" action="../cart/cart_insert.do" style="display:none" id="cartFrm">
	           <input type="hidden" name="goods_no" value="${vo.no }">
	           <input type="hidden" name="price" value="" id="price">
	           <input type="hidden" name="type" value="${type }">
	           <input type="hidden" name="amount" value="" id="amount">
	           <input type=submit class="btn btn-lg btn-success" value="장바구니" style="width: 150px">
	          </form>
	          <input type=button class="btn btn-lg btn-info" value="바로구매" style="width: 150px" data-price="${price }" id="byBtn">
	        </td>
	       </tr>
       </c:if>
     </table>
    </div>
   </main>
  </div>
</body>
</html>





