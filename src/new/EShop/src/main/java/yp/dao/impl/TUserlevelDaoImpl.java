package yp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import yp.dao.TUserlevelDaoI;
import yp.model.TAddress;
import yp.model.TUserlevel;

@Repository
public class TUserlevelDaoImpl extends BaseDaoImpl<TUserlevel> implements TUserlevelDaoI {

	@Override
	public List<TUserlevel> getUserLevelList() {
		// TODO Auto-generated method stub
		String hql = "from TUserlevel";
		Query query = this.getCurrentSession().createQuery(hql);
		List<TUserlevel> tUserLevel = query.list();
		return tUserLevel;
	}

}
