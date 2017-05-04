package com.jet.vframe.sys.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetImgSrc {
	
	//根据正则表达式提取html页面中的图片的src
	public static List<String> getImgSrcList(String htmlStr){
		String img="";   
        Pattern p_image;   
        Matcher m_image;   
        List<String> pics = new ArrayList<String>();
        String regEx_img = "<IMG.*src\\s*=\\s*(.*?)[^>]*?>"; 
        p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);   
        m_image = p_image.matcher(htmlStr); 
       	while(m_image.find()){   
            img = img + "," + m_image.group();   
            Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while(m.find()){
                pics.add(m.group(1));
            }
        }   
        return pics;   
	}
}
