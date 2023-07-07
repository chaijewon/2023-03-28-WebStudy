package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class ReplyModel {
   private String[] url={"","../food/food_detail.do",
		    "../goods/goods_detail.do",
		    "../seoul/seoul_detail.do"};
   @RequestMapping("reply/reply_insert.do")
   public String reply_insert(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String cno=request.getParameter("cno");
	   String type=request.getParameter("type");
	   String msg=request.getParameter("msg");
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   ReplyVO vo=new ReplyVO();
	   vo.setCno(Integer.parseInt(cno));
	   vo.setType(Integer.parseInt(type));
	   vo.setId(id);
	   vo.setName(name);
	   vo.setMsg(msg);
	   
	   // DAO를 통해서 오라클로 전송 
	   ReplyDAO dao=ReplyDAO.newInstance();
	   dao.replyInsert(vo);
	   
	   return "redirect:"+url[Integer.parseInt(type)]+"?fno="+cno;
   }
   // 댓글 삭제 
   // JSP (.do) => @RequestMapping() => Model에서 요청 처리 => 화면 이동 
   @RequestMapping("reply/reply_delete.do")
   public String reply_delete(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   String type=request.getParameter("type");
	   String cno=request.getParameter("cno");
	   // 기능 처리 
	   ReplyDAO dao=ReplyDAO.newInstance();
	   dao.replyDelete(Integer.parseInt(no));
	   return "redirect:"+url[Integer.parseInt(type)]+"?fno="+cno;
   }
   @RequestMapping("reply/reply_update.do")
   public String reply_update(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String no=request.getParameter("no");
	   String type=request.getParameter("type");
	   String cno=request.getParameter("cno");
	   String msg=request.getParameter("msg");
	   ReplyDAO dao=ReplyDAO.newInstance();
	   dao.replyUpdate(Integer.parseInt(no), msg);
	   return "redirect:"+url[Integer.parseInt(type)]+"?fno="+cno;
   }
}
