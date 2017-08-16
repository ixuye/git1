/** 
 * <pre>项目名称:ssi-pojo 
 * 文件名称:FingerprintRequest.java 
 * 包名:com.jk.pojo.fingerprint 
 * 创建日期:2017年8月3日下午11:15:02 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.pojo.fingerprint;

/** 
 * <pre>项目名称：ssi-pojo    
 * 类名称：FingerprintRequest    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月3日 下午11:15:02    
 * 修改人：徐叶    
 * 修改时间：2017年8月3日 下午11:15:02    
 * 修改备注：       
 * @version </pre>    
 */
public class FingerprintRequest extends Fingerprint{
		private Integer userID;

		public Integer getUserID() {
			return userID;
		}

		public void setUserID(Integer userID) {
			this.userID = userID;
		}

		@Override
		public String toString() {
			return "FingerprintRequest [userID=" + userID + "]";
		}
		
		
		
		
		
		
		
		
		
}
