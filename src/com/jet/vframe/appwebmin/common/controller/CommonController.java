package com.jet.vframe.appwebmin.common.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.lingala.zip4j.exception.ZipException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.tool.AESFileUtils;
import com.jet.vframe.sys.tool.AESUtils;
import com.jet.vframe.sys.tool.FileUtils;
import com.jet.vframe.sys.tool.RuntimeUtils;
import com.jet.vframe.sys.tool.UUID;
import com.jet.vframe.sys.tool.zip4j.CompressUtil;
import com.jet.vframe.sys.upload.Progress;

@Controller
@RequestMapping("/appwebmin/common/admin")
public class CommonController extends BaseController {
	private final static String update_key_password = "QHe=odIYzG8L1GKuB0qW^c8FtRcwaL";
	private final static String update_dir_path = File.separator + "home" + File.separator + "update_dir";

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView update() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("appwebmin/common/admin/update");
		mv.addObject("v_gapdisplay", RuntimeUtils.execWithReturn("./GAPDisplayFB -v", "/home/GAP_IN"));
		mv.addObject("v_gapencode", RuntimeUtils.execWithReturn("./GAPEncode -v", "/home/GAP_IN"));
		mv.addObject("v_gapreceiver", RuntimeUtils.execWithReturn("java -jar GAPReceiver.jar -v", "/home/GAP_IN"));

		return mv;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@RequestParam(value = "update_pkg", required = true) MultipartFile uploadify, @RequestParam(value = "update_key", required = true) String update_key, HttpServletRequest request)
			throws IOException {
		int res = -3;
		if (update_key_password != null && update_key_password.trim().length() > 0) {
			byte[] encKey = AESUtils.parseHexStr2Byte(update_key);
			byte[] decKey = AESUtils.decrypt(encKey, update_key_password);
			String tmp_name = UUID.generate();
			String ori_path = System.getProperty("java.io.tmpdir") + File.separator + tmp_name + ".update";
			File tmp = new File(ori_path);
			uploadify.transferTo(tmp);
			String dec_path = ori_path + ".dec";
			// System.out.println(key);
			// System.out.println(ori_path);
			// System.out.println(dec_path);
			if (decKey == null || decKey.length <= 0) {
				return -1;
			}
			if (AESFileUtils.decrypt(ori_path, dec_path, new String(decKey))) {
				File update_dir = new File(update_dir_path);
				if (update_dir.exists()) {
					FileUtils.delAllFile(update_dir_path);
				} else {
					update_dir.mkdir();
				}
				try {
					File[] files = CompressUtil.unzip(dec_path, update_dir_path, "");
					for (int i = 0; i < files.length; i++) {
						System.out.println(files[i]);
					}
					res = 0;
				} catch (ZipException e) {
					e.printStackTrace();
					res = -2;
				}
			} else {
				res = -1;
			}
			tmp.delete();
			new File(dec_path).delete();
		} else {
			res = -4;
		}
		request.getSession().removeAttribute("upload_ps");
		return res;
	}

	@RequestMapping(value = "progress")
	@ResponseBody
	public int getUploadProgress(HttpServletRequest request) throws IOException {
		int percent = 0;
		Progress status = (Progress) request.getSession().getAttribute("upload_ps");
		if (status != null) {
			long a = status.getBytesRead();
			long b = status.getContentLength();
			if (a > 0 && b > 0) {
				percent = (int) (a * 100 / b);
			}
			// System.out.println(a+","+b+","+percent);

		}
		return percent;
	}
}
