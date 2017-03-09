package yp.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TUserDaoI;
import yp.model.TUser;

@Repository
public class TUserDaoImpl extends BaseDaoImpl<TUser> implements TUserDaoI {
	@Override
	public boolean modifyNickNameById(int userid, String userNickName) {
		// TODO Auto-generated method stub
		try{
			//获取相应id对象
			TUser tUser = findById(TUser.class,userid);
			if(tUser==null)
				return false;
			//修改昵称
			tUser.setUserNickname(userNickName);
			//update到数据库
			this.getCurrentSession().update(tUser);
		}catch(HibernateException e){
			return false;
		}
		return true;
		}
	
	private String hql = "from TUser as u where u.userEmail = ?";
	public TUser getUserByUserEmail(String UserEmail){
		TUser user = (TUser) this.getCurrentSession().createQuery(hql).setParameter(0, UserEmail).list().get(0);
		return user;
	}

	private  String hqlQuery = "from TUser as u where u.userEmail = ?"; 
	@Override
	public TUser getUserInfoByUserEmail( String userEmail){
		if(getCurrentSession().createQuery(hqlQuery).setParameter(0, userEmail).list().size() < 1){
			return null;
		}
		else
		{
		TUser user = (TUser) getCurrentSession().createQuery(hql).setParameter(0, userEmail).list().get(0);
		return user;
		}
	}

        @Override
	public boolean checkParimaryPassword(Integer userId, String password) {
		// TODO Auto-generated method stub
		TUser user = findById(TUser.class, userId);
		if(user==null || !(password.equals(user.getUserPassword()))){
			return false;
		}
		return true;
	}
        
        @Override
	public boolean modifyPassword(Integer userId, String password) {
		// TODO Auto-generated method stub
		try{
			TUser user = findById(TUser.class, userId);
			user.setUserPassword(password);
			this.getCurrentSession().update(user);
		}catch(RuntimeException e){
			return false;
		}
		return true;
        }
}

	


