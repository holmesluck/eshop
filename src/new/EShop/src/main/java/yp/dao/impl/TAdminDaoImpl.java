package yp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import yp.dao.TAdminDaoI;
import yp.model.TAdmin;

@Repository
public class TAdminDaoImpl extends BaseDaoImpl<TAdmin> implements TAdminDaoI {

	@Override
	public TAdmin getAdminByAdminName(String adminName) {
		// TODO Auto-generated method stub
		String hql = "from TAdmin as admin where admin.adminName=?";
		List adminList = getCurrentSession().createQuery(hql).setParameter(0, adminName).list();
		if(adminList.size() < 1){
			return null;
		}
		else
			return (TAdmin) adminList.get(0);
	}

}