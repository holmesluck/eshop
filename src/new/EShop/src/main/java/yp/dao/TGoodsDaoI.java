package yp.dao;

import java.util.List;

import yp.model.TGoods;

public interface TGoodsDaoI extends BaseDaoI<TGoods>{
	/**
	 * 获取每个品牌的三个商品
	 * @author YP
	 */
	public List<TGoods> getTop3FromEachBranch();
	
	/**
	 * 快速搜索，输入值有：
	 * @param searchWords
	 * 			String[]
	 * @param priceSort
	 * 			String:"asc"|"desc"|"no"
	 * @param soldSort
	 * 			String:"asc"|"desc"|"no"
	 * 
	 * @return List<TGoods>
	 * @author YP
	 */
	public List<TGoods> getQuickSearchResult(String[] searchWords,String priceSort,String soldSort,Integer page);

	/**
	 * 高级搜索商品。输入值有：
	 * 16个checkbox选值(以字符0.1串中间逗号形式)
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
	 * 			(页码，为0，则结果重定向；否则为传json数据)
	 * @return
	 * @author YP
	 */
	public List<TGoods> getAdvancedSearchResult(String[] conditions,String priceSort,String soldSort,Integer page);

	/**
	 * 根据二级目录id获取商品列表
	 * @param secondCatalogId
	 * 			二级商品目录id
	 * 		   priceSort("asc"|"desc"|"no")
	 *        价格排序方式
	 * 		  soldSort("asc"|"desc"|"no")
	 * 		      销量排序方式
	 * @return 商品列表
	 * @author 郑天然
	 */
	public List<TGoods> getGoodListBySecondCatalogId(Integer secondCatalogId,String priceSort, String soldSort);
	
	/**
	 * 根据一级目录id获取商品列表
	 * @paramfirstCatalogId
	 * 			一级商品目录id
	 * 			 priceSort("asc"|"desc"|"no")
	 *        价格排序方式
	 * 		  soldSort("asc"|"desc"|"no")
	 * 		      销量排序方式
	 * @return 商品列表
	 * @author 郑天然
	 */
	public List<TGoods> getGoodListByFirstCatalogId(Integer firstCatalogId,String priceSort, String soldSort);
	

	/*
	 * 获取商品信息
	 * @author RichardChan
	 */
	public List<TGoods> getGoods(int pageNum, int secondcatalogId);

	/**
	 * 获取数据库中的所有商品对象
	 * @author RichardChan
	 * @return
	 */
	public List<TGoods> getAllGoods();

	/**
	 * 获取一级目录下的商品
	 * @author RichardChan
	 * @param firstcatalogId
	 * @return
	 */
	public List<TGoods> getGoodListByFirstCatalogId(Integer firstcatalogId);

	/**
	 * 获取二级目录中的商品
	 * @author RichardChan
	 * @param secondCatalogId
	 * @return
	 */
	public List<TGoods> getGoodListBySecondCatalogId(int secondCatalogId);
}
