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
  width:550px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
     <div class="text-center">
       <img src="image/qna.jpg" style="width:550px;height: 100px" class="img-rounded">
     </div>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <form method="post" action="delete_ok.jsp">
      <table class="table">
        <tr>
         <td class="text-center">
         비밀번호:<input type=password name=pwd size=15>
         <input type=hidden name=no value="${param.no }">
         </td>
        </tr>
        <tr>
          <td  class="text-center">
            <input type=submit value="삭제"
              class="btn btn-sm btn-danger">
            <input type=button value="취소"
              class="btn btn-sm btn-danger"
              onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      </form>
    </div>
   </div>

</body>
</html>