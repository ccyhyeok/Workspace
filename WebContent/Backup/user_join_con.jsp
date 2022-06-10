<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%
		
		request.setCharacterEncoding("utf-8");
	
		String id = request.getParameter("pr-id");
		
		UserDAO dao = UserDAO.getInstance();
		
		if(dao.confirmId(id)) { %>
			<script>
				alert("아이디가 중복되었습니다.");
				history.back();
			</script>
	<%	} else {
		System.out.println("아이디 중복 안됨! 회원가입 로직 진행!");

		UserVO vo = new UserVO(
				id,
				request.getParameter("pr-password"),
				request.getParameter("pr-email"),
				request.getParameter("pr-name"),
				request.getParameter("pr-address")
				);
		dao.insertUser(vo);%>
		<script>
			alert("회원가입을 축하합니다.");
			location.href="user_login.jsp";
		</script>
			
	<% } %>
		
	
