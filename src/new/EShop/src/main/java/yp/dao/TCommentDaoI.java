package yp.dao;

import java.util.List;

import yp.model.TComment;

public interface TCommentDaoI extends BaseDaoI<TComment>{

	/**
	 * 
	 * @param goodsId
	 * @author RichardChan
	 */
	public double getAverageMark(int goodsId);
	/**
	 * 根据商品查找评论
	 * @param goodsId
	 * @return
	 * @author yu
	 */
	public List<TComment> getCommentByGoods(Integer goodsId);

	/**
	 * 根据评论id删除商品评论
	 * @param commentId
	 * 			评论id
	 * @return boolean 
	 * 			成功与否	
	 * @author 郑天然
	 */
	public boolean deleteCommentById(Integer commentId);
}
