package yp.dao;

import java.util.List;

import yp.model.TAddress;
import yp.model.TUserlevel;

public interface TUserlevelDaoI extends BaseDaoI<TUserlevel>{
	/**
	 * 获得用户等级列表
	 * @author  郑天然
	 * 	 */
	List<TUserlevel> getUserLevelList();
}
