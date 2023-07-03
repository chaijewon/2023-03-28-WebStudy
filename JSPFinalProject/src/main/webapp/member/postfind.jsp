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
  margin-top: 20px;
}
.row{
  margin: 0px auto;
  width:480px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#postBtn').on("click",function(){
		let dong=$('#dong').val();
		if(dong.trim()=="")
		{
			$('#dong').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../member/postfind_result.do',
			data:{"dong":dong},
			success:function(result)
			{
				alert(result)
				$('#result').html(result);
			}
		})
	})
})
</script>
</head>
<body>
   <div class="container">
     <div class="row">
      <table class="table">
        <tr>
          <td>
          입력:<input type=text name=dong id=dong size=12
              class="input-sm">
           <input type=button value="검색" class="btn btn-sm btn-primary"
              id="postBtn">
          </td>
        </tr>
        <tr>
          <td class="text-right">
           <sub style="color:red">※동읍면을 입력하세요</sub>
          </td>
        </tr>
      </table>
      <hr>
      <div id="result">
       
      </div>
     </div>
   </div>
</body>
</html>




