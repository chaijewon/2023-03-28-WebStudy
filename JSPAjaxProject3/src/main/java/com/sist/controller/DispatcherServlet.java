package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 스프링의 MVC구조 
/*
 *    화면단(View단) : JSP => EL/JSTL
 *       => View 
 *    구현단(비지니스 로직) : Java => 데이터베이스 연결 (요청 처리)
 *       => Model ( VO , DAO , Model , Manager )
 *    연결 (화면 , 구현) : 서블릿 
 *       => Controller 
 *    --------------------------------------------------
 *     model 1 (순수하게 JSP) => 장점 : 분석하기 편리 , 단점: 확장성 , 추가,재사용이 어렵다  
 *     model 2 (JSP/Java) => 단점 : 복합성 , 장점:여러명이 동시에 개발 ,
 *                                         추가,확장성, 재사용 용이 => 유지보수 
 *                        => Controller에 집중이 된다 
 *     domain : Controller를 나눠서 (분산) 작업 => 스프링 5 ~ 6
 *              기능별 서버를 나눠서 독립적으로 개발 
 *              MSA => Spring - Cloud 
 *              ---------------------- CI/CD (git기반 , 도커 , 쿠바네티스, 젠킨스)
 *              DevOps (개발/운영)
 *     
 *     MVC의 동작 구조 
 *     -------------
 *     * Controller를 찾는 방법 : .do (URL주소 설정) 
 *     
 *                  *.do
 *                  -(어떤 단어가 들어와도 된다)
 *                  board/list.do
 *                  board/insert.do
 *                  food/category.do
 *                  
 *     사용자 요청 --------------- Controller(DispatcherServlet) -------- Model ---- DAO
 *                              (Front Controller)
 *                                    |
 *                            =>저장되어 있는 Model클래스를 찾는다
 *                            =>요청 처리에 대한 메소드를 찾아서 호출 
 *                            =>결과값을 request에 담아서 JSP로 전송 
 *                            =>Spring / Struts (FilterDispatcher)
 *                              ---------------
 *                                |
 *                              DispatcherServlet
 *                              
 *                            => 이미 만들어진 메소드를 호출 (재호출) : redirect
 *                            => 새로운 데이터를 JSP로 전송 
 *                               forward 
 *                            => 어노테이션 : if을 대체 
 *                                 | 인덱스 (찾기)
 *     JSP => JSP (Model1) => 홈페이지 , 소기업 : 유지보수가 없는 경우
 *     JSP => 자바 => JSP => MV 
 *     *** JSP => 서블릿 => 자바 => 서블릿 => JSP   => 유지보수 / 대규모         
 *     ---------
 */
import java.util.*;
import com.sist.model.*;
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		clsList.add("com.sist.model.DiaryModel");
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 사용자가 보내준 URI주소를 읽어 온다 
		String cmd=request.getRequestURI();
		// http://localhost/JSPAjaxProject3/diary3/diary.do
		//                 --------------------------------- URI
		System.out.println("cmd="+cmd);
		cmd=cmd.substring(request.getContextPath().length()+1);
		System.out.println("cmd="+cmd);
		try
		{
			// 메소드를 찾아서 호출 
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				// 클래스 정보 읽기 
				Object obj=clsName.getDeclaredConstructor().newInstance();
				// 클래스 메모리 할당 
				Method[] methods=clsName.getDeclaredMethods();
				// 메소드 전체를 읽어 온다 
				// 메소드 위에 있는 어노테이션 읽기 
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd))
					{
						// 조건에 맞는 메소드를 호출 
						String jsp=(String)m.invoke(obj, request,response);
						// request를 보내주고 결과값을 담아서 들어온다 
						RequestDispatcher rd=
								request.getRequestDispatcher(jsp);
						rd.forward(request, response);
					}
				}
			}
		}catch(Exception ex) {}
	}

}
