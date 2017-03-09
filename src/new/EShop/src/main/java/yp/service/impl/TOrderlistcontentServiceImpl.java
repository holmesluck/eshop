package yp.service.impl;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.service.TOrderlistcontentServiceI;
import yp.model.TGoods;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;
import yp.dao.TOrderlistcontentDaoI;

@Service
public class TOrderlistcontentServiceImpl implements TOrderlistcontentServiceI{
	private TOrderlistcontentDaoI tOrderlistcontentDao;

	public TOrderlistcontentDaoI gettOrderlistcontentDao() {
		return tOrderlistcontentDao;
	}
    
	@Autowired
	public void settOrderlistcontentDao(TOrderlistcontentDaoI tOrderlistcontentDao) {
		this.tOrderlistcontentDao = tOrderlistcontentDao;
	}

	@Override
	public void addOrderlistcontent(TOrderlistcontent tOrderlistcontent) {
		// TODO Auto-generated method stub
		tOrderlistcontentDao.save(tOrderlistcontent);
	}

	@Override
	public List<TOrderlistcontent> getOrderlistContent(TOrderlist orderlistId) {
		// TODO Auto-generated method stub
		return tOrderlistcontentDao.getOrderlistContentByOrderlistId(orderlistId);
	}

	@Override
	public List<TOrderlistcontent> getOrderlistcontentByGoods(TGoods tGoods,
			Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		return tOrderlistcontentDao.getOrderlistByGoods(tGoods, startTime, endTime);
	}

	@Override
	public List<TOrderlistcontent> getOrderlistcontentByGoods(TGoods goods) {
		// TODO Auto-generated method stub
		return tOrderlistcontentDao.getOrderlistByGoods(goods);
	}


}