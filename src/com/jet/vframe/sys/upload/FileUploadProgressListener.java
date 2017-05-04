package com.jet.vframe.sys.upload;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadProgressListener implements ProgressListener {
	private HttpSession session;
	
	

	public FileUploadProgressListener() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUploadProgressListener(HttpSession session) {
		super();
		this.session = session;
		Progress status = new Progress();
		session.setAttribute("upload_ps", status);
	}



	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// TODO Auto-generated method stub
		Progress status = (Progress)session.getAttribute("upload_ps");
		status.setBytesRead(pBytesRead);
		status.setContentLength(pContentLength);
		status.setItems(pItems);
		session.setAttribute("upload_ps", status);
		//System.out.println(pBytesRead +","+pContentLength+","+pItems);
	}

}
