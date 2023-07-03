package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class FoodModel {
  @RequestMapping("food/location_find.do")
  public String food_find(HttpServletRequest request,HttpServletResponse response)
  {
	  // 검색어를 받는다 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex){}
	  String fd=request.getParameter("fd");
	  if(fd==null)
	  {
		  fd="마포";
	  }
	  String page=request.getParameter("page");
	  // JSP안에서는 page는 내장 객체 
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  // => DAO를 연결해서 값을 전송 
	  FoodDAO dao=FoodDAO.newInstance();
	  List<FoodVO> list=dao.foodLocationFindData(fd, curpage);
	  int totalpage=dao.foodLoactionTotalPage(fd);
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // location_find.jsp로 전송할 데이터 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("list", list);
	  request.setAttribute("fd", fd);
	  request.setAttribute("main_jsp", "../food/location_find.jsp");
	  return "../main/main.jsp";
  }
}





