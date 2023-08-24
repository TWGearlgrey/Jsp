package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.User2DTO;

public class User2DAO {

	private final String HOST = "jdbc:mysql://52.79.237.168:3306/UserDB";
	private final String USER = "java";
	private final String PASS = "!Q2w3e4r";
	
	// ━┫기본 CRUD┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
	
	public void insertUser2(User2DTO dto) {
		
	}
	
	public User2DTO selectUser2(String uid) {
		return null;
	}
	
	public List<User2DTO> selectUser2s() {
		
		List<User2DTO> users = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User2`;");
			
			while(rs.next()) {
				User2DTO dto = new User2DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				users.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void updateUser2(User2DTO dto) {
		
	}
	
	public void deleteUser2(String uid) {
		
	}
	
	// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
}
