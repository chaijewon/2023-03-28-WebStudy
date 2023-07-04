<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
     FoodDAO dao=new FoodDAO();
     List<FoodVO> list=dao.foodListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:1200px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" >
$(function(){
	$('.images').css("cursor","pointer")
	$('.images').click(function(){
		let fno=$(this).attr("data-fno")
		$.ajax({
			type:'post',
			url:'detail.jsp',
			data:{"fno":fno},
			success:function(res)
			{
				$('#dialog').html(res);
				$('#dialog').dialog({
					autoOpen:false,
					width:700,
					height:650,
					modal:true
				}).dialog("open")
			}
			
		})
	})
})
</script>
</head>
<body>
  <div class="container">
   <div class="row">
     <%
        for(FoodVO vo:list)
        {
     %>
             <div class="col-md-3">
			    <div class="thumbnail">
			      <%-- <a href="detail.jsp?fno=<%=vo.getFno()%>"> --%>
			       <img src="<%=vo.getPoster() %>"  style="width:100%" class="images" data-fno="<%=vo.getFno()%>">
			        <div class="caption">
			          <p><%=vo.getName() %></p>
			        </div>
			      <!-- </a> -->
			    </div>
			  </div>
     <%
        }
     %>
   </div>
   <div id="dialog" title="상세보기">
   
   </div>
  </div>
</body>
</html>






