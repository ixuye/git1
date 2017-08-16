/** 
 * <pre>项目名称:ssi-service-01 
 * 文件名称:RoleServiceImpl.java 
 * 包名:com.jk.service.role.impl 
 * 创建日期:2017年7月26日下午2:39:04 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.role.RoleDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.role.RoleService;

/**
 * 
 * <pre>项目名称：ssi-service    
 * 类名称：RoleServiceImpl    
 * 类描述：    
 * 创建人：徐叶
 * 创建时间：2017年7月27日 上午10:56:31    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 上午10:56:31    
 * 修改备注：       
 * @version </pre>
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	
	@Resource
	private RoleDao roleDao;

	@Override
	public void insertRoleInfo(RoleRequest roleRequest) {
		roleDao.insertRoleInfo(roleRequest);
		
	}
	
	@Override
	public void deleteRole(RoleRequest roleRequest) {
		roleDao.deleteRoleByID(roleRequest);
		
	}
	
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return roleDao.selectRoleCount(roleRequest);
	}
	
	
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return roleDao.selectRoleList(roleRequest);
	}
	
	
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return roleDao.selectRoleMenuListJson(menuRequest);
	}
	
	
	@Override
	public void insertRoleMenuList(List<MenuRequest> menuRequestList) {
		roleDao.deleteAllRoleMenuByRoleID(menuRequestList.get(0));
		roleDao.insertRoleMenuList(menuRequestList);
	}

	/**
	 * 
	 * 
	 */
	@Override
	public RoleResponse getRoleInfoById(RoleRequest roleRequest) {
		return roleDao.getRoleInfoById(roleRequest);
	}

	@Override
	public void updateRoleInfoById(RoleRequest roleRequest) {
			roleDao.updateRoleInfoById(roleRequest);
	}
}
