package com.jet.vframe.appwebmin.datalog.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataLog {

	public DataLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataLog(String id, int channel_name,String content, int content_type, int status, Date create_date) {
		super();
		this.id = id;
		this.channel_name = channel_name;
		this.content = content;
		this.content_type = content_type;
		this.status = status;
		this.create_date = create_date;
	}

	private String id;
	private int channel_name;
	private String content;
	private String file_name;
	private byte[] file_content;
	private int content_type;
	private int status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(int channel_name) {
		this.channel_name = channel_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public byte[] getFile_content() {
		return file_content;
	}

	public void setFile_content(byte[] file_content) {
		this.file_content = file_content;
	}

	public int getContent_type() {
		return content_type;
	}

	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
