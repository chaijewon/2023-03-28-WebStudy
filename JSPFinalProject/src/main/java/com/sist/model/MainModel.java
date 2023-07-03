package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.vo.*;
import com.sist.dao.*;
import java.util.*;
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_page(HttpServletRequest request,HttpServletResponse response)
   {
	   //home.jsp => category전송
	   FoodDAO dao=FoodDAO.newInstance();
	   List<CategoryVO> list=dao.foodCategoryListData();
	   request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../main/home.jsp");
	   return "../main/main.jsp";
   }
}
