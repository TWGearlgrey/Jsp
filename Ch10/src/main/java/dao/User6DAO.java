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

import dto.User6DTO;

public class User6DAO {
	
	private final String HOST = "jdbc:mysql://52.79.237.168:3306/UserDB";
	private final String USER = "java";
	private final String PASS = "!Q2w3e4r";

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser6(User6DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User6` VALUES (?, ?, ?, ?);");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User6DAO insertUser6 error : " + e.getMessage());
		}
	}
	
	public User6DTO selectUser6(String uid) {
		User6DTO dto = new User6DTO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User6` WHERE `uid`=?");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
			}
			rs.close();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User6DAO selectUser6 error : " + e.getMessage());
		}
		return dto;
	}
	
	public List<User6DTO> selectUser6s() {
		List<User6DTO> users = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User6`;");
			
			while(rs.next()) {
				User6DTO dto = new User6DTO();
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
			logger.error("User6DAO selectUser6s error : " + e.getMessage());
		}
		
		return users;
	}
	
	public void updateUser6(User6DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User6` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			psmt.executeUpdate();
			psmt.close();
			conn.close();

		}catch(Exception e) {
			logger.error("User6DAO updateUser6 error : " + e.getMessage());
		}
	}
	
	public void deleteUser6(String uid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User6` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			psmt.close();
			conn.close();

		}catch(Exception e) {
			logger.error("User6DAO deleteUser6 error : " + e.getMessage());
		}
	}
}