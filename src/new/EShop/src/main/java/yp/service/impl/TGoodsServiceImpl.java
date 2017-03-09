package yp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TGoodsDaoI;
import yp.model.TGoods;
import yp.model.TSecondcatalog;
import yp.model.view.GoodsJsonView;
import yp.service.TGoodsServiceI;

@Service
public class TGoodsServiceImpl implements TGoodsServiceI {
	private TGoodsDaoI tGoodsDao;

	public TGoodsDaoI gettGoodsDao() {
		return tGoodsDao;
	}

	@Autowired
	public void settGoodsDao(TGoodsDaoI tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}

	@Override
	public TGoods getAGoodsInfo(int goodsId) {
		// TODO Auto-generated method stub
		TGoods goods = tGoodsDao.findById(TGoods.class, goodsId);
		if(goods==null || goods.getGoodsIsdel()==false){
			return goods;
		}else{
			return null;//商品已下架
		}	
	}

	@Override
	public List<TGoods> getTop3FromEachBranch() {
		// TODO Auto-generated method stub
		return tGoodsDao.getTop3FromEachBranch();
	}

	@Override
	public List<TGoods> getQuickSearchGoods(String searchWords, String priceSort,
			String soldSort,Integer page) {
		// TODO Auto-generated method stub
		String[] searchWord = searchWords.split(" ");
		return tGoodsDao.getQuickSearchResult(searchWord, priceSort, soldSort,page);
	}

	@Override
	public List<TGoods> getAdvancedSearchGoods(String searchConditions,
			String priceSort, String soldSort, String page) {
		// TODO Auto-generated method stub
		//��searchConditions���
		try {
			searchConditions = new String(searchConditions.getBytes("ISO8859_1"), "UTF-8");//ת����֧������
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] conditions = searchConditions.split(",");
		if(conditions.length!=16){
			return null;//����
		}else{
			return tGoodsDao.getAdvancedSearchResult(conditions, priceSort, soldSort, Integer.parseInt(page));
		}
	}

	@Override
	public void updateAverageMark(int goodsId, double averageMark) {
		// TODO Auto-generated method stub
		TGoods tGoods = tGoodsDao.findById(TGoods.class, goodsId);
		tGoods.setGoodsAveragemark(averageMark);
		tGoodsDao.update(tGoods);
	}

	@Override
	public List<TGoods> getGoodListBySecondCatalogIdAndPage(
			Integer secondCatalogId, Integer pageNum,String priceSort, String soldSort) {
		// TODO Auto-generated method stub
		//用户返回的列表
		List<TGoods> tGoodsList;
		List<TGoods> goodsList = tGoodsDao.getGoodListBySecondCatalogId(secondCatalogId,priceSort, soldSort);
		
		//选取list里面符合pageNum要求的数据
		Integer size=goodsList.size();
		if(size>=(pageNum+1)*12){
		tGoodsList=goodsList.subList(pageNum*12,(pageNum+1)*12);
		}
		else if(size<(pageNum+1)*12&&size>pageNum*12){
			tGoodsList=goodsList.subList(pageNum*12,size);
		}
		else{
			//没有合适信息则返回一个没有数据的列表
			tGoodsList=new ArrayList<TGoods>();
		}
		return tGoodsList;
	}

	@Override
	public List<TGoods> getGoodListByFirstCatalogIdAndPage(
			Integer firstCatalogId, Integer pageNum,String priceSort, String soldSort) {
		// TODO Auto-generated method stub
		//用户返回的列表
				List<TGoods> tGoodsList;
				List<TGoods> goodsList = tGoodsDao.getGoodListByFirstCatalogId(firstCatalogId, priceSort, soldSort);
				
				//选取list里面符合pageNum要求的数据
				Integer size=goodsList.size();
				if(size>=(pageNum+1)*12){
				tGoodsList=goodsList.subList(pageNum*12,(pageNum+1)*12);
				}
				else if(size<(pageNum+1)*12&&size>pageNum*12){
					tGoodsList=goodsList.subList(pageNum*12,size);
				}
				else{
					//没有合适信息则返回一个没有数据的列表
					tGoodsList=new ArrayList<TGoods>();
				}
				return tGoodsList;
	}

	@Override
	public void update(TGoods tGoods) {
		// TODO Auto-generated method stub
		tGoodsDao.update(tGoods);
	}

	@Override
	public List<TGoods> getGoodsBySecondCatalog(int secondCatalogId) {
		// TODO Auto-generated method stub
		return tGoodsDao.getGoodListBySecondCatalogId(secondCatalogId);
	}

	@Override
	public List<TGoods> getGoodListByFirstCatalog(Integer firstcatalogId) {
		// TODO Auto-generated method stub
		return tGoodsDao.getGoodListByFirstCatalogId(firstcatalogId);
	}

	@Override
	public List<TGoods> getGoods(int pageNum, int secondcatalogId) {
		// TODO Auto-generated method stub
		return tGoodsDao.getGoods(pageNum, secondcatalogId);
	}
	
	@Override
	public List<TGoods> getAllGoods() {
		// TODO Auto-generated method stub
		return tGoodsDao.getAllGoods();
	}
}
