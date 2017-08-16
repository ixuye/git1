package com.jk.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.service.menu.MenuService;

/**
 * 
 * <pre>项目名称：ssi-controller    
 * 类名称：MenuController    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午4:51:52    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午4:51:52    
 * 修改备注：       
 * @version </pre>
 */

@Controller
@RequestMapping("/menu/")
public class MenuController {

	@Resource
	private MenuService menuService;
	
	/**
	 * <pre>toMenuPage(去到菜单展示页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午5:00:38    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午5:00:38    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toMenuPage")
	public String toMenuPage(){
		return "menu/showMenuList";
	}
	
	/**
	 * <pre>selectMenuListJson(查询菜单列表)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午5:30:15    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午5:30:15    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("selectMenuListJson")
	@ResponseBody
	List<MenuResponse> selectMenuListJson(MenuRequest menuRequest){
		List<MenuResponse> menuList = menuService.selectMenuListJson(menuRequest);
		return menuList;
	}
	
	/**
	 * <pre>toAddMenuPage(到新增页面，有id回显;查询所有的一级菜单，展示到新增页面的下拉框)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:17:54    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:17:54    
	 * 修改备注： 
	 * @param menuRequest
	 * @param m
	 * @return</pre>
	 */
	@RequestMapping("toAddMenuPage")
	String toAddMenuPage(MenuRequest menuRequest,Model m){
		if(Integer.valueOf(menuRequest.getMenuID())!=null && menuRequest.getMenuID()!=0){
			MenuResponse menuResponse = menuService.queryMenuById(menuRequest);
			m.addAttribute("menu", menuResponse);
		}
		
		List<MenuResponse> menuFirstList = menuService.queryFirstMenuList(menuRequest);
		m.addAttribute("menuFirstList", menuFirstList);
		return "menu/addMenuPage";
	}
	
	/**
	 * <pre>insertMenu(有id是修改，没有id是新增)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:19:13    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:19:13    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("insertMenu")
	@ResponseBody
	public String insertMenu(MenuRequest menuRequest){
		if(Integer.valueOf(menuRequest.getMenuID())!=null && menuRequest.getMenuID()!=0){
			menuService.updateMenuById(menuRequest);
		}else{
			
			menuService.insertMenu(menuRequest);
		}
		return "{}";
	}
	
	/**
	 * <pre>deleteMenuById(根据id删除菜单，并删除其关联的子菜单)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:20:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:20:04    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("deleteMenuById")
	@ResponseBody
	public String deleteMenuById(MenuRequest menuRequest){
		menuService.deleteMenuById(menuRequest);
		return "{}";
	}
	
	
}
