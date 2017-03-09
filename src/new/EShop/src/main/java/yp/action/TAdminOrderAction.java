package yp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.model.OrderDisplay;
import yp.model.TAddress;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;
import yp.model.view.OrderlistJsonView;
import yp.service.TAdminOrderServiceI;
import yp.service.TOrderlistServiceI;
import yp.service.TOrderlistcontentServiceI;

import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("cshop-package")
@Namespace("/")
public class TAdminOrderAction extends ActionSupport{
	private List<TOrderlist> orderList;
	private List<OrderlistJsonView> orderlistJsonView;
	private TAdminOrderServiceI adminOrderServiceI;
	private Integer orderlistId = null;
	private List<OrderDisplay> orderlistDisplay;   //json返回，显示用户订单
	private OrderDisplay orderDisplay;
	
	private TOrderlistServiceI tOrderlistService;
	private TOrderlistcontentServiceI tOrderlistcontentService ;
	
	
	
	public List<OrderDisplay> getOrderlistDisplay() {
		return orderlistDisplay;
	}
	public void setOrderlistDisplay(List<OrderDisplay> orderlistDisplay) {
		this.orderlistDisplay = orderlistDisplay;
	}
	public OrderDisplay getOrderDisplay() {
		return orderDisplay;
	}
	public void setOrderDisplay(OrderDisplay orderDisplay) {
		this.orderDisplay = orderDisplay;
	}
	public TOrderlistcontentServiceI gettOrderlistcontentService() {
		return tOrderlistcontentService;
	}
	@Autowired
	public void settOrderlistcontentService(
			TOrderlistcontentServiceI tOrderlistcontentService) {
		this.tOrderlistcontentService = tOrderlistcontentService;
	}
	public TOrderlistServiceI gettOrderlistService() {
		return tOrderlistService;
	}
	@Autowired
	public void settOrderlistService(TOrderlistServiceI tOrderlistService) {
		this.tOrderlistService = tOrderlistService;
	}
	public Integer getOrderlistId() {
		return orderlistId;
	}
	public void setOrderlistId(Integer orderlistId) {
		this.orderlistId = orderlistId;
	}
	public List<TOrderlist> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<TOrderlist> orderList) {
		this.orderList = orderList;
	}
	public List<OrderlistJsonView> getOrderlistJsonView() {
		return orderlistJsonView;
	}
	public void setOrderlistJsonView(List<OrderlistJsonView> orderlistJsonView) {
		this.orderlistJsonView = orderlistJsonView;
	}
	public TAdminOrderServiceI getAdminOrderServiceI() {
		return adminOrderServiceI;
	}
	@Autowired
	public void setAdminOrderServiceI(TAdminOrderServiceI adminOrderServiceI) {
		this.adminOrderServiceI = adminOrderServiceI;
	}
	
	/**
	 * 管理员审核未发货的订单
	 * @return
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "adminConfirmOrderList",results = {
			@Result(name = "success",type="chain",location = "adminShowOrderList")})
	public String adminConfirmOrderList() throws AdminException{
		String strOrderList = ServletActionContext.getRequest().getParameter("strOrderList");//得到复选框选中的订单号
		String [] strList = strOrderList.split(",");
		
		for(int i=0;i<strList.length;i++){
			if(!adminOrderServiceI.orderConfirm(Integer.parseInt(strList[i]))){
				//抛出异常
				AdminException exception=new AdminException("啊咧类，出错了哦");
	        	exception.setExceptionLocation("TAdminOrderAction:adminConfirmOrderList");
	        	exception.setExceptionType(CShopExceptionI.DATABASE);
	        	throw exception;
			}
		}
		
		return SUCCESS;
		
	}
	/**
	 * 管理员查询订单
	 * 输入值有：
	 * 订单号
	 * 会员号
	 * 发货状态
	 * 起始时间  Date型
	 * 结束时间  Date型
	 * @return
	 * @author yu
	 */
	@Action(value = "adminShowOrderList",results = {
			@Result(name = "success",location = "/webpages/back/ordermanagement/ordermanage.jsp"),
			@Result(name = "jsonResult", type = "json", params = { "root","orderlistJsonView" })})
	public String adminShowOrderList(){
		//获取输入值
		String strOrderId = ServletActionContext.getRequest().getParameter("orderId");
		String strUserId = ServletActionContext.getRequest().getParameter("userId");
		String strOrderState = ServletActionContext.getRequest().getParameter("orderState");
		String strBeginDate = ServletActionContext.getRequest().getParameter("beginDate");
		String strEndDate =  ServletActionContext.getRequest().getParameter("endDate");
		System.out.println("strBeginDate   " +strBeginDate);
		System.out.println("strEndDate   " +strEndDate);
		
		System.out.println("orderState   " +strOrderState);
		
		String strPage =  ServletActionContext.getRequest().getParameter("page");	
		System.out.println("page   " +strPage);	
		Integer page = null; //初始化为null,默认为重定向页面
		if(strPage!=null)
			page = Integer.valueOf(strPage);
		if(page==null){
			orderList = adminOrderServiceI.getOrderList(strOrderId,strUserId,strOrderState,strBeginDate,strEndDate,0);
			System.out.println(orderList);
			//测试orderList
			for(int i=0;i<orderList.size();i++){
				System.out.println("orderListId:  " + orderList.get(i).getOrderlistId());
				System.out.println("orderState:  " + orderList.get(i).getOrderlistStatus());
			}
			
			return "success";
		}
		
		else if(page>=0){
			orderList = adminOrderServiceI.getOrderList(strOrderId,strUserId,strOrderState,strBeginDate,strEndDate,page);
			System.out.println(orderList);
			orderlistJsonView = new ArrayList<OrderlistJsonView>();
			if(orderList!=null){
				System.out.println("查到订单");
				for(int i=0;i<orderList.size();i++){
				OrderlistJsonView orderJsonView = new OrderlistJsonView();
				orderJsonView.setOrderlistId(orderList.get(i).getOrderlistId());
				orderJsonView.setOrderlistOrderdate(orderList.get(i).getOrderlistOrderdate());
				orderJsonView.setUserId(orderList.get(i).getTUser().getUserId());
				orderJsonView.setOrderlistStatus(orderList.get(i).getOrderlistStatus());
				orderJsonView.setOrderlistTotalprice(orderList.get(i).getOrderlistTotalprice());
				orderlistJsonView.add(orderJsonView);
				}
			}else
				System.out.println("没有查到订单");
			System.out.println(orderlistJsonView);
	
	    
			return "jsonResult";
			//json返回			
		}
		
		return null;
		
	}
	/**
	 * 复用荣强的userQueryTransactionInfo.action
	 * @param   orderlistId(要显示详情的订单id)
	 * @author yu
	 * @return
	 * 
	 */
	@Action(value = "adminShowOrderDetail",results = {
			@Result(name = "success",location = "/webpages/back/ordermanagement/orderdetailsview.jsp")
			})
	public String adminShowOrderDetail(){
		int orderlistId = Integer.parseInt(ServletActionContext.getRequest().getParameter("orderlistId"));
		//如果管理员页面调用action,传入一个fromAdmin ,值为1
		String fromAdmin = null;
		fromAdmin = ServletActionContext.getRequest().getParameter("fromAdmin");
		//int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userId").toString());
		int userId = 1;
		//获取订单详情
		TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
		userId = orderlist.getTUser().getUserId();
		//userId = orderList.get(0).getTUser().getUserId();
		System.out.println("坑出翔:" + userId);
		List<TOrderlistcontent> orderlistcontent =  tOrderlistcontentService.getOrderlistContent(orderlist);
		System.out.println(orderlistId + "订单中共有商品:" + orderlistcontent.size());
		String OrderlistId = orderlist.getOrderlistId().toString();
		String orderlistOrderDate = orderlist.getOrderlistOrderdate().toString();
		String orderlistTotalPrice = orderlist.getOrderlistTotalprice().toString();
		int orderlistStatus = orderlist.getOrderlistStatus();
		TAddress address = orderlist.getTAddress();
		//创建显示OrderlistView
		List<String> goodsName = new ArrayList<String>();
		List<Integer> amount = new ArrayList<Integer>();
		List<Double> goodsOriginalPrice = new ArrayList<Double>();
		List<Double> goodsPrice = new ArrayList<Double>();
		List<Double> goodsTotalPrice = new ArrayList<Double>();
		List<Integer> goodsId = new ArrayList<Integer>();
		for(int j = 0; j < orderlistcontent.size(); j++){
			goodsName.add(orderlistcontent.get(j).getTGoods().getGoodsName());
			amount.add(orderlistcontent.get(j).getOrderlistcontentNum());
			goodsOriginalPrice.add(orderlistcontent.get(j).getOrderlistcontentOriginalprice());
			goodsPrice.add(orderlistcontent.get(j).getOrderlistcontentPrice());
			goodsTotalPrice.add(orderlistcontent.get(j).getOrderlistcontentTotalprice());
			goodsId.add(orderlistcontent.get(j).getTGoods().getGoodsId());
		 }
		OrderDisplay transactionDisplay = new OrderDisplay();
		transactionDisplay.setGoodsName(goodsName);
		transactionDisplay.setAmount(amount);
		transactionDisplay.setOrderlistId(OrderlistId);
		transactionDisplay.setOrderlistOrderDate(orderlistOrderDate);
		transactionDisplay.setOrderlistStatus(orderlistStatus);
		transactionDisplay.setOrderlistTotalPrice(orderlistTotalPrice);
		transactionDisplay.setGoodsOriginalPrice(goodsOriginalPrice);
		transactionDisplay.setGoodsPrice(goodsPrice);
		transactionDisplay.setGoodsTotalPrice(goodsTotalPrice);
		setOrderDisplay(transactionDisplay);
		orderlistDisplay = new ArrayList<OrderDisplay>();
		orderlistDisplay.add(getOrderDisplay());
		ServletActionContext.getRequest().setAttribute("userId", userId);
		ServletActionContext.getRequest().setAttribute("address", address);
		ServletActionContext.getRequest().setAttribute("goodsId", goodsId);
		return "success";
	}
}
