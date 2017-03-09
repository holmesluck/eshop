package yp.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TGoodsDaoI;
import yp.dao.TSecondcatalogDaoI;
import yp.model.TGoods;
import yp.model.TSecondcatalog;

@Repository
public class TGoodsDaoImpl extends BaseDaoImpl<TGoods> implements TGoodsDaoI {
	private TSecondcatalogDaoI tSecondcatalogDao;

	public TSecondcatalogDaoI gettSecondcatalogDao() {
		return tSecondcatalogDao;
	}

	@Autowired
	public void settSecondcatalogDao(TSecondcatalogDaoI tSecondcatalogDao) {
		this.tSecondcatalogDao = tSecondcatalogDao;
	}

	@Override
	public List<TGoods> getTop3FromEachBranch() {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		/*
		 * String sql =
		 * "(SELECT * FROM cshopdb.t_goods AS goods JOIN cshopdb.t_firstcatalog AS fc USING(firstcatalog_id) WHERE fc.firstcatalog_id = 1 LIMIT 3) "
		 * +
		 * "UNION (SELECT * FROM cshopdb.t_goods AS goods JOIN cshopdb.t_firstcatalog AS fc USING(firstcatalog_id) WHERE fc.firstcatalog_id = 2 LIMIT 3) "
		 * +
		 * "UNION (SELECT * FROM cshopdb.t_goods AS goods JOIN cshopdb.t_firstcatalog AS fc USING(firstcatalog_id) WHERE fc.firstcatalog_id = 3 LIMIT 3) "
		 * +
		 * "UNION (SELECT * FROM cshopdb.t_goods AS goods JOIN cshopdb.t_firstcatalog AS fc USING(firstcatalog_id) WHERE fc.firstcatalog_id = 4 LIMIT 3) "
		 * ;
		 */
		// List<TGoods> goodsList = session.createSQLQuery(sql).list();
		String hql1 = "FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 1 AND goods.goodsIsdel = false ORDER BY goods.goodsDiscount ASC";
		List<TGoods> goodsList = session.createQuery(hql1).setFirstResult(0)
				.setMaxResults(3).list();
		String hql2 = "FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 2 AND goods.goodsIsdel = false ORDER BY goods.goodsDiscount ASC";
		List<TGoods> goodsList2 = session.createQuery(hql2).setFirstResult(0)
				.setMaxResults(3).list();
		String hql3 = "FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 3 AND goods.goodsIsdel = false ORDER BY goods.goodsDiscount ASC";
		List<TGoods> goodsList3 = session.createQuery(hql3).setFirstResult(0)
				.setMaxResults(3).list();
		String hql4 = "FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 4 AND goods.goodsIsdel = false ORDER BY goods.goodsDiscount ASC";
		List<TGoods> goodsList4 = session.createQuery(hql4).setFirstResult(0)
				.setMaxResults(3).list();
		/*
		 * +
		 * "UNION (SECECT TOP 3 * FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 1) "
		 * +
		 * "UNION (SECECT TOP 3 * FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 2) "
		 * +
		 * "UNION (SECECT TOP 3 * FROM TGoods AS goods WHERE goods.TFirstcatalog.firstcatalogId = 3)"
		 */
		goodsList.addAll(goodsList2);
		goodsList.addAll(goodsList3);
		goodsList.addAll(goodsList4);

		// session.close();
		return goodsList;
	}

