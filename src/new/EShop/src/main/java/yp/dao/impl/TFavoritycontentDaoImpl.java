package yp.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import yp.dao.TFavoritycontentDaoI;
import yp.model.TFavority;
import yp.model.TFavoritycontent;
import yp.model.TGoods;

@Repository
public class TFavoritycontentDaoImpl extends BaseDaoImpl implements TFavoritycontentDaoI {

	@Override
	public List<TFavoritycontent> getAllEntity(TFavority tFavority,int page) {
		// TODO Auto-generated method stub
		
		Session session = getCurrentSession();
		List<TFavoritycontent> list = null;
		//用createQuery方法执行hql语句
		if(page<0)
			list = session.createQuery(
				"from TFavoritycontent f where f.TFavority=?").setEntity(0,tFavority).list();
		else
			list = session.createQuery("from TFavoritycontent f where f.TFavority=?")
			.setEntity(0, tFavority).setFirstResult(page *12).setMaxResults(12).list();
		//session.close();
		return list;
	}

	@Override
	public boolean deleteFavoritycontent(int gooodsId, int favorityId) {
		// TODO Auto-generated method stub
		//getCurrentSession().createQuery("delete  TFavoritycontent f where f.");
		return false;
	}
	

	/*@Override
	public TFavoritycontent getByGoodIdAndFavorityId(TFavority f, TGoods g) {
		// TODO Auto-generated method stub
		
		TFavoritycontent ft = this.getCurrentSession().createQuery(
				"from TFavoritycontent f where f.TFavority=? and f.TGoods=?").set
		return null;
	}*/
	
}
