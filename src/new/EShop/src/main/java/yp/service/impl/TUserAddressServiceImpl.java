package yp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TAddressDaoI;
import yp.dao.TUserDaoI;
import yp.model.TAddress;
import yp.model.TUser;
import yp.service.TUserAddressServiceI;

@Service
public class TUserAddressServiceImpl implements TUserAddressServiceI {
	TAddressDaoI taddressDao;
	TUserDaoI tUserDao;
	TAddress taddress;

	public TUserDaoI gettUserDao() {
		return tUserDao;
	}
	
	@Autowired
	public void settUserDao(TUserDaoI tUserDao) {
		this.tUserDao = tUserDao;
	}
	
	public TAddressDaoI getTaddressDao() {
		return taddressDao;
	}
	
	@Autowired
	public void setTaddressDao(TAddressDaoI taddressDao) {
		this.taddressDao = taddressDao;
	}

	@Override
	public Integer deleteAddress(Integer addressId) {
		// TODO Auto-generated method stub
		return taddressDao.deleteById(addressId);
	}

	@Override
	public boolean modifyAddress(Integer addressId, String addressLinkman,
			String addressAddress, String addressPhone, String addressPostcode) {
		// TODO Auto-generated method stub
		return taddressDao.modifyById(addressId, addressLinkman, addressAddress, addressPhone, addressPostcode);
	}

	@Override
	public boolean addAddress(Integer userid,String addressLinkman, String addressAddress,
			String addressPhone, String addressPostcode) {
		// TODO Auto-generated method stub
		TUser tUser = tUserDao.findById(TUser.class, userid);
		if(tUser!=null)
			return taddressDao.addAddress(tUser, addressLinkman, addressAddress, addressPhone, addressPostcode);
		return false;
	}

	@Override
	public List<TAddress> getAddressList(Integer userId) {
		// TODO Auto-generated method stub
		return taddressDao.getAddressList(userId);
	}

	@Override
	public boolean updateDefaultAddress(Integer addressId) {
		// TODO Auto-generated method stub
		return taddressDao.updateDefaultAddress(addressId);
	}
	
	public TAddress getAddressById(int addressId){
		return taddressDao.findById(TAddress.class, addressId);
	}

	@Override
	public TAddress getUserDefaultAddress(TUser user) {
		// TODO Auto-generated method stub
		return taddressDao.getUserDefaultAddress(user);
	}

}
