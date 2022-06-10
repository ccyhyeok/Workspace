package kr.co.jsp.user.model;

public interface IUserDAO {

	
		// 중복 ID 여부를 검증하는 메서드(Primary Key이기 때문에 중복되면 안됨.)
		boolean confirmId(String id);
		
		// 회원 가입을 처리하는 메서드
		void insertUser(UserVO vo);
		/* INSERT, UPDATE, DELETE 리턴 타입: void*/
		
		// 로그인 유효성을 검증하는 메서드
		int userCheck(String id, String pw);
		/* (int: 아디오류,비번오류,로그인 성공실패 여부 총 세 가지로 boolean이 아닌 int) */
		
		// 특정 회원의 모든 정보를 가져오는 메서드
		UserVO getUserInfo(String id);
		
		// 비밀번호를 변경하는 메서드
		void changePassword(String id, String newPw);
		
		// 회원 정보를 수정하는 메서드(비밀번호 제외)
		void updateuser(UserVO vo);
		
		// 회원 탈퇴를 처리할 메서드
		void deleteUser(String pw);
		/* primay key user_id만 필요로 함.
		 지금은 임시 프로젝트로 id를 pw로 바꿈*/
		
		
	}