	@Override
	public List<TGoods> getQuickSearchResult(String[] searchWords,
			String priceSort, String soldSort, Integer page) {
		// TODO Auto-generated method stub
		String hql = "FROM TGoods WHERE goodsIsdel = false AND goodsDescription LIKE '%"
				+ searchWords[0] + "%'";
		// String hql = "FROM TGoods WHERE goodsDescription LIKE ?";
		// 加搜索分词条件
		for (int i = 1; i < searchWords.length; i++) {
			hql += " OR goodsDescription LIKE '%" + searchWords[i] + "%'";
			// hql += " OR goodsDescription LIKE ?";
		}

		// 排序
		if (priceSort.equals("asc")) {
			hql += " ORDER BY goodsPrice ASC";
		} else if (priceSort.equals("desc")) {
			hql += " ORDER BY goodsPrice DESC";
		} else if (soldSort.equals("asc")) {
			hql += " ORDER BY goodsSales ASC";
		} else if (soldSort.equals("desc")) {
			hql += " ORDER BY goodsSales DESC";
		} else {// 默认id升序
			hql += " ORDER BY goodsId ASC";
		}
		List<TGoods> goodsList = getCurrentSession().createQuery(hql)
				.setFirstResult(page * 12).setMaxResults(12).list();
		// Query query = getCurrentSession().createQuery(hql);
		//
		// //加入参数
		// for (int i = 0; i < searchWords.length; i++) {
		// //hql += " OR goodsDescription LIKE '%" + searchWords[i] + "%'";
		// String param = "'%"+searchWords[i]+"%'";
		// try {
		// param = new String(param.getBytes("ISO8859_1"),"UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("param:"+param);
		// query.setString(i, param);
		// }
		// List<TGoods> goodsList = query.setFirstResult(page *
		// 10).setMaxResults(10).list();
		return goodsList;
	}

