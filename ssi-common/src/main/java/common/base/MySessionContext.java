package common.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * 
 * <pre>项目名称：xu-001    
 * 类名称：MySessionContext    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月21日 下午7:37:11    
 * 修改人：徐叶   
 * 修改时间：2017年7月21日 下午7:37:11    
 * 修改备注：       
 * @version </pre>
 */
public class MySessionContext {

	private static Map<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();
	
	
	public static void addSession(String userID, HttpSession session) {
		sessionMap.put(userID, session);
	}
	
	public static void removeSession(String userID,HttpSession session) {
		HttpSession httpSession = sessionMap.get(userID);
		if (null != httpSession) {
			//使原来存在的session失效
			//判断session是否失效，没有失效的让他失效
			String json = new Gson().toJson(httpSession);
			boolean valid = new JsonParser().parse(json).getAsJsonObject()
					.get("session").getAsJsonObject()
					.get("isValid").getAsBoolean();
			//session失效，并且请求的session与失效的session的ID不同,才将失效的session销毁
			if (valid && !httpSession.getId().equals(session.getId())) {
				httpSession.invalidate();
			}
		}
	}
	
	public static HttpSession getSession(String userID) {
		return sessionMap.get(userID);
	}
	
}
