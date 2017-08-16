package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jk.pojo.UsersInfo;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//从session中获取到userInfo对象
		UsersInfo user = (UsersInfo) session.getAttribute("userInfo");
		//获取到请求的路径
		String requestURI = request.getRequestURI();
		System.out.println(requestURI+"11111");
		//验证用户是否登录
		if(user != null){
			return true;
		}
		//如果是登录操作的请求，继续向下执行
		else if(request.getRequestURI().equals("/ssi-controller/checkUserInfo.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/uploadPhoto.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/checkUserName.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/insertUserInfo.jhtml")){
			return true;
		}
		else if(request.getRequestURI().equals("/ssi-controller/qrCode.jhtml")){
			return true;
		}
		else{
			//请求到注册页面
			if(requestURI.equals("/ssi-controller/toAddUserPage.jhtml")){
				System.out.println("注册");
				return true;
			}else {
				//未登录，重定向页面到登陆页面
				//判断是否是ajax请求
				
				String type = request.getHeader("X-Requested-With");// XMLHttpRequest
				// 重定向
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
				// 转发
				if (StringUtils.equals("XMLHttpRequest", type)) {
					// ajax请求
					response.setHeader("SESSIONSTATUS", "TIMEOUT");
					response.setHeader("CONTEXTPATH", basePath+"login.jsp");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} else {
					//常规
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}
		}
		
	
		
		
		//System.out.println("没有登录");
		//return false;
		return false;
	}
	
	
	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		UsersInfo user = (UsersInfo) session.getAttribute("loginInfo");
		if(user!=null){
			System.out.println("已经登录");
			return;
		}else if(user == null){
			//获取访问的url
			String requestURI = request.getRequestURI();
			System.out.println(requestURI+"2222");
			if(requestURI.equals("/xu-001/toAddUserPage.jhtml")){
				System.out.println("zhuce");
				return;
			}else{
				modelAndView.setViewName("../../login");
			}
		}
		
	}
	*/
}
