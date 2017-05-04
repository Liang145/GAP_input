package com.jet.vframe.sys.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyMultipartResolver extends CommonsMultipartResolver {
	private HttpServletRequest request;
	protected FileUpload newFileUpload(FileItemFactory fileItemFactory){
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		upload.setSizeMax(-1);
		if(request!=null){
			HttpSession session = request.getSession();
			FileUploadProgressListener progressListener = new FileUploadProgressListener(session);
			upload.setProgressListener(progressListener);
		}
		return upload;
	}
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String encoding = determineEncoding(request);
		FileUpload fileUpload  = prepareFileUpload(encoding);
		FileUploadProgressListener progressListener = new FileUploadProgressListener(session);
		fileUpload.setProgressListener(progressListener);
		
		try {
			List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
			return parseFileItems(fileItems,encoding);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MultipartException("could not parse multipart servlet request",e);
		}
		
		//return super.parseRequest(arg0);
	}
	@Override
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request)
			throws MultipartException {
		// TODO Auto-generated method stub
		this.request = request;
		return super.resolveMultipart(request);
	}
	

}
