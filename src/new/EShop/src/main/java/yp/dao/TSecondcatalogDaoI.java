package yp.dao;

import java.util.List;

import yp.model.TFirstcatalog;
import yp.model.TSecondcatalog;

public interface TSecondcatalogDaoI extends BaseDaoI<TSecondcatalog>{
	/**
	 * 获取某一二级目录的详细信息
	 * @param secondcatalogId
	 * 			二级目录id
	 * @return TSecondcatalog
	 * 				二级目录对象
	 * @author YP
	 */
	public TSecondcatalog getASeCatalogInfo(Integer secondcatalogId);
	
	/**
	 * 添加新二级目录
	 * @param catalogName
	 * 			目录名称
	 * @param firstCatalogId
	 * 			其父目录id
	 * @param catalogDescription
	 * 			目录描述
	 * @return boolean
	 * 			是否成功
	 * @author YP
	 */
	public boolean addASeCatalog(String catalogName,Integer firstCatalogId,String catalogDescription);
	
	/**
	 * 管理员修改二级目录
	 * @param seCatalogId
	 * 			二级目录id
	 * @param catalogName
	 * 			二级目录名字
	 * @param firstCatalogId
	 * 			父目录id
	 * @param catalogDescription
	 * 			目录描述
	 * @return
	 */
	public boolean modifySeCatalog(Integer seCatalogId,String catalogName,Integer firstCatalogId,String catalogDescription);

	
	/**
	 * 管理员删除二级目录
	 * @param seCatalogId
	 * 			二级目录id
	 * @return boolean
	 * 			成功或失败
	 * @author 郑天然
	 */
	public boolean deleteSeCatalog(Integer deleteSeCatalog);

	/**
	 * 获取一级目录对应的二级目录
	 * @author RichardChan
	 * @param tFirstcatalog
	 * @return
	 */
	public List<TSecondcatalog> getSecondcatalogList(TFirstcatalog tFirstcatalog);
	
	/**
	 * 根据一级目录id获取二级目录列表
	 * @param firstCatalogId
	 * 			一级目录id
	 * 			
	 * @return List<TSecondcatalog>
	 * 
	 * @author YP
	 */
	public List<TSecondcatalog> getSecondcatalogList(Integer firstCatalogId);
	
	/**
	 * 验证修改后的目录名是否和一级目录下其他系列名重名
	 * @author RichardChan
	 * @param seCatalogId
	 * @param catalogName
	 * @return
	 */
	public boolean checkSecondCatalogName(int seCatalogId, String catalogName);

	/**
	 * 添加新目录时验证目录名是否和一级目录下其他系列名重名
	 * @author RichardChan
	 * @param fiCatalogId
	 * @param catalogName
	 * @return
	 */
	public boolean add_checkSecondCatalogName(int fiCatalogId,
			String catalogName);
}
