<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%
    String id=(String)session.getAttribute("id");
    List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
    
    List<CartVO> uList=new ArrayList<CartVO>();
    
    for(CartVO vo:list)
    {
    	if(id.equals(vo.getId()))
    	{
    		uList.add(vo);
    	}
    }
%>
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
  width:500px;
}
</style>
</head>
<body>
  <div class="container">
    <h1 class="text-center"><%=id %>님의 장바구니</h1>
    <div class="row">
      <table class="table">
       <%
           if(uList.size()==0)
           {
       %>
                <tr>
                 <td class="text-center">장바구니가 비어있습니다</td>
                </tr>
       <%
           }
           else
           {
       %>
                <tr>
                  <th class="text-center">번호</th>
                  <th class="text-center"></th>
                  <th class="text-center">가격</th>
                  <th class="text-center">수량</th>
                </tr>
                <%
                   for(CartVO vo:uList)
                   {
                %>
                      <tr>
                        <td class="text-center"><%=vo.getCno() %></td>
                        <td class="text-center">
                         <img src="<%=vo.getPoster() %>"
                           style="width: 30px;height: 30px">
                        </td>
                        <td class="text-center"><%=vo.getPrice() %></td>
                        <td class="text-center"><%=vo.getAccount() %></td>
                      </tr>
                <%
                   }
                %>
       <%
           }
       %>
      </table>
    </div>
  </div>
</body>
</html>



