package com.sist.model;
/*
 *   Java : 오라클 연동 ======> DAO
 *          브라우저로 값 전송 ===> Model
 *          
 *          .do(request)
 *    View        Model(기능) => DAO,VO
 *   JSP(웹)  ===> Model  ===> DAO
 *              1. 값을 받는다 
 *              request.getParameter()
 *              DAO연동 => 데이터 읽기
 *              request.setAttribute()
 *              2.request를 받을 JSP설정 
 *           <===         <===
 *           
 *        ===> 사용자 요청 (여러개) ==> 구분 (조건문) if 
 *                                  -------------- 어노테이션 
 *                                  -------------- 안에 내용이 중복되면 오류
 *                                  @RequestMapping("|")
 *                                         중복제거 => 폴더
 */

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyBoardDAO;
import com.sist.vo.ReplyBoardVO;

public class ReplyBoardModel {
   @RequestMapping("replyboard/list.do")
   public String replyboard_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // 목록 전송 => DAO
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   List<ReplyBoardVO> list=dao.replyBoardListData(curpage);
	   int totalpage=dao.replyBoardTotalPage();
	   // java => jsp (request,session)
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../replyboard/list.jsp");
	   return "../main/main.jsp";
   }
   // 추가 
   @RequestMapping("replyboard/insert.do")
   public String replyboard_insert(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../replyboard/insert.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("replyboard/insert_ok.do")
   public String replyboard_insert_ok(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   ReplyBoardVO vo=new ReplyBoardVO();
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setId(id);
	   vo.setName(name);
	   
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   dao.replyBoardInsert(vo);
	   
	   return "redirect:../replyboard/list.do";
   }
   // 상세보기 
   @RequestMapping("replyboard/detail.do")
   public String replyboard_detail(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   // 연동 
	   ReplyBoardVO vo=dao.replyBoardDetailData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../replyboard/detail.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("replyboard/delete.do")
   public String replyboard_delete(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   dao.replyBoardDelete(Integer.parseInt(no));
	   return "redirect:../replyboard/list.do";
   }
   @RequestMapping("replyboard/update.do")
   public String replyboard_update(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   ReplyBoardVO vo=dao.replyBoardUpdatelData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../replyboard/update.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("replyboard/update_ok.do")
   public String replyboard_update_ok(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String no=request.getParameter("no");
	   
	   ReplyBoardVO vo=new ReplyBoardVO();
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setNo(Integer.parseInt(no));
	   
	   ReplyBoardDAO dao=ReplyBoardDAO.newInstance();
	   dao.replyBoardUpdate(vo);
	   return "redirect:../replyboard/detail.do?no="+no;
   }
}





