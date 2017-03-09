package yp.service;

import yp.model.TAddress;
import yp.model.TUser;;

/**
 * 会员service接口
 * @author YP
 */
public interface TUserServiceI {
	
	/**
	 * 通过id获得用户object
	 * @param UserId
	 * 			用户Id
	 * @return TUser
	 * 			对象
	 * @author 郑天然
	 */
	public TUser getUserInfoById(int userId);
	/**
	 * 通过id获得用户object
	 * @param UserId
	 * 			用户Id
	 * @return TUser
	 * 			对象
	 * @author 郑天然
	 */
	public TUser getUserObject(int UserId);
	/**
	 * 修改对应用户昵称
	 * @param UserId
	 * 			用户Id
	 * 		userNickName
	 * 			昵称
	 * @return boolean
	 * 			成功失败
	 * @author 郑天然
	 */
	public boolean modifyUserNickName(int UserId,String userNickName);
	
	/**
	 *通过会员注册邮箱 获得指定会员的全部信息
	 *@param userEmail
	 *         会员注册邮箱
	 * @return TUser
	 * 			对象
	 * @author 陈荣强
	 */
	public TUser getUserInfoByUserEmail( String userEmail);
	
	/**
	 * 判断用户登录是否成功
	 * @param userEmail
	 * 			会员Email
	 * @param userPassword
	 *         会员密码
	 * @return String
	 * 			状态信息(”成功“|"用户名不存在"|"用户名与密码不匹配")
	 * @author 陈荣强
	 */
	public String  userLogin(String userEmail, String userPassword);
	
	/**
	 * 验证邮箱是否已被注册
	 * @param userEmail
	 * 			注册邮箱
	 * @return boolean
	 * 			
	 * @author 陈荣强
	 */
	public boolean isRegisted(String userEmail);
	
	/**
	 * 将会员注册信息写入数据库
	 * @param user
	 * 		  对象实例
	 * @author 陈荣强
	 */
	public boolean addUser(TUser user);
	
	public TUser findUserInfoById(int userId);
	
	public int findUserIdByEmail(String userEmail);
	
	/**
	 * 修改密码时，检查原密码输入是否正确
	 * @param userId
	 * 			用户id
	 * @param password
	 * 			原密码
	 * @return boolean
	 * @author YP
	 */
	public boolean checkPrimaryPassword(Integer userId,String password);
	
	/**
	 * 用户修改密码
	 * @param userId
	 * 			用户id
	 * @param password
	 * 			传入的新密码
	 * @return boolean
	 * @author YP
	 */
	public boolean modifyPassword(Integer userId,String password);
	
	/**
	 * 根据用户id删除地址
	 * 
	 * @param userId url传入
	 *            用户Id
	 * @return boolean 
	 * 			是否成功
	 * @author 郑天然
	 */
	public boolean deleteUser(Integer userId);
	
	/**
	 * 更新会员数据库
	 * @author RichardChan
	 * @param user
	 */
	public void update(TUser user);
	
	
}