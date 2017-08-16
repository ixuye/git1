package com.jk.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.pojo.Tree;
import com.jk.pojo.UsersInfo;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.fingerprint.FingerprintResponse;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

@Repository
public class BookDaoImpl extends SqlMapClientDaoSupport implements BookDao{
	
	
	@Override
	public void insertUserRole(UsersInfo user) {
		this.getSqlMapClientTemplate().insert("users.insertUserRole", user);
		
	}
	
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("users.selectTreeListJson", menuRequest);
	}
	
	@Override
	public void deleteUser(UsersInfo user) {
		this.getSqlMapClientTemplate().delete("users.deleteUserByUserId", user.getUserId());
		
	}
	
	@Override
	public void deleteAllRolesByUserID(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("users.deleteAllRolesByUserID", roleRequest);
		
	}
	
	@Override
	public void insertUserRoleList(final List<RoleRequest> roleRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (RoleRequest roleRequest : roleRequestList) {
					sqlMap.insert("users.insertUserRole", roleRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
		
	}
	
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		List queryForList = this.getSqlMapClientTemplate().queryForList("users.selectUserRoleListJson", roleRequest);
		return queryForList;
	}
	
	@Override
	public int selectUserCount(UsersInfo user) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("users.selectUserCount",user);
	}
	
	@Override
	public int querySonCount(Integer treeId) {
		List<Integer> queryForList = this.getSqlMapClientTemplate().queryForList("tree.selectByTreeIdCount", treeId);
		Integer count = (Integer) queryForList.size();
		int i = count.intValue();
		return i;
	}
	
	@Override
	public List<Tree> findTree(int i) {
		List queryForList = this.getSqlMapClientTemplate().queryForList("tree.selectTreeListByParentId", Integer.valueOf(i));
		return queryForList;
	}
	
	@Override
	public void updateLoginFailNumZero(UsersInfo user) {
			this.getSqlMapClientTemplate().update("users.updateLoginFailNumToZero",user);
	}
	
	@Override
	public void updateUserLoginFailNum(UsersInfo user) {
		this.getSqlMapClientTemplate().update("users.updateUserLoginFailNum", user);
	}
	
	@Override
	public Long queryCount(Integer treeId) {
		List queryForList = this.getSqlMapClientTemplate().queryForList("tree.selectTreeListCount", treeId);
		Integer size = queryForList.size();
		Long count = size.longValue();
		return count;
	}
	
	@Override
	public List<Tree> queryTree(Tree tree) {
		List queryForList = this.getSqlMapClientTemplate().queryForList("tree.selectTreeList");
		return queryForList;
	}
	
	@Override
	public void deleteUserInfoByIds(String ids) {
		this.getSqlMapClientTemplate().delete("users.deleteUserByUserIds", ids);
	}
	
	public void updateUserInfo(UsersInfo user) {
		int update = this.getSqlMapClientTemplate().update("users.updateUser", user);
		System.out.println(update);
	}
	
	public void deleteUserInfo(Integer valueOf) {
		int delete = this.getSqlMapClientTemplate().delete("users.deleteUserByUserId", valueOf);
		System.out.println(delete);
			//getSqlMapClientTemplate().update("user.deleteUser",ids);
	}
	
	public UsersInfo queryUserInfoByUserId(Integer userId) {
		List<UsersInfo> queryForList = this.getSqlMapClientTemplate().queryForList("users.selectUserByUserId", userId);
		if(!queryForList.isEmpty()){
			UsersInfo usersInfo = queryForList.get(0);
			return usersInfo;
		}
		return null;
	}

	
	public void insertUserInfo(UsersInfo user) {
		Object insert = this.getSqlMapClientTemplate().insert("users.insertUser", user);
		System.out.println(insert);
	}
	
	public List<UsersInfo> bookUserInfo(UsersInfo user) {
		List queryForList = this.getSqlMapClientTemplate().queryForList("users.selectUserList",user);
		
		return queryForList;
	}
	
	public UsersInfo queryUserInfoByUserName(UsersInfo user) {
		List<UsersInfo> queryForList = this.getSqlMapClientTemplate().queryForList("users.selectUserByUserName", user.getUserName());
		if(!queryForList.isEmpty()){
			 UsersInfo usersInfo = queryForList.get(0);
			 return usersInfo;
		}
		return null;
	}
	
	
	public List selectBookInfo() {
		List queryForList = this.getSqlMapClientTemplate().queryForList("book.selectBookList");
		return queryForList;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.BookDao#selectUserMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectUserMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("users.selectUserMenuListJson", menuRequest);
	}
	
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.BookDao#insertFileFinger(com.jk.pojo.fingerprint.FingerprintRequest)    
	 */
	@Override
	public Integer insertFileFinger(FingerprintRequest fingerprintRequest) {
		
		Integer fid = fingerprintRequest.getFid();
			Integer insert = (Integer) this.getSqlMapClientTemplate().insert("fingerprint.insertFingerprint", fingerprintRequest);
			System.out.println(insert);
			return insert;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.BookDao#queryFingerPrintByMd5(java.lang.String)    
	 */
	@Override
	public FingerprintResponse queryFingerPrintByMd5(String md5) {
		return (FingerprintResponse) this.getSqlMapClientTemplate().queryForObject("fingerprint.queryFingerPrintByMd5", md5);
	}
	
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.BookDao#insertUserFileFingerprint(com.jk.pojo.fingerprint.FingerprintRequest)    
	 */
	@Override
	public void insertUserFileFingerprint(FingerprintRequest fingerprintRequest) {
			this.getSqlMapClientTemplate().insert("fingerprint.insertUserFileFingerprint", fingerprintRequest);
	}
}
