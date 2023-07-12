<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://dev-style.agentfirecdn.com/bootstrap.client.css">
<link rel="stylesheet" href="https://dev-style.agentfirecdn.com/bootstrap.admin.css"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="../layout/styles/style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../layout/scripts/script.js"></script>
<link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="../layout/styles/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width: 960px;
}
</style>
</head>
<body>
<div class="wrapper row3">
   <main class="container clear">
     <div class="row">
      <div class="col-sm-4">
       <jsp:include page="../mypage/mypage_nav.jsp"></jsp:include>
      </div>
      <div class="col-sm-8">
       <%-- 변경위치 --%>
       <jsp:include page="${mypage_jsp }"></jsp:include>
      </div>
     </div>
   </main>
</div>
</body>
</html>