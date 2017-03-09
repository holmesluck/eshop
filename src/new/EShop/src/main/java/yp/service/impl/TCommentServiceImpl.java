package yp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TCommentDaoI;
import yp.model.TComment;
import yp.service.TCommentServiceI;

@Service
public class TCommentServiceImpl implements TCommentServiceI{
	private TCommentDaoI tCommentDao;

	public TCommentDaoI gettCommentDao() {
		return tCommentDao;
	}

	@Autowired
	public void settCommentDao(TCommentDaoI tCommentDao) {
		this.tCommentDao = tCommentDao;
	}

	@Override
	public void addComment(TComment comment) {
		// TODO Auto-generated method stub
		tCommentDao.save(comment);
	}

	@Override
	public double getAverageMark(int goodsId) {
		// TODO Auto-generated method stub
		return tCommentDao.getAverageMark(goodsId);
	}

	@Override
	public boolean deleteCommentById(Integer commentId) {
		// TODO Auto-generated method stub
		return tCommentDao.deleteCommentById(commentId);
	}
}