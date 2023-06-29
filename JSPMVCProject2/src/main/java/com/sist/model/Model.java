package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Model {
  public String execute(HttpServletRequest request,HttpServletResponse response);
}
