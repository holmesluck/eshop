package yp.service;

import java.util.List;

import yp.model.TAddress;
import yp.model.TUser;

public interface TUserAddressServiceI {
	/**
	 * 删除用户信息
	 * 
	 * @param addressId
	 *            地址Id
	 * @return boolean 是否成功
	 * @author 郑天然
	 */
	Integer deleteAddress(Integer addressId);
	/**
	 * 修改地址信息
	 * 
	 * @param addressId
	 *            地址Id
	 *        addressLinkman
	 *            收件人
	 *        addressAddress
	 *            地址
	 *        addressPhone
	 *            电话
	 *        addressPostcode
	 *            邮编
	 * @return Integer status
	 * @author 郑天然
	 */
	boolean modifyAddress(Integer addressId,String addressLinkman,
			String addressAddress,String addressPhone,String addressPostcode);
	
	/**
	 * 添加地址信息
	 * 
	 * @param userid 
	 * 			用户id
	 * 		addressLinkman
	 *            收件人
	 *        addressAddress
	 *            地址
	 *        addressPhone
	 *            电话
	 *        addressPostcode
	 *            邮编
	 * @return Integer status
	 * @author 郑天然
	 */
	boolean addAddress(Integer userid,String addressLinkman,
			String addressAddress,String addressPhone,String addressPostcode);
	/**
	 * 根据用户id获得地址列表
	 * @param userId
	 * 			用户id
	 * 
	 */
	List<TAddress> getAddressList(Integer userId);
	/**
	 * 根据诋毁id修改默认地址
	 * 
	 * @param addressId 
	 * 			地址id
	 * @author 郑天然
	 */
	boolean updateDefaultAddress(Integer addressId);
	
	public TAddress getAddressById(int addressId);
	
	/**
	 * 
	 * @Author RichardChan
	 * @param user
	 * @return
	 */
	public TAddress getUserDefaultAddress(TUser user);
	
}
