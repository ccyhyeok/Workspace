<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<% 

	request.setCharacterEncoding("utf-8");
	
	UserVO vo = (UserVO)session.getAttribute("user");
	String id = vo.getId();
	
	String pw = request.getParameter("pw_check");
	
	UserDAO dao = UserDAO.getInstance();
	
	if(dao.userCheck(id, pw) == 0 ) { %>
		<script>
			alert("비밀번호가 들렸습니다.");
			location.href="user_mypage.jsp";
		</script>
	<%	} else {
		dao.deleteUser(id);
		session.invalidate(); %>
		<script>
			alert("회원탈퇴가 정상적으로 이루어졌습니다.");
			location.href="/Workspace";
		</script>
		
	<% }
		
	
	%>