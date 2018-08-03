package blog.csdn.net.mchenys.domain;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class User implements Serializable,HttpSessionActivationListener{

	public String uid;
	public String username;
	public String password;
	
	public String name;
	public String email;
	public String telephone;
	
	public Date birthday;
	public String sex;
	public Integer state = 0; //0:未注册  1:已注册
	public String code;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("user 被钝化...");
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("user 被活化...");
		
	}

}
