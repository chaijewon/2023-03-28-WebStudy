package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel {
	   public String execute(HttpServletRequest request)
	   {
		   request.setAttribute("msg", "추가하기");
		   return "insert.jsp";
	   }
}
