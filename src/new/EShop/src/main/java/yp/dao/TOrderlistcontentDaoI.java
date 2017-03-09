package yp.dao;

import java.sql.Timestamp;
import java.util.List;

import yp.model.TGoods;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;

public interface TOrderlistcontentDaoI extends BaseDaoI<TOrderlistcontent>{

	/**
	 * 根据订单号查询订单详情
	 * @author RichardChan
	 */
	public List<TOrderlistcontent> getOrderlistContentByOrderlistId(TOrderlist orderlistId);

	/**
	 * 获得某商品某段时间所有订单记录
	 * @author RichardChan
	 * @param tGoods
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	public List<TOrderlistcontent> getOrderlistByGoods(TGoods tGoods, Timestamp startTime, Timestamp endTime);

	/**
	 * 根据商品对象获取全部订单记录
	 * @author RichardChan
	 * @param goods
	 * @return
	 */
	public List<TOrderlistcontent> getOrderlistByGoods(TGoods goods);

}
