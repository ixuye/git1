/** 
 * <pre>项目名称:ssi-common 
 * 文件名称:SessionListener.java 
 * 包名:common.listener 
 * 创建日期:2017年7月23日下午4:12:30 
 * Copyright (c) 2017, ichengpan@aliyun.com All Rights Reserved.</pre> 
 */  
package common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** 
 * <pre>项目名称：ssi-common    
 * 类名称：SessionListener    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月23日 下午4:12:30    
 * 修改人：徐叶    
 * 修改时间：2017年7月23日 下午4:12:30    
 * 修改备注：       
 * @version </pre>    
 */
public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId();
		System.out.println("创建了一个session会话：" + id);
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
