package yp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import yp.dao.TSecondcatalogDaoI;
import yp.model.TFirstcatalog;
import yp.model.TSecondcatalog;

@Repository
public class TSecondcatalogDaoImpl extends BaseDaoImpl<TSecondcatalog>
		implements TSecondcatalogDaoI {

	@Override
	public TSecondcatalog getASeCatalogInfo(Integer secondcatalogId) {
		// TODO Auto-generated method stub
		TSecondcatalog secondatalog = this.findById(TSecondcatalog.class,
				secondcatalogId);
		return secondatalog;
	}

	@Override
	public boolean addASeCatalog(String catalogName, Integer firstCatalogId,
			String catalogDescription) {
		// TODO Auto-generated method stub
		// 查看是否已有同名的二级目录
		List<TSecondcatalog> catalogList = this
				.getCurrentSession()
				.createQuery(
						"FROM TSecondcatalog c WHERE c.secondcatalogName =:catalogName AND c.TFirstcatalog.firstcatalogId =:firstCatalogId")
				.setString("catalogName", catalogName)
				.setInteger("firstCatalogId", firstCatalogId).list();
		if (catalogList.size() > 0) {
			return false;
		}

		TSecondcatalog newSecondCatalog = new TSecondcatalog();
		newSecondCatalog.setSecondcatalogName(catalogName);
		TFirstcatalog firstCatalog = (TFirstcatalog) this.getCurrentSession()
				.get(TFirstcatalog.class, firstCatalogId);
		// 判断父目录是否存在
		if (firstCatalog == null) {
			return false;
		}
		newSecondCatalog.setTFirstcatalog(firstCatalog);
		// 描述为空则不插入
		if (catalogDescription != null) {
			newSecondCatalog.setSecondcatalogDescription(catalogDescription);
		}

		// 插入到数据库
		if (this.getCurrentSession().save(newSecondCatalog) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifySeCatalog(Integer seCatalogId, String catalogName,
			Integer firstCatalogId, String catalogDescription) {
		// TODO Auto-generated method stub
		TSecondcatalog secondCatalog = this.findById(TSecondcatalog.class,
				seCatalogId);
		//修改的目录不存在
		if(secondCatalog==null){
			return false;
		}
		secondCatalog.setSecondcatalogName(catalogName);
		TFirstcatalog firstCatalog = (TFirstcatalog) this.getCurrentSession().get(TFirstcatalog.class, firstCatalogId);
		//设置的父目录没找到
		if(firstCatalog==null){
			return false;
		}
		secondCatalog.setTFirstcatalog(firstCatalog);
		//加入描述
		if(catalogDescription!=null){
			secondCatalog.setSecondcatalogDescription(catalogDescription);
		}	
		this.update(secondCatalog);//更新数据库信息
		return true;
	}

	@Override
	public List<TSecondcatalog> getSecondcatalogList(TFirstcatalog tFirstcatalog) {
		// TODO Auto-generated method stub
		String hql = "from TSecondcatalog as secondCatalog where secondCatalog.TFirstcatalog=?";
		List<TSecondcatalog> secondCatalogList = getCurrentSession().createQuery(hql).setParameter(0,tFirstcatalog).list();
		if(secondCatalogList.size() < 1)
			return null;
		else
			return secondCatalogList;
	}

        @Override
	public boolean deleteSeCatalog(Integer deleteSeCatalog) {
		// TODO Auto-generated method stub
		try{
		TSecondcatalog tSecondcatalog = this.findById(TSecondcatalog.class,deleteSeCatalog);
		this.delete(tSecondcatalog);
		}
		catch(HibernateException e){
			return false;
		}
		return true;
	}

		@Override
		public List<TSecondcatalog> getSecondcatalogList(Integer firstCatalogId) {
			// TODO Auto-generated method stub
			String hql = "from TSecondcatalog as secondCatalog where secondCatalog.TFirstcatalog.firstcatalogId =:firstCatalogId";
			return this.getCurrentSession().createQuery(hql).setInteger("firstCatalogId", firstCatalogId).list();
		}

		@Override
		public boolean checkSecondCatalogName(int seCatalogId,
				String catalogName) {
			// TODO Auto-generated method stub
			String hql = "select tsc.TFirstcatalog from TSecondcatalog as tsc where tsc.secondcatalogId = ?";
			String hql1 = "from TSecondcatalog as tsc where tsc.secondcatalogId = ?";
			String hql2 = "from TSecondcatalog as tsc where tsc.TFirstcatalog = ?";
			TFirstcatalog tfc = (TFirstcatalog) getCurrentSession().createQuery(hql).setParameter(0, seCatalogId).list().get(0);
			List<TSecondcatalog> scList =  getCurrentSession().createQuery(hql2).setParameter(0, tfc).list();
			TSecondcatalog sc = (TSecondcatalog) getCurrentSession().createQuery(hql1).setParameter(0, seCatalogId).list().get(0);
			if(scList == null || scList.size() < 1){
				return false;
			}
			else
			{
				for(int i = 0; i < scList.size(); i++){
					if(scList.get(i).getSecondcatalogName().equals(sc.getSecondcatalogName()))
						continue;
					if(catalogName.equals(scList.get(i).getSecondcatalogName())){
						return false;
					}
				}
				return true;
			}
		}

		@Override
		public boolean add_checkSecondCatalogName(int fiCatalogId,
				String catalogName) {
			// TODO Auto-generated method stub
			String hql = "from TFirstcatalog as tfc where tfc.firstcatalogId = ?";
			TFirstcatalog tfc = (TFirstcatalog) getCurrentSession().createQuery(hql).setParameter(0, fiCatalogId).list().get(0);
			if(tfc == null){
				return false;
			}
			else{
				String hql2 = "from TSecondcatalog as tsc where tsc.TFirstcatalog = ?";
				List<TSecondcatalog> tsc = getCurrentSession().createQuery(hql2).setParameter(0, tfc).list();
				System.out.println("155");
				if(tsc == null || tsc.size() < 1)
					return false;
				else
				{
					for(int i = 0; i < tsc.size(); i++){
						System.out.println(tsc.get(i).getSecondcatalogName());
						System.out.println(catalogName);
						String tscName = tsc.get(i).getSecondcatalogName();
						System.out.println(tscName.equals(catalogName));
						if(tsc.get(i).getSecondcatalogName().equals(catalogName))
							return false;
					}
					System.out.println("164");
					return true;
				}
			}	
		}
}
