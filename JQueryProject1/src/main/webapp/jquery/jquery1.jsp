<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      JQuery : 자바스크립트 라이브러리 
      => 라이브러리 다운로드 / 원격 읽기 
      => 자바스크립트 
         시작과 동시에 처리 => window.onload=function(){}
      => jquery => $(document).ready(function(){})
                     -----------------생략이 가능 
                => $(function(){})
                => $() => 태그 (selector) => querySelector()
                  ------------------------------------------
                                    |
                                   DOM => 태그를 제어하는 프로그램 
                  $(내장 객체) => window , document, location, history..
                  $() => function
      => Vue => mounted() , React => componentDidMount()
      
      Jquery 
      ------
      1. 라이브러리 로드 
         => <script type="text/javascript" 
              src="http://code.jquery.com/jquery.js">
         => include => 파일을 여러개 묶어서 사용 (jquery충돌시 작동하지 않는다)
            -------- main.jsp (자바스크립트를 모아둔다)
      2. 문서 객체 선택 (selector)
         ------- 태그 
         $('태그명') 
          <h1>1</h1>
          <h1>2</h1>
          <h1>3</h1>
          <h1>4</h1>
          <h1>5</h1>
          
         let h1=$('h1')  ==> let h1=[] => 선택된 h1태그를 읽기 $(this)
         => 모든 태그에 이벤트가 가능 
            --------------------
         
         $('#아이디명') => id속성 사용 
         $('.클래스명') => class속성 사용 
         $('CSS style')
         $('p + h1') , $('p ~ h1') , $('p > h1') $('p h1')
         $('a[href*="a"]')
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 모든 처리가 포함 => 값 읽기 , 이벤트...., CSS적용 
	// 1. CSS적용 
	$('h1').css("color","red")
	$('h1').css("backgroundColor","green")
	/*
	     <style>
	      h1 {
	    	  
	    	  color:"red"
	      }
	     </style>
	*/
})
</script>
</head>
<body>
  <h1>Hello Jquery</h1>
  <h1>Hello Jquery</h1>
  <h1>Hello Jquery</h1>
  <h1>Hello Jquery</h1>
  <h1>Hello Jquery</h1>
</body>
</html>