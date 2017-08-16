package com.jk.controller.user;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jk.pojo.Book;
import com.jk.pojo.Tree;
import com.jk.pojo.UsersInfo;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.fingerprint.FingerprintResponse;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.BookService;

import common.base.MySessionContext;
import common.util.DateUtil;
import common.util.FTPUtil;
import common.util.FileUtil;
import util.JedisUtil;
import util.JsonUtil;
import util.MyUtil;

/**
 * 
 * <pre>项目名称：ssi    
 * 类名称：BookController    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月14日 下午3:20:41    
 * 修改人：徐叶    
 * 修改时间：2017年7月14日 下午3:20:41    
 * 修改备注：       
 * @version </pre>
 */
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	
	/**
	 * <pre>selectBookList(查询book)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:44:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:44:04    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("selectBookList")
	public ModelAndView selectBookList() {
		List<Book> boList = bookService.selectBookInfo();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", boList);
		mv.setViewName("showBookInfo");
		return mv;
	}
	
	/**
	 * <pre>test1(测试)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:44:27    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:44:27    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("test")
	public ModelAndView  test1(){
		String name = "aa====aaa";
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.setViewName("../aa");
		return mv;
		
	}
	/**
	 * <pre>logOff()   (退出登录)
	 * 创建人：     徐叶
	 * 创建时间：2017年7月24日 上午9:37:12    
	 * 修改人：徐叶      
	 * 修改时间：2017年7月24日 上午9:37:12    
	 * 修改备注： 
	 * @param request
	 * @return</pre>
	 */
	@RequestMapping("logOff")
	public String logOff(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "redirect:login.jsp";
		
		
	}
	
	@RequestMapping("toShowAuthority")
	public String toShowAuthority(){
		return "showAuthority";
		
	}
	
	/**
	 * <pre>qrCode(获取二维码)   
	 * 创建人：徐叶    
	 * 创建时间：2017年7月24日 上午9:38:18    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月24日 上午9:38:18    
	 * 修改备注： 
	 * @param request
	 * @param response</pre>
	 */
	@RequestMapping("qrCode")
	public void qrCode(HttpServletRequest request,HttpServletResponse response){
		MyUtil.genQrcode(null,"E:/ye",request,response);
	}
	
	/**
	 * <pre>checkUserLoginInfo(验证登录信息)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:45:21    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:45:21    
	 * 修改备注： 
	 * @param user</pre>
	 */
	@RequestMapping("checkUserInfo")
	@ResponseBody
	public Map<String,Object> checkUserLoginInfo(UsersInfo user,HttpServletRequest request){
		HttpSession session = request.getSession();
		//获取验证码
		Object codeObj = session.getAttribute("picCode");
		if (null == codeObj) {
			codeObj = "";
		}
		String code = codeObj.toString();
		user.setSelfImg(code);
		//获取二维码验证
		Object qrcode = session.getAttribute("picQrCode");
		if (null == qrcode) {
			qrcode = "";
		}
		String qrCode = qrcode.toString();
		user.setSelfQrCode(qrCode);
		Map<String, Object> map = bookService.queryUserInfoByUserName(user);
		if(null!=map.get("userInfo")){
			UsersInfo userinfo = (UsersInfo) map.get("userInfo");
			//将用户信息存入session中
			session.setAttribute("userInfo", map.get("userInfo"));
			//设置session过期时间(单位：秒)
			session.setMaxInactiveInterval(600);
			//获取用户id
			String userID = userinfo.getUserId() + "";
			//根据用户id移除session
			MySessionContext.removeSession(userID,session);
			//移除之后，重新添加一个session
			MySessionContext.addSession(userID, session);
			map.put("loginMsg", "你的账号在别处登录,请确保是本人操作！");
			
			//查询出用户的权限树
			MenuRequest menuRequest = new MenuRequest();
			menuRequest.setUserId(userinfo.getUserId());
			List<MenuResponse> treeList = bookService.selectUserMenuListJson(menuRequest);
			//放到redis中
			JedisUtil.setString(userID + "#tree_list", JsonUtil.toJsonString(treeList), -1);
		
		}
		return map;
		
	}
	
	/**
	 * <pre>toHonerPage(到展示一面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月24日 上午9:40:36    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月24日 上午9:40:36    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toHonerPage")
	public String toHonerPage(){
		return "showUserList";
	}
	
	/**
	 * <pre>queryTree(查询树,异步树)   
	 * 创建人：徐叶  
	 * 创建时间：2017年7月24日 上午9:41:23    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月24日 上午9:41:23    
	 * 修改备注： 
	 * @param tree
	 * @return</pre>
	 */
	@RequestMapping("queryTree")
	@ResponseBody
	//异步树
	public List<Map<String,Object>> queryTree(Tree tree){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		List<Tree> list = bookService.queryTree(tree);
		for (Tree  tre: list) {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("id", tre.getId());
			resultMap.put("text", tre.getText());
			resultMap.put("url", tre.getUrl());
			Long totalCount = bookService.queryCount(tre.getId());
			//resultMap.put("state", totalCount>0?"closed":"open");
			mapList.add(resultMap);
		}
		return mapList;
		
	}
	
 // 同步树
    @RequestMapping("queryTree1")
    @ResponseBody
    public List<Tree> queryTree1(){
		List<Tree> list = bookService.findTree(0);
		List<Tree> querySonTree = querySonTree(list);
		return querySonTree;
	}
	public List<Tree> querySonTree(List<Tree> menu){
		List<Tree> menuList = new ArrayList<Tree>();
		for (Tree tree : menu) {
			if(bookService.querySonCount(tree.getId())!=0){
				List<Tree> findTree = getSonTree(tree.getId());
				tree.setNodes(findTree);
				menuList.add(tree);
			}else{
				menuList.add(tree);
			}
		}
		return menuList;
	}
	public List<Tree> getSonTree(Integer id){
		List<Tree> resultList = new ArrayList<Tree>();
		List<Tree> list = bookService.findTree(id);
		for (Tree tree : list) {
			if(bookService.querySonCount(tree.getId())!=0){
				List<Tree> findTree = getSonTree(tree.getId());
				tree.setNodes(findTree);
				resultList.add(tree);
			}else{
				resultList.add(tree);
			}
		}
		return resultList;
	}
	
	@RequestMapping("selectTreeListJson")
	@ResponseBody
	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		List<Map<String, Object>> treeList = bookService.selectTreeListJson(menuRequest);
		return treeList;
	}
	
	/**
	 * <pre>queryUserInfo(查询用户信息)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:47:11    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:47:11    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("queryUserInfo")
	@ResponseBody
	public Map<String, Object> queryUserInfo(String pageNumber,UsersInfo user){
		//查询总条数
				int totalCount = bookService.selectUserCount(user);
				user.setTotalCount(totalCount);
				if (null == pageNumber || "".equals(pageNumber.trim())) {
					pageNumber = "1";
				}
				user.setPageIndex(Integer.valueOf(pageNumber));
				//计算分页信息
				user.calculate();
				//查询列表
				List<UsersInfo> userList = bookService.queryUserInfo(user);
				//封装结果
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("total", totalCount);
				map.put("rows", userList);
				return map;
	}
	
	@RequestMapping("toUserRolePage")
	public String toUserRolePage(ModelMap mm, UsersInfo user){
		mm.addAttribute("userId", user.getUserId());
		return "user_role";
	}
	
	@RequestMapping("selectUserRoleListJson")
	@ResponseBody
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest){
		List<RoleResponse> roleList = bookService.selectUserRoleListJson(roleRequest);
		return roleList;
	}
	
	@RequestMapping("insertUserRoleList")
	@ResponseBody
	String insertUserRoleList(@RequestBody List<RoleRequest> roleRequestList) {
		bookService.insertUserRoleList(roleRequestList);
		return "{}";
	}
	
	@RequestMapping("toShowUserInfo")
	public String toShowUserInfo(){
		return "showUserInfo";
	}
	
	/**
	 * <pre>uploadPhoto(ajax上传图片)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月17日 上午7:41:28    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月17日 上午7:41:28    
	 * 修改备注： 
	 * @param users
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException</pre>
	 */
	  @RequestMapping("uploadPhoto") 
	  @ResponseBody
	public Map<String,Object> uploadPhoto(FingerprintRequest fingerprintRequest,UsersInfo users,@RequestParam(value="file",required=false) CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException{
		  String path=""; 
		  Map<String,Object> map = new HashMap<String,Object>();
		  try {
				InputStream inputStream = file.getInputStream();
				String md5 = FileUtil.getMD5(inputStream, "md5");
				//从数据库判断这个指纹存在
				System.out.println("文件指纹是：" + md5);
				
				FingerprintResponse fingerprintResponse = bookService.queryFingerPrintByMd5(md5);
				//如果存在，直接把地址返回给用户
				if(fingerprintResponse!=null){
					 map.put("fid", fingerprintResponse.getFid());
					 map.put("path", fingerprintResponse.getFileUrl());
				}
				else{
					//如果不存在，保存这个文件到FTP服务器，并且把保存的路径以及文件指纹存到数据库
					//文件名
						String originalFilename = file.getOriginalFilename();
						      //后缀
						String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
						String fileName = UUID.randomUUID().toString() + suffix;
						path = "1702A/" + DateUtil.formatDateToString(new Date(), "yyyy/MM/dd");
						map.put("path", path+"/"+fileName);
						boolean boo = FTPUtil.uploadFile(file.getInputStream(), fileName, path);
						if (boo) {
							fingerprintRequest.setFileFingerprint(md5);
							fingerprintRequest.setFileUrl(path + "/" + fileName);
							//得到新增的文件指纹的id
							Integer fingerId = bookService.insertFileFinger(fingerprintRequest);
							 map.put("fid", fingerId);
							System.out.println("指纹id===="+fingerId);
							System.out.println("文件上传成功，保存在：》》" + path + "/" + fileName);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  
			System.out.println();
			
	        
	        return map;
	}
	
	/**
	 * <pre>toAddUserPage(跳转到新增页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:47:19    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:47:19    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toAddUserPage")
	public String toAddUserPage(){
		return "addUser";
	}
	
	/**
	 * <pre>queryUserInfoByUserId(修改回显)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:47:27    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:47:27    
	 * 修改备注： 
	 * @param user
	 * @return</pre>
	 */
	@RequestMapping("queryUserInfoByUserId")
	public ModelAndView queryUserInfoByUserId(UsersInfo user){
		UsersInfo usersInfo = bookService.queryUserInfoByUserId(user.getUserId());
		ModelAndView view = new ModelAndView();
		view.addObject("user", usersInfo);
		view.setViewName("addUser");
		return view;
	}
	
	/**
	 * <pre>insertUserInfo(新增修改)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:47:32    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:47:32    
	 * 修改备注： 
	 * @param user 有id新增，无id修改
	 * @return</pre>
	 */
	@RequestMapping("insertUserInfo")
	@ResponseBody
	public Map<String,Object> insertUserInfo(FingerprintRequest fingerprintRequest,UsersInfo user,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		if(user.getUserId()!=null){
			bookService.updateUserInfo(user);
		}else{
			HttpSession session = request.getSession();
			Object codeObj = session.getAttribute("picCode");
			if (null == codeObj) {
				codeObj = "";
			}
			String code = codeObj.toString();
			user.setSelfImg(code);
			//if(user.getCodeImg().equals(user.getSelfImg())){
				bookService.insertUserInfo(user,fingerprintRequest);
				map.put("insertInfo", true);
			}/*else{
				map.put("codeInfo", Constant.LOGIN_CODE_ERROR);//验证码错误
			}
			
		}*/
		return map;
	}
	
	/**
	 * <pre>toDeleteUserInfo(批删)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月14日 下午10:47:37    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月14日 下午10:47:37    
	 * 修改备注： 
	 * @param user
	 * @return</pre>
	 */
	@RequestMapping("toDeleteUserInfo")
	@ResponseBody
	public String toDeleteUserInfo(HttpServletRequest request,UsersInfo user){
		String ids = user.getIds();
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			
			String realPath = request.getSession().getServletContext().getRealPath("");
			UsersInfo usersInfo = bookService.queryUserInfoByUserId(Integer.valueOf(split[i]));
			String url = realPath+"\\"+usersInfo.getUserPhoto();
			System.out.println(url);
			 //文件路径
			   String filedir = request.getSession().getServletContext().getRealPath("/") + usersInfo.getUserPhoto();
			   //文件夹
			   String directorydir = request.getSession().getServletContext().getRealPath("/") + "upImg";
			   //保存文件 文件夹路径
			   String rootPath = request.getSession().getServletContext().getRealPath("/") + "upImg";
				   File files = new File(filedir);
				  // if (files.isFile() && files.exists()) {
					   files.delete();//"删除单个文件"+name+"成功！"
				  // }
		       
			//FileUtil.deleteFile(request, usersInfo.getUserPhoto());
				
			
			
		}
		bookService.deleteUserInfo(user.getIds());
		return "{}";
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser(UsersInfo user){
		bookService.deleteUser(user);
		return "{}";
	}
	
	/**
	 * <pre>checkUserName(新增时,验证用户名是否可用)   
	 * 创建人：徐叶    
	 * 创建时间：2017年7月24日 上午9:43:05    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月24日 上午9:43:05    
	 * 修改备注： 
	 * @param user
	 * @return</pre>
	 */
	@RequestMapping("checkUserName")
	@ResponseBody
	public Map<String, Object> checkUserName(UsersInfo user){
		
		Map<String, Object> map = bookService.checkUserInfoByUserName(user);
		
		return map;
		
	}
	
	@RequestMapping("toAddRolesPage")
	public String toAddRolesPage(){
		return "addRolesPage";
	}
	
}
