package yp.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TFavorityDaoI;
import yp.dao.TFavoritycontentDaoI;
import yp.dao.TGoodsDaoI;
import yp.dao.TUserDaoI;
import yp.model.TFavority;
import yp.model.TFavoritycontent;
import yp.model.TGoods;
import yp.model.TUser;
import yp.service.TFavorityServiceI;

@Service
public class TFavorityServiceImpl implements TFavorityServiceI{
	
	private TFavorityDaoI tFavorityDao;
	private TFavoritycontentDaoI tFavoritycontentDao;
	private TGoodsDaoI tGoodsDao;
	private TUserDaoI UserDao;

	public TUserDaoI getUserDao() {
		return UserDao;
	}
	@Autowired
	public void setUserDao(TUserDaoI userDao) {
		UserDao = userDao;
	}
	public TGoodsDaoI gettGoodsDao() {
		return tGoodsDao;
	}
	@Autowired
	public void settGoodsDao(TGoodsDaoI tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}
	public TFavorityDaoI gettFavorityDao() {
		return tFavorityDao;
	}
	@Autowired
	public void settFavorityDao(TFavorityDaoI tFavorityDao) {
		this.tFavorityDao = tFavorityDao;
	}

	public TFavoritycontentDaoI gettFavoritycontentDao() {
		return tFavoritycontentDao;
	}
	@Autowired
	public void settFavoritycontentDao(TFavoritycontentDaoI tFavoritycontentDao) {
		this.tFavoritycontentDao = tFavoritycontentDao;
	}

	// 用户加入商品到收藏夹
	@Override
	public boolean addToFavority(int goodsId,int userId) {
		// TODO Auto-generated method stub
		//根据用户id找到收藏夹
		TUser user = UserDao.findById(TUser.class, userId);
		TFavority tFavority = (TFavority) tFavorityDao.findByUser(TFavority.class, user);
		//根据收藏夹id找到所有的收藏夹商品
		List<TFavoritycontent> list =  tFavoritycontentDao.getAllEntity(tFavority,-1);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			
			TFavoritycontent tFavoritycontent = (TFavoritycontent) iter.next();
			System.out.println("FavoritycontentId："+tFavoritycontent.getFavoritycontentId());
			//若收藏夹已有该商品，返回false
			if(tFavoritycontent.getTGoods().getGoodsId().intValue()==goodsId){
				System.out.println("商品已经存，返回false");
				return false;
			}
		}
		//收藏夹还未加入该商品,则找到该商品对象，调用TFavoritycontent构造函数new一个对象然后加入数据库
		TGoods goods = tGoodsDao.findById(TGoods.class, goodsId);
		TFavoritycontent fGoods = new TFavoritycontent(goods,tFavority);
		tFavoritycontentDao.save(fGoods);
		return true;
	}
	
	//用户删除收藏夹商品
	@Override
	public String deleteFromFavority(int goodsId, int userId) {
		// TODO Auto-generated method stub
		//根据用户id找到收藏夹
		TUser user = UserDao.findById(TUser.class, userId);
		TFavority tFavority = (TFavority) tFavorityDao.findByUser(TFavority.class, user);
		//根据收藏夹id和商品id找到并删除收藏夹商品
		//deleteFavoritycontent(goodsId,tFavority.getFavorityId().intValue());
		List<TFavoritycontent> list =  tFavoritycontentDao.getAllEntity(tFavority,-1);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			TFavoritycontent tFavoritycontent = (TFavoritycontent) iter.next();
			//找到对应收藏商品则删除
			if(tFavoritycontent.getTGoods().getGoodsId()==goodsId){
				tFavoritycontentDao.delete(tFavoritycontent);
				//不需要任何过程了
				break;
			}
		}
		return null;
	}
	//用户进入收藏夹
	@Override
	public List<TFavoritycontent> enterFavority(int userId,int page) {
		// TODO Auto-generated method stub
		List<TFavoritycontent> list=null;
		//根据用户id找到收藏夹
		TUser user = UserDao.findById(TUser.class, userId);
		TFavority tFavority = (TFavority) tFavorityDao.findByUser(TFavority.class, user);
		//根据收藏夹id找到所有的收藏夹商品
		list =  tFavoritycontentDao.getAllEntity(tFavority,page);
		return list;
	}
}
