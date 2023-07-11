package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class RecipeModel {
   @RequestMapping("recipe/recipe_list.do")
   public String recipe_list(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   // DB연동 
	   RecipeDAO dao=RecipeDAO.newInstance();
	   List<RecipeVO> list=dao.recipeListData(curpage);
	   int count=dao.recipeRowCount();
	   int totalpage=(int)(Math.ceil(count/20.0));
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1,11,21...
	   // 1~10 => 1 , 11~20 => 11
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// 10 20 30...
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // 출력할 데이터 전송 
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("count", count);
	   request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("recipe/chef.do")
   public String recipe_chef(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   RecipeDAO dao=RecipeDAO.newInstance();
	   List<ChefVO> list=dao.chefListData(curpage);
	   int totalpage=dao.chefTotalPage();
	   
	   // 데이터 전송 
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("list", list);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("main_jsp", "../recipe/chef.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("recipe/chef_list.do")
   public String chef_list(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String sel=request.getParameter("sel");
	   if(sel==null)
		   sel="all";
	   String fd=request.getParameter("fd");
	   String chef=request.getParameter("chef");
	   //System.out.println("chef="+chef);
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   RecipeDAO dao=RecipeDAO.newInstance();
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   int totalpage=0;
	   if(sel.equals("all"))
	   {
	       list=dao.chefMakeRecipeData(curpage, chef);
	       totalpage=dao.chefListTotalPage(chef);
	   }
	   else
	   {
		   list=dao.chefFindRecipeData(curpage, chef, fd);
		   totalpage=dao.chefFindTotalPage(chef, fd);
	   }
	   
	   final int BLOCK=5;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1,11,21...
	   // 1~10 => 1 , 11~20 => 11
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// 10 20 30...
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // 출력할 데이터 전송 
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("chef", chef);
	   request.setAttribute("list", list);
	   request.setAttribute("sel", sel);
	   request.setAttribute("fd", fd);
	   request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
	   return "../main/main.jsp";
   }
}






