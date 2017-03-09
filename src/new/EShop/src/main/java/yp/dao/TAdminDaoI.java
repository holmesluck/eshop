package yp.dao;

import yp.model.TAdmin;

public interface TAdminDaoI extends BaseDaoI<TAdmin>{

	/**
	 * 通过账号获得管理员对象
	 * @author RichardChan
	 * @param adminName
	 * @return
	 */
	TAdmin getAdminByAdminName(String adminName);
	
}
