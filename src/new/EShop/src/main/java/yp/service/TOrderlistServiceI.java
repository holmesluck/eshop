package yp.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import yp.model.TOrderlist;
import yp.model.TUser;

public interface TOrderlistServiceI {
	
	/**
	 *确认收货
	 *@param orderlistId
	 *         订单Id
	 * @return boolean
	 * 			
	 * @author 陈荣强
	 */
	public boolean updateConfirmReceived(int orderlistId);
	
	/**
	 *会员下单
	 *@param userId
	 *         会员Id
	 * @return boolean
	 * 			
	 * @author RichardChan
	 */
	public void addOrderlist(int userId, int addressId, Map orderlistDetail);

	/**
	 *显示会员订单记录
	 *@param pageNum
	 *         订单记录页数
	 * @return boolean
	 * 			
	 * @author RichardChan
	 */
	public List<TOrderlist> getOrderlist(TUser user, int pageNum);

	/**
	 * 将订单写入数据库
	 * @param tOrderlist
	 * @author RichardChan
	 */
	public void addOrderlist(TOrderlist tOrderlist);
	
	/**
	 * 根据订单Id获取订单对象 
	 * @author RichardChan
	 * @param orderlistId
	 * @return
	 */
	public TOrderlist getOrderlistById(int orderlistId);
	
	/**
	 * 获取交易记录
	 * @author RichardChan
	 * @param endTime 
	 * @param startTime 
	 * @param orderlist
	 * @return
	 */
	public List<TOrderlist> getTransaction(TUser user, int pageNum, Timestamp startTime, Timestamp endTime);

	/**
	 * 获取第一笔交易的时间
	 * @author RichardChan
	 * @param user
	 * @return
	 */
	public Timestamp getFirstTransactionTime(TUser user);
}

