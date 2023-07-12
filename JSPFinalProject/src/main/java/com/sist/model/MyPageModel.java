package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

public class MyPageModel {
  @RequestMapping("mypage/mypage_main.do")
  public String mypage_main(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
}
