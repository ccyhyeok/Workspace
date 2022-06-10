<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	String oldPw = request.getParameter("old_pw");
	String newPw = request.getParameter("new_pw");
	
	UserVO vo = (UserVO) session.getAttribute("user");
	String id = vo.getId();

	UserDAO dao = UserDAO.getInstance();
	
	if(dao.userCheck(id, oldPw) == 1){
		dao.changePassword(id, newPw); %>
		<script>
			alert("비밀번호가 정상적으로 변경되었습니다!");
			location.href="user_mypage.jsp";
		</script>
	<% } else { %>
		<script>
			alert("현재 비밀번호가 다릅니다.");
			history.back();
		</script>
	<% } 
	

%>