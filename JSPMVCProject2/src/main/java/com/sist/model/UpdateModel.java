package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateModel implements Model {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "수정 기능!!");
		return "view/update.jsp";
	}

}
