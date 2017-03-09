package yp.service;

import yp.model.TComment;

/**
 * 评论Service接口
 * @author RichardChan
 *
 */
public interface TCommentServiceI{
    
	/**
	 * 将商品评论信息写入数据库
	 * @param comment
	 * @author RichardChan
	 */
	public void addComment(TComment comment);
	
	/**
	 * 获取某商品的平均评分
	 * @author RichardChan
	 */
	public double getAverageMark(int goodsId);
	
	/**
	 * 根据评论删除商品评论
	 * @param commentId
	 * 			评论id
	 * @return boolean 
	 * 			成功与否	
	 * @author 郑天然
	 */
	public boolean deleteCommentById(Integer commentId);
}