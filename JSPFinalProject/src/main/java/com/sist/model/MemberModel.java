package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

public class MemberModel {
  
	@RequestMapping("member/join.do")
	public String memberJoin(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
}
