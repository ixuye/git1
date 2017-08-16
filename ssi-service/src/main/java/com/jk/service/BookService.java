package com.jk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.Tree;
import com.jk.pojo.UsersInfo;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.fingerprint.FingerprintResponse;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

public interface BookService {

	List selectBookInfo();

	Map<String, Object> queryUserInfoByUserName(UsersInfo user);

	List<UsersInfo> queryUserInfo(UsersInfo user);

	void insertUserInfo(UsersInfo user, FingerprintRequest fingerprintRequest);

	UsersInfo queryUserInfoByUserId(Integer userId);

	void deleteUserInfo(String ids);

	void updateUserInfo(UsersInfo user);

	Map<String, Object> checkUserInfoByUserName(UsersInfo user);

	List<Tree> queryTree(Tree tree);

	Long queryCount(Integer treeId);

	List<Tree> findTree(int i);

	int querySonCount(Integer treeId);

	int selectUserCount(UsersInfo user);

	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	void deleteUser(UsersInfo user);

	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);

	/** <pre>selectUserMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:21:50    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:21:50    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectUserMenuListJson(MenuRequest menuRequest);

	/** <pre>insertFileFinger(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月3日 下午11:20:58    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月3日 下午11:20:58    
	 * 修改备注： 
	 * @param fingerprintRequest</pre>    
	 */
	Integer insertFileFinger(FingerprintRequest fingerprintRequest);

	/** <pre>queryFingerPrintByMd5(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月4日 上午9:04:46    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月4日 上午9:04:46    
	 * 修改备注： 
	 * @param md5
	 * @return</pre>    
	 */
	FingerprintResponse queryFingerPrintByMd5(String md5);
}
