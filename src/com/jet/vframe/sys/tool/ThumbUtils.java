package com.jet.vframe.sys.tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbUtils {

	public ThumbUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void generalThumb(File srcFile, int dstWidth, int dstHeight, File dstFile)
			throws IOException {
		Thumbnails.of(srcFile).size(dstWidth, dstHeight)
				.outputFormat(FileUtils.getExtType(dstFile)).toFile(dstFile);
	}

	public static byte[] generalThumb(File srcFile, int dstWidth, int dstHeight) throws IOException {
		String dir = System.getProperty("java.io.tmpdir");
		String name = UUID.generate() + ".jpg";
		File tmp = new File(dir + name);
		ThumbUtils.generalThumb(srcFile, dstWidth, dstHeight, tmp);
		byte[] buffer = FileUtils.getBytesFromFile(tmp);
		tmp.delete();
		return buffer;

	}

	public static void main(String[] args) throws Exception {
		// File file = new File("d:/S40305-173223.jpg");
		// generalThumb(file, 80, 80);
		// Thumbnails.of("D:/wall/aa.jpg").scale(1f).outputQuality(0.25f)
		// .outputFormat("jpg").toFile("D:/wall/yangsuo.jpg");
	}

	public static InputStream getImgInputStream(InputStream inputStream, String id) {
		InputStream is = null;
		File file = null;
		if (inputStream != null) {
			try {
				Thumbnails
						.of(inputStream)
						.scale(1f)
						.outputQuality(
								Float.parseFloat(SP.getSystemValue("image.Quality")))
						.outputFormat("jpg")
						.toFile(Thread.currentThread().getContextClassLoader().getResource("")
								.toURI().getPath()
								+ id + ".jpg");
				file = new File(Thread.currentThread().getContextClassLoader().getResource("")
						.toURI().getPath()
						+ id + ".jpg");
				byte[] imgByte = FileToByte.getByte(file);
				if (imgByte != null) {
					is = new ByteArrayInputStream(imgByte);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file != null) {
			file.delete();
		}
		return is;
	}

	/**
	 * 
	 * @param inputStream
	 * @param id
	 * @return 获取80*80的小图片
	 */
	public static InputStream getSmallImgInputStream(InputStream inputStream, String id) {
		InputStream is = null;
		File file = null;
		if (inputStream != null) {
			try {
				Thumbnails
						.of(inputStream)
						.scale(1f)
						.outputQuality(
								Float.parseFloat(SP.getSystemValue("image.Quality")))
						.outputFormat("jpg")
						.toFile(Thread.currentThread().getContextClassLoader().getResource("")
								.toURI().getPath()
								+ id + ".jpg");
				file = new File(Thread.currentThread().getContextClassLoader().getResource("")
						.toURI().getPath()
						+ id + ".jpg");
				byte[] imgByte = generalThumb(file, 80, 80);
				if (imgByte != null) {
					is = new ByteArrayInputStream(imgByte);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file != null) {
			file.delete();
		}
		return is;
	}

}
