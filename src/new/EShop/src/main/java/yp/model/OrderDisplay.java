package yp.model;

import java.util.List;

/**
 * 一个用于数据存储的类，用来存储要显示的订单表
 * @author RichardChan
 *
 */
public class OrderDisplay {

	//订单信息
	String orderlistId;
	String orderlistOrderDate;
	String orderlistTotalPrice;
	int orderlistStatus;
	//地址信息
	String address;
	String phoneNumber;
	String linkMan;
	//商品详情
	
	List<Integer> goodsId;
	List<String> goodsImage;
	List<String> goodsName;
	List<Integer> amount;
	List<Double> goodsOriginalPrice;
	List<Double> goodsPrice;
	List<Double> goodsTotalPrice;
	
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public List<String> getGoodsName() {
		return goodsName;
	}
	public List<Double> getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}
	public List<Double> getGoodsPrice() {
		return goodsPrice;
	}
	public List<Double> getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public List<Integer> getAmount() {
		return amount;
	}
	public List<Integer> getGoodsId() {
		return goodsId;
	}
	public List<String> getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(List<String> goodsImage) {
		this.goodsImage = goodsImage;
	}
	public void setGoodsName(List<String> goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsOriginalPrice(List<Double> goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}
	public void setGoodsPrice(List<Double> goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public void setGoodsTotalPrice(List<Double> goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public void setAmount(List<Integer> amount) {
		this.amount = amount;
	}
	public void setGoodsId(List<Integer> goodsId) {
		this.goodsId = goodsId;
	}

	public String getOrderlistId() {
		return orderlistId;
	}
	public void setOrderlistId(String orderlistId) {
		this.orderlistId = orderlistId;
	}
	public String getOrderlistOrderDate() {
		return orderlistOrderDate;
	}
	public void setOrderlistOrderDate(String orderlistOrderDate) {
		this.orderlistOrderDate = orderlistOrderDate;
	}
	public String getOrderlistTotalPrice() {
		return orderlistTotalPrice;
	}
	public void setOrderlistTotalPrice(String orderlistTotalPrice) {
		this.orderlistTotalPrice = orderlistTotalPrice;
	}
	public int getOrderlistStatus() {
		return orderlistStatus;
	}
	public void setOrderlistStatus(int orderlistStatus) {
		this.orderlistStatus = orderlistStatus;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public OrderDisplay(){}
	public OrderDisplay(String orderlistId, String orderlistOrderDate,
			String orderlistTotalPrice, int orderlistStatus, String address,
			String phoneNumber, String linkMan, List<String> goodsName,
			List<Double> goodsOriginalPrice, List<Double> goodsPrice,
			List<Double> goodsTotalPrice, List<Integer> amount,
			List<Integer> goodsId, List<String> goodsImage) {
		super();
		this.orderlistId = orderlistId;
		this.orderlistOrderDate = orderlistOrderDate;
		this.orderlistTotalPrice = orderlistTotalPrice;
		this.orderlistStatus = orderlistStatus;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.linkMan = linkMan;
		this.goodsName = goodsName;
		this.goodsOriginalPrice = goodsOriginalPrice;
		this.goodsPrice = goodsPrice;
		this.goodsTotalPrice = goodsTotalPrice;
		this.amount = amount;
		this.goodsId = goodsId;
		this.goodsImage = goodsImage;
	}
	


}