	@Override
	public List<TGoods> getAdvancedSearchResult(String[] conditions,
			String priceSort, String soldSort, Integer page) {
		// TODO Auto-generated method stub
		String hql = "FROM TGoods goods WHERE goodsIsdel = false";
		boolean noConditions = true;// 假设conditions中全0，默认全部搜索

		/* 添加勾选条件 */
		int i = 0;
		// 品牌
		for (; i < 4; i++) {//找到第一个品牌条件
			if(conditions[i].equals("1")){//到达品牌的第一个选中点
				// 把noConditions置false
				if (noConditions == true) {
					noConditions = false;
				}
				if(i==3){//品牌结束
					hql += " AND goods.TFirstcatalog.firstcatalogId = " + (i + 1);
				}else{//还没结束,则加上括号
					hql += " AND (goods.TFirstcatalog.firstcatalogId = " + (i + 1);
				}
				i = i+1;//i增加1
				break;
			}
		}		
		for (; i < 4; i++) {//添加第二个往后的品牌条件
			if(conditions[i].equals("1")){
				hql+=" OR goods.TFirstcatalog.firstcatalogId = " + (i + 1);
			}
			if(i==3){//品牌结束
				hql+=")";
			}
		}
		//价格
		for (; i < 8; i++) {//找到第一个价格条件
			if(conditions[i].equals("1")){//到达价格的第一个选中点
				// 把noConditions置false
				if (noConditions == true) {
					noConditions = false;
				}
				if(i==4){//价格1
					hql += " AND (goodsPrice < 3000 ";
				}else if(i==5){//价格2
					hql += " AND (goodsPrice BETWEEN 3000 AND 3999";
				}else if(i==6){//价格3
					hql += " AND (goodsPrice BETWEEN 4000 AND 5000";
				}else if(i==7){//价格4、结束
					hql += " AND goodsPrice > 5000";
				}
				i = i+1;//i增加1
				break;
			}
		}
		for (; i < 8; i++) {//添加第二个往后的价格条件
			if(conditions[i].equals("1")){
				if(i==5){//价格2
					hql += " OR goodsPrice BETWEEN 3000 AND 3999";
				}else if(i==6){//价格3
					hql += " OR goodsPrice BETWEEN 4000 AND 5000";
				}else if(i==7){//价格4、结束
					hql += " OR goodsPrice > 5000";
				}
			}
			if(i==7){//价格结束
				hql+=")";
			}
		}
		//处理器
		for (; i < 12; i++) {//找到第一个处理器条件
			if(conditions[i].equals("1")){//到达处理器的第一个选中点
				// 把noConditions置false
				if (noConditions == true) {
					noConditions = false;
				}
				if(i==8){//处理器1
					hql += " AND (goodsProcessor LIKE '%i3%'";
				}else if(i==9){//处理器2
					hql += " AND (goodsProcessor LIKE '%i5%'";
				}else if(i==10){//处理器3
					hql += " AND (goodsProcessor LIKE '%i7%'";
				}else if(i==11){//处理器4、结束
					hql += " AND goodsProcessor LIKE '%AMD%'";
				}
				i = i+1;//i增加1
				break;
			}
		}
		for (; i < 12; i++) {//添加第二个往后的处理器条件
			if(conditions[i].equals("1")){
				if(i==9){//处理器2
					hql += " OR goodsProcessor LIKE '%i5%'";
				}else if(i==10){//处理器3
					hql += " OR goodsProcessor LIKE '%i7%'";
				}else if(i==11){//处理器4、结束
					hql += " OR goodsProcessor LIKE '%AMD%'";
				}
			}
			if(i==11){//处理器结束
				hql+=")";
			}
		}
		//内存
		for (; i < 16; i++) {//找到第一个内存条件
			if(conditions[i].equals("1")){//到达内存的第一个选中点
				// 把noConditions置false
				if (noConditions == true) {
					noConditions = false;
				}
				if(i==12){//内存1
					hql += " AND (goodsMemory LIKE '1GB%'";
				}else if(i==13){//内存2
					hql += " AND (goodsMemory LIKE '2GB%'";
				}else if(i==14){//内存3
					hql += " AND (goodsMemory LIKE '4GB%'";
				}else if(i==15){//内存4、结束
					hql += " AND goodsMemory LIKE '8GB%'";
				}
				i = i+1;//i增加1
				break;
			}
		}
		for (; i < 16; i++) {//添加第二个往后的处理器条件
			if(conditions[i].equals("1")){
				if(i==13){//内存2
					hql += " OR goodsMemory LIKE '2GB%'";
				}else if(i==14){//内存3
					hql += " OR goodsMemory LIKE '4GB%'";
				}else if(i==15){//内存4、结束
					hql += " OR goodsMemory LIKE '8GB%'";
				}
			}
			if(i==15){//内存结束
				hql+=")";
			}
		}
		
/*		
		// 添加勾选conditinos
		for (int i = 0; i < conditions.length; i++) {
			if (conditions[i].equals("1")) {// 条件选中
				// 把noConditions置false
				if (noConditions == true) {
					noConditions = false;
				}
				// 添加条件
				if (i < 4) {// 品牌
					hql += " OR goods.TFirstcatalog.firstcatalogId = "
							+ (i + 1);
				} else if (i == 4) {// 价格
					hql += " OR goodsPrice < 3000";
				} else if (i == 5) {
					hql += " OR goodsPrice BETWEEN 3000 AND 3999";
				} else if (i == 6) {
					hql += " OR goodsPrice BETWEEN 4000 AND 4999";
				} else if (i == 7) {
					hql += " OR goodsPrice > 5000";
				} else if (i == 8) {// cpu
					hql += " OR goodsProcessor LIKE '%i3%'";
				} else if (i == 9) {
					hql += " OR goodsProcessor LIKE '%i5%'";
				} else if (i == 10) {
					hql += " OR goodsProcessor LIKE '%i7%'";
				} else if (i == 11) {
					hql += " OR goodsProcessor LIKE '%AMD%'";
				} else if (i == 12) {// 内存大小
					hql += " OR goodsMemory LIKE '1GB%'";
				} else if (i == 13) {
					hql += " OR goodsMemory LIKE '2GB%'";
				} else if (i == 14) {
					hql += " OR goodsMemory LIKE '4GB%'";
				} else if (i == 15) {
					hql += " OR goodsMemory LIKE '8GB%'";
				}
			}
		}
*/
		
		// 加入条件全空（为0）,则默认为全部搜索
		if (noConditions == true) {
			hql = new String("FROM TGoods goods WHERE goods.goodsIsdel = false");
		}

		// 添加排序条件
		if (priceSort.equals("asc")) {
			hql += " ORDER BY goodsPrice ASC";
		} else if (priceSort.equals("desc")) {
			hql += " ORDER BY goodsPrice DESC";
		} else if (soldSort.equals("asc")) {
			hql += " ORDER BY goodsSales ASC";
		} else if (soldSort.equals("desc")) {
			hql += " ORDER BY goodsSales DESC";
		} else {// 默认id升序
			hql += " ORDER BY goodsId ASC";
		}
		List<TGoods> goodsList = getCurrentSession().createQuery(hql)
				.setFirstResult(page * 12).setMaxResults(12).list();
		return goodsList;
	}

