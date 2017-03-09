package yp.service;

import java.util.List;

import yp.model.TAddress;

public  interface TOrderConfirmServiceI {
	
	/**
	 * 查询指定用户的默认送货地址并返回地址对象，若没有默认地址，返回null
	 * @param userId
	 * @return 
	 * @author yu
	 */
	public TAddress selectDefaultAddress(Integer userId);
	/**
	 * 通过地址id找到地址对象
	 * @param selectedAddressId
	 * @return
	 */
	public TAddress getSelectedAddress(Integer selectedAddressId);
	
	/**
	 * 获取用户的所有地址
	 * @param userId
	 * @return
	 */
	public List<TAddress> getUserAddress(Integer userId);
	/**
	 * 计算会员折扣后总价
	 * @param totalPrice ,userId
	 * @return
	 * @author yu
	 */
	public double getCountPrice(double totalPrice,int userId);

}
