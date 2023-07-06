package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.common.*;
import com.sist.controller.RequestMapping;
public class SeoulModel {
   @RequestMapping("seoul/seoul_list.do")
   public String seoul_list(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   String type=request.getParameter("type");
	   if(type==null)
		   type="1";
	   int curpage=Integer.parseInt(page);
	   SeoulDAO dao=SeoulDAO.newInstance();
	   List<SeoulVO> list=dao.seoulListData(curpage, 
			   Integer.parseInt(type));
	   int totalpage=dao.seoulTotalPage(Integer.parseInt(type));
	   
	   final int BLOCK=5;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // 전송 
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("list", list);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("type", type);
	   request.setAttribute("main_jsp", "../seoul/seoul_list.jsp");
	   String[] msg={"","서울 명소","서울 자연 & 관광","서울 쇼핑"};
	   request.setAttribute("msg", msg[Integer.parseInt(type)]);
	   
	   CommonModel.commonRequestData(request);
	   return "../main/main.jsp";
   }
}






