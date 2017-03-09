package yp.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yp.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T>{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(Object object) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

//	@Override
//	public boolean addUser() {
//		// TODO Auto-generated method stub
//		User user = new User();
//		user.setUserName("金城武");
//		user.setPassword(123456);
//		user.setDescription("没用的家伙");
//		sessionFactory.getCurrentSession().save(user);
//		return true;
//	}
	
}
