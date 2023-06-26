<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     브라우저에서만 제어 
 --%>
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
  width:960px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0;// 전역변수
$(function(){
	$('#addBtn').on("click",function(){
		$('#user-table').append(
		  '<tr id="m'+(fileIndex)+'">'
		  +'<td>'
		  +'File '+(fileIndex+1)+': <input type=file size=20>'
		  +'</td>'
		  +'</tr>'
		)
		fileIndex++;
	})
	$('#removeBtn').click(function(){
		if(fileIndex>0)
		{
			$('#m'+(fileIndex-1)).remove();
			fileIndex--;
		}
	})
})
</script>
</head>
<body>
   <div class="container">
     <h1 class="text-center">자료실</h1>
     <div class="row">
       <table class="table">
        <tr>
          <th width=20% class="text-center">이름</th>
          <td width=80%><input type=text size=15></td>
        </tr>
        <tr>
          <th width=20% class="text-center">제목</th>
          <td width=80%><input type=text size=50></td>
        </tr>
        <tr>
          <th width=20% class="text-center">내용</th>
          <td width=80%><textarea rows="10" cols="50"></textarea></td>
        </tr>
        <tr>
          <th width=20% class="text-center">첨부파일</th>
          <td width=80%>
            <table class="table">
              <tr>
                <td class="text-right">
                  <input type=button value="add"
                    class="btn btn-xs btn-danger" id="addBtn">
                  <input type=button value="remove"
                    class="btn btn-xs btn-success" id="removeBtn">
                </td>
              </tr>
            </table>
            <table class="table" id="user-table">
              
            </table>
          </td>
        </tr>
       </table>
     </div>
   </div>
</body>
</html>





