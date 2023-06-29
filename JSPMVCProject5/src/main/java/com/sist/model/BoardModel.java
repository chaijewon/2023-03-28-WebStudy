package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class BoardModel {
   public String boardList(HttpServletRequest request)
   {
	   request.setAttribute("msg", "게시판 목록");
	   return "board/list.jsp";
   }
   public String boardInsert(HttpServletRequest request)
   {
	   request.setAttribute("msg", "게시판 데이터 추가");
	   return "board/insert.jsp";
   }
   public String boardUpdate(HttpServletRequest request)
   {
	   request.setAttribute("msg", "게시판 데이터 수정");
	   return "board/update.jsp";
   }
   public String boardDelete(HttpServletRequest request)
   {
	   request.setAttribute("msg", "게시판 데이터 삭제");
	   return "board/delete.jsp";
   }
}
