package yp.dao;

import java.util.Iterator;
import java.util.List;

import yp.model.TFavority;
import yp.model.TFavoritycontent;
import yp.model.TGoods;

public interface TFavoritycontentDaoI extends BaseDaoI{
	
	/**
	 * 按照favority查找并返回所有TFavoritycontent  page 为-1返回所有，page>0返回相应的页数的商品
	 * @param favorityID  page
	 * @return List<TFavoritycontent>
	 * @author yu
	 */
	public List<TFavoritycontent> getAllEntity(TFavority tFavority,int page);
	
	/**
	 * (不可用)
	 * 删除收藏夹商品
	 * @param gooodsId  商品id
	 * @param favorityId   收藏夹id
	 * @return
	 * @author yu
	 */
	public boolean deleteFavoritycontent(int gooodsId,int favorityId);
	
}
