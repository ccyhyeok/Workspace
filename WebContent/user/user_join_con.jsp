
<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
    request.setCharacterEncoding("UTF-8");
    
    UserDAO dao = UserDAO.getInstance();
    
    String id = request.getParameter("pr-id");
    
    if(dao.confirmId("pr-id")){ %>
<script>
	alert("아이디 중복됨. 아이디 다시 만드셔요");
	history.back();
</script>
<% } else {
	    	UserVO vo = new UserVO(
	    			id,
	    	    	request.getParameter("pr-password"),
	    	    	request.getParameter("pr-name"),
	    	    	request.getParameter("pr-email"),
	    	    	request.getParameter("pr-address"));
	    	dao.insertUser(vo); %>
<script>
	alert("회원가입 완료됨. 로그인 페이지로 이동합니다.");
	location.href("user_login.jsp");
</script>

<%} %>
