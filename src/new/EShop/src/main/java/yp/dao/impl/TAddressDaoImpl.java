package yp.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yp.dao.TAddressDaoI;
import yp.model.TAddress;
import yp.model.TUser;
import yp.model.TFavoritycontent;

@Repository
public class TAddressDaoImpl extends BaseDaoImpl<TAddress> implements TAddressDaoI {


	@Override
	public List<TAddress> findAllEntity(Integer userId) {
		// TODO Auto-generated method stub
		/*Session session = getSessionFactory().openSession();
		//
		List<TAddress> list = session.createQuery(
				"from TAddress a where a.TUser.userId=?").setInteger(0, userId).list();
		session.close();
		return list;*/
		TAddress tAddress;
		Query query = this.getCurrentSession().createQuery("from TAddress a where a.TUser.userId=:userId and a.addressIsdel=false");
		query.setParameter("userId", userId);
		//把addressIsdel为true的东西删除
		List<TAddress> tAddressList = query.list();
		/*Iterator iterate = tAddressList.iterator();
		while(iterate.hasNext()){
			tAddress = (TAddress)iterate.next();
			if(tAddress.getAddressIsdel()){
				iterate.remove();
			}
		}*/
		return tAddressList;
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer deleteById(Integer addressId) {
		// TODO Auto-generated method stub
		try{
		TAddress tAddress = this.findById(TAddress.class, addressId);
		tAddress.setAddressIsdel(true);
		tAddress.setAddressIsdefault(false);
		this.getCurrentSession().update(tAddress);
		}
		catch(HibernateException e){
			return 0;
		}
		//Query query = this.getCurrentSession().createQuery("delete TAddress a where a.addressId=:addressId");
		//query.setParameter("addressId",addressId);
		//return query.executeUpdate();
		return 1;
	}

	@Override
	public boolean modifyById(Integer addressId, String addressLinkman,
			String addressAddress, String addressPhone, String addressPostcode) {
		// TODO Auto-generated method stub
		try{
		//找到对应地址
		TAddress tAddress =findById(TAddress.class,addressId);
		if(tAddress!=null){
			//假如地址已被删除
			if(tAddress.getAddressIsdel())
				return false;
			//修改地址
			tAddress.setAddressLinkman(addressLinkman);
			tAddress.setAddressPhone(addressPhone);
			tAddress.setAddressPostcode(addressPostcode);
			tAddress.setAddressAddress(addressAddress);
			//update
			this.getCurrentSession().update(tAddress);
			}
		else
			return false;
		}
		catch(HibernateException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean addAddress(TUser tUser,String addressLinkman, String addressAddress,
			String addressPhone, String addressPostcode) {
		// TODO Auto-generated method stub
		
		TAddress tAddress = new TAddress(addressAddress,addressPostcode,addressPhone,addressLinkman);
		tAddress.setTUser(tUser);
		tAddress.setAddressIsdefault(false);
		tAddress.setAddressIsdel(false);
		try{
		this.getCurrentSession().save(tAddress);
		}
		catch(HibernateException e){
			return false;
		}
		return true;
	}

	@Override
	public List<TAddress> getAddressList(Integer userId) {
		// TODO Auto-generated method stub
		//Session session = this.sessionFactory.openSession();
		TAddress tAddress;
		Query query = this.getCurrentSession().createQuery("from TAddress tAddress where tAddress.TUser.userId=:userId and tAddress.addressIsdel=false");
		query.setParameter("userId", userId);
		//把addressIsdel为true的东西删除
		List<TAddress> tAddressList = query.list();
		/*Iterator iterate = tAddressList.iterator();
		while(iterate.hasNext()){
			tAddress = (TAddress)iterate.next();
			if(tAddress.getAddressIsdel()){
				iterate.remove();
			}
		}*/
		return tAddressList;
		//session.close();
	}

	@Override
	public boolean updateDefaultAddress(Integer addressId) {
		// TODO Auto-generated method stub
		try{
			//设置新默认地址
			TAddress tAddress =findById(TAddress.class,addressId);
			if(tAddress!=null){
				//若该地址已被删除
				if(tAddress.getAddressIsdel())
					return false;
				//找到原默认地址
				TAddress tOldAddress;
				Iterator iter;
				Query query = this.getCurrentSession().createQuery("from TAddress where addressIsdefault = 1");
				iter = query.iterate();
				//修改原默认地址
				while(iter.hasNext()){
					tOldAddress=(TAddress)iter.next();
					tOldAddress.setAddressIsdefault(false);
					this.getCurrentSession().update(tOldAddress);
					}
				tAddress.setAddressIsdefault(true);
				this.getCurrentSession().update(tAddress);
			}
			else{
				return false;
			}
			
		}
		catch(HibernateException e){
			return false;
		}
		return true;
	}

	@Override
	public TAddress getUserDefaultAddress(TUser user) {
		// TODO Auto-generated method stub
		String hql = "from TAddress as address where address.TUser = ? and addressIsdefault = ?";
		TAddress address = (TAddress) getCurrentSession().createQuery(hql).setParameter(0, user).setParameter(1, true).list().get(0);
		return address;		
	}
	
}
