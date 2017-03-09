package yp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TCommentDaoI;
import yp.dao.TGoodsDaoI;
import yp.model.TComment;
import yp.model.TGoods;
import yp.model.TOrderlistcontent;

@Repository
public class TCommentDaoImpl extends BaseDaoImpl<TComment> implements TCommentDaoI {
     
	TGoodsDaoI tGoodsDao;
	
	public TGoodsDaoI gettGoodsDao() {
		return tGoodsDao;
	}

	@Autowired
	public void settGoodsDao(TGoodsDaoI tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}

	@Override
	public double getAverageMark(int goodsId) {
		// TODO Auto-generated method stub
		TGoods goods = tGoodsDao.findById(TGoods.class, goodsId);
		String hql = "from TComment as comment where comment.TGoods=?";
		List<TComment> orderlistContentList = (List<TComment>) getCurrentSession().createQuery(hql).setParameter(0, goods).list();
		int num = orderlistContentList.size();
		double totalMark = 0;
		for(int i = 0; i < num; i++)
		{
			totalMark += orderlistContentList.get(i).getCommentMark();
		}
		double averageMark = totalMark / num;
		return averageMark;
	}

	@Override
	public boolean deleteCommentById(Integer commentId) {
		// TODO Auto-generated method stub
		try{
		TComment tComment = this.findById(TComment.class, commentId);
		this.delete(tComment);
		}
		catch(HibernateException e){
			return false;
		}
		return true;
	}
	@Override
	public List<TComment> getCommentByGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		List<TComment> list = null;
		String hql = "from TComment comment where comment.TGoods.goodsId=?";
		Session session = getCurrentSession();
		list = session.createQuery(hql).setInteger(0, goodsId).list();
		
		return list;
	}
	
}
