package yp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TUserlevelDaoI;
import yp.model.TUserlevel;
import yp.service.TUserlevelServiceI;

@Service
public class TUserlevelServiceImpl implements TUserlevelServiceI {
	private TUserlevelDaoI tUserlevelDao;
	

	public TUserlevelDaoI gettUserlevelDao() {
		return tUserlevelDao;
	}

	@Autowired
	public void settUserlevelDao(TUserlevelDaoI tUserlevelDao) {
		this.tUserlevelDao = tUserlevelDao;
	}

	@Override
	public TUserlevel getUserlevelById(int userlevelId){
		return tUserlevelDao.findById(TUserlevel.class, userlevelId);
	}

	@Override
	public List<TUserlevel> getUserlevelList() {
		// TODO Auto-generated method stub
		
		return tUserlevelDao.getUserLevelList();
	}
}
