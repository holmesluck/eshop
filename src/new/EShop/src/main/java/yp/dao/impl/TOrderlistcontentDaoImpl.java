package yp.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TOrderlistcontentDaoI;
import yp.model.TGoods;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;
import yp.model.TUser;

@Repository
public class TOrderlistcontentDaoImpl extends BaseDaoImpl<TOrderlistcontent> implements TOrderlistcontentDaoI {

	private String hql = "from TOrderlistcontent as orderlistContent where orderlistContent.TOrderlist = ?";
	@Override
	public List<TOrderlistcontent> getOrderlistContentByOrderlistId(TOrderlist orderlistId) {
		// TODO Auto-generated method stub
		List<TOrderlistcontent> orderlistContent = getCurrentSession().createQuery(hql).setParameter(0, orderlistId).list();
		return orderlistContent;
	}
	@Override
	public List<TOrderlistcontent> getOrderlistByGoods(TGoods tGoods, Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		String hql = "select toc from TOrderlistcontent toc inner join toc.TOrderlist to where toc.TGoods = ? and to.orderlistOrderdate between ? and ?";
		List<TOrderlistcontent> orderlistContentList = getCurrentSession().createQuery(hql).setParameter(0, tGoods).setParameter(1, startTime).setParameter(2, endTime).list();
		if(orderlistContentList.size() < 1)
			return null;
		else
			return orderlistContentList;
	}
	@Override
	public List<TOrderlistcontent> getOrderlistByGoods(TGoods goods) {
		// TODO Auto-generated method stub 
		String hql = "select toc from TOrderlistcontent toc inner join toc.TOrderlist to where toc.TGoods = ?";
		List<TOrderlistcontent> orderlistContentList = getCurrentSession().createQuery(hql).setParameter(0, goods).list();
		if(orderlistContentList.size() < 1)
			return null;
		else
			return orderlistContentList;
	}

}
