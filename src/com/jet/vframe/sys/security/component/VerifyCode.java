package com.jet.vframe.sys.security.component;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.stereotype.Component;
@Component
public class VerifyCode {

	private  ConfigurableCaptchaService cs = null;

	private  ColorFactory cf = null;

	private  RandomWordFactory wf = null;

	private  Random r = new Random();

	private  CurvesRippleFilterFactory crff = null;
	private  RandomFontFactory ff = null;

	private  MarbleRippleFilterFactory mrff = null;

	private  DoubleRippleFilterFactory drff = null;

	private  WobbleRippleFilterFactory wrff = null;

	private  DiffuseRippleFilterFactory dirff = null;
	
	

	/*
	 * 默认配置说明： FontFactory 字体
	 * 默认：RandomFontFactory（Verdana，Tahoma，minSize、maxSize=45） WordFactory
	 * 单词范围和长度 默认：RandomWordFactory 单词6-6 ColorFactory 默认：SingleColorFactory 黑色
	 * BackgroundFactory 默认：SingleColorBackgroundFactory 白色 TextRenderer 文本渲染
	 * 默认：BestFitTextRenderer FilterFactory 样式
	 * 默认：CurvesRippleFilterFactory（曲线波纹带干扰线，干扰线颜色为当前colorFactory） width/height
	 * 图片长宽（默认160*70）
	 */
	

	public ConfigurableCaptchaService getCs() {
		return cs;
	}

	public void setCs(ConfigurableCaptchaService cs) {
		this.cs = cs;
	}

	public ColorFactory getCf() {
		return cf;
	}

	public void setCf(ColorFactory cf) {
		this.cf = cf;
	}

	public RandomWordFactory getWf() {
		return wf;
	}

	public void setWf(RandomWordFactory wf) {
		this.wf = wf;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public CurvesRippleFilterFactory getCrff() {
		return crff;
	}

	public void setCrff(CurvesRippleFilterFactory crff) {
		this.crff = crff;
	}

	public RandomFontFactory getFf() {
		return ff;
	}

	public void setFf(RandomFontFactory ff) {
		this.ff = ff;
	}

	public MarbleRippleFilterFactory getMrff() {
		return mrff;
	}

	public void setMrff(MarbleRippleFilterFactory mrff) {
		this.mrff = mrff;
	}

	public DoubleRippleFilterFactory getDrff() {
		return drff;
	}

	public void setDrff(DoubleRippleFilterFactory drff) {
		this.drff = drff;
	}

	public WobbleRippleFilterFactory getWrff() {
		return wrff;
	}

	public void setWrff(WobbleRippleFilterFactory wrff) {
		this.wrff = wrff;
	}

	public DiffuseRippleFilterFactory getDirff() {
		return dirff;
	}

	public void setDirff(DiffuseRippleFilterFactory dirff) {
		this.dirff = dirff;
	}

	public VerifyCode() {
		// TODO Auto-generated constructor stub
		cs = new ConfigurableCaptchaService();

		cf = new SingleColorFactory(new Color(25, 60, 170));

		ff = new RandomFontFactory();
		ff.setMaxSize(32);
		ff.setMinSize(28);
		// 文本内容
		wf = new RandomWordFactory();
		// wf.setCharacters("123abc");
		wf.setMinLength(4);
		wf.setMaxLength(4);

		crff = new CurvesRippleFilterFactory(cs.getColorFactory());

		drff = new DoubleRippleFilterFactory();

		wrff = new WobbleRippleFilterFactory();

		dirff = new DiffuseRippleFilterFactory();

		mrff = new MarbleRippleFilterFactory();

		cs.setFontFactory(ff);
		cs.setWordFactory(wf);
		cs.setFilterFactory(wrff);
		cs.setColorFactory(cf);

		cs.setWidth(100);

		cs.setHeight(34);
	}

	public  String getImage(ByteArrayInputStream in) {
		String res = null;
		try {
			OutputStream os = new ByteArrayOutputStream();
			res = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
			in = parse(os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public  String getImage(OutputStream os) {
		String res = null;
		try {

			res = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ByteArrayOutputStream parse(InputStream in) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream;
	}

	public  ByteArrayInputStream parse(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(
				baos.toByteArray());
		return swapStream;
	}

	public String parse_String(InputStream in) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream.toString();
	}

	public String parse_String(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(
				baos.toByteArray());
		return swapStream.toString();
	}

	public ByteArrayInputStream parse_inputStream(String in) throws Exception {
		ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
		return input;
	}

	public ByteArrayOutputStream parse_outputStream(String in) throws Exception {
		return parse(parse_inputStream(in));
	}

}
