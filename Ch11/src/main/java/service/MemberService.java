package service;

import java.util.List;

import dao.MemberDAO;
import dto.MemberDTO;

public enum MemberService {

	INSTANCE;
	
	/*
	public static MemberService instance = new MemberService();
	public MemberDAO getDao() {
		return dao;
	}
	private MemberService() {}
	*/
	
	private MemberDAO dao = MemberDAO.getInstance();
	
	
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}
	
	public MemberDTO selectMember(String uid) {
		return dao.selectMember(uid);
	}
	
	public List<MemberDTO> selectMembers() {
		return dao.selectMembers();
	}
	
	public void updateMember(MemberDTO dto) {
		dao.updateMember(dto);
	}
	
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
}
