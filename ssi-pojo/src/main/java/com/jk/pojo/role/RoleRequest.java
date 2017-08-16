/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:RoleRequest.java 
 * 包名:com.jk.pojo.role 
 * 创建日期:2017年7月25日下午12:17:56 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.pojo.role;

/**
 * 
 * <pre>项目名称：ssi-pojo    
 * 类名称：RoleRequest    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午3:49:51    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午3:49:51    
 * 修改备注：       
 * @version </pre>
 */
public class RoleRequest extends Role {
	
	private static final long serialVersionUID = 2672954567914178206L;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
