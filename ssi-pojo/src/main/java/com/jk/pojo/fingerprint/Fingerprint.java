/** 
 * <pre>项目名称:ssi-pojo 
 * 文件名称:Fingerprint.java 
 * 包名:com.jk.pojo.fingerprint 
 * 创建日期:2017年8月3日下午11:14:32 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.pojo.fingerprint;

import java.util.Date;

/** 
 * <pre>项目名称：ssi-pojo    
 * 类名称：Fingerprint    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月3日 下午11:14:32    
 * 修改人：徐叶    
 * 修改时间：2017年8月3日 下午11:14:32    
 * 修改备注：       
 * @version </pre>    
 */
public class Fingerprint {
	
	  private Integer fid;
	
	  private String fileFingerprint;
	  
	  private String fileUrl;
	  
	  private Date fileCreateDate;
	  
	  private Date fileUpdateDate;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFileFingerprint() {
		return fileFingerprint;
	}

	public void setFileFingerprint(String fileFingerprint) {
		this.fileFingerprint = fileFingerprint;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Date getFileCreateDate() {
		return fileCreateDate;
	}

	public void setFileCreateDate(Date fileCreateDate) {
		this.fileCreateDate = fileCreateDate;
	}

	public Date getFileUpdateDate() {
		return fileUpdateDate;
	}

	public void setFileUpdateDate(Date fileUpdateDate) {
		this.fileUpdateDate = fileUpdateDate;
	}

	@Override
	public String toString() {
		return "Fingerprint [fid=" + fid + ", fileFingerprint=" + fileFingerprint + ", fileUrl=" + fileUrl
				+ ", fileCreateDate=" + fileCreateDate + ", fileUpdateDate=" + fileUpdateDate + "]";
	}
	
	
	
	
	
	
	
}
