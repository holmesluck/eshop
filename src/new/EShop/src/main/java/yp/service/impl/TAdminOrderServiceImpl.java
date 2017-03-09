package yp.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yp.dao.TOrderlistDaoI;
import yp.model.TOrderlist;
import yp.service.TAdminOrderServiceI;

@Service
public class TAdminOrderServiceImpl implements TAdminOrderServiceI{

	TOrderlistDaoI tOrderlistDao;
	
	
	
	public TOrderlistDaoI gettOrderlistDao() {
		return tOrderlistDao;
	}

	@Autowired
	public void settOrderlistDao(TOrderlistDaoI tOrderlistDao) {
		this.tOrderlistDao = tOrderlistDao;
	}

	@Override
	public boolean orderConfirm(Integer orderId) {
		// TODO Auto-generated method stub
		if(tOrderlistDao.modifyConfirmStateById(orderId))
			return true;
		else
			return false;
	}

	@Override
	public List<TOrderlist> getOrderList(String strOrderId, String strUserId,
			String strOrderState, String strBeginDate, String strEndDate,
			Integer page) {
		// TODO Auto-generated method stub
		Timestamp beginDate = null;
		Timestamp endDate = null;
		Timestamp recentDate = null;
		Integer orderId = null;
		Integer userId = null;
		Integer orderState = null;
		//转换日期格式并判断是否为空
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(strBeginDate!=null &&!strBeginDate.isEmpty()&&strBeginDate!="" )
			try {
				beginDate = new Timestamp(sdf.parse(strBeginDate).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("timestamp format transmate error ");
				return null;
			}
		else
			beginDate=null;
		
		if(strEndDate!=null&&!strEndDate.isEmpty()&& strEndDate!="" )
			try {
				endDate = new Timestamp(sdf.parse(strEndDate).getTime()+24*60*60*1000);
				System.out.println("啦啦啦啦 ：endDate :" + endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		else
			endDate = null;
		//转换id数据类型 ，并判断是否为空
		if(strOrderId != null&&strOrderId!="")
			orderId=Integer.valueOf(strOrderId);
		if(strUserId != null && strUserId!="")
			userId = Integer.valueOf(strUserId);
		if(strOrderState != null && strOrderState!="")
			orderState = Integer.valueOf(strOrderState);
		
		//获得90天之前的时间
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -90);
		recentDate = new Timestamp(date.getTimeInMillis());
		System.out.println(recentDate);
		System.out.println("beginDate:  " +beginDate);
		System.out.println("endDate:  " + endDate);
		
		return tOrderlistDao.findOrderlist(orderId, userId, orderState, beginDate, endDate, page,recentDate);
		
	}


	

}
