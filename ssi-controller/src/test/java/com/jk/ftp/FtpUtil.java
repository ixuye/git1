/** 
 * <pre>项目名称:ssi-controller 
 * 文件名称:FtpUtil.java 
 * 包名:com.jk.ftp 
 * 创建日期:2017年8月1日下午5:19:35 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.sun.org.glassfish.external.statistics.Statistic;

/** 
 * <pre>项目名称：ssi-controller    
 * 类名称：FtpUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月1日 下午5:19:35    
 * 修改人：徐叶    
 * 修改时间：2017年8月1日 下午5:19:35    
 * 修改备注：       
 * @version </pre>    
 */
public class FtpUtil {
	
	@Test
	public void connFTP(){
		//实例化ftp
		FTPClient ftp = new FTPClient();
		try {
			//连接ftp
			ftp.connect("192.168.1.198", 21);
			//登录ftp
			boolean login = ftp.login("root", "root");
			if(login){
				//切换文件夹路径
				boolean changeWorkingDirectory = ftp.changeWorkingDirectory("/user/bbq");
				//判断文件夹是否存在
				if(!changeWorkingDirectory){
					//新建不存在的文件夹
					ftp.makeDirectory("/user/bbq");
				}
				//设置文件上传的二进制流
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				//上传文件的绝对路径
				FileInputStream fileInputStream = new FileInputStream("E:/soft/Theme/background/timg.jpg");
				boolean storeFile = ftp.storeFile(new String("这是个测试1.jpg".getBytes("GBK"), "ISO-8859-1"), fileInputStream);
				if(storeFile){
					System.out.println("上传成功");
				}else {
					System.out.println("上传失败");
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void upPhoto(String url){
		
			//实例化ftp
			FTPClient ftp = new FTPClient();
			try {
				//连接ftp
				ftp.connect("192.168.1.198", 21);
				//登录ftp
				boolean login = ftp.login("root", "root");
				if(login){
					//切换文件夹路径
					boolean changeWorkingDirectory = ftp.changeWorkingDirectory("/user/bbq");
					//判断文件夹是否存在
					if(!changeWorkingDirectory){
						//新建不存在的文件夹
						ftp.makeDirectory("/user/bbq");
					}
					//设置文件上传的二进制流
					ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
					//上传文件的绝对路径
					FileInputStream fileInputStream = new FileInputStream(url);
					boolean storeFile = ftp.storeFile(new String("这是个测试1.jpg".getBytes("GBK"), "ISO-8859-1"), fileInputStream);
					if(storeFile){
						System.out.println("上传成功");
					}else {
						System.out.println("上传失败");
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
