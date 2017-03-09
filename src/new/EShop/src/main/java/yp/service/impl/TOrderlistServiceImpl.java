package yp.service.impl;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.service.TOrderlistServiceI;
import yp.model.TOrderlist;
import yp.model.TUser;
import yp.dao.TOrderlistDaoI;

@Service
public class TOrderlistServiceImpl implements TOrderlistServiceI{
	private TOrderlistDaoI tOrderlistDao;
	
	
	public TOrderlistDaoI gettOrderlistDao() {
		return tOrderlistDao;
	}

	@Autowired
	public void settOrderlistDao(TOrderlistDaoI tOrderlistDao) {
		this.tOrderlistDao = tOrderlistDao;
	}
	
	@Override
	public boolean updateConfirmReceived(int orderlistId){
		TOrderlist tOrderlist = tOrderlistDao.findById(TOrderlist.class, orderlistId);
		tOrderlist.setOrderlistStatus(2);//将订单状态设为确认收货
		tOrderlistDao.saveOrUpdate(tOrderlist);
		return true;
	}
	
	public void addOrderlist(int userId, int addressId, Map orderlistDetail){
		Set set = orderlistDetail.entrySet();
		Iterator i = set.iterator();
		
	}

	@Override
	public List<TOrderlist> getOrderlist(TUser user, int pageNum) {
		// TODO Auto-generated method stub
		int num;
		if(pageNum * 10 > tOrderlistDao.getNum()){
			num = tOrderlistDao.getNum();
		}
		else num = pageNum * 10;
		return tOrderlistDao.getOrderlist(user, num);
	}

	@Override
	public void addOrderlist(TOrderlist tOrderlist) {
		// TODO Auto-generated method stub
		tOrderlistDao.save(tOrderlist);
	}

	@Override
	public TOrderlist getOrderlistById(int orderlistId) {
		// TODO Auto-generated method stub
		return tOrderlistDao.findById(TOrderlist.class, orderlistId);
	}


	@Override
	public Timestamp getFirstTransactionTime(TUser user) {
		// TODO Auto-generated method stub
		return tOrderlistDao.getFirstTransactionTime(user);
	}

	@Override
	public List<TOrderlist> getTransaction(TUser user, int pageNum,Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		return tOrderlistDao.getTransaction(user, pageNum, startTime, endTime);
	}

}