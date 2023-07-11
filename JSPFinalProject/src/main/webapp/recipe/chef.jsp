<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:700px;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">쉐프</h2>
   <div class="row">
     <table class="table">
       <tr>
        <td>
          <c:forEach var="vo" items="${list }">
            <table class="table">
              <tr>
                <td width=30% rowspan="2" class="text-center">
                 <a href="../recipe/chef_list.do?chef=${vo.chef }">
                  <img src="${vo.poster }" style="width: 100px;height: 100px" class="img-circle">
                 </a>
                </td>
                <td colspan="4">
                  <h3 style="color:orange"><a href="../recipe/chef_list.do?chef=${vo.chef }">${vo.chef }</a></h3>
                </td>
              </tr>
              <tr>
               <td><img src="image/mem1.png">&nbsp;${vo.mem_cont1 }</td>
               <td><img src="image/mem2.png">&nbsp;${vo.mem_cont2 }</td>
               <td><img src="image/mem7.png">&nbsp;${vo.mem_cont7 }</td>
               <td><img src="image/mem3.png">&nbsp;${vo.mem_cont3 }</td>
              </tr>
            </table>
          </c:forEach>
        </td>
       </tr>
     </table>
     <table class="table">
       <tr>
        <td class="text-center">
          <a href="../recipe/chef.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-success">이전</a>
          ${curpage } page / ${totalpage } pages
          <a href="../recipe/chef.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-info">다음</a>
        </td>
       </tr>
     </table>
   </div>
  </main>
</div>
</body>
</html>