package com.jet.vframe.sys.security.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jet.vframe.sys.user.pojo.User;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	// current login user cache
	public final static Map<String, HttpSession> login_user = new HashMap<String, HttpSession>();
	public final static String session_login_key = "webUser";

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
		//System.out.println("listener add");
		if (hsbe.getName().equals(session_login_key)) {
			User webUser = (User) hsbe.getValue();
			if(webUser!=null){//因为remove时也会触发，所以要判断
				HttpSession session = login_user.remove(webUser.getUser_name());
				if (session != null) {
					session.invalidate();//挤掉别人
				}
				login_user.put(webUser.getUser_name(), hsbe.getSession());
			}
		}

	}
//當正常註銷時，不應該設置SESSION無效，只應該REMOVE，因为在同一個無關閉的瀏覽器上正常註銷後再登錄時，SESSION對後端是一直有效的
	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
		//System.out.println("listener remove");
		if (hsbe.getName().equals(session_login_key)) {
			User webUser = (User) hsbe.getValue();
			if(webUser!=null){
				login_user.remove(webUser.getUser_name());
//				login_user.put(webUser.getUser_name(), hsbe.getSession());
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
//		System.out.println("replace");
//		if (hsbe.getName().equals(session_login_key)) {
//			User webUser = (User) hsbe.getValue();
//			HttpSession session = login_user.remove(webUser.getUser_name());
//			 if(session!=null){
//			 //session.invalidate();
//			 }
//			login_user.put(webUser.getUser_name(), hsbe.getSession());
//		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
//		System.out.println("");
//		hse.getSession().getId();
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
//		System.out.println("des");
		User webUser = (User) hse.getSession().getAttribute(session_login_key);
		if(webUser!=null){
			login_user.remove(webUser.getUser_name());
		}
		
	}

}
