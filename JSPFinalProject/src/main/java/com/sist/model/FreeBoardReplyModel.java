package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class FreeBoardReplyModel {
  @RequestMapping("board/reply_insert.do")
  public String reply_insert(HttpServletRequest request, 
		    HttpServletResponse response)
  {
	  try
	  {
		 request.setCharacterEncoding("UTF-8");  
	  }catch(Exception ex) {}
	  String bno=request.getParameter("bno");
	  String msg=request.getParameter("msg");
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  String name=(String)session.getAttribute("name");
	  
	  FreeBoardReplyVO vo=new FreeBoardReplyVO();
	  vo.setBno(Integer.parseInt(bno));
	  vo.setMsg(msg);
	  vo.setId(id);
	  vo.setName(name);
	  
	  // DAO로 전송 
	  FreeBoardReplyDAO dao=FreeBoardReplyDAO.newInstance();
	  dao.replyInsert(vo);
	  return "redirect:../board/detail.do?no="+bno;
  }
}
