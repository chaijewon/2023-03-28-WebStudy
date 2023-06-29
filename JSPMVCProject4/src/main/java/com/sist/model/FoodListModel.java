package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
public class FoodListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FoodDAO dao=FoodDAO.newInstance();
		String cno=request.getParameter("cno");
	    CategoryVO cvo=dao.foodCategoryInfoData(Integer.parseInt(cno));
	    List<FoodVO> list=dao.foodCategoryListData(Integer.parseInt(cno));
	    request.setAttribute("cvo", cvo);
	    request.setAttribute("list", list);
		return "food/food_list.jsp";
	}

}
