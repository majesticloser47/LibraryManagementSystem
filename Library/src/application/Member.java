package application;

public class Member {
	private String memid;
	private String firstname;
	private String lastname;
	private int age;
	private String type;
	public Member(String memid, String firstname, String lastname, int age, String type) {
		super();
		this.memid = memid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.type = type;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
