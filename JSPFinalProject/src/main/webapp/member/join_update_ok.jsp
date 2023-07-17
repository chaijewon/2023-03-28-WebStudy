<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${mvo.msg=='yes' }">
  <c:redirect url="../main/main.do"/>
</c:if>
<c:if test="${mvo.msg=='no' }">
  <script>
   alert("비밀번호가 틀립니다!!");
   history.back();
  </script>
</c:if>