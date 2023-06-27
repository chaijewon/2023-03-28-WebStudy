<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.GoodsDAO"/>
<%
    // 사용자 보내 준 상품번호를 받는다 
    String no=request.getParameter("no");
    // goods_detail.jsp?no=1
    GoodsBean vo=dao.goodsDetailData(Integer.parseInt(no));
    String s=vo.getPrice();
    s=s.replaceAll("[^0-9]", "");// 원 , 지우기 => 숫자를 제외한 나머지 공백으로 만든다 
    vo.setPrice_int(Integer.parseInt(s));
    System.out.println(s);
    System.out.println(vo.getPrice_int());
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
  width:960px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#account').change(function(){
		let acc=$('#account').val();
		let price=$('#total').attr("data-price");
		let total=acc*price;
		$('#total').text(total+"원");
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <form method=post action="cart_ok.jsp">
      <table class="table">
        <tr>
          <td width=35% class="text-center" rowspan="6">
            <img src="<%=vo.getPoster() %>" style="width:100%">
          </td>
          <td width=65%>
            <strong><%=vo.getName() %></strong>
          </td>
        </tr>
        <tr>
          <td width=65%>
            <span style="color:gray"><%=vo.getSub() %></span>
          </td>
        </tr>
        <tr>
          <td width=65%>
            <span style="color:red"><%=vo.getDiscount() %>%</span>
            &nbsp;&nbsp;<b><%=vo.getPrice() %></b>
          </td>
        </tr>
        <tr>
          <td width=65%>
            <span style="color:green;font-size: 8px">첫구매가 할인</span>
            &nbsp;<b><%=vo.getFp() %></b>
          </td>
        </tr>
        <tr>
          <td width=65%>
            수량:<select name=account id="account">
               <option>1</option>
               <option>2</option>
               <option>3</option>
               <option>4</option>
               <option>5</option>
            </select>
            &nbsp;총금액:<span id="total" data-price="<%=vo.getPrice_int()%>"></span>
            <input type="hidden" name="no" value="<%=vo.getNo()%>">
          </td>
        </tr>
        <tr>
          <td width=65%>
            <%
                String id=(String)session.getAttribute("id");
                if(id!=null)
                {
            %>
            <input type=submit value="장바구니"
              class="btn btn-sm btn-success" id="cartBtn">
            <%
                }
            %>
          </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>




