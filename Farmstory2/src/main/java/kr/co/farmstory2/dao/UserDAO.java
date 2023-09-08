package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.UserDTO;

public class UserDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertUser(UserDTO dto) {
		logger.debug("insertUser() start");
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			close();
			
			logger.debug("insertUser() try end...");
			
		} catch (Exception e) {
			logger.error("insertUser() ERROR : " + e.getMessage());
		}
		
	}
	
	public UserDTO selectUser(String uid, String pass) {
		logger.debug("selectUser() start");
		UserDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectUser() error : " + e.getMessage());
		}
		logger.debug("selectUser() try end... (dto: " + dto + ")");
		return dto;
	}
	
	public List<UserDTO> selectUsers(int start) {
		logger.debug("selectUsers() start");
		List<UserDTO> users = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USERS);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
				users.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectUsers() ERROR : " + e.getMessage());
		}
		logger.debug("selectUsers() try end...");
		return users;
	}
	
	public void updateUser(UserDTO dto) {}
	
	public void deleteUser(String uid) {}
	
	public int selectCountUsersTotal() {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_USERS_ALL);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectCountUersTotal() ERROR : " + e.getMessage());
		}
		return total;
	}
	
	
	
	// 유효성 검사 및 중복 검사 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	
	// 아이디 중복 체크
	public int selectCountUid(String uid) {
		logger.debug("selectCountUid() start");
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCountUid() error : " + e.getMessage());
		}
		logger.debug("selectCountUid() try end... (result: " + result +")");
		return result;
	}
	
	// 닉네임 중복 체크
	public int selectCountNick(String nick) {
		logger.debug("selectCountNick() start");
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCountNick() error : " + e.getMessage());
		}
		logger.debug("selectCountNick() try end... (result: " + result +")");
		return result;
	}
	
	// 휴대폰 중복 체크
	public int selectCountHp(String hp) {
		logger.debug("selectCountHp() start");
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCountHp() error : " + e.getMessage());
		}
		logger.debug("selectCountHp() try end... (result: " + result +")");
		return result;
	}
	
	// 이메일 중복 체크
	public int selectCountEmail(String email) {
		logger.debug("selectCountEmail() start");
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCountEmail() error : " + e.getMessage());
		}
		logger.debug("selectCountEmail() try end... (result: " + result +")");
		return result;
	}
}
