package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sist.common.CommonModel;
import com.sist.controller.RequestMapping;

public class RecommandModel {
  @RequestMapping("weather/weather.do")
  public String today_weather(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  try
	  {
		  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
		  Element section=doc.selectFirst("section#content");
		  String html="<section id=\"content\">";
		  html+=section.html();
		  html+="</section>";
		  // <img src="https://korean.visitseoul.net/resources/theme/images/weather/img-weather10.png" alt="흐리고 비">
		  html=html.replace("src=\"","src=\"https://korean.visitseoul.net" );
		  System.out.println(html);
		  request.setAttribute("html", html);
	  }catch(Exception ex){}
	  request.setAttribute("main_jsp", "../weather/weather.jsp");
	  CommonModel.commonRequestData(request);
	  return "../main/main.jsp";
  }
}
