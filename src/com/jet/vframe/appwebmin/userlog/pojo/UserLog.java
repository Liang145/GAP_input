package com.jet.vframe.appwebmin.userlog.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserLog {
	private String id;
	private String user_name;
	private String operate_name;
	private String operate_ip;
	private String operate_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getOperate_name() {
		return operate_name;
	}

	public void setOperate_name(String operate_name) {
		this.operate_name = operate_name;
	}

	public String getOperate_ip() {
		return operate_ip;
	}

	public void setOperate_ip(String operate_ip) {
		this.operate_ip = operate_ip;
	}

	public String getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
