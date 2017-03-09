package yp.service;

import java.util.List;

import yp.model.TUserlevel;

public interface TUserlevelServiceI{
	
	/**
	 * 获得指定会员等级的全部信息
	 * @param userlevelId
	 * 			会员等级Id
	 * @return TUserlevel
	 * 			对象
	 * @author 陈荣强
	 */
	public TUserlevel getUserlevelById(int userlevelId);
	
	/**
	 * 获得会员等级列表
	 *
	 * @return List<TUserlevel>
	 * 			会员等级对象列表
	 * @author 郑天然
	 */
	public List<TUserlevel> getUserlevelList();

}
