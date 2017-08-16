package com.jk.service.menu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.menu.MenuDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.service.menu.MenuService;
@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	
	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		
		return menuDao.selectMenuListJson(menuRequest);
	}
	
	@Override
	public List<MenuResponse> queryFirstMenuList(MenuRequest menuRequest) {
		return menuDao.queryFirstMenuList(menuRequest);
	}
	
	@Override
	public void insertMenu(MenuRequest menuRequest) {
		if(menuRequest.getPid()==0){
			menuRequest.setParent(true);
		}
			menuDao.insertMenu(menuRequest);
	}

	@Override
	public void deleteMenuById(MenuRequest menuRequest) {
		menuDao.deleteMenuById(menuRequest);
	}

	@Override
	public void updateMenuById(MenuRequest menuRequest) {
		if(menuRequest.getPid()==0){
			menuRequest.setParent(true);
		}
		menuDao.updateMenuById(menuRequest);
	}
	
	@Override
	public MenuResponse queryMenuById(MenuRequest menuRequest) {
		return menuDao.queryMenuById(menuRequest);
	}
}
