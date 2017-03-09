package yp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TCommentDaoI;
import yp.dao.TFirstcatalogDaoI;
import yp.dao.TGoodsDaoI;
import yp.dao.TSecondcatalogDaoI;
import yp.model.TComment;
import yp.model.TFirstcatalog;
import yp.model.TGoods;
import yp.model.TSecondcatalog;
import yp.service.TAdminGoodsServiceI;

@Service
public class TAdminGoodsServiceImpl implements TAdminGoodsServiceI{

	TGoodsDaoI tGoodsDao ;
	TFirstcatalogDaoI tFirstcatalogDao;
	TSecondcatalogDaoI tSecondcatalogDao;
	TCommentDaoI tCommentDao;
	
	
	public TCommentDaoI gettCommentDao() {
		return tCommentDao;
	}
	@Autowired
	public void settCommentDao(TCommentDaoI tCommentDao) {
		this.tCommentDao = tCommentDao;
	}
	public TFirstcatalogDaoI gettFirstcatalogDao() {
		return tFirstcatalogDao;
	}
	@Autowired
	public void settFirstcatalogDao(TFirstcatalogDaoI tFirstcatalogDao) {
		this.tFirstcatalogDao = tFirstcatalogDao;
	}
	public TSecondcatalogDaoI gettSecondcatalogDao() {
		return tSecondcatalogDao;
	}
	@Autowired
	public void settSecondcatalogDao(TSecondcatalogDaoI tSecondcatalogDao) {
		this.tSecondcatalogDao = tSecondcatalogDao;
	}
	public TGoodsDaoI gettGoodsDao() {
		return tGoodsDao;
	}
	@Autowired
	public void settGoodsDao(TGoodsDaoI tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}

	@Override
	public boolean adminAddGoods(String name, String firstId,String secondId,
			String description, String image, String model, String os,
			String processer, String memory, String hardDisk,
			String screenSize, String resolution, Double price, Integer stock,
			Double discount) {
		// TODO Auto-generated method stub
		
		TGoods goods = null; //将要加入数据库的goods
		TFirstcatalog tFirstcatalog = null;
		TSecondcatalog tSecondcatalog = null;
		if(firstId!=null){
			tFirstcatalog = tFirstcatalogDao.findById(TFirstcatalog.class,Integer.valueOf(firstId));//找到一级目录对象
		}
		if(secondId!=null){
			tSecondcatalog = tSecondcatalogDao.findById(TSecondcatalog.class, Integer.valueOf(secondId));
		}
		if(tFirstcatalog!=null && tSecondcatalog!=null){
			if(tSecondcatalog.getTFirstcatalog().equals(tFirstcatalog)==false)  //当一二级目录都不为空又不对应时返回false
				return false;
		}
		goods = new TGoods();
		goods.setGoodsName(name);
		goods.setGoodsDescription(description);
		goods.setGoodsDiscount(discount);
		goods.setGoodsHarddiskCapacity(hardDisk);
		goods.setGoodsImage(image);
		goods.setGoodsIsdel(false);
		goods.setGoodsMemory(memory);
		goods.setGoodsModel(model);
		goods.setGoodsOperationSystem(os);
		goods.setGoodsPrice(price);
		goods.setGoodsProcessor(processer);
		goods.setGoodsResolutionDefinition(resolution);
		goods.setGoodsScreenSize(screenSize);
		goods.setGoodsStock(stock);
		goods.setTFirstcatalog(tFirstcatalog);
		goods.setTSecondcatalog(tSecondcatalog);
		goods.setGoodsAveragemark(0.0);
		goods.setGoodsSales(0);
		
		tGoodsDao.save(goods);
			
		return true;
	}
	@Override
	public boolean adminDeleteGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		TGoods goods = tGoodsDao.findById(TGoods.class, goodsId);
		goods.setGoodsIsdel(true);
		tGoodsDao.update(goods);
		return true;
	}
	@Override
	public TGoods adminGetGoodsInfo(Integer goodsId) {
		// TODO Auto-generated method stub
		return tGoodsDao.findById(TGoods.class, goodsId);
	}
	@Override
	public List<TComment> adminGetGoodsComment(Integer goodsId) {
		// TODO Auto-generated method stub
		return tCommentDao.getCommentByGoods(goodsId);
	}
	@Override
	public List<TFirstcatalog> adminGetFirstCatalogList() {
		// TODO Auto-generated method stub
		return tFirstcatalogDao.getFirstcatalogList();
	}
	@Override
	public boolean adminModifyGoods(Integer goodsId,String name, String firstId,
			String secondId, String description, String image, String model,
			String os, String processer, String memory, String hardDisk,
			String screenSize, String resolution, Double price, Integer stock,
			Double discount) {
		// TODO Auto-generated method stub
		TGoods goods = null; //将要加入数据库的goods
		TFirstcatalog tFirstcatalog = null;
		TSecondcatalog tSecondcatalog = null;
		if(firstId!=null){
			tFirstcatalog = tFirstcatalogDao.findById(TFirstcatalog.class,Integer.valueOf(firstId));//找到一级目录对象
		}
		if(secondId!=null){
			tSecondcatalog = tSecondcatalogDao.findById(TSecondcatalog.class, Integer.valueOf(secondId));
		}
		if(tFirstcatalog!=null && tSecondcatalog!=null){
			if(tSecondcatalog.getTFirstcatalog().equals(tFirstcatalog)==false)  //当一二级目录都不为空又不对应时返回false
				return false;
		}
		goods = tGoodsDao.findById(TGoods.class,goodsId);
		if(goods==null)
			return false;
		if(name!=null)
			goods.setGoodsName(name);
		if(description!=null)
			goods.setGoodsDescription(description);
		if(discount!=null)
			goods.setGoodsDiscount(discount);
		if(hardDisk!=null)
			goods.setGoodsHarddiskCapacity(hardDisk);
		if(image!=null)
			goods.setGoodsImage(image);
		if(memory!=null)
			goods.setGoodsMemory(memory);
		if(model!=null)
			goods.setGoodsModel(model);
		if(os!=null)
			goods.setGoodsOperationSystem(os);
		if(price!=null)
			goods.setGoodsPrice(price);
		if(processer!=null)
			goods.setGoodsProcessor(processer);
		if(resolution!=null)
			goods.setGoodsResolutionDefinition(resolution);
		if(screenSize!=null)
			goods.setGoodsScreenSize(screenSize);
		if(stock!=null)
			goods.setGoodsStock(stock);
		if(tFirstcatalog!=null)
			goods.setTFirstcatalog(tFirstcatalog);
		if(tSecondcatalog!=null)
			goods.setTSecondcatalog(tSecondcatalog);
		
		tGoodsDao.update(goods);
			
		return true;
	}
	
	

}
