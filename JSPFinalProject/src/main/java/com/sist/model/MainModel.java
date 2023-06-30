package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_page(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../main/home.jsp");
	   return "../main/main.jsp";
   }
}
