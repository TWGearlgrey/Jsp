package kr.co.farmstory2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.UserDTO;

public enum UserService {

	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	UserDAO dao = new UserDAO();
	
	private static String generatedCode;
	
	
	
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	// 유효성 검사 및 중복 검사 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	
	// 닉네임 중복 체크
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	// 휴대폰 중복 체크
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	// 이메일 중복 체크
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public int sendCodeByEmail(String receiver) {
		
		// 인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generatedCode = ""+code;
		logger.info("receiver : " + receiver);
		
		// 기본정보
		String sender   = "shipdesignup@gmail.com";
		String password = "pwufxjssxylqfkmi";
		String title    = "Jboard2 인증코드 입니다.";
		String content  = "<h1>인증 코드는 "+ generatedCode + "</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		//  메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		try{
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			
			status = 1;
			
		}catch(Exception e) {
			status = 0;
			logger.error("sendCodeMyEmail error : " + e.getMessage());
		}
		
		return status;
	} // sendCodeByEmail end...
	
	public int confirmCodeByEmail(String code) {
		
		if(code.equals(generatedCode)) {
			logger.info("generatedCode return 1...");
			return 1;
			
		}else {
			logger.info("generatedCode return 0...");
			return 0;
		}
	}
}
