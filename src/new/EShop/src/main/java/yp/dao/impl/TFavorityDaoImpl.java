package yp.dao.impl;

import org.springframework.stereotype.Repository;

import yp.dao.TFavorityDaoI;
import yp.model.TFavority;
import yp.model.TUser;

@Repository
public class TFavorityDaoImpl extends BaseDaoImpl implements TFavorityDaoI {

	@Override
	public TFavority findByUser(Class<TFavority> class1, TUser user) {
		// TODO Auto-generated method stub
		String hql = "from TFavority as favority where favority.TUser = ?";
		TFavority favority = (TFavority) getCurrentSession().createQuery(hql).setParameter(0, user).list().get(0);
		return favority;
	}

}
