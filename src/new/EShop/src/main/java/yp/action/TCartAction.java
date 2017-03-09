package yp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;
import yp.model.GoodsInCart;
import yp.model.TGoods;
import yp.service.TCartServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TCartAction extends ActionSupport{
	
	private TCartServiceI cartService;
	
	/**
	 * 购物车商品hash列表，
	 * 键为商品Id，
	 * 值为GoodsInCart型对象
	 */
	private Map<Integer,GoodsInCart> cartList = null;
	//传给前台的
	private List<GoodsInCart> cartListToShow;
	private TGoods goods;
	private String strInfo =null;
	private int amount =1;
	private int isAddSuccessJson = 0;
	private int isDeleteSuccessJson =0;  
	private int modifyStatusJson =0;
	private int amountInCart = 0; //购物车商品总数量
	
	
	

	public int getAmountInCart() {
		return amountInCart;
	}
	public void setAmountInCart(int amountInCart) {
		this.amountInCart = amountInCart;
	}
	public int getModifyStatusJson() {
		return modifyStatusJson;
	}
	public void setModifyStatusJson(int modifyStatusJson) {
		this.modifyStatusJson = modifyStatusJson;
	}
	public int getIsDeleteSuccessJson() {
		return isDeleteSuccessJson;
	}
	public void setIsDeleteSuccessJson(int isDeleteSuccessJson) {
		this.isDeleteSuccessJson = isDeleteSuccessJson;
	}
	public int getIsAddSuccessJson() {
		return isAddSuccessJson;
	}
	public void setIsAddSuccessJson(int isAddSuccessJson) {
		this.isAddSuccessJson = isAddSuccessJson;
	}
	public Map<Integer, GoodsInCart> getCartList() {
		return cartList;
	}
	public void setCartList(Map<Integer, GoodsInCart> cartList) {
		this.cartList = cartList;
	}
	public List<GoodsInCart> getCartListToShow() {
		return cartListToShow;
	}
	public void setCartListToShow(List<GoodsInCart> cartListToShow) {
		this.cartListToShow = cartListToShow;
	}
	public TGoods getGoods() {
		return goods;
	}
	public void setGoods(TGoods goods) {
		this.goods = goods;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStrInfo() {
		return strInfo;
	}
	public void setStrInfo(String strInfo) {
		this.strInfo = strInfo;
	}
	public TCartServiceI getCartService() {
		return cartService;
	}
	@Autowired
	public void setCartService(TCartServiceI cartService) {
		this.cartService = cartService;
	}
	
	//更新购物车中物品总数
	private boolean updateAmountInCart() throws CShopException{
		Map session = ActionContext.getContext().getSession();
		Map<Integer,GoodsInCart> cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		if(cartList==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
			exception.setExceptionLocation("TCartAction:updateAmountInCart");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		Collection<GoodsInCart> collection = cartList.values();
		if(collection==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
			exception.setExceptionLocation("TCartAction:updateAmountInCart");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		int tempAmount = collection.size();
		session.put("amountInCart", tempAmount);
		return true;
		/*Iterator iter = collection.iterator();
		if(iter==null)
			return false;
		int tempAmount =0;
		
		while(iter.hasNext()){
			iter.next();
			tempAmount += 1;
		}
		System.out.println("购物车当前数量:" + tempAmount);
		session.put("amountInCart", tempAmount);
		return true;*/
	}
	/**
	 * 用户进入购物车/显示购物车
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "enterCart",results = {
			@Result(name = "success",location = "/webpages/web/shoppingcart/shoppingcart.jsp"),
			@Result(name = "error",location = "/webpages/userRegister/error.jsp")})
	public String enterCart() throws CShopException{
		Map session = ActionContext.getContext().getSession();
		cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		if(cartList==null){
			//没有向购物车添加过商品 ，也可能出错了-。-
			
			return SUCCESS;
		}
		Iterator it = null;
		try{
			it = cartList.values().iterator();
		}catch(NullPointerException ex)  {
			CShopException exception =new CShopException("购物车出错了，请重试");
			exception.setExceptionLocation("TCartAction:enterCart");
        	exception.setExceptionType(CShopExceptionI.INTERNAL);
        	throw exception;
		}
		
		cartListToShow = new ArrayList();
		while(it.hasNext()){
			cartListToShow.add((GoodsInCart) it.next());
		}
		//得到勾选的商品id
		String strSelectedId = ServletActionContext.getRequest().getParameter("selectedId");
		//如果有勾选商品id，则处理cartList
		if(strSelectedId!=null){
			String [] strList = strSelectedId.split(",");
			for(int i =0;i<cartListToShow.size();i++){
				cartListToShow.get(i).setBoolSelected(false);
				for(int j=0;j<strList.length;j++){
					
					if(cartListToShow.get(i).getTGoods().getGoodsId().equals(Integer.valueOf(strList[j]))){
						cartListToShow.get(i).setBoolSelected(true);
						break;
					}
				}
			}
		}
		for(int i=0;i<cartListToShow.size();i++)
			System.out.println("cartListToShow: " + cartListToShow.get(i).getTGoods().getGoodsId()
					+ "  : " + cartListToShow.get(i).isBoolSelected());
		
		
		updateAmountInCart();
		
		return SUCCESS;
	}

	/**
	 * 通过url传入 goodsId 和  amount（ 购买数量）
	 * 返回json数据 ： int 型  modifyStatusJson ,  值为1 表示正常更改数值，2表示库存不够不能修改值，3表示数量小于0不能修改值。
	 * 用户在购物车页面编辑购物车数量
	 * @return
	 * @throws CShopException 
	 */
	@Action(value = "modifyCart",results = {
			@Result(name = "jsonResult",type = "json",params = { "root","modifyStatusJson" }),
			@Result(name = "error",location = "/webpages/userRegister/error.jsp")})
	public String modifyCart() throws CShopException{
		String strAmount = ServletActionContext.getRequest().getParameter("amount");
		String strGoodsId = ServletActionContext.getRequest().getParameter("goodsId");
		if(strAmount==null || strGoodsId==null)
			return "error";
		Integer amount = Integer.valueOf(strAmount);
		Integer goodsId = Integer.valueOf(strGoodsId);
		//从session中获取
		Map session = ActionContext.getContext().getSession();
		cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		if(cartList==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
			exception.setExceptionLocation("TCartAction:modifyCart");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		GoodsInCart modifyGoods = cartList.get(goodsId);
		
		if(modifyGoods==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，出错了哦");
			exception.setExceptionLocation("TCartAction:modifyCart");
			exception.setExceptionType(CShopException.INTERNAL);
			throw exception;
		}
		if(amount<=modifyGoods.getStock()&& amount>=0){
			modifyGoods.setAmount(amount);
			cartList.put(goodsId, modifyGoods);
			updateAmountInCart();
			session.put("cartList", cartList);
			modifyStatusJson = 1;
		}else if(amount>modifyGoods.getStock())
			modifyStatusJson = 2;
		else if(amount<0)
			modifyStatusJson =3;
			
		return "jsonResult";
	}
	
	/**
	 * 用户将商品加入购物车  
	 * @param  goodsId  goodsStock
	 * @return  返回json  ：  int 型  isAddSuccessJson  ,1 表示成功，2表示商品已下架，3表示商品无库存,4表示库存能不够
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "addToCart",results = {
			@Result(name = "error",location = "/webpages/userRegister/error.jsp"),
			@Result(name = "jsonResult",type = "json",params = { "root","isAddSuccessJson" })})
	public String addToCart() throws CShopException{
		//从URL获得商品id
		Integer goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		//获得商品当前库存
		//Integer currentStock = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsStock"));
		goods = cartService.getGoodsById(goodsId);
		if(goods==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，出错了哦");
			exception.setExceptionLocation("TCartAction:addToCart");
			exception.setExceptionType(CShopException.INTERNAL);
			throw exception;
		}
		//判断是否商品已下架
		if(goods.getGoodsIsdel()){
			strInfo = "商品已下架";
			isAddSuccessJson = 2 ;
			return "jsonResult";
		}
		//判断商品是否有库存
		int stock = goods.getGoodsStock();
		if(stock <= 0){
			strInfo = "商品无库存";
			isAddSuccessJson = 3 ;
			return "jsonResult";
		}
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		//购物车中还没有商品
		if(cartList==null){
			
			cartList = new HashMap();
			cartList.put(goodsId,new GoodsInCart(goods,amount,stock));
			strInfo = "商品    " + goods.getGoodsName() + " 成功加入到购物车" ;
		}
		//购物车中已有商品,查找cartList，看要加入购物车的商品是否已经在购物车
		else{
			//已经在购物车中
			if(cartList.containsKey(goodsId)){
				//判断库存量是否大于或等于购物车中商品数量,直接返回库存不足json
				if(cartList.get(goodsId).getAmount()>=stock){
					strInfo = "商品库存不足";
					System.out.println(strInfo);
					isAddSuccessJson = 4 ;
					return "jsonResult";
				}
				//变化购物车中商品总数量
				
				amount += Integer.valueOf(cartList.get(goodsId).getAmount());
				
				cartList.put(goodsId,new GoodsInCart(goods,amount,stock));
				strInfo = "商品    :" + goods.getGoodsName() + " 在购物车中数量变更为  " + amount ;
				
			}
			//不在购物车中
			else{
				
				cartList.put(goodsId,new GoodsInCart(goods,amount,stock));
				strInfo = "商品    " + goods.getGoodsName() + " 成功加入到购物车" ;
			}
		}
		//把cartList放入到session对象中
		
		
		session.put("cartList", cartList);
		updateAmountInCart();
		isAddSuccessJson = 1 ;
		
		return "jsonResult";
		
	}
	/**
	 * 用户删除购物车商品  ,
	 * 传入参数goodsId,
	 * 返回json： int型  isDeleteSuccessJson  ， 1表示删除成功            (已修改 ：重定向页面)
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "deleteFromCart",results = {
			//@Result(name = "jsonResult",type = "json",params = { "root","isDeleteSuccessJson" }) ,
			@Result(name = "success",type = "redirectAction",location = "enterCart")
			})
	public String deleteFromCart() throws CShopException{
		//从URL获得商品id
		Integer goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		if(cartList==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
			exception.setExceptionLocation("TCartAction:deleteFromCart");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		//在session中找到要删除的购物车中的相应商品
		if(cartList.containsKey(goodsId)){
			
			cartList.remove(goodsId);
			
			updateAmountInCart();
			session.remove("cartList");
			session.put("cartList", cartList);
			strInfo = "商品    " + goodsId + " 成功从购物车删除" ;
			
			isDeleteSuccessJson = 1;
			return "success";
		}
		else{
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，出错了哦");
			exception.setExceptionLocation("TCartAction:deleteFromCart");
			exception.setExceptionType(CShopException.LOGICAL);
			throw exception;
		}
		
	}
	
	@Action(value = "modifyCheckStateInCart",results = {
			@Result(name = "jsonResult",type = "json",params = { "root","modifyStatusJson" }) 
			})
	public String modifyCheckStateInCart() throws CShopException{
		Integer goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		String checkState = ServletActionContext.getRequest().getParameter("checkState");
		cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
		if(cartList==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
			exception.setExceptionLocation("TCartAction:deleteFromCart");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		GoodsInCart goods = cartList.get(goodsId);
		if(checkState.equals("1")){
			System.out.println("checkState equals 1");
			goods.setBoolSelected(true);
		}else if(checkState.equals("0")){
			System.out.println("checkState equals 0");
			goods.setBoolSelected(false);
		}
		cartList.put(goodsId, goods);
		
		session.put("cartList", cartList);
		modifyStatusJson=1;
		return "jsonResult";
	}
	
	
}
