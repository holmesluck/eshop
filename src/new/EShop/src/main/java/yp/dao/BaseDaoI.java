package yp.dao;

import java.io.Serializable;

/**
 * 基础数据库操作类
 * 
 * 其他DAO继承此类获取常用的数据库操作方法
 * 
 * @author 许志鹏
 * 
 * @param <T>
 *            模型
 */
public interface BaseDaoI<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(T o);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void saveOrUpdate(T o);

	/**
	 * 通过主键获得对象
	 * 
	 * @param c
	 *            类名.class
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public T findById(Class<T> c, Serializable id);
}