	@Override
	public List<TGoods> getGoodListBySecondCatalogId(Integer secondCatalogId,String priceSort, String soldSort) {
		// TODO Auto-generated method stub
		String hql = "from TGoods tgoods where tgoods.TSecondcatalog.secondcatalogId =:secondCatalogId and tgoods.goodsIsdel=false";
		
		//决定排序顺序
		if (priceSort.equals("asc")) {
			hql += " ORDER BY goodsPrice ASC";
		} else if (priceSort.equals("desc")) {
			hql += " ORDER BY goodsPrice DESC";
		} else if (soldSort.equals("asc")) {
			hql += " ORDER BY goodsSales ASC";
		} else if (soldSort.equals("desc")) {
			hql += " ORDER BY goodsSales DESC";
		} else {// 默认id升序
			hql += " ORDER BY goodsId ASC";
		}
		
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("secondCatalogId", secondCatalogId);
		List<TGoods> goodsList;
		goodsList = query.list();
		return goodsList;
	}

	@Override
	public List<TGoods> getGoodListByFirstCatalogId(Integer firstCatalogId,String priceSort, String soldSort) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String hql = "from TGoods tgoods where tgoods.TFirstcatalog.firstcatalogId =:firstCatalogId and tgoods.goodsIsdel=false";
		
		//决定排序顺序
				if (priceSort.equals("asc")) {
					hql += " ORDER BY goodsPrice ASC";
				} else if (priceSort.equals("desc")) {
					hql += " ORDER BY goodsPrice DESC";
				} else if (soldSort.equals("asc")) {
					hql += " ORDER BY goodsSales ASC";
				} else if (soldSort.equals("desc")) {
					hql += " ORDER BY goodsSales DESC";
				} else {// 默认id升序
					hql += " ORDER BY goodsId ASC";
				}
				
				
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("firstCatalogId", firstCatalogId);
		List<TGoods> goodsList;

		goodsList = query.list();

		return goodsList;
	}

	@Override
	public List<TGoods> getGoods(int pageNum, int secondcatalogId) {
		// TODO Auto-generated method stub
		TSecondcatalog secondCatalog = tSecondcatalogDao.findById(TSecondcatalog.class, secondcatalogId);
		String hql = "from TGoods as goods where goods.TSecondcatalog = ?";
		List<TGoods> goodsList = getCurrentSession().createQuery(hql).setParameter(0, secondCatalog).setFirstResult(pageNum * 10).setMaxResults(pageNum).list();
		if(goodsList.size() < 1)
			return null;
		else
			return goodsList;
	}

	@Override
	public List<TGoods> getAllGoods() {
		// TODO Auto-generated method stub
		String hql = "from TGoods";
		List<TGoods> goods = getCurrentSession().createQuery(hql).list();
		if(goods.size() < 1)
			return null;
		else
			return goods;
	}

	@Override
	public List<TGoods> getGoodListByFirstCatalogId(Integer firstcatalogId) {
		// TODO Auto-generated method stub
		String hql = "from TGoods tgoods where tgoods.TFirstcatalog.firstcatalogId=?";
		List<TGoods> goodsList = getCurrentSession().createQuery(hql).setParameter(0, firstcatalogId).list();
		return goodsList;
	}

	@Override
	public List<TGoods> getGoodListBySecondCatalogId(int secondCatalogId) {
		// TODO Auto-generated method stub
		String hql = "from TGoods tgoods where tgoods.TSecondcatalog.secondcatalogId=?";
		List<TGoods> goodsList = getCurrentSession().createQuery(hql).setParameter(0, secondCatalogId).list();
		return goodsList;
	}

}
