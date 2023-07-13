package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.common.*;
import com.sist.controller.RequestMapping;
public class NoticeModel {
  @RequestMapping("notice/notice_list.do")
  public String notice_list(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  NoticeDAO dao=NoticeDAO.newInstance();
	  List<NoticeVO> list=dao.noticeListDate(curpage);
	  int totalpage=dao.noticeTotalPage();
	  
	  String[] msg={"","일반공지","이벤트공지","긴급공지"};
	  for(NoticeVO vo:list)
	  {
		  vo.setNotice_type(msg[vo.getType()]);
	  }
	  request.setAttribute("list", list);
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("main_jsp", "../notice/notice_list.jsp");
	  CommonModel.commonRequestData(request);
	  return "../main/main.jsp";
  }
  @RequestMapping("notice/notice_detail.do")
  public String notice_detail(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  NoticeDAO dao=NoticeDAO.newInstance();
	  NoticeVO vo=dao.noticeDetailData(Integer.parseInt(no));
	  String[] msg={"","일반공지","이벤트공지","긴급공지"};
	  vo.setNotice_type(msg[vo.getType()]);
	  
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../notice/notice_detail.jsp");
	  CommonModel.commonRequestData(request);
	  return "../main/main.jsp";
  }
}
