package yp.dao;

import java.util.List;

import yp.dao.impl.BaseDaoImpl;
import yp.model.TFirstcatalog;
import yp.model.TGoods;

public interface TFirstcatalogDaoI extends  BaseDaoI<TFirstcatalog>{
	/**
	 * 获取所有的一级目录
	 */
	public List<TFirstcatalog> getFirstcatalogList();
}
