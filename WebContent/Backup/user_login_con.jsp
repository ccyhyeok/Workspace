<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	request.setCharacterEncoding("utf-8");
    
    	String id = request.getParameter("pr-id");
    	String pw = request.getParameter("pr-pw");
    	
    	UserDAO dao = UserDAO.getInstance();
    	
    	int result = dao.userCheck(id, pw);
    	System.out.println(result);
    	
    	if(result == -1){ %>
    		<script>
    			alert("아이디가 존재하지 않습니다.");
    			location.href="user_login.jsp";
    		</script>
    	<% } else if(result == 0) { %>	
			<script>
				alert("비밀번호가 틀렸습니다.");
				history.back();
			</script>
    	<% } else {
    		UserVO vo = dao.getUserInfo(id);
    		session.setAttribute("user", vo);
    		response.sendRedirect("user_mypage.jsp");
    	}
    	  %>
    