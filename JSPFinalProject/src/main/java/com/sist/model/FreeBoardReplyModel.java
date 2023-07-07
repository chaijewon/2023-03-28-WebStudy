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
  @RequestMapping("board/reply_update.do")
  public String reply_update(HttpServletRequest request, 
		    HttpServletResponse response)
  {
	  try
	  {
		 request.setCharacterEncoding("UTF-8");  
	  }catch(Exception ex) {}
	  // 디코딩 (한글 변환)
	  String bno=request.getParameter("bno");//게시물번호 (이동 목적)
	  String no=request.getParameter("no");// 댓글번호 
	  String msg=request.getParameter("msg");
	  // DAO연결 => 오라클 변경 
	  FreeBoardReplyDAO dao=FreeBoardReplyDAO.newInstance();
	  dao.replyUpdate(Integer.parseInt(no), msg);
	  return "redirect:../board/detail.do?no="+bno;
  }
}
