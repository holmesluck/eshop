package yp.service;

import java.util.List;

import yp.model.TGoods;
import yp.model.TSecondcatalog;

/**
 * 商品service接口
 * 
 * @author YP
 */
public interface TGoodsServiceI {

	/**
	 * 获得指定商品的全部信息
	 * 
	 * @param goodsId
	 *            商品Id
	 * @return TGoods 对象
	 * @author YP
	 */
	public TGoods getAGoodsInfo(int goodsId);

	/**
	 * 获取每个品牌的三个商品
	 * 
	 * @author YP
	 */
	public List<TGoods> getTop3FromEachBranch();
	
	/**
	 * 快速搜索，输入值有：
	 * 		searchWords(空格要做分词);//进队description模糊搜索
	 * 		priceSort("asc"|"desc"|"no");
	 * 		soldSort("asc"|"desc"|"no")
	 * 
	 * @return
	 * @author YP
	 */
	public List<TGoods> getQuickSearchGoods(String searchWords,String priceSort,String soldSort,Integer page);

	/**
	 * 高级搜索商品。输入值有：
	 * 16个checkbox选值(以字符串中间逗号形式)
	 * @param searchConditions
	 * 					联想1、宏基2、戴尔3、华硕4
	 * 					3000以下、3000-3999、4000-4999、5000-5999
	 * 					酷睿i3	酷睿i5	酷睿i7
	 * 					内存1GB	2GB	4GB	8GB
	 * @param priceSort
	 * 			("asc"|"desc"|"no") 
	 * @param soldSort
	 *			("asc"|"desc"|"no")
	 * @param page   
	 * 			(页码，为0，则结果重定向；否则为穿json数据)
	 * @return
	 * @author YP
	 */
	public List<TGoods> getAdvancedSearchGoods(String searchConditions,String priceSort,String soldSort,String page);


    /**
     * 更新商品平均评分
     * @author RichardChan
     * @param mark
     */
	public void updateAverageMark(int goodsId, double averageMark);
	

	/**
	 * 根据二级目录id获取商品列表
	 * @param secondCatalogId
	 * 			二级商品目录id
	 * @param pageNum
	 *          页面数
	 *        priceSort("asc"|"desc"|"no")
	 *        价格排序方式
	 * 		  soldSort("asc"|"desc"|"no")
	 * 		      销量排序方式
	 * @return 商品列表
	 * 			若不正常则返回空列表
	 * @author 郑天然
	 */
	public List<TGoods> getGoodListBySecondCatalogIdAndPage(Integer secondCatalogId,Integer pageNum,String priceSort, String soldSort);
	
	/**
	 * 根据一级目录id获取商品列表
	 * @param firstCatalogId
	 * 			一级商品目录id
	 * @param pageNum
	 *          页面数
	 *        priceSort("asc"|"desc"|"no")
	 *        价格排序方式
	 * 		  soldSort("asc"|"desc"|"no")
	 * 		      销量排序方式
	 * @return 商品列表
	 * 			若不正常则返回空列表
	 * @author 郑天然
	 */
	public List<TGoods> getGoodListByFirstCatalogIdAndPage(Integer firstCatalogId,Integer pageNum,String priceSort, String soldSort);

	/**
	 * 更新商品信息
	 * @author RichardChan
	 * @param tGoods
	 */
	public void update(TGoods tGoods);

	/**
	 * 获取二级目录下的所有商品
	 * @author RichardChan
	 * @param integer
	 * @return
	 */
	public List<TGoods> getGoodsBySecondCatalog(int secondCatalogId);

	/**
	 * 获取一级目录下的所有商品
	 * @author RichardChan
	 * @param firstcatalogId
	 * @return
	 */
	public List<TGoods> getGoodListByFirstCatalog(Integer firstcatalogId);
	
	
	/*
	 * 获取商品信息
	 * @author RichardChan
	 */
	public List<TGoods> getGoods(int pageNum, int secondCatalogId);

    /**
	 * 获取数据库中的所有商品对象
	 * @author RichardChan
	 * @return
	 */
	public List<TGoods> getAllGoods();
}
