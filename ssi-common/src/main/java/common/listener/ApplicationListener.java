/** 
 * <pre>项目名称:ssi-common 
 * 文件名称:ApplicationListener.java 
 * 包名:common.listener 
 * 创建日期:2017年7月23日下午4:11:56 
 * Copyright (c) 2017, ichengpan@aliyun.com All Rights Reserved.</pre> 
 */  
package common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** 
 * <pre>项目名称：ssi-common    
 * 类名称：ApplicationListener    
 * 类描述：    
 * 创建人：徐叶 
 * 创建时间：2017年7月23日 下午4:11:56    
 * 修改人：徐叶    
 * 修改时间：2017年7月23日 下午4:11:56    
 * 修改备注：       
 * @version </pre>    
 */
public class ApplicationListener implements ServletContextListener {

	//只是在服务器打开的时候监听，只监听一次
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("打开了服务器进行监听");
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
