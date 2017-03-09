package yp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TGoodsDaoI;
import yp.model.TGoods;
import yp.service.TCartServiceI;


@Service
public class TCartServiceImpl implements TCartServiceI{
	
	private TGoodsDaoI tGoodsDao;
	
	public TGoodsDaoI gettGoodsDao() {
		return tGoodsDao;
	}
	@Autowired
	public void settGoodsDao(TGoodsDaoI tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}
	@Override
	public TGoods getGoodsById(int goodsId) {
		// TODO Auto-generated method stub
		return tGoodsDao.findById(TGoods.class, goodsId);
		
		
	}
	
	

}
