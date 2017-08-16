/** 
 * <pre>项目名称:ssi-controller-01 
 * 文件名称:RoleController.java 
 * 包名:com.jk.controller.role 
 * 创建日期:2017年7月26日下午2:37:21 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.role.RoleService;

/**
 * 
 * <pre>项目名称：ssi-controller    
 * 类名称：RoleController    
 * 类描述：    
 * 创建人：徐叶
 * 创建时间：2017年7月26日 下午8:29:38    
 * 修改人：徐叶    
 * 修改时间：2017年7月26日 下午8:29:38    
 * 修改备注：       
 * @version </pre>
 */
@Controller
@RequestMapping("/role/")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	/**
	 * <pre>toRoleListPage(到角色展示列表页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:20:48    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:20:48    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toRoleListPage")
	String toRoleListPage() {
		return "role/showRoleList";
	}
	
	/**
	 * <pre>selectRoleListJson(查询角色信息,并分页)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:21:20    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:21:20    
	 * 修改备注： 
	 * @param pageNumber 当前页数
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("selectRoleListJson")
	@ResponseBody
	Map<String, Object> selectRoleListJson(String pageNumber, RoleRequest roleRequest) {
		//查询总条数
		int totalCount = roleService.selectRoleCount(roleRequest);
		roleRequest.setTotalCount(totalCount);
		if (null == pageNumber || "".equals(pageNumber.trim())) {
			pageNumber = "1";
		}
		roleRequest.setPageIndex(Integer.valueOf(pageNumber));
		//计算分页信息
		roleRequest.calculate();
		//查询列表
		List<RoleResponse> roleList = roleService.selectRoleList(roleRequest);
		//封装结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalCount);
		map.put("rows", roleList);
		return map;
	}
	
	/**
	 * <pre>toRoleMenuPage(到角色-权限中间表展示页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:22:33    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:22:33    
	 * 修改备注： 
	 * @param m
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("toRoleMenuPage")
	String toRoleMenuPage(Model m, RoleRequest roleRequest) {
		m.addAttribute("roleID", roleRequest.getRoleID());
		return "role/role_menu";
	}
	
	/**
	 * <pre>selectRoleMenuListJson(查询角色权限列表)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:23:18    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:23:18    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("selectRoleMenuListJson")
	@ResponseBody
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		List<MenuResponse> menuList = roleService.selectRoleMenuListJson(menuRequest);
		return menuList;
	}
	
	/**
	 * <pre>insertRoleMenuList(新增角色的权限)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:25:19    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:25:19    
	 * 修改备注： 
	 * @param menuRequestList
	 * @return</pre>
	 */
	@RequestMapping("insertRoleMenuList")
	@ResponseBody
	String insertRoleMenuList(@RequestBody List<MenuRequest> menuRequestList) {
		roleService.insertRoleMenuList(menuRequestList);
		return "{}";
	}
	
	/**
	 * <pre>deleteRole(删除角色)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:26:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:26:04    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("deleteRoles")
	@ResponseBody
	public String deleteRole(RoleRequest roleRequest){
		
		roleService.deleteRole(roleRequest);
		return "{}";
	}
	
	/**
	 * <pre>toAddRolesPage(到新增角色页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:26:17    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:26:17    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toAddRolesPage")
	public String toAddRolesPage(){
		return "role/addRolesPage";
	}
	
	/**
	 * <pre>insertRoleInfo(新增角色信息)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:26:41    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:26:41    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("insertRoleInfo")
	@ResponseBody
	public String insertRoleInfo(RoleRequest roleRequest){
		roleService.insertRoleInfo(roleRequest);
		return "{}";
	}
	
	/**
	 * <pre>getRoleInfoById(根据id查询角色信息)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:26:56    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:26:56    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("getRoleInfoById")
	public ModelAndView getRoleInfoById(RoleRequest roleRequest){
		ModelAndView mv = new ModelAndView();
		RoleResponse roleResponse = roleService.getRoleInfoById(roleRequest);
		mv.addObject("role",roleResponse);
		mv.setViewName("/role/addRolesPage");
		return mv;
	}
	
	/**
	 * <pre>updateRoleInfoById(新增或修改角色信息)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:27:21    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:27:21    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("updateRoleInfoById")
	@ResponseBody
	public String updateRoleInfoById(RoleRequest roleRequest){
		if(roleRequest.getRoleID() != null){
			roleService.updateRoleInfoById(roleRequest);
		}else{
			roleService.insertRoleInfo(roleRequest);
		}
		return "{}";
	}
}
