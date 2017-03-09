package yp.dao;


import java.util.List;

import yp.model.TUser;

public interface TUserDaoI extends BaseDaoI<TUser>{
	/**
	 * 修改
	 * param userid nickname
	 * result 成功与否
	 * @author 郑天然
	 */
	boolean modifyNickNameById(int userid,String userNickName);
	public TUser getUserByUserEmail(String UserEmail);	

	/**
	 *通过会员注册邮箱 获得指定会员的全部信息
	 *@param userEmail
	 *         会员注册邮箱
	 * @return TUser
	 * 			对象
	 * @author 陈荣强
	 */
	public TUser getUserInfoByUserEmail(String userEmail);
	
	/**
	 * 修改密码时，检查原密码输入是否正确
	 * @param userId
	 * 			用户id
	 * @param password
	 * 			原密码
	 * @return boolean
	 * @author YP
	 */
	public boolean checkParimaryPassword(Integer userId,String password);
	
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
}
