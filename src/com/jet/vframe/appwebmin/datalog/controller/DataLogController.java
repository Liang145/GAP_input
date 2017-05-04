package com.jet.vframe.appwebmin.datalog.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jet.vframe.appwebmin.channel.service.ChannelService;
import com.jet.vframe.appwebmin.datalog.pojo.DataLog;
import com.jet.vframe.appwebmin.datalog.service.DataLogService;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.func.datagrid.DataGrid;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.user.pojo.User;

@Controller
@RequestMapping("/appwebmin/datalog/admin")
public class DataLogController extends BaseController {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	private DataLogService dataLogService;
	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		return "appwebmin/datalog/admin/dataGrid";
	}

	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public DataGrid<DataLog> dataGrid(HttpServletRequest request) {
		User webUser = this.getWebUser(request);
		int role = webUser.getRole();
		if (1 == role) {
			return dataLogService.getDataGridByPage(
					new String[] { "id", "channel_name", "content", "content_type", "status", "create_date" }, null,
					"create_date DESC", null, this.getPageStart(request), this.getPageLength(request));
		} else if (3 == role) {
			List<Integer> channelNames = channelService.getNamesByUser(webUser.getUser_name());
			if (channelNames != null && channelNames.size() > 0) {
				return dataLogService
						.getDataGridByPage(
								new String[] { "id", "channel_name", "content", "content_type", "status",
										"create_date" },
								"channel_name IN (:channelNames)", "create_date DESC",
								HQLUtils.generalMapParamsSingle("channelNames", channelNames.toArray()),
								this.getPageStart(request), this.getPageLength(request));
			} else {
				return dataLogService.getNull();
			}
		}
		return null;

	}

	@RequestMapping("delete")
	@ResponseBody
	public boolean delete(HttpServletRequest request) {
		User webUser = this.getWebUser(request);
		int role = webUser.getRole();
		if (1 == role) {
			dataLogService.deleteAll();
		} else if (3 == role) {
			dataLogService.deleteByUser(webUser.getUser_name());
		} else {
			return false;
		}
		return true;
	}

	/*
	 * @RequestMapping("export") public ResponseEntity<byte[]> export() {
	 * System.out.println("quert start"); List<String> csvList =
	 * dataLogService.getCsvList(); System.out.println("quert end"); int len =
	 * csvList.size(); StringBuilder sb = new
	 * StringBuilder("\uFEFF\"ID\",\"通道名称\",\"数据内容\",\"数据类型\",\"状态\",\"传输时间\"");
	 * System.out.println("add start"); for (int i = 0; i < len; i++) {
	 * sb.append("\r\n"); sb.append(csvList.get(i)); } System.out.println(
	 * "add end"); HttpHeaders headers = new HttpHeaders();
	 * 
	 * headers.setContentDispositionFormData("attachment", sdf.format(new
	 * Date()) + ".csv");
	 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); return new
	 * ResponseEntity<byte[]>(sb.toString().getBytes(), headers, HttpStatus.OK);
	 * }
	 */
	@RequestMapping("export")
	public void export(HttpServletRequest request,@ModelAttribute("start_date") String start_date,@ModelAttribute("end_date") String end_date,
			HttpServletResponse response) {
		User webUser = this.getWebUser(request);
		int role = webUser.getRole();
		List<Integer> channelNames= channelService.getNamesByUser(webUser.getUser_name());
		List<String> csvList = dataLogService.getCsvList(channelNames,webUser,role,start_date,end_date);
		int len = csvList.size();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", sdf.format(new Date()) + ".csv");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=" + sdf.format(new Date()) + ".csv");
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = null;
			String header = "\uFEFF\"ID\",\"通道名称\",\"数据内容\",\"数据类型\",\"状态\",\"传输时间\"";
			buff = header.getBytes();
			out.write(buff, 0, buff.length);
			for (int i = 0; i < len; i++) {
				String content = "\r\n";
				content += csvList.get(i);
				buff = content.getBytes();
				out.write(buff, 0, buff.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
