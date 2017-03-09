package yp.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TOrderlistDaoI;
import yp.model.TOrderlist;
import yp.model.TUser;

@Repository
public class TOrderlistDaoImpl extends BaseDaoImpl<TOrderlist> implements TOrderlistDaoI {
	
	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		String hql = "from TOrderlistcontent";
		int num = getCurrentSession().createQuery(hql).list().size();
		System.out.println(num);
		return num;
	}

	@Override
	public List<TOrderlist> getOrderlist(TUser user, int num) {
		// TODO Auto-generated method stub
		String hql = "from TOrderlist as orderlist where orderlist.TUser = ? and orderlist.orderlistStatus <> 2 ORDER BY orderlist.orderlistOrderdate DESC";
		List<TOrderlist> Torderlist = getCurrentSession().createQuery(hql).setParameter(0, user).setFirstResult(num-10).setMaxResults(10).list();
		return Torderlist;
	}

	@Override
	public List<TOrderlist> getTransaction(TUser user, int num,
			Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		String hql = "from TOrderlist as orderlist where orderlist.TUser = ? and orderlist.orderlistStatus = 2 and orderlist.orderlistOrderdate > ? and orderlist.orderlistOrderdate < ? ORDER BY orderlist.orderlistOrderdate DESC";
		List<TOrderlist> Torderlist = getCurrentSession().createQuery(hql).setParameter(0, user).setParameter(1, startTime).setParameter(2, endTime).setFirstResult(num * 10 - 10).setMaxResults(10).list();
		return Torderlist;
		
	}

	@Override
	public Timestamp getFirstTransactionTime(TUser user) {
		// TODO Auto-generated method stub
		String hql = "from TOrderlist as orderlist where orderlist.TUser=? and orderlist.orderlistStatus = 2";
		List<TOrderlist> orderDate = getCurrentSession().createQuery(hql).setParameter(0, user).list();
		if(orderDate.size() < 1)
			return null;
		else
			return orderDate.get(0).getOrderlistOrderdate();
	}
	
	/**
	 * @author yu
	 */
	@Override
	public boolean modifyConfirmStateById(Integer id) {
		// TODO Auto-generated method stub
		TOrderlist orderlist = this.findById(TOrderlist.class, id);
		if(orderlist==null)
			return false;
		orderlist.setOrderlistStatus(new Integer(1));
		this.getCurrentSession().update(orderlist);
		return true;
	}

	/**
	 * @author yu
	 */
	@Override
	public List<TOrderlist> findOrderlist(Integer orderId, Integer userId,
			Integer orderState, Timestamp beginDate, Timestamp endDate, Integer page,Timestamp recentDate) {
		// TODO Auto-generated method stub
		String hql = "from TOrderlist orderlist where 1=1";
		if(orderId!=null)
			//hql += " AND orderlist.orderlistId LIKE '%" + orderId + "%'";
			hql += " AND orderlist.orderlistId =" + orderId ;
		if(userId!=null)
			//hql += " AND orderlist.TUser.userId LIKE '%" + userId + "%'";
			hql += " AND orderlist.TUser.userId =" + userId ;
		if(orderState!=null)
			hql += " AND orderlist.orderlistStatus = " + orderState ;
		if(beginDate!=null)
			hql += " AND orderlist.orderlistOrderdate > ?"  ;
		if(endDate!=null)
			hql += " AND orderlist.orderlistOrderdate < ?";
		//if(beginDate!=null && endDate!=null && beginDate.equals(endDate))
		//如果输入值为空，搜索默认的3个月内订单
		if(orderId==null && userId==null && beginDate == null && endDate==null && orderState==null)
			hql = new String("from TOrderlist orderlist where orderlist.orderlistOrderdate >?");
		
		//设定排序为时间顺序
		hql += " ORDER BY orderlist.orderlistOrderdate ASC" ;
		
		List<TOrderlist> list = null;
		//蛋疼的写法，先用着吧- -
		if(orderId==null && userId==null && beginDate == null && endDate==null && orderState==null)
			list = getCurrentSession().createQuery(hql)
				.setFirstResult(page*10).setMaxResults(10).setTimestamp(0, recentDate).list();
		else if(beginDate!=null && endDate!=null)
			list = getCurrentSession().createQuery(hql).setTimestamp(0, beginDate).setTimestamp(1, endDate)
					.setFirstResult(page*10).setMaxResults(10).list();

		else if(beginDate!=null && endDate==null){
			list = getCurrentSession().createQuery(hql).setTimestamp(0, beginDate)
					.setFirstResult(page*10).setMaxResults(10).list();
		}
		else if(beginDate==null && endDate!=null){
			list = getCurrentSession().createQuery(hql).setTimestamp(0, endDate)
					.setFirstResult(page*10).setMaxResults(10).list();
		}
		else if(beginDate==null && endDate==null){
			list = getCurrentSession().createQuery(hql)
					.setFirstResult(page*10).setMaxResults(10).list();
		}

		return list;
	}
}
