package yp.model.view;

import java.sql.Timestamp;

import yp.model.TAddress;
import yp.model.TUser;

/**
 * 用于显示订单列表，不含关联模型，用作json对象视图
 * 
 * 
 * @author yu
 *
 */
public class OrderlistJsonView {
	
	private Integer orderlistId;
	private Integer userId;
	private Timestamp orderlistOrderdate;
	private Integer orderlistStatus;
	private Double orderlistTotalprice;
	
	public Integer getOrderlistId() {
		return orderlistId;
	}
	public void setOrderlistId(Integer orderlistId) {
		this.orderlistId = orderlistId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getOrderlistOrderdate() {
		return orderlistOrderdate;
	}
	public void setOrderlistOrderdate(Timestamp orderlistOrderdate) {
		this.orderlistOrderdate = orderlistOrderdate;
	}
	public Integer getOrderlistStatus() {
		return orderlistStatus;
	}
	public void setOrderlistStatus(Integer orderlistStatus) {
		this.orderlistStatus = orderlistStatus;
	}
	public Double getOrderlistTotalprice() {
		return orderlistTotalprice;
	}
	public void setOrderlistTotalprice(Double orderlistTotalprice) {
		this.orderlistTotalprice = orderlistTotalprice;
	}


}
