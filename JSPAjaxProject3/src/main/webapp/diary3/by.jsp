<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="requestPay();">아임포트</button>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략 가능
IMP.init("imp68206770"); // 예: imp00000000
function requestPay() {
	console.log('clicked');
  // IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({
	    pg : 'kakao', // version 1.1.0부터 지원.
	        /*
	            'kakao':카카오페이,
	            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
	            'nice':나이스,
	            'jtnet':jtnet,
	            'uplus':LG유플러스
	        */
	    pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount : 14000,
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
</body>
</html>
</head>
<body>
  
</body>
</html>