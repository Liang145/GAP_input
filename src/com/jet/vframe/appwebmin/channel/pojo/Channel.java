package com.jet.vframe.appwebmin.channel.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Channel {

	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Channel(String id, int name, int level, boolean is_filter, String mark, Date create_date) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.is_filter = is_filter;
		this.mark = mark;
		this.create_date = create_date;
	}

	private String id;
	private int name;
	private String password;
	private int level;
	private String filter_ip;
	private boolean is_filter;
	private String user_name;
	private String mark;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_date;

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
