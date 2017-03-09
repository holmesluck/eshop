package yp.service;

import java.util.Date;
import java.util.List;

import yp.model.TOrderlist;

public interface TAdminOrderServiceI {

	
	public boolean orderConfirm(Integer orderId);
	public List<TOrderlist> getOrderList(String strOrderId, String strUserId, String strOrderState, String strBeginDate, String strEndDate, Integer page);
}
