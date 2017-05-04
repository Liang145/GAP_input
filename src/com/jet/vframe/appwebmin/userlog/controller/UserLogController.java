package com.jet.vframe.appwebmin.userlog.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jet.vframe.appwebmin.userlog.pojo.UserLog;
import com.jet.vframe.appwebmin.userlog.service.UserLogService;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.func.datagrid.DataGrid;

@Controller
@RequestMapping("/appwebmin/userlog/admin")
public class UserLogController extends BaseController {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	private UserLogService userLogService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		return "appwebmin/userlog/admin/dataGrid";
	}

	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public DataGrid<UserLog> dataGrid(HttpServletRequest request) {
		return userLogService.getDataGridByPage(null, "create_date DESC", null, this.getPageStart(request),
				this.getPageLength(request));
	}

	@RequestMapping("export")
	public ResponseEntity<byte[]> export() {
		List<String> csvList = userLogService.getCsvList();
		int len = csvList.size();
		StringBuilder sb = new StringBuilder("\uFEFF\"ID\",\"操作用户名\",\"操作名称\",\"操作IP\",\"操作类型\",\"操作时间\"");
		for (int i = 0; i < len; i++) {
			sb.append("\r\n");
			sb.append(csvList.get(i));
		}
		HttpHeaders headers = new HttpHeaders();

		headers.setContentDispositionFormData("attachment", sdf.format(new Date()) + ".csv");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(sb.toString().getBytes(), headers, HttpStatus.OK);
	}

	@RequestMapping("import")
	@ResponseBody
	public boolean upload(@RequestParam(value = "uploadify", required = false) MultipartFile uploadify,
			HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println(uploadify.getContentType());
		System.out.println(new String(uploadify.getBytes(), "UTF-8"));
		return true;
	}
}
