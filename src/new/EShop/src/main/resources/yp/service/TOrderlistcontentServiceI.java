package yp.service;

import java.sql.Timestamp;
import java.util.List;

import yp.model.TGoods;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;

public interface TOrderlistcontentServiceI {
	
	/**
	 * 将订单商品详情写入数据库
	 * @author RichardChan
	 */
	public void addOrderlistcontent(TOrderlistcontent tOrderlistcontent);
	
	/**
	 * 根据订单号查询订单详情
	 * @author RichardChan
	 */
	public List<TOrderlistcontent> getOrderlistContent(TOrderlist orderlistId);

	/**
	 * 获得某商品某段时间所有订单记录
	 * @author RichardChan
	 * @param tGoods
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	public List<TOrderlistcontent> getOrderlistcontentByGoods(TGoods tGoods, Timestamp startTime, Timestamp endTime);

	/**
	 * 根据商品对象获取全部订单记录
	 * @author RichardChan
	 * @param goods
	 * @return
	 */
	public List<TOrderlistcontent> getOrderlistcontentByGoods(TGoods goods);

}