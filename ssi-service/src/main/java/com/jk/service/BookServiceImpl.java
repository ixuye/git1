package com.jk.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.dao.BookDao;
import com.jk.pojo.Tree;
import com.jk.pojo.UsersInfo;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.fingerprint.FingerprintResponse;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

import common.constant.Constant;
import util.JedisUtil;
import util.JsonUtil;

@Service
@Transactional(readOnly=true)
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		//从redis缓存中获取权限列表
		
		String string = JedisUtil.getString(menuRequest.getUserId() + "#menu_list");
		//如果没有获取到，则查询数据库，
		if (null == string) {
			treeList = bookDao.selectTreeListJson(menuRequest);
			if (null != treeList && 0 < treeList.size()) {
				//开始调用递归
				treeList = queryTreeListNodes(treeList, menuRequest);
			}
			//把查询的结果存一份到redis上
			JedisUtil.setString(menuRequest.getUserId() + "#menu_list", 
					JsonUtil.toJsonString(treeList), 600);
		} else {
			//如果查询到了结果，直接返回
			treeList = JsonUtil.fromJson(string, new ArrayList<Map<String, Object>>(){}.getClass());
		}
		return treeList;
		
	}
	
	//递归查询树菜单
		private List<Map<String, Object>> queryTreeListNodes(List<Map<String, Object>> treeList, MenuRequest menuRequest) {
			for (Map<String, Object> map : treeList) {
				if ("0".equals(map.get("pid").toString())) {
					//取出ID作为下次查询的pid
					int pid = Integer.valueOf(map.get("id").toString());
					menuRequest.setPid(pid);
					List<Map<String, Object>> queryTreeListNodes = 
							queryTreeListNodes(bookDao.selectTreeListJson(menuRequest), menuRequest);
					map.put("nodes", queryTreeListNodes);
				}
			}
			return treeList;
		}
	
	@Override
	public void deleteUser(UsersInfo user) {
		bookDao.deleteUser(user);
	}
	
	
	@Override
	public void insertUserRoleList(List<RoleRequest> roleRequestList) {
		// 1、删除用户之前的所有角色（mid）
		bookDao.deleteAllRolesByUserID(roleRequestList.get(0));
				// 2、添加用户勾选的所有角色（mid）
		bookDao.insertUserRoleList(roleRequestList);
		
	}
	
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		return bookDao.selectUserRoleListJson(roleRequest);
		
	}
	
	@Override
	public int selectUserCount(UsersInfo user) {
		
		return bookDao.selectUserCount(user);
	}
	
	
	@Override
	public int querySonCount(Integer treeId) {
		
		return bookDao.querySonCount(treeId);
	}
	
	@Override
	public List<Tree> findTree(int i) {
		
		return bookDao.findTree(i);
	}
	
	@Override
	public Long queryCount(Integer treeId) {
		
		return bookDao.queryCount(treeId);
	}
	
	@Override
	public List<Tree> queryTree(Tree tree) {
		
		return bookDao.queryTree(tree);
	}
	
	@Override
	public Map<String, Object> checkUserInfoByUserName(UsersInfo user) {
		Map<String,Object> map = new HashMap<String,Object>();
		 UsersInfo queryUserInfoByUserName = bookDao.queryUserInfoByUserName(user);
		 if(queryUserInfoByUserName!=null){
			 map.put("checkInfo", Constant.USER_NAME_DISABLED);//用户名已存在，不可用
		 }else{
			 map.put("checkInfo", Constant.USER_NAME_USABLE);//用户名可用
		 }
		 return map;
	}
	
	@Transactional(readOnly=false)
	public void updateUserInfo(UsersInfo user) {
		bookDao.updateUserInfo(user);
	}
	
	@Transactional(readOnly=false)
	public void deleteUserInfo(String ids) {
		//String[] split = ids.split(",");
		bookDao.deleteUserInfoByIds(ids);
		
		/*for (int j = 0; j < split.length; j++) {
			bookDao.deleteUserInfo(Integer.valueOf(split[j]));
			
		}*/
	}
	
	public UsersInfo queryUserInfoByUserId(Integer userId) {
		UsersInfo usersInfo = bookDao.queryUserInfoByUserId(userId);
		return usersInfo;
	}
	
	@Transactional(readOnly=false)
	public void insertUserInfo(UsersInfo user,FingerprintRequest fingerprintRequest) {
		bookDao.insertUserInfo(user);
		user.setRoleID(-3);
		fingerprintRequest.setUserID(user.getUserId());
		bookDao.insertUserFileFingerprint(fingerprintRequest);
		bookDao.insertUserRole(user);
	}
	
	public List<UsersInfo> queryUserInfo(UsersInfo user) {
		return bookDao.bookUserInfo(user);
	}
	
	public Map<String, Object> queryUserInfoByUserName(UsersInfo user) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		//默认设置密码错误
				map.put("flag", Constant.LOGIN_PWD_ERROR);
				map.put("userInfo", null);
				//判断验证码是否一致
		 if(user.getCodeImg().equals(user.getSelfImg())){
			 //判断二维码是否一致
		 // if(user.getQrCode().equals(user.getSelfQrCode())){
				 //根据用户名进行查询
				 UsersInfo queryUserInfoByUserName = bookDao.queryUserInfoByUserName(user);
				
				 
					 if(queryUserInfoByUserName!=null){
						 //判断距离上次登陆的时间，大于5分钟，将登陆失败次数修改为0
						 if(queryUserInfoByUserName.getLoginFailDate()>300000){
							 //user.setLoginFailNum(0);
							 bookDao.updateLoginFailNumZero(user);
						 }
						//判断是否被锁定（小于连续3次失败并且距离最近一次失败大于5分钟）
						 if(null != queryUserInfoByUserName &&
								 ( 0 == queryUserInfoByUserName.getLoginFailNum()
											|| 0 < (queryUserInfoByUserName.getLoginFailNum()%3) 
											|| queryUserInfoByUserName.getLoginFailDate() > 300000)){
						 //密码一致
						if(queryUserInfoByUserName.getUserPassword().equals(user.getUserPassword())){
							queryUserInfoByUserName.setLoginInfo("登陆成功");
							//登陆成功
							map.put("userInfo", queryUserInfoByUserName);
							map.put("flag", Constant.LOGIN_SUCCESS);
							//user.setLoginFailNum(0);
							bookDao.updateLoginFailNumZero(user);
							
						}else{
							//重新再查询一次用户信息，以便防止进行修改后不能得到即时的数据
							UsersInfo usersInfo = bookDao.queryUserInfoByUserName(user);
							//密码错误
							map.put("loginFailNum", usersInfo.getLoginFailNum() + 1);
							queryUserInfoByUserName = user;
							queryUserInfoByUserName.setLoginInfo("密码错误");
							map.put("flag", Constant.LOGIN_PWD_ERROR);
							bookDao.updateUserLoginFailNum(user);
						}
					
					 }else{
						 //账户锁定
						 map.put("flag", Constant.USER_ACCOUNT_LOCK);
					 }
				  }
				 else{
						//用户名不存在
						queryUserInfoByUserName = new UsersInfo();
						queryUserInfoByUserName = user;
						queryUserInfoByUserName.setLoginInfo("用户名不存在");
						map.put("flag", Constant.LOGIN_ACCOUNT_ERROR);
					}
		//  }
			/* else{
				 //二维码错误
				 map.put("flag", Constant.LOGIN_QRCODE_ERROR);
			 }*/
		 }
		else{
			//验证码错误
			map.put("flag", Constant.LOGIN_CODE_ERROR);
		}
		
		
		
		return map;
		
	}
	
	public List selectBookInfo() {
		return bookDao.selectBookInfo();
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.BookService#selectUserMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectUserMenuListJson(MenuRequest menuRequest) {
		return bookDao.selectUserMenuListJson(menuRequest);
	}
	
	
	/* (non-Javadoc)    
	 * @see com.jk.service.BookService#insertFileFinger(com.jk.pojo.fingerprint.FingerprintRequest)    
	 */
	@Override
	public Integer insertFileFinger(FingerprintRequest fingerprintRequest) {
		return bookDao.insertFileFinger(fingerprintRequest);
		
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.BookService#queryFingerPrintByMd5(java.lang.String)    
	 */
	@Override
	public FingerprintResponse queryFingerPrintByMd5(String md5) {
		return bookDao.queryFingerPrintByMd5(md5);
	}
	
}
