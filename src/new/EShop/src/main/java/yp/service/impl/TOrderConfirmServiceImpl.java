package yp.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TAddressDaoI;
import yp.dao.TUserDaoI;
import yp.model.TAddress;
import yp.model.TUser;
import yp.service.TOrderConfirmServiceI;


@Service
public class TOrderConfirmServiceImpl implements TOrderConfirmServiceI{

	private TAddressDaoI tAddressDaoI;
	private TUserDaoI tUserDaoI;
	
	public TAddressDaoI gettAddressDaoI() {
		return tAddressDaoI;
	}

	@Autowired
	public void settAddressDaoI(TAddressDaoI tAddressDaoI) {
		this.tAddressDaoI = tAddressDaoI;
	}
	public TUserDaoI gettUserDaoI() {
		return tUserDaoI;
	}
	@Autowired
	public void settUserDaoI(TUserDaoI tUserDaoI) {
		this.tUserDaoI = tUserDaoI;
	}

	
	
	
	@Override
	public TAddress selectDefaultAddress(Integer userId) {
		// TODO Auto-generated method stub
		List<TAddress> list = tAddressDaoI.findAllEntity(userId);
		Iterator iter =list.iterator();
		while(iter.hasNext()){
			TAddress address = (TAddress) iter.next();
			System.out.println("当前address对象:"+ address+ "   isDefault:" + address.getAddressIsdefault());
			if(address.getAddressIsdefault()){
				return address;
			}
		}
		return null;
	}

	@Override
	public TAddress getSelectedAddress(Integer selectedAddressId) {
		// TODO Auto-generated method stub
		return (TAddress)tAddressDaoI.findById(TAddress.class, selectedAddressId);
	}

	@Override
	public List<TAddress> getUserAddress(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCountPrice(double totalPrice,int userId) {
		// TODO Auto-generated method stub
		TUser user = tUserDaoI.findById(TUser.class, userId);
		double count = user.getTUserlevel().getUserlevelDiscount();
		System.out.println("会员的折扣是：" + count);
		count = totalPrice*count*100;
		int temp = (int) count;
		System.out.println("temp：" + temp);
		count = temp/(double)100;
		System.out.println("会员的折扣价是：" + count);
		return  count;
	}



	
	

}
