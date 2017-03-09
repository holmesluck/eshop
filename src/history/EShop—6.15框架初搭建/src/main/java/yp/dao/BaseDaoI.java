package yp.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDaoI<T> {
	//public boolean addUser();
	/**
	 * 保存一个对象
	 */
	public Serializable save(T object);
	
	/**
	 * 查询
	 */
	public List<T> find(String hql);
}
