/** 
 * <pre>项目名称:ssi-common 
 * 文件名称:MyFilter.java 
 * 包名:common.filter 
 * 创建日期:2017年7月23日下午4:11:14 
 * Copyright (c) 2017, ichengpan@aliyun.com All Rights Reserved.</pre> 
 */  
package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.jk.pojo.UsersInfo;

/** 
 * <pre>项目名称：ssi-common    
 * 类名称：MyFilter    
 * 类描述：    
 * 创建人：徐叶 
 * 创建时间：2017年7月23日 下午4:11:14    
 * 修改人：徐叶    
 * 修改时间：2017年7月23日 下午4:11:14    
 * 修改备注：       
 * @version </pre>    
 */
public class MyFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("进入过滤器");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		System.out.println(httpRequest.getSession().getId());
		HttpSession session = httpRequest.getSession();
		
		//从session中获取到userInfo对象
				UsersInfo user = (UsersInfo) session.getAttribute("userInfo");
				//获取到请求的路径
				String requestURI = httpRequest.getRequestURI();
				System.out.println(requestURI+"11111");
				//验证用户是否登录
				if(user != null){
					chain.doFilter(request, response);
				}
				//如果是登录操作的请求，继续向下执行
				else if(httpRequest.getRequestURI().equals("/ssi-controller/checkUserInfo.jhtml")){
					chain.doFilter(request, response);
				}
				else if(httpRequest.getRequestURI().equals("/ssi-controller/uploadPhoto.jhtml")){
					chain.doFilter(request, response);
				}
				else if(httpRequest.getRequestURI().equals("/ssi-controller/checkUserName.jhtml")){
					chain.doFilter(request, response);
				}
				else if(httpRequest.getRequestURI().equals("/ssi-controller/insertUserInfo.jhtml")){
					chain.doFilter(request, response);
				}
				else if(httpRequest.getRequestURI().equals("/ssi-controller/qrCode.jhtml")){
					chain.doFilter(request, response);
				}
				else{
					//请求到注册页面
					if(requestURI.equals("/ssi-controller/toAddUserPage.jhtml")){
						System.out.println("注册");
						chain.doFilter(request, response);
					}else {
						//未登录，重定向页面到登陆页面
						//判断是否是ajax请求
						
						String type = httpRequest.getHeader("X-Requested-With");// XMLHttpRequest
						// 重定向
						String path = httpRequest.getContextPath();
						String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
						// 转发
						if (StringUtils.equals("XMLHttpRequest", type)) {
							// ajax请求
							httpResponse.setHeader("SESSIONSTATUS", "TIMEOUT");
							httpResponse.setHeader("CONTEXTPATH", basePath+"login.jsp");
							httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
						} else {
							//常规
 							httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
						}
						//httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
					}
				}
				
			
				
				
				//System.out.println("没有登录");
				//return false;
		//调用这个方法，让请求延续
		//chain.doFilter(request, response);
		
		
		
	}

	public void destroy() {
		
	}

}
