package kr.co.jsp.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class UserDAO implements IUserDAO {
		
	private DataSource ds;
	private UserDAO() {
		
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/myOracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	///////////////////////////////////
	
	@Override
	public boolean confirmId(String id) {
		boolean flag = false;
		String sql = "SELECT * FROM pr_user WHERE id=?";
		
		try(Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	@Override
	public void insertUser(UserVO vo) {
		String sql = "INSERT INTO pr_user VALUES(?,?,?,?,?)";
		try(Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1,vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public int userCheck(String id, String pw) {
		int check = 0;
		
		String sql = "SELECT * FROM pr_user WHERE id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pr-pw").equals(pw)) {
					// 
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public UserVO getUserInfo(String id) {
		UserVO user = null;
		String sql = "SELECT * FROM pr_user WHERE id = ?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				user = new UserVO(
						rs.getString("id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("address")
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return user;
	}

	@Override
	public void changePassword(String id, String newPw) {
		String sql = "UPDATE user SET pw=? WHERE id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateuser(UserVO vo) {
		String sql = "UPDATE pr_user "
				+ "SET name=?, email=?, address=?"
				+ " WHERE id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(String pw) {
		String sql = "DELETE FROM pr_user WHERE pw=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	
	
}








