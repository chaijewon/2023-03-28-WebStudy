<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    // 장바구니에 들어 갈 데이터 
    String gno=request.getParameter("no");
    String account=request.getParameter("account");
    String id=(String)session.getAttribute("id");
    GoodsDAO dao=GoodsDAO.newInstance();
    GoodsBean gb=dao.goodsDetailData(Integer.parseInt(gno));
    
    CartVO vo=new CartVO();
    vo.setGno(Integer.parseInt(gno));
    vo.setAccount(Integer.parseInt(account));
    vo.setId(id);
    vo.setName(gb.getName());
    vo.setPoster(gb.getPoster());
    vo.setPrice(gb.getPrice());
    
    List<CartVO> sList=(List<CartVO>)session.getAttribute("cart");
    int no=1;
    if(sList==null)
    {
    	sList=new ArrayList<CartVO>();
    }
    else
    {
    	int max=0;
    	for(int i=0;i<sList.size();i++)
    	{
    		if(max<sList.get(i).getCno())
    		{
    			max=sList.get(i).getCno();
    		}
    	}
    	no=max+1;
    }
    vo.setCno(no);
    
    sList.add(vo);
    session.setAttribute("cart", sList);
    
    response.sendRedirect("cart.jsp");
    
%>
