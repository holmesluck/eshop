package yp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TFirstcatalogDaoI;
import yp.model.TFirstcatalog;
import yp.service.TFirstcatalogServiceI;

@Service
public class TFirstcatalogServiceImpl implements TFirstcatalogServiceI {

	private TFirstcatalogDaoI tFirstcatalogDao;

	public TFirstcatalogDaoI gettFirstcatalogDao() {
		return tFirstcatalogDao;
	}

	@Autowired
	public void settFirstcatalogDao(TFirstcatalogDaoI tFirstcatalogDao) {
		this.tFirstcatalogDao = tFirstcatalogDao;
	}

	@Override
	public List<TFirstcatalog> getFirstcatalogList() {
		// TODO Auto-generated method stub
		return tFirstcatalogDao.getFirstcatalogList();
	}

}
