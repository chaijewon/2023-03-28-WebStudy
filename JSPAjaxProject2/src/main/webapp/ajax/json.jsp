<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.manager.*"%>
<%
    String no=request.getParameter("no");
    MovieManager m=new MovieManager();
    String json=m.movieListData(Integer.parseInt(no));
%>
<%=json %>
