package zttc.itat.model;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
public class User {
	private Integer sid;
	private String username;
	private String password;
	private String nickname;
	private String email;
	public User() {
		
	}
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@NotEmpty(message="用户名不能为空")
	public String getUsername(){
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}

	@Size(min=1,max=10,message="密码的长度应该在1-10之间")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Email(message="邮箱的格式不正确")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User(String username, String password, String nickname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", nickname=" + nickname + ", email=" + email
				+ "]";
	}
	
	
}
