/** 
 * <pre>项目名称:ssi-dao-01 
 * 文件名称:RoleDaoImpl.java 
 * 包名:com.jk.dao.role.impl 
 * 创建日期:2017年7月26日下午2:40:27 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.role.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.role.RoleDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;


@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {

	
	@Override
	public void deleteRoleByID(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteRoleById",roleRequest);
		
	}
	
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("role.selectRoleCount", roleRequest);
	}
	
	
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleList", roleRequest);
	}
	
	
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleMenuListJson", menuRequest);
	}
	
	
	@Override
	public void deleteAllRoleMenuByRoleID(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteAllRoleMenuByRoleID", menuRequest);
	}
	
	
	@Override
	public void insertRoleMenuList(final List<MenuRequest> menuRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				sqlMap.startBatch();
				for (MenuRequest menuRequest : menuRequestList) {
					sqlMap.insert("role.insertRoleMenu", menuRequest);
				}
				sqlMap.executeBatch();
				return null;
			}
		});
	}
	
	@Override
	public void insertRoleInfo(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().insert("role.insertRoleInfo", roleRequest);
		
	}
	
	@Override
	public RoleResponse getRoleInfoById(RoleRequest roleRequest) {
		RoleResponse roleResponse = (RoleResponse) this.getSqlMapClientTemplate().queryForObject("role.getRoleInfoById", roleRequest);
		return roleResponse;
	}

	@Override
	public void updateRoleInfoById(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().update("role.updateRoleInfoById", roleRequest);
		
	}
}
