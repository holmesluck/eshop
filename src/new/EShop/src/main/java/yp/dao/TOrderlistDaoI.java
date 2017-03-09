package yp.dao;

import java.sql.Timestamp;
import java.util.List;

import yp.model.TOrderlist;
import yp.model.TUser;

public interface TOrderlistDaoI extends BaseDaoI<TOrderlist>{
	
	/**
	 * 获取会员在数据库中订单记录数
	 * @author RichardChan
	 */
	public int getNum();

	/**
	 *显示会员订单记录
	 *@param num
	 *         订单记录数
	 * @return boolean
	 * 			
	 * @author RichardChan
	 */
	public List<TOrderlist> getOrderlist(TUser user, int num);
   
	/**
	 * 获取会员的交易记录
	 * @author RichardChan
	 * @param user
	 * @param num
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	public List<TOrderlist> getTransaction(TUser user, int num, Timestamp startTime, Timestamp endTime);

	/**
	 * 获取会员第一次交易的时间
	 * @author RichardChan
	 * @param user
	 * @return
	 */
	public Timestamp getFirstTransactionTime(TUser user);
	
	/**
	 * 根据id 改变订单状态为已审核
	 * @author yu
	 * @param id
	 * @return
	 */
	public boolean modifyConfirmStateById(Integer id);
	/**
	 * 按照输入值查找订单
	 * @return
	 * @param page 分页 
	 * @param endDate 结束日期
	 * @param beginDate 开始日期
	 * @param strOrderState   订单状态
	 * @param strUserId  会员id
	 * @param strOrderId  订单id
	 * @author yu
	 */ 
	public List<TOrderlist> findOrderlist(Integer strOrderId, Integer strUserId, Integer strOrderState, 
			Timestamp beginDate, Timestamp endDate, Integer page,Timestamp recentDate);
    
}
