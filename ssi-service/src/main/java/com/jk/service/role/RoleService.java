/** 
 * <pre>项目名称:ssi-service-01 
 * 文件名称:RoleService.java 
 * 包名:com.jk.service.role 
 * 创建日期:2017年7月26日下午2:38:36 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

public interface RoleService {

	int selectRoleCount(RoleRequest roleRequest);
	
	
	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	
	void insertRoleMenuList(List<MenuRequest> menuRequestList);


	void deleteRole(RoleRequest roleRequest);


	void insertRoleInfo(RoleRequest roleRequest);


	RoleResponse getRoleInfoById(RoleRequest roleRequest);


	void updateRoleInfoById(RoleRequest roleRequest);

}
