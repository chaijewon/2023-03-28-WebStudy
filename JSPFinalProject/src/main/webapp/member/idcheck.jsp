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
  width:300px;
}
</style>
</head>
<body>
   <div class="container">
     <div class=row>
      <table class="table">
        <tr>
         <td>ID:<input type=text name=id id=id size=15 class="input-sm">
             <input type=button value="중복체크" class="btn btn-sm btn-warning">
         </td>
        </tr>
      </table>
     </div>
   </div>
</body>
</html>