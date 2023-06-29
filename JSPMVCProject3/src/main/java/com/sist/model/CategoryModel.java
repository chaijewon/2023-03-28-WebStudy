package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
//        request   request
// Controller = Model = JSP
/*
 *    class A
 *    {
 *       int aa;
 *    }
 *    
 *    public void set(A a)
 *    {
 *      a.aa=100;
 *    }
 *    
 *    A b=new A();
 *    set(b);
 *    
 *    int c=100;
 *    aaa(c)
 *    public void aaa(int a)
 */
public class CategoryModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("CategoryModel:"+request);
		FoodDAO dao=FoodDAO.newInstance();
		List<CategoryVO> list=dao.foodCategoryData();
		// request : DispatcherServlet
		request.setAttribute("list", list);
		return "food/category.jsp";
	}

}
