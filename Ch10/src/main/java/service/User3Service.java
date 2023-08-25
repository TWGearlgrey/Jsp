package service;

import java.util.List;

import dao.User3DAO;
import dto.User3DTO;

public class User3Service {
	
	private User3DAO dao = new User3DAO();

	public void insertUser3(User3DTO dto) {
		dao.insertUser3(dto);
	}
	
	public User3DTO selectUser3(String uid) {
		return dao.selectUser3(uid);
	}
	
	public List<User3DTO> selectUser3s() {
		return dao.selectUser3s();
	}
	
	public void updateUser3(User3DTO dto) {
		dao.updateUser3(dto);
	}
	
	public void deleteUser3(String uid) {
		dao.deleteUser3(uid);
	}
	
}
