<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 450px;
}
h1{
    text-align: center
}
</style>
</head>
<body>
  <div class="container">
    <h1>우편번호 검색</h1>
    <div class="row">
      <table class="table">
       <tr>
         <td>
          우편번호 : <input type="text" name=dong size=15
                   class="input-sm">
                   <input type=button value="검색"
                     class="btn btn-sm btn-danger">
         </td>
       </tr>
      </table>
    </div>
  </div>
</body>
</html>