const xmlHttpRequest=new XMLHttpRequest();

/*
   XMLHttpRequest: 브라우저 내장 객체 
   --------------- 전송 (요청) , 결과가값을 받는다 
   1) 연결 : open 
   2) 데이터 전송 : send()
   3) 결과값을 받아서 처리 
      readyState , status : 상태  => 200(정상 상태)
      ---------- 준비 과정 
      0,1,2,3,4=>send()완료 
      if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200)
      {
		  데이터를 읽어서 출력 
	  }
	  --------------------------------------------------------------
	  success:function()
*/
function ajaxConfig(method,url,callback)
{
	xmlHttpRequest.open(method,url,true); //true => 비동기
	xmlHttpRequest.onreadystatechange=callback;
	xmlHttpRequest.send(null);
}

/*
      $.ajax({
		  type:
		  url:
		  data:{}
		  success:function(e)
	  })
 */





