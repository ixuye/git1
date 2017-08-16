/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:MsgTask.java 
 * 包名:com.jk.task 
 * 创建日期:2017年8月1日下午2:08:32 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：MsgTask    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月1日 下午2:08:32    
 * 修改人：徐叶    
 * 修改时间：2017年8月1日 下午2:08:32    
 * 修改备注：       
 * @version </pre>    
 */
@Component
public class MsgTask {
	
	@Scheduled(cron = "10 21 14 * * ?")
	public void cronMsg(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("这是cronMsg>>>>"+sim.format(new Date()));
	}
	
	@Scheduled(fixedRate = 3000, initialDelay = 5000)
	public void simpleMsg(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("这是simpleMsg>>>>"+sim.format(new Date()));
	}
}
