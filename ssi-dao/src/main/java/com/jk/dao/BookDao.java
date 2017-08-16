package com.jk.dao;

import java.util.List;
import java.util.Map;

import com.jk.pojo.Tree;
import com.jk.pojo.UsersInfo;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.fingerprint.FingerprintResponse;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

public interface BookDao {

	List selectBookInfo();

	UsersInfo queryUserInfoByUserName(UsersInfo user);

	List<UsersInfo> bookUserInfo(UsersInfo user);

	void insertUserInfo(UsersInfo user);

	UsersInfo queryUserInfoByUserId(Integer userId);

	void deleteUserInfo(Integer valueOf);

	void updateUserInfo(UsersInfo user);

	void deleteUserInfoByIds(String ids);

	List<Tree> queryTree(Tree tree);

	Long queryCount(Integer treeId);

	void updateUserLoginFailNum(UsersInfo user);

	void updateLoginFailNumZero(UsersInfo user);

	List<Tree> findTree(int i);

	int querySonCount(Integer treeId);

	int selectUserCount(UsersInfo user);

	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void deleteAllRolesByUserID(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	void deleteUser(UsersInfo user);

	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);

	void insertUserRole(UsersInfo user);

	/** <pre>selectUserMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:24:49    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:24:49    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectUserMenuListJson(MenuRequest menuRequest);

	/** <pre>insertFileFinger(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月3日 下午11:30:17    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月3日 下午11:30:17    
	 * 修改备注： 
	 * @param fingerprintRequest</pre>    
	 */
	Integer insertFileFinger(FingerprintRequest fingerprintRequest);

	/** <pre>queryFingerPrintByMd5(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月4日 上午9:05:42    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月4日 上午9:05:42    
	 * 修改备注： 
	 * @param md5
	 * @return</pre>    
	 */
	FingerprintResponse queryFingerPrintByMd5(String md5);

	/** <pre>insertUserFileFingerprint(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月4日 上午11:22:41    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月4日 上午11:22:41    
	 * 修改备注： 
	 * @param fingerprintRequest</pre>    
	 */
	void insertUserFileFingerprint(FingerprintRequest fingerprintRequest);

}
