package yp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TAdminDaoI;
import yp.model.TAdmin;
import yp.service.TAdminServiceI;

@Service
public class TAdminServiceImpl implements TAdminServiceI {
	
	private TAdminDaoI tAdminDao;

	public TAdminDaoI gettAdminDao() {
		return tAdminDao;
	}

	@Autowired
	public void settAdminDao(TAdminDaoI tAdminDao) {
		this.tAdminDao = tAdminDao;
	}

	@Override
	public String adminLogin(String adminName, String adminPassword) {
		// TODO Auto-generated method stub
		TAdmin admin = tAdminDao.getAdminByAdminName(adminName);
		if(admin == null){
			return "此管理员账号不存在";
		}
		else if(admin.getAdminPassword().equals(adminPassword) == true){
			return "成功";
			
		}
		else return "管理员密码不正确";
			
		
	}

	@Override
	public int getAdminIdByAdminName(String adminName) {
		// TODO Auto-generated method stub
		TAdmin admin = tAdminDao.getAdminByAdminName(adminName);
		return admin.getAdminId();
	}

	@Override
	public TAdmin getAdminById(int adminId) {
		// TODO Auto-generated method stub
		return tAdminDao.findById(TAdmin.class, adminId);
	}

}
