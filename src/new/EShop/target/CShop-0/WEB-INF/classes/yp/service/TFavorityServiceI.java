package yp.service;

import java.util.List;

import yp.model.TFavoritycontent;

/**
 * 收藏夹service接口
 * @author yu
 */
public interface TFavorityServiceI {
	
	
	/**
	 *将商品加入收藏夹 
	 * @param goodid 商品id
	 * @author yu
	 */
	public boolean addToFavority(int goodId,int userId);
	
	public String deleteFromFavority(int goodId,int userId);
	
	public List<TFavoritycontent> enterFavority(int userId,int page);

}
