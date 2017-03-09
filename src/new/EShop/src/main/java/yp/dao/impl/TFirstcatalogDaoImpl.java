package yp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import yp.dao.TFirstcatalogDaoI;
import yp.model.TFirstcatalog;

@Repository
public class TFirstcatalogDaoImpl extends BaseDaoImpl<TFirstcatalog> implements
		TFirstcatalogDaoI {

	@Override
	public List<TFirstcatalog> getFirstcatalogList() {
		// TODO Auto-generated method stub
		//Session session = getSessionFactory().openSession();
		Session session = getCurrentSession();
		List<TFirstcatalog> firstcatalogList = session.createQuery(
				"FROM TFirstcatalog").list();
//		for (int i = 0; i < firstcatalogList.size(); i++) {// 查出对应的二级目录
//			firstcatalogList.get(i).getTSecondcatalogs();
//		}
		//session.close();
		return firstcatalogList;
	}

}
