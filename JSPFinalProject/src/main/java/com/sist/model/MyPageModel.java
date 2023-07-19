package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.common.CommonModel;
import com.sist.controller.RequestMapping;
import com.sist.dao.CartDAO;
import com.sist.dao.FoodJjimLikeDAO;
import com.sist.dao.MemberDAO;
import com.sist.dao.ReserveDAO;
import com.sist.vo.CartVO;
import com.sist.vo.FoodJJimVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReserveVO;

import java.io.PrintWriter;
import java.util.*;
public class MyPageModel {
  @RequestMapping("mypage/mypage_main.do")
  public String mypage_main(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/mypage_reserve.do")
  public String mypage_reserve(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  ReserveDAO dao=ReserveDAO.newInstance();
	  List<ReserveVO> list=dao.reserveInfoData(id);
	  request.setAttribute("list", list);
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  CommonModel.commonRequestData(request);
	  return "../main/main.jsp";
  }
  // id,name,admin => session 
  //나머지는 ?뒤에 값을 전송 
  @RequestMapping("mypage/mypage_jjim_list.do")
  public String mypage_jjim(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  FoodJjimLikeDAO dao=FoodJjimLikeDAO.newInstance();
	  List<FoodJJimVO> list=dao.foodJjimListData(id);
	  request.setAttribute("list", list);
	  request.setAttribute("mypage_jsp", "../mypage/mypage_jjim.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  CommonModel.commonRequestData(request);
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/mypage_update.do")
	public String memberUpdate(HttpServletRequest request,
			HttpServletResponse response)
	{
		// 이전의 개인 정보를 보낸다 => id
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		// DAO연동 
		MemberDAO dao=MemberDAO.newInstance();
		MemberVO vo=dao.memberUpdateData(id);
		request.setAttribute("vo", vo);
		request.setAttribute("mypage_jsp", "../member/join_update.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonModel.commonRequestData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("mypage/join_update_ok.do")
	public String memberUpdateOk(HttpServletRequest request,
			HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String phone1=request.getParameter("phone1");
		String phone=request.getParameter("phone");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone1+"-"+phone);
		vo.setContent(content);
		
		// DAO연결 
		MemberDAO dao=MemberDAO.newInstance();
		MemberVO mvo=dao.memberUpdate(vo);
		if(mvo.getMsg().equals("yes"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("name", mvo.getName());
		}
		request.setAttribute("mvo", mvo);
		return "../member/join_update_ok.jsp";
	}
	@RequestMapping("mypage/mypage_delete.do")
	public String memberDelete(HttpServletRequest request,
			HttpServletResponse response)
	{
		request.setAttribute("mypage_jsp", "../member/join_delete.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonModel.commonRequestData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("member/join_delete_ok.do")
	public void memberDeleteOk(HttpServletRequest request,
			HttpServletResponse response)
	{
		
		String pwd=request.getParameter("pwd");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//DAO
		MemberDAO dao=MemberDAO.newInstance();
		String result=dao.memberDeleteOk(id, pwd);
		System.out.println(id+"|"+pwd);
		if(result.equals("yes"))
		{
			session.invalidate();
		}
		try
		{
			PrintWriter out=response.getWriter();
			out.println(result);
		}catch(Exception ex) {}
	}
	@RequestMapping("mypage/mypage_cart.do")
	public String mypage_cart(HttpServletRequest request,
			HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		CartDAO dao=CartDAO.newInstance();
		List<CartVO> list=dao.mypageCartListData(id);
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../mypage/mypage_cart.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonModel.commonRequestData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("mypage/mypage_buy.do")
	public String mypage_buy(HttpServletRequest request,
			HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		CartDAO dao=CartDAO.newInstance();
		List<CartVO> list=dao.mypageCartBuyListData(id);
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../mypage/mypage_buy.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonModel.commonRequestData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("mypage/cart_cancel.do")
	public String mypage_cart_cancel(HttpServletRequest request,
			HttpServletResponse response)
	{
		String no=request.getParameter("no");
		CartDAO dao=CartDAO.newInstance();
		dao.cartCancel(Integer.parseInt(no));
		return "redirect:../mypage/mypage_cart.do";
	}
}
