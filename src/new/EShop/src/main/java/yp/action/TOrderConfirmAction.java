package yp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;
import yp.model.GoodsInCart;
import yp.model.TAddress;
import yp.service.TOrderConfirmServiceI;
import yp.service.TUserAddressServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("cshop-package")
@Namespace("/")
public class TOrderConfirmAction extends ActionSupport{
	
	
	private Integer selectedAddressId;//用户选择配送的地址id
	private TAddress defaultAddress;//存储用户配送地址的对象
	//private List<TAddress> userAddress ;//存储用户的所有地址
	private List<GoodsInCart> buyList;//用户要支付的商品列表
	private Double totalPrice =0.0;
	private int score =0; //用户获得积分
	private double countPrice = 0.0;//用户最终折后价
	private String strInfo ;
	private TOrderConfirmServiceI tOrderConfirmServiceI;
	private TUserAddressServiceI userAddressService;
	
	public TUserAddressServiceI getUserAddressService() {
		return userAddressService;
	}
	@Autowired
	public void setUserAddressService(TUserAddressServiceI userAddressService) {
		this.userAddressService = userAddressService;
	}
	public double getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(double countPrice) {
		this.countPrice = countPrice;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Integer getSelectedAddressId() {
		return selectedAddressId;
	}
	public void setSelectedAddressId(Integer selectedAddressId) {
		this.selectedAddressId = selectedAddressId;
	}
	public List<GoodsInCart> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<GoodsInCart> buyList) {
		this.buyList = buyList;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStrInfo() {
		return strInfo;
	}
	public void setStrInfo(String strInfo) {
		this.strInfo = strInfo;
	}
	public TOrderConfirmServiceI gettOrderConfirmServiceI() {
		return tOrderConfirmServiceI;
	}
	@Autowired
	public void settOrderConfirmServiceI(TOrderConfirmServiceI tOrderConfirmServiceI) {
		this.tOrderConfirmServiceI = tOrderConfirmServiceI;
	}
	public TAddress getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(TAddress defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	/**
	 * 用户进入订单确认页面
	 * 将 购买商品列表buyList,配送地址defaultAddress,总价totalPrice,会员折后总价 countPrice 放入session中
	 * 前台可以获取数据包括： buyList,defaultAddress,totolPrice ,score，userAddress，countPrice
	 * @author yu
	 * @return
	 * @throws CShopException 
	 */
	@Action(value = "userEnterPayment",results = {
			//@Result(name = "success",location = "/webpages/web/settlement/settlement.jsp"),
			@Result(name = "success",type="chain",location = "userGetAddress"),
			@Result(name = "error",location = "/webpages/userRegister/error.jsp")})
	public String userEnterPayment() throws CShopException{
		//从session中获得用户id
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		String userId_t = session.get("userId").toString();
		int userId = Integer.valueOf(userId_t);
		/*//获得用户要购买的商品
		String strBuyList = ServletActionContext.getRequest().getParameter("selectedId");
		if(strBuyList==null)
			return ERROR;
		String[] strArryBuyList = strBuyList.split(",");
		//测试
		for(int i=0;i<strArryBuyList.length;i++)
			System.out.println("strArryBuyList" + strArryBuyList[i]);*/
		//将用户要买的商品放入buyList中
		if(session.get("cartList")==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TOrderConfirmAction:userEnterPayment");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		Map<Integer,GoodsInCart> cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		
	/*	//将cartList中的商品boolSelected设为false
		Iterator iterList = cartList.values().iterator();
		while(iterList.hasNext()){
			GoodsInCart goods = (GoodsInCart) iterList.next();
			goods.setBoolSelected(false);
			cartList.put(goods.getTGoods().getGoodsId(), goods);
		}*/
		//将要买的商品放入buyList中并计算总价,并将要买的cartList中的商品boolSelected设为true
		buyList = new ArrayList();
		
		Iterator iterList = cartList.values().iterator();
		while(iterList.hasNext()){
			GoodsInCart goods = (GoodsInCart) iterList.next();
			if(goods.isBoolSelected()){
				buyList.add(goods);
				totalPrice += goods.getTotalPrice();
				//把商品单价格全都改为折后
				goods.setCountPrice(tOrderConfirmServiceI.getCountPrice(goods.getTotalPrice(), userId));  
			}
				
		}
		/*for(int i=0;i<strArryBuyList.length;i++){
			Integer id = Integer.parseInt(strArryBuyList[i]);
			buyList.add(cartList.get(id));
			cartList.get(id).setBoolSelected(true);
			totalPrice += cartList.get(id).getTotalPrice();  
		}*/
		//text
		for(int i=0;i<buyList.size();i++)
			System.out.println("buyList:   "+buyList.get(i).getTGoods().getGoodsId());
		//计算得到积分
		score = totalPrice.intValue();
		//计算会员折扣后的总价
		countPrice = tOrderConfirmServiceI.getCountPrice(totalPrice, userId);
		
		//将值传入session中
		//session.put("cartList", cartList);
		session.put("countPrice", countPrice);
		session.put("buyList", buyList);
		session.put("totalPrice", totalPrice);
		
		
		//获得用户选择的配送地址
		String strSelectedAddressId = ServletActionContext.getRequest().getParameter("selectedAddressId");//如果没有该参数有问题吗？？
		if(strSelectedAddressId!=null)
			selectedAddressId = Integer.parseInt(strSelectedAddressId);
		//没有传递用户选择的地址id
		if(selectedAddressId==null){
			defaultAddress = tOrderConfirmServiceI.selectDefaultAddress(userId);
			if(defaultAddress==null){
				strInfo = "没有默认地址";
				return SUCCESS;
			}else{
				strInfo = "默认地址为"+defaultAddress;
				session.put("defaultAddress",defaultAddress);
				return SUCCESS;
			}	
		}
		else{
			defaultAddress = tOrderConfirmServiceI.getSelectedAddress(selectedAddressId);
			//修改数据库中的默认地址
			if(!userAddressService.updateDefaultAddress(selectedAddressId))
			{
				//抛出异常
				CShopException exception=new CShopException("啊咧类，出错了哦");
	        	exception.setExceptionLocation("TFavorityAction:userAddToFavority");
	        	exception.setExceptionType(CShopExceptionI.LOGICAL);
	        	throw exception;
			}
			
			if(defaultAddress!=null){
				session.put("defaultAddress",defaultAddress);
				strInfo = "默认地址为"+defaultAddress;
				return SUCCESS;
			}else{
				//抛出异常
				CShopException exception=new CShopException("啊咧类，出错了哦");
	        	exception.setExceptionLocation("TFavorityAction:userAddToFavority");
	        	exception.setExceptionType(CShopExceptionI.LOGICAL);
	        	throw exception;
			}
		}
	}

	
	/**
	 * （删除不用）
	 * 用户选择配送至的地址
	 * @return
	 * @author yu
	 */
	@Action(value = "userSelectAddress",results = {
			@Result(name = "success",type = "chain",location = "/webpages/userRegister/error.jsp")})
	public String userSelectAddress(){
		//从URL获得地址
		selectedAddressId = Integer.parseInt(ServletActionContext.getRequest().getParameter("selectedAddressId"));
		//userAddressService
		return SUCCESS;
	}
}
