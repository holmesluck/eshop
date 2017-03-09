package yp.service;

import yp.model.TAdmin;

public interface TAdminServiceI {
	/**
	 * 管理员登录
	 * 
	 * @author RichardChan
	 */
	public String adminLogin(String adminName, String adminPassword);

	/**
	 * 通过管理员名字获得管理员Id
	 * @author RichardChan
	 * @return
	 */
	public int getAdminIdByAdminName(String adminName);

	/**
	 * 根据管理员Id获取管理员对象
	 * @author RichardChan
	 * @return
	 */
	public TAdmin getAdminById(int adminId);
}