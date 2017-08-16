/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:PermissionInterceptor.java 
 * 包名:common.interceptor 
 * 创建日期:2017年7月28日上午9:38:32 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.jk.pojo.UsersInfo;

import util.JedisUtil;
import util.JsonUtil;



/**
 * 
 * <pre>项目名称：ssi-common    
 * 类名称：PermissionInterceptor    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月28日 下午9:37:29    
 * 修改人：徐叶    
 * 修改时间：2017年7月28日 下午9:37:29    
 * 修改备注：       
 * @version </pre>
 */
public class PermissionInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//从session中获取到userInfo对象
		UsersInfo user = (UsersInfo) session.getAttribute("userInfo");
		//获取到请求的路径
		String requestURI = request.getRequestURI();
		if(request.getRequestURI().equals("/ssi-controller/checkUserInfo.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/qrCode.jhtml")){
			return true;
		}else if(request.getRequestURI().equals("/ssi-controller/uploadPhoto.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/checkUserName.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/insertUserInfo.jhtml")){
			return true;
		}
		
		Object obj = session.getAttribute("userInfo");
		String json = new Gson().toJson(obj);
		int userID = new JsonParser().parse(json).getAsJsonObject().get("userId").getAsInt();
		//获取用户的访问uri，与权限的url做对比，匹配上就向后执行，匹配不上，重定向到无权限页面
		String uri = request.getRequestURI();
		//取出redis中保存的菜单列表 
		String string = JedisUtil.getString(userID + "#tree_list");
		
		int flag = 0;
		//List<Map<String, Object>>
		List<Map<String, Object>> treeList = JsonUtil.fromJson(string, new ArrayList<Map<String, Object>>(){}.getClass());
		for (Map<String, Object> map : treeList) {
			Object href = map.get("url");
			if (null == href) {
				continue;
			} else if (uri.contains(href.toString())) {
				flag = 1;
				break;
			}
		}
		
		if (1 == flag) {
			return true;
		} else {
			return false;
		}
		
		
	}

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)    
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
