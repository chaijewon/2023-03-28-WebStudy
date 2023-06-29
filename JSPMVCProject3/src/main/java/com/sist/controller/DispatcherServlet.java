package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
@WebServlet("*.do")
// main.do , category.do food_list.do , food_detail.do .....
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String,Model> clsMap=new HashMap<String,Model>();
	public void init(ServletConfig config) throws ServletException {
		clsMap.put("category.do", new CategoryModel());
		clsMap.put("food_list.do", new FoodListModel());
		clsMap.put("food_detail.do", new FoodDetailModel());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		cmd=cmd.substring(cmd.lastIndexOf("/")+1);
		Model model=clsMap.get(cmd);
		System.out.println("Controller:"+request);
		String jsp=model.handlerRequest(request, response);
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
