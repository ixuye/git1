/** 
 * <pre>项目名称:ssi-dao-01 
 * 文件名称:RoleDao.java 
 * 包名:com.jk.dao.role 
 * 创建日期:2017年7月26日下午2:40:05 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.role;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;


public interface RoleDao {

	
	int selectRoleCount(RoleRequest roleRequest);

	
	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	void deleteAllRoleMenuByRoleID(MenuRequest menuRequest);

	
	void insertRoleMenuList(List<MenuRequest> menuRequestList);


	void deleteRoleByID(RoleRequest roleRequest);


	void insertRoleInfo(RoleRequest roleRequest);


	RoleResponse getRoleInfoById(RoleRequest roleRequest);


	void updateRoleInfoById(RoleRequest roleRequest);

}
