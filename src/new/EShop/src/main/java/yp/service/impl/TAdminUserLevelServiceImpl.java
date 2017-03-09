package yp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TUserlevelDaoI;
import yp.model.TUserlevel;
import yp.service.TAdminUserLevelServiceI;

@Service
public class TAdminUserLevelServiceImpl implements TAdminUserLevelServiceI{

	TUserlevelDaoI tUserLevelDao = null;
	
	
	public TUserlevelDaoI gettUserLevelDao() {
		return tUserLevelDao;
	}
	@Autowired
	public void settUserLevelDao(TUserlevelDaoI tUserLevelDao) {
		this.tUserLevelDao = tUserLevelDao;
	}


	@Override
	public boolean adminModifyUserLevel(Integer userLevelId, Double discount) {
		// TODO Auto-generated method stub
		TUserlevel userLevel = tUserLevelDao.findById(TUserlevel.class, userLevelId);
		if(userLevel == null)
			return false;
		userLevel.setUserlevelDiscount(discount);
		tUserLevelDao.update(userLevel);
		return true;
	}

	
	
}
