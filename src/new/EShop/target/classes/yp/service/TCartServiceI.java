package yp.service;

import yp.model.TGoods;

public interface TCartServiceI{
	
	
	/**
	 * 通过商品id从数据库中获取商品信息
	 * @param goodsId
	 * @return
	 */
	public TGoods getGoodsById(int goodsId);

}
