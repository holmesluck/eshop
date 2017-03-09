package yp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TSecondcatalogDaoI;
import yp.model.TFirstcatalog;
import yp.model.TSecondcatalog;
import yp.service.TSecondcatalogServiceI;

@Service
public class TSecondcatalogServiceImpl implements TSecondcatalogServiceI {

	private TSecondcatalogDaoI secondcatalogDao;
	
	public TSecondcatalogDaoI getSecondcatalogDao() {
		return secondcatalogDao;
	}

	@Autowired
	public void setSecondcatalogDao(TSecondcatalogDaoI secondcatalogDao) {
		this.secondcatalogDao = secondcatalogDao;
	}
	
	@Override
	public TSecondcatalog getASeCatalogInfo(Integer secondcatalogId) {
		// TODO Auto-generated method stub
		return secondcatalogDao.getASeCatalogInfo(secondcatalogId);
	}

	@Override
	public boolean addASeCatalog(String catalogName, Integer firstCatalogId,
			String catalogDescription) {
		// TODO Auto-generated method stub
		return secondcatalogDao.addASeCatalog(catalogName, firstCatalogId, catalogDescription);
	}

	@Override
	public boolean modifySeCatalog(Integer seCatalogId, String catalogName,
			Integer firstCatalogId, String catalogDescription) {
		// TODO Auto-generated method stub		
		return secondcatalogDao.modifySeCatalog(seCatalogId, catalogName, firstCatalogId, catalogDescription);
	}

	@Override
	public List<TSecondcatalog> getSecondcatalogList(TFirstcatalog tFirstcatalog) {
		// TODO Auto-generated method stub
		return secondcatalogDao.getSecondcatalogList(tFirstcatalog);
	}

        @Override
	public boolean deleteSeCatalog(Integer deleteSeCatalog) {
		// TODO Auto-generated method stub
		return secondcatalogDao.deleteSeCatalog(deleteSeCatalog);
	}

		@Override
		public List<TSecondcatalog> getSecondcatalogList(Integer firstCatalogId) {
			// TODO Auto-generated method stub
			return secondcatalogDao.getSecondcatalogList(firstCatalogId);
		}

		@Override
		public boolean checkSecondCatalogName(int seCatalogId,
				String catalogName) {
			// TODO Auto-generated method stub
			return secondcatalogDao.checkSecondCatalogName(seCatalogId, catalogName);
		}

		@Override
		public boolean add_checkSecondCatalogName(int fiCatalogId,
				String catalogName) {
			// TODO Auto-generated method stub
			return secondcatalogDao.add_checkSecondCatalogName(fiCatalogId, catalogName);
		}

}
