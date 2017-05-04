package com.jet.vframe.sys.security.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jet.vframe.sys.security.component.VerifyCode;

@Controller
public class VerifyCodeController {
	@Autowired
	private VerifyCode verityCodeG;

	// 生成验证码 action
	@RequestMapping("/verifyCode/{random}")
	public void verifyCode(@PathVariable String random, HttpServletRequest request, HttpServletResponse response) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		String res = verityCodeG.getImage(output);
		if (res != null) {
			request.getSession().setAttribute("verifyCode", res);
			System.out.println("verifyCode:" + res);

			response.setContentType("application/octet-stream");// image/png
			OutputStream out = null;
			try {
				out = response.getOutputStream();
				out.write(output.toByteArray());
				// out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}

}
