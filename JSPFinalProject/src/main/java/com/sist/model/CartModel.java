package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class CartModel {
  /*
   *   <input type="hidden" name="goods_no" value="${vo.no }">
	   <input type="hidden" name="price" value="" id="price">
	   <input type="hidden" name="type" value="${type }">
	   <input type="hidden" name="amount" value="" id="amount">
   */
  @RequestMapping("cart/cart_insert.do")
  public String cart_insert(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String goods_no=request.getParameter("goods_no");
	  String price=request.getParameter("price");
	  String type=request.getParameter("type");
	  String amount=request.getParameter("amount");
	  CartVO vo=new CartVO();
	  vo.setGoods_no(Integer.parseInt(goods_no));
	  vo.setPrice(Integer.parseInt(price));
	  vo.setAmount(Integer.parseInt(amount));
	  vo.setType(Integer.parseInt(type));
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  vo.setId(id);
	  
	  CartDAO dao=CartDAO.newInstance();
	  dao.cartInsert(vo);
	  
	  return "redirect:../goods/goods_detail.do?no="+goods_no+"&type="+type;
  }
  @RequestMapping("cart/cart_buy.do")
  public void cart_buy(HttpServletRequest request,HttpServletResponse response)
  {
	  String no=request.getParameter("cart_no");
	  System.out.println("no="+no);
	  CartDAO dao=CartDAO.newInstance();
	  dao.cartBuy(Integer.parseInt(no));
  }
  
}
