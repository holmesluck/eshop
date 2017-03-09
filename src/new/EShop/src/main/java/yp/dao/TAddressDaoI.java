package yp.dao;

import java.util.List;

import yp.model.TAddress;
import yp.model.TUser;

public interface TAddressDaoI extends BaseDaoI<TAddress>{

	public List<TAddress> findAllEntity(Integer userId);

	/**
	 * 根据id删除地址
	 * @author 郑天然
	 */
	Integer deleteById(Integer addressId);
	/**
	 * 修改地址
	 * @param addressId
	 *            地址Id
	 *        addressLinkman
	 *            收件人
	 *        addressAddress
	 *           地址
	 *        addressPhone
	 *            电话
	 *        addressPostcode
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然 
	 * 	 */
	boolean modifyById(Integer addressId,String addressLinkman,
			String addressAddress,String addressPhone,String addressPostcode);
	
	/**
	 * 添加地址
	 * @param  TUser
	 * 			用户对象
	 * 		addressLinkman
	 *            收件人
	 *        addressAddress
	 *           地址
	 *        addressPhone
	 *            电话
	 *        addressPostcode
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然
	 */
	boolean addAddress(TUser tUser,String addressLinkman,
			String addressAddress,String addressPhone,String addressPostcode);
	
	/**
	 * 根据用户id获得地址列表
	 * @param userId
	 * 			用户id
	 * @author  郑天然
	 * 	 */
	List<TAddress> getAddressList(Integer userId);
	/**
	 * 修改默认地址
	 * @author 郑天然
	 */
	boolean updateDefaultAddress(Integer addressId);
	
	/**
	 * 
	 * @Author RichardChan
	 * @param user
	 * @return
	 */
	public TAddress getUserDefaultAddress(TUser user);
}
