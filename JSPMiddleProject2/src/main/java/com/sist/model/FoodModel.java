package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
public class FoodModel {
    public void foodListData(HttpServletRequest request)
    {
    	FoodDAO dao=FoodDAO.newInstance();
    	String strPage=request.getParameter("page");
        if(strPage==null)
        	strPage="1";
        int curpage=Integer.parseInt(strPage);
        List<FoodBean> list=dao.foodListData(curpage);
        int totalpage=dao.foodTotalPage();
        
        // request에 담아준다 => JSP로 보내준다 
        request.setAttribute("curpage", curpage);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("list", list);
    }
}
