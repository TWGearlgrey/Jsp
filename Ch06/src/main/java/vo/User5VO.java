package vo;

public class User5VO {
	
	private String uid;
	private String name;
	private String birth;
	private String gender;
	private int    age;
	private String address;
	private String hp;
	
	public User5VO() {
        this.uid = "";
        this.name = "";
        this.birth = "";
        this.gender = "1"; // 기본값으로 "1" (남성) 설정
        this.age = 0;      // 기본값으로 0 설정
        this.address = "";
        this.hp = "";
    }
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
}
