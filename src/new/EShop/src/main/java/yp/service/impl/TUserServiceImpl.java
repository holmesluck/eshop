package yp.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TFavorityDaoI;
import yp.dao.TUserDaoI
;
import yp.model.TAddress;
import yp.model.TFavority;
import yp.model.TUser;
import yp.service.TUserServiceI;
import yp.util.AESEncrypt;

@Service
public class TUserServiceImpl implements TUserServiceI {
	private TUserDaoI tUserDao;
	private TFavorityDaoI tFavorityDao ;

	public TFavorityDaoI gettFavorityDao() {
		return tFavorityDao;
	}

	@Autowired
	public void settFavorityDao(TFavorityDaoI tFavorityDao) {
		this.tFavorityDao = tFavorityDao;
	}

	public TUserDaoI gettUserDao() {
		return tUserDao;
	}

	@Autowired
	public void settUserDao(TUserDaoI tUserDao) {
		this.tUserDao = tUserDao;
	}
	
	@Override
	public TUser getUserInfoByUserEmail( String userEmail){
		return tUserDao.getUserInfoByUserEmail(userEmail);
	}
	
	@Override
	public String userLogin(String  userEmail, String userPassword) {
		// TODO Auto-generated method stub
		TUser user = tUserDao.getUserInfoByUserEmail(userEmail);
		if(user == null){
			return "用户名不存在";
		}
		else if(user.getUserIsdel() == true){
			return "用户名不存在";
		}
		else if(user.getUserPassword().equals(userPassword) == false){
			return "用户名与密码不匹配";
		}
		else
		{
			return "成功";
		}
	}
	
	@Override
	public boolean isRegisted(String userEmail){
		if(tUserDao.getUserInfoByUserEmail(userEmail) == null) return false;
		else return true;
	}
	
	@Override
	public boolean addUser(TUser user){
		tUserDao.save(user);
		TFavority favority = new TFavority();
		favority.setTUser(user);
		tFavorityDao.save(favority);
		return true;
	}
	
	@Override
	public TUser findUserInfoById(int userId){
		return tUserDao.findById(TUser.class, userId);
	}
	@Override
	public int findUserIdByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return tUserDao.getUserInfoByUserEmail(userEmail).getUserId();
	}

	@Override
	public boolean checkPrimaryPassword(Integer userId,String password) {
		// TODO Auto-generated method stub	
		return tUserDao.checkParimaryPassword(userId, password);
	}

	@Override
	public boolean modifyPassword(Integer userId, String password) {
		// TODO Auto-generated method stub
		return tUserDao.modifyPassword(userId, password);
	}
	
	@Override
	public boolean modifyUserNickName(int UserId, String userNickName) {
		// TODO Auto-generated method stub
		//if(tUser)
		return tUserDao.modifyNickNameById(UserId, userNickName);
		
	}
	
	public TUser getUserInfoById(int userId){
		System.out.println(tUserDao);
		return tUserDao.findById(TUser.class, userId);
	}
	
	@Override
	public TUser getUserObject(int UserId) {
		// TODO Auto-generated method stub
		return tUserDao.findById(TUser.class,UserId);
	}

	@Override
	public boolean deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		TFavority tFavority;
		TUser tUser = tUserDao.findById(TUser.class, userId);
		//通过级联删除删除用户收藏夹
		tUser.getTFavorities().clear();
		//修改用户isdel字段
		tUser.setUserIsdel(true);
		try{
		tUserDao.update(tUser);
		}
		catch(HibernateException e)
		{
			return false;
		}
		return true;
	}

	@Override
	public void update(TUser user) {
		// TODO Auto-generated method stub
		tUserDao.update(user);
	}
}
