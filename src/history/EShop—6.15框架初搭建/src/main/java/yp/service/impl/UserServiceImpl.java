package yp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.BaseDaoI;
import yp.model.User;
import yp.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private BaseDaoI<User> baseDao;

	public BaseDaoI<User> getBaseDao() {
		return baseDao;
	}
	
	@Autowired
	public void setBaseDao(BaseDaoI<User> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println(baseDao.save(user));
		return true;
	}

	// @Override
	// public void testUSer() {
	// // TODO Auto-generated method stub
	// System.out.println("testUserOOOO");
	// }

}
