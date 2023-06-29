package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class UpdateModel {
	   public String execute(HttpServletRequest request)
	   {
		   request.setAttribute("msg", "수정하기");
		   return "update.jsp";
	   }
}
