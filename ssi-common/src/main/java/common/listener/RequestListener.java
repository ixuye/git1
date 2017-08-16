/** 
 * <pre>项目名称:ssi-common 
 * 文件名称:RequestListener.java 
 * 包名:common.listener 
 * 创建日期:2017年7月23日下午4:12:47 
 * Copyright (c) 2017, ichengpan@aliyun.com All Rights Reserved.</pre> 
 */  
package common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;

/** 
 * <pre>项目名称：ssi-common    
 * 类名称：RequestListener    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月23日 下午4:12:47    
 * 修改人：徐叶    
 * 修改时间：2017年7月23日 下午4:12:47    
 * 修改备注：       
 * @version </pre>    
 */
public class RequestListener extends RequestContextListener{

	
	/* (non-Javadoc)    
	 * @see org.springframework.web.context.request.RequestContextListener#requestInitialized(javax.servlet.ServletRequestEvent)    
	 */
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		//监听用户发送的请求
		HttpServletRequest servletRequest = (HttpServletRequest) requestEvent.getServletRequest();
		System.out.println("用户创建了一个请求：" + servletRequest.getRequestURI());
	}
	
}
