package com.jk.dao.menu.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.dao.menu.MenuDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

@Repository
public class MenuDaoImpl extends SqlMapClientDaoSupport implements MenuDao {

	
	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectMenuListJson", menuRequest);
	}
	
	@Override
	public List<MenuResponse> queryFirstMenuList(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectFirstMenuList", menuRequest);
	}

	@Override
	public void insertMenu(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().insert("menu.insertMenu", menuRequest);
	}
	
	@Override
	public void deleteMenuById(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().delete("menu.deleteMenuById", menuRequest);
	}
	
	@Override
	public void updateMenuById(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().update("menu.updateMenuById", menuRequest);
	}
	
	@Override
	public MenuResponse queryMenuById(MenuRequest menuRequest) {
		return (MenuResponse) this.getSqlMapClientTemplate().queryForObject("menu.queryMenuById", menuRequest);
	}
}
