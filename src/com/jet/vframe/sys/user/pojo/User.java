package com.jet.vframe.sys.user.pojo;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String user_name, String real_name, int role, int level, boolean is_filter, boolean enable, Date create_date) {
		super();
		this.user_name = user_name;
		this.real_name = real_name;
		this.role = role;
		this.level = level;
		this.is_filter = is_filter;
		this.enable = enable;
		this.create_date = create_date;
	}
	
    @Length(min = 5, max = 20, message = "{user.name.length.illegal}")  
    //@Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}")  
	private String user_name;
	private String real_name;
	private String password;
	private int role;
	private int level;
	private String filter_ip;
	private boolean is_filter;
	private boolean enable;
	private String last_login_ip;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date last_login_time;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date create_date;
	//@NotEmpty(message = "{user.name.null}")  
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getFilter_ip() {
		return filter_ip;
	}

	public void setFilter_ip(String filter_ip) {
		this.filter_ip = filter_ip;
	}

	public boolean getIs_filter() {
		return is_filter;
	}

	public void setIs_filter(boolean is_filter) {
		this.is_filter = is_filter;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
