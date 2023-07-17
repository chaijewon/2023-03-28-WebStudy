<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:600px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
  $( function() {
      $( "#tabs" ).tabs();
      $('#emailBtn').click(function(){
    	  let name=$('#name').val();
    	  if(name.trim()==="")
    	  {
    		  $('#name').focus();
    		  return;
    	  }
    	  
    	  let email=$('#email').val();
    	  if(email.trim()==="")
    	  {
    		  $('#email').focus();
    		  return;
    	  }
    	  // axios.get(params{name:name,email:email}) , async axios.post() => vue,react => 서버연결 , 결과값 받기 
    	  // name=aaa&email=aaa
    	  $.ajax({
    		  type:'post',
    		  url:'../member/passwordfindOk.do',
    		  data:{"name":name,"email":email},
    		  success:function(result)
    		  {
    			  let res=result.trim();
    			  if(res==='NO')
    			  {
    				  $('#pwd_email').html('<span style="color:red">이름이나 이메일이 존재하지 않습니다</span>')
    			  }
    			  else
    			  {
    				  $('#pwd_email').html('<span style="color:blue">'+res+"</span>")
    			  }
    		  }
    	  })
      })
  });
</script>
</head>
<body>
<div class="wrapper row3">
   <main class="container clear">
    <h2 class="sectiontitle">비밀번호 찾기</h2>
    <div class="row">
      <div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">이메일로 찾기</a></li>
		    <li><a href="#tabs-2">전화번호로 찾기</a></li>
		  </ul>
		  <div id="tabs-1">
		    <table class="table">
		     <tr>
		       <td width=20%>이름</td>
		       <td width=80%>
		        <input type=text id="name" class="input-sm">
		       </td>
		     </tr>
		     <tr>
		       <td width=20%>이메일</td>
		       <td width=80%>
		         <input type=text id="email" class="input-sm">
		       </td>
		     </tr>
		     <tr>
		       <td class="text-center" colspan="2">
		        <input type=button value="검색"
		          class="btn btn-sm btn-danger" id="emailBtn">
		       </td>
		     </tr>
		     <tr>
		       <td class="text-center" colspan="2">
		        <h3 id="pwd_email"></h3>
		       </td>
		     </tr>
		    </table>
		  </div>
		  <div id="tabs-2">
		    <table class="table">
		     <tr>
		       <td class="inline">
		       이름:<input type=text id="name" class="input-sm">
		       </td>
		     </tr>
		     <tr>
		       <td class="inline">
		        전화번호:<input type=text id="tel" class="input-sm">
		        
		       </td>
		     </tr>
		     <tr>
		       <td class="text-center">
		        <input type=button value="검색"
		          class="btn btn-sm btn-danger" id="telBtn">
		       </td>
		     </tr>
		     <tr>
		       <td class="text-center">
		        <h3 id="pwd_tel"></h3>
		       </td>
		     </tr>
		    </table>
		  </div>
		  
		</div>
    </div>
   </main>
</div>
</body>
</html>