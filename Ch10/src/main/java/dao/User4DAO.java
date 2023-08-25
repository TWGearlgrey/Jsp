package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User4DTO;

public class User4DAO {

	private final String HOST = "jdbc:mysql://52.79.237.168:3306/UserDB";
	private final String USER = "java";
	private final String PASS = "!Q2w3e4r";

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser4(User4DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User4` SET `name`=?, `gender`=?, `age`=?, `addr`=?;");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO insertUser4 error : " + e.getMessage());
		}
	}
	
	public User4DTO selectUser4(String seq) {
		User4DTO dto = new User4DTO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User4` WHERE `seq`=?");
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
			}
			rs.close();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO selectUser4 error : " + e.getMessage());
		}
		
		return null;
	}
	
	public List<User4DTO> selectUser4s() {
		List<User4DTO> users = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User4`;");
			
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
				users.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO selectUser4s error : " + e.getMessage());
		}

		return null;
	}
	
	public void updateUser4(User4DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User4` SET `name`=?, `gender`=?, `age`=?, `addr`=? WHERE `seq`=?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.setInt(5, dto.getSeq());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO updateUser4 error : " + e.getMessage());
		}
		
	}
	
	public void deleteUser4(String seq) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User4` WHERE `seq`=?");
			psmt.setString(1, seq);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO deleteUser4 error : " + e.getMessage());
		}
		
	}
}
