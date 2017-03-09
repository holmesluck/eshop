package yp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import yp.model.OrderDisplay;
import yp.model.TAddress;
import yp.model.TComment;
import yp.model.TGoods;
import yp.model.TOrderlist;
import yp.model.TOrderlistcontent;
import yp.model.TUser;
import yp.model.TUserlevel;
import yp.service.TCommentServiceI;
import yp.service.TGoodsServiceI;
import yp.service.TOrderlistServiceI;
import yp.service.TOrderlistcontentServiceI;
import yp.service.TUserAddressServiceI;
import yp.service.TUserServiceI;
import yp.service.TUserlevelServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")

public class TOrderlistAction extends ActionSupport{
	private TOrderlistServiceI tOrderlistService;
	private TOrderlistcontentServiceI tOrderlistcontentService;
	private TUserServiceI tUserService;
	private TUserlevelServiceI tUserlevelService;
	private TUserAddressServiceI tUserAddressService;
	private TGoodsServiceI tGoodsService;
	private List<OrderDisplay> orderlistDisplay;   //json返回，显示用户订单
	//private List<OrderDisplay> transactionDisplay; //显示交易记录
	private OrderDisplay orderDisplay;                 //用于订单显示
	private List<TGoods> goods;
	private String submitContent;
	private String jsonMessage;                             //返回json消息
	
	public String getJsonMessage() {
		return jsonMessage;
	}

	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}

	public String getSubmitContent() {
		return submitContent;
	}

	public void setSubmitContent(String submitContent) {
		this.submitContent = submitContent;
	}
	private TCommentServiceI tCommentService;
	private String submitPayment;
	
	
	public TGoodsServiceI gettGoodsService() {
		return tGoodsService;
	}

	@Autowired
	public void settGoodsService(TGoodsServiceI tGoodsService) {
		this.tGoodsService = tGoodsService;
	}
	
	public TUserlevelServiceI gettUserlevelService() {
		return tUserlevelService;
	}

	@Autowired
	public void settUserlevelService(TUserlevelServiceI tUserlevelService) {
		this.tUserlevelService = tUserlevelService;
	}

	public TUserAddressServiceI gettUserAddressService() {
		return tUserAddressService;
	}

	@Autowired
	public void settUserAddressService(TUserAddressServiceI tUserAddressService) {
		this.tUserAddressService = tUserAddressService;
	}

	public OrderDisplay getOrderDisplay() {
		return orderDisplay;
	}

	public void setOrderDisplay(OrderDisplay orderDisplay) {
		this.orderDisplay = orderDisplay;
	}
	
	public String getSubmitPayment() {
		return submitPayment;
	}

	public void setSubmitPayment(String submitPayment) {
		this.submitPayment = submitPayment;
	}

	public TCommentServiceI gettCommentService() {
		return tCommentService;
	}

	@Autowired
	public void settCommentService(TCommentServiceI tCommentService) {
		this.tCommentService = tCommentService;
	}

	

	public TOrderlistcontentServiceI gettOrderlistcontentService() {
		return tOrderlistcontentService;
	}
 
	@Autowired
	public void settOrderlistcontentService(
			TOrderlistcontentServiceI tOrderlistcontentService) {
		this.tOrderlistcontentService = tOrderlistcontentService;
	}

	public List<TGoods> getGoods() {
		return goods;
	}

	public void setGoods(List<TGoods> goods) {
		this.goods = goods;
	}

	public List<OrderDisplay> getOrderlistDisplay() {
		return orderlistDisplay;
	}

	public void setOrderlistDisplay(List<OrderDisplay> orderlistDisplay) {
		this.orderlistDisplay = orderlistDisplay;
	}

	public TUserServiceI gettUserService() {
		return tUserService;
	}

	@Autowired
	public void settUserService(TUserServiceI tUserService) {
		this.tUserService = tUserService;
	}

	public TOrderlistServiceI gettOrderlistService() {
		return tOrderlistService;
	}

    @Autowired
	public void settOrderlistService(TOrderlistServiceI tOrderlistService) {
		this.tOrderlistService = tOrderlistService;
	}
    
    /**
     * 会员确认收货
     * @author RichardChan
     */
	@Action(value = "userConfirmReceiveGoods", results = {@Result(name = "success", location = "/webpages/web/addcomment/addcomment.jsp"),
			                                                                           @Result(name = "input", location = "/webpages/web/userRegister/error.jsp")})
		public String userConfirmReceiveGoods() throws CShopException{
			//获取orderlistId
			Map session = ActionContext.getContext().getSession();
			if(session.get("userId") == null)
			{
				CShopException exception = new CShopException("该用户貌似未登录的哦！");
				exception.setExceptionLocation("TUserAction:userConfirmReceiveGoods");
				exception.setExceptionType(CShopExceptionI.INTERNAL);		
				throw exception;
			}
		    int userId = Integer.parseInt(session.get("userId").toString());
			int orderlistId = Integer.parseInt(ServletActionContext.getRequest().getParameter("orderlistId"));
			//更新数据库
			TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
			/*
			if(tOrderlistService.updateConfirmReceived(orderlistId) == true) {
				
				//更新用户积分
				TUser user = tUserService.getUserInfoById(userId);
				int userlevelId = user.getTUserlevel().getUserlevelId();
				//判断会员等级是否提升
				if(userlevelId != 4){
				TUserlevel level = tUserlevelService.getUserlevelById(userlevelId + 1); 
				user.setUserRewardpoints((int) (user.getUserRewardpoints() + Math.round(orderlist.getOrderlistTotalprice())));
				if(user.getUserRewardpoints() >= Integer.parseInt(level.getUserlevelLimits())){
					user.setTUserlevel(level);
					//ServletActionContext.getRequest().getSession().setAttribute("userLevel", level.getUserlevelName());
					session.put("userLevel", level.getUserlevelName());
				}
				}
				else{
					user.setUserRewardpoints((int) (user.getUserRewardpoints() + Math.round(orderlist.getOrderlistTotalprice())));
				}
				tUserService.update(user);
				*/
				//获取订单下的商品详情
				List<TOrderlistcontent> orderContent = tOrderlistcontentService.getOrderlistContent(orderlist);
				goods = new ArrayList<TGoods>();
				for(int i = 0; i < orderContent.size(); i ++){
					TGoods Tgoods = orderContent.get(i).getTGoods();
					goods.add(Tgoods);
				}
				//ServletActionContext.getRequest().getSession().setAttribute("goods", goods);
				//ServletActionContext.getRequest().setAttribute("orderlistId", orderlistId);
				session.put("goods", goods);
				session.put("orderlistId", orderlistId);
				return SUCCESS;
			//}
		}
	
	/**
	 * 填写评论和评分
	 * @author RichardChan
	 * @throws IOException 
	 */
	@Action(value = "userSubmitComment", results ={@Result(name = "success",type="redirectAction",location="userGetOrderlist", params={"pageNum", "1"})})
	public String userSubmitComment() throws IOException, CShopException
	{
		Map session = ActionContext.getContext().getSession();
		if(session.get("userId") == null)
		{
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userSubmitComment");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		//int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userId").toString());
		int userId = Integer.parseInt(session.get("userId").toString());
		int orderlistId = Integer.parseInt(session.get("orderlistId").toString());
		//获取Session中传递的订单商品列表
		List<TGoods> goods = (List<TGoods>) session.get("goods");
		//获取当前时间
		Date nowTime = new Date(System.currentTimeMillis());
		Timestamp orderDate = new Timestamp(nowTime.getTime());
		System.out.println(orderDate.toString());
		//获取用户
		TUser user = tUserService.getUserInfoById(userId);
		//评分
		int mark = 5;
		//获取goodsId数组
		int[] goodsId = new int[goods.size()];
		for(int i = 0; i < goods.size(); i++){
			goodsId[i] = goods.get(i).getGoodsId();
		}
		//销毁Session变量
		//ServletActionContext.getRequest().getSession().removeAttribute("goods");
		session.remove("goods");
		session.remove("orderlistId");
		if(tOrderlistService.updateConfirmReceived(orderlistId) == true) {
			TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
			//更新用户积分
			int userlevelId = user.getTUserlevel().getUserlevelId();
			//判断会员等级是否提升
			user.setUserRewardpoints((int) (user.getUserRewardpoints() + Math.round(orderlist.getOrderlistTotalprice())));
			if(userlevelId != 4){
				if(user.getUserRewardpoints() > Integer.parseInt(tUserlevelService.getUserlevelById(4).getUserlevelLimits())){
					TUserlevel level = tUserlevelService.getUserlevelById(4);
					user.setTUserlevel(level);
					session.put("userLevel", level.getUserlevelName());
				}
				else{
				for(int i = userlevelId; i < 4; i++)
				{
					if( user.getUserRewardpoints() < Integer.parseInt(tUserlevelService.getUserlevelById(i+1).getUserlevelLimits())){
						TUserlevel level = tUserlevelService.getUserlevelById(i);
						user.setTUserlevel(level);
						session.put("userLevel", level.getUserlevelName());
						break;
					}
				}
			  }
			}
			tUserService.update(user);
		}
		//更新数据库
		for(int i = 0; i < goodsId.length; i++){
			//获取评分
			TComment comment = new TComment();
			String markCheckBox = goodsId[i] + "";
			for(int j = 0; j < 5; j++){
				if(ServletActionContext.getRequest().getParameter(markCheckBox + j) != null){
					mark = j + 1;
				}
			}
			System.out.println(mark);
			//获取评论
			String commentText = goodsId[i] + "comment";
			String goodsComment = ServletActionContext.getRequest().getParameter(commentText);
			System.out.println(goodsComment);
		
			//写入数据库
			comment.setTUser(user);
			comment.setTGoods(tGoodsService.getAGoodsInfo(goodsId[i]));
			comment.setCommentDate(orderDate);
			comment.setCommentMark((short)mark);
			comment.setCommentContent(goodsComment);
			tCommentService.addComment(comment);
			//更新商品评分
			double averageMark = tCommentService.getAverageMark(goodsId[i]);
			tGoodsService.updateAverageMark(goodsId[i], averageMark);
		}
		setJsonMessage("成功");
		return SUCCESS;
	}
	
	/**
	 * 提交订单
	 * @author RichardChan
	 */
	@Action(value = "userSubmitPayment", results = {@Result(name = "success", location="/webpages/web/settlement/settle.jsp"),
													@Result(name = "jsonMessage", type="json", params = {"root", "jsonMessage"})})
	public String userSubmitPayment() throws CShopException{
		//获取Session中保存的用户Id，地址对象，商品列表
		Map session = ActionContext.getContext().getSession();
		if(session.get("userId") == null)
		{
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userSubmitPayment");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		//int  userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userId").toString());
		int  userId = Integer.parseInt(session.get("userId").toString());
		TUser user = tUserService.getUserInfoById(userId);
	    //TAddress  address = (TAddress) ServletActionContext.getRequest().getSession().getAttribute("defaultAddress");
		TAddress  address = tUserAddressService.getUserDefaultAddress(user);
		if(address == null)
		{
			CShopException exception = new CShopException("没有默认地址哦，赶紧去添加吧!");
			exception.setExceptionLocation("TUserAction:userSubmitPayment");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		//double totalPrice = Double.parseDouble(ServletActionContext.getRequest().getSession().getAttribute("totalPrice").toString());
		double totalPrice = Double.parseDouble(session.get("totalPrice").toString());
		//List<GoodsInCart> buyList = (List<GoodsInCart>) ServletActionContext.getRequest().getSession().getAttribute("buyList");
		List<GoodsInCart> buyList = (List<GoodsInCart>) session.get("buyList");

		//异常判断及处理
		if(address == null || buyList.size() < 1)
		{
			CShopException exception=new CShopException("1.地址信息出问题啦！2.请不要刷新页面重复提交订单！3.购物车奔溃了...");
        	exception.setExceptionLocation("TUserAction:userSubmitPayment");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		//测试数据
		
		//TAddress address = tUserAddressService.getAddressById(1);
		//double totalPrice = 7000;
		/*
		List<GoodsInCart> buyList = new ArrayList();
		TGoods goods2 = tGoodsService.getAGoodsInfo(2);
		TGoods goods6 = tGoodsService.getAGoodsInfo(6);
		TGoods goods7 = tGoodsService.getAGoodsInfo(7);
		TGoods goods8 = tGoodsService.getAGoodsInfo(8);
		TGoods goods9 = tGoodsService.getAGoodsInfo(9);
		GoodsInCart goodsInCart1 = new GoodsInCart(goods2, 3, 20);
		GoodsInCart goodsInCart2 = new GoodsInCart(goods6, 4, 21);
		GoodsInCart goodsInCart3 = new GoodsInCart(goods7, 5, 22);
		GoodsInCart goodsInCart4 = new GoodsInCart(goods8, 6, 23);
		GoodsInCart goodsInCart5 = new GoodsInCart(goods9, 7, 24);
		buyList.add(goodsInCart1);
		buyList.add(goodsInCart2);
		buyList.add(goodsInCart3);
		buyList.add(goodsInCart4);
		buyList.add(goodsInCart5);
		*/
		//获取当前时间作为下单时间
		Date nowTime = new Date(System.currentTimeMillis());
		Timestamp orderDate = new Timestamp(nowTime.getTime());
		System.out.println(orderDate.toString());
		
		//判断购买数量是否超过库存
		List<String> noMoreGoodsName = new ArrayList<String>();
		
		for(GoodsInCart g : buyList){
			//获得购买量超出商品库存的商品名
			if(g.getAmount() > g.getTGoods().getGoodsStock())
				noMoreGoodsName.add(g.getTGoods().getGoodsName());
				//******************************************************************
		}
		
		if(noMoreGoodsName.size() < 1){
		//将订单写入数据库
		TOrderlist tOrderlist = new TOrderlist();
		System.out.println(tOrderlist);
		tOrderlist.setTUser(tUserService.getUserInfoById(userId));
		tOrderlist.setTAddress(address);
		tOrderlist.setOrderlistTotalprice(totalPrice);
		tOrderlist.setOrderlistStatus(0);
		tOrderlist.setOrderlistOrderdate(orderDate);
		tOrderlistService.addOrderlist(tOrderlist);
		//写入TOrderlistcontent表
		for(GoodsInCart i : buyList){
			TOrderlistcontent tOrderlistcontent = new TOrderlistcontent();
			tOrderlistcontent.setTOrderlist(tOrderlist);
			tOrderlistcontent.setTGoods(i.getTGoods());
			tOrderlistcontent.setOrderlistcontentNum(i.getAmount());
			tOrderlistcontent.setOrderlistcontentOriginalprice(i.getTGoods().getGoodsPrice());
			tOrderlistcontent.setOrderlistcontentPrice((double)(Math.round(i.getTGoods().getGoodsPrice() * i.getTGoods().getGoodsDiscount() * user.getTUserlevel().getUserlevelDiscount())*100)/100);
			tOrderlistcontent.setOrderlistcontentTotalprice(i.getTGoods().getGoodsPrice() * i.getTGoods().getGoodsDiscount() * i.getAmount());
			tOrderlistcontentService.addOrderlistcontent(tOrderlistcontent);
			//更新商品销量
			i.getTGoods().setGoodsSales(i.getTGoods().getGoodsSales() + i.getAmount());
			//更新库存
			
			i.getTGoods().setGoodsStock(i.getTGoods().getGoodsStock() - i.getAmount());
			tGoodsService.update(i.getTGoods());
		    }
		//销毁存在Session中的传递变量
//		ServletActionContext.getRequest().getSession().removeAttribute("address");
//		ServletActionContext.getRequest().getSession().removeAttribute("totalPrice");
//		ServletActionContext.getRequest().getSession().removeAttribute("cartList");//删除session中的购物车商品
//		ActionContext.getContext().getSession().put("amountInCart", 0);//将购物车总商品清零
		session.remove("address");
		session.remove("totalPrice");
		//不删除session中cartLIst，更新session中的cartList
		Map<Integer,GoodsInCart> cartList = null;
		Map<Integer,GoodsInCart> tempCartList = null;
		Iterator iter = null;
		try{
			cartList = (Map<Integer, GoodsInCart>) session.get("cartList");
			tempCartList = new HashMap();
			iter = cartList.values().iterator();
		}catch(Exception e){
			CShopException exception = new CShopException("啊咧咧，出错了哦,应该是长时间未响应");
			exception.setExceptionLocation("TOrderlistAction:userSubmitPayment");
			exception.setExceptionType(CShopException.PARAMETERS);
			throw exception;
		}
		while(iter.hasNext()){
			GoodsInCart goods = (GoodsInCart) iter.next();
			if(goods.isBoolSelected()==false){
				tempCartList.put(goods.getTGoods().getGoodsId(), goods);
			} 
		}
		int amountInCart = tempCartList.size();
		session.put("amountInCart", amountInCart);
		session.remove("cartList");
		session.put("cartList", tempCartList);
		//返回状态信息
		setJsonMessage("成功");
		ServletActionContext.getRequest().setAttribute("orderlistId", tOrderlist.getOrderlistId());
		System.out.println("成功");
		return SUCCESS;
		}
		else{
			String goodsName = "";
			for(int i = 0; i < (noMoreGoodsName.size() - 1); i++){
			goodsName += (noMoreGoodsName.get(i) + ",");
			}
			goodsName += noMoreGoodsName.get(noMoreGoodsName.size() - 1);
			//ServletActionContext.getRequest().setAttribute("goodsName", goodsName);
			setSubmitPayment(goodsName);
			return "jsonMessage";
		}
		
	}
	
	/**
	 * @author RichardChan
	 */
	@Action(value = "userGetOrderlist", results = {@Result(name = "success", location="/webpages/web/indent/indentModified.jsp"),
			                                                                @Result(name="jsonResult", type="json",params={"root", "orderlistDisplay"}),
			                                                                @Result(name="jsonMessage", type="json", params={"root", "jsonMessage"})})
	public String userGetOrderlist() throws CShopException{
		//获取显示页数
		Map session = ActionContext.getContext().getSession();
		if(session.get("userId") == null)
		{
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userGetOrderlist");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		int pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		ActionContext context = ActionContext.getContext();
		//Map<?, ?> session = context.getSession();
				
		String userid = session.get("userId").toString();
		int userId = Integer.valueOf(userid);
		//int userId = Integer.parseInt(ServletActionContext.getRequest().getParameter("userId"));
		//获取要显示的用户订单
		TUser user = tUserService.findUserInfoById(userId);
		List<TOrderlist> orderlistList= tOrderlistService.getOrderlist(user, pageNum);
		//生成要显示的订单表
		if(orderlistList.isEmpty() || orderlistList == null) {
			if(pageNum == 1)
				return SUCCESS;
			else{
				setJsonMessage("已显示所有订单");
				return "jsonMessage";
			}
		}
		else{
		System.out.println("共有订单："+orderlistList.size());
		orderlistDisplay = new ArrayList<OrderDisplay>();
		for(int i = 0; i < orderlistList.size(); i++){
			int orderlistId = orderlistList.get(i).getOrderlistId();
			TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
			List<TOrderlistcontent> orderlistcontent =  tOrderlistcontentService.getOrderlistContent(orderlist);
			System.out.println(orderlistId + "订单中共有商品:" + orderlistcontent.size());
			String OrderlistId = orderlistList.get(i).getOrderlistId().toString();
			String orderlistOrderDate = orderlistList.get(i).getOrderlistOrderdate().toString();
			String orderlistTotalPrice = orderlistList.get(i).getOrderlistTotalprice().toString();
			int orderlistStatus = orderlistList.get(i).getOrderlistStatus();
			System.out.println(orderlistList.get(i).getTAddress());
			//获取地址信息
			String address = orderlistList.get(i).getTAddress().getAddressAddress();
			//String address = tUserAddressService.getAddressById(1).getAddressAddress();
			String phoneNumber = orderlistList.get(i).getTAddress().getAddressPhone();
			//String phoneNumber = tUserAddressService.getAddressById(1).getAddressPhone();
			String linkMan = orderlistList.get(i).getTAddress().getAddressLinkman();
			//创建显示OrderlistView
			List<Integer> goodsId = new ArrayList<Integer>();
			List<String> goodsImage = new ArrayList<String>();
			List<String> goodsName = new ArrayList<String>();
			List<Integer> amount = new ArrayList<Integer>();
			for(int j = 0; j < orderlistcontent.size(); j++){
				goodsId.add(orderlistcontent.get(j).getTGoods().getGoodsId());
				goodsName.add(orderlistcontent.get(j).getTGoods().getGoodsName());
				goodsImage.add(orderlistcontent.get(j).getTGoods().getGoodsImage());
				amount.add(orderlistcontent.get(j).getOrderlistcontentNum());
			 }
			OrderDisplay orderDisplay = new OrderDisplay();
			orderDisplay.setAddress(address);
			orderDisplay.setPhoneNumber(phoneNumber);
			orderDisplay.setLinkMan(linkMan);
			orderDisplay.setGoodsName(goodsName);
			orderDisplay.setAmount(amount);
			orderDisplay.setGoodsId(goodsId);
			orderDisplay.setGoodsImage(goodsImage);
			orderDisplay.setOrderlistId(OrderlistId);
			orderDisplay.setOrderlistOrderDate(orderlistOrderDate);
			orderDisplay.setOrderlistStatus(orderlistStatus);
			orderDisplay.setOrderlistTotalPrice(orderlistTotalPrice);
			setOrderDisplay(orderDisplay);
			orderlistDisplay.add(getOrderDisplay());
			}
		System.out.println(orderlistDisplay.size());
		//测试用
		for(int i = 0; i < orderlistDisplay.size(); i++){
			//System.out.println("lalalllallalaallaa");
			System.out.println(orderlistDisplay.get(i).getOrderlistId());
		}
		if(pageNum == 1) return SUCCESS;
		else return "jsonResult";
		}
	}
	
	/**
	 * 显示会员交易记录
	 * @author RichardChan
	 * @throws ParseException 
	 */
	@Action(value = "userQueryTransaction", results = {@Result(name = "success", location="/webpages/web/transaction/transaction.jsp"),
			                                                                @Result(name="jsonResult", type="json",params={"root", "orderlistDisplay"}),
			                                                                @Result(name="jsonMessage", type="json",params={"root","jsonMessage"})})
	public String userQueryTransaction() throws ParseException, CShopException{
		Map session = ActionContext.getContext().getSession();
		if(session.get("userId") == null)
		{
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userQueryTransaction");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		//获取显示页数
		int pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		int userId = Integer.parseInt(session.get("userId").toString());
		TUser user = tUserService.findUserInfoById(userId);
		Timestamp startTime = tOrderlistService.getFirstTransactionTime(user);
		Date nowTime = new Date(System.currentTimeMillis());
		Timestamp endTime = new Timestamp(nowTime.getTime());
		System.out.println(startTime + "   " + endTime);
		//时间转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//判断会员是否选择查询时间
			if(ServletActionContext.getRequest().getParameter("startDate") != null){
				if(ServletActionContext.getRequest().getParameter("startDate").equals("") == false){
				Date StartTime =sdf.parse(ServletActionContext.getRequest().getParameter("startDate"));
				startTime = new Timestamp(StartTime.getTime());
				}
				else
				{
					Timestamp StartTime = new Timestamp(endTime.getTime());
					StartTime.setMonth(StartTime.getMonth() - 3);
					startTime = new Timestamp(StartTime.getTime());
					System.out.println(startTime);
				}
			}
			else
			{
				Timestamp StartTime = new Timestamp(endTime.getTime());
				StartTime.setMonth(StartTime.getMonth() - 3);
				startTime = new Timestamp(StartTime.getTime());
				System.out.println(startTime);
			}
			if(ServletActionContext.getRequest().getParameter("terminalDate") != null && ServletActionContext.getRequest().getParameter("terminalDate").equals("") == false){
				Date EndTime =sdf.parse(ServletActionContext.getRequest().getParameter("terminalDate"));
				endTime = new Timestamp(EndTime.getTime());
				endTime.setDate(endTime.getDate() + 1);
				endTime = new Timestamp(endTime.getTime());
			}	
		System.out.println(startTime + "   " + endTime);
		//获取要显示的用户交易记录
		List<TOrderlist> orderlistList= tOrderlistService.getTransaction(user, pageNum, startTime, endTime);
		//生成要显示的交易记录表
		if(orderlistList.isEmpty() || orderlistList == null) {
			System.out.println("没有交易记录");
			if(pageNum == 1)
				return SUCCESS;
			else{
				setJsonMessage("已显示所有订单");
				return "jsonMessage";
			}
		}
		else{
		System.out.println("共有交易记录："+orderlistList.size());
		orderlistDisplay = new ArrayList<OrderDisplay>();
		for(int i = 0; i < orderlistList.size(); i++){
			int orderlistId = orderlistList.get(i).getOrderlistId();
			TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
			List<TOrderlistcontent> orderlistcontent =  tOrderlistcontentService.getOrderlistContent(orderlist);
			System.out.println(orderlistId + "订单中共有商品:" + orderlistcontent.size());
			String OrderlistId = orderlistList.get(i).getOrderlistId().toString();
			String orderlistOrderDate = orderlistList.get(i).getOrderlistOrderdate().toString();
			String orderlistTotalPrice = orderlistList.get(i).getOrderlistTotalprice().toString();
			int orderlistStatus = orderlistList.get(i).getOrderlistStatus();
			String address = orderlistList.get(i).getTAddress().getAddressAddress();
			String phoneNumber = orderlistList.get(i).getTAddress().getAddressPhone();
			//创建显示OrderlistView
			List<String> goodsName = new ArrayList<String>();
			List<Integer> amount = new ArrayList<Integer>();
			List<Integer> orderlistIdList = new ArrayList<Integer>();
			for(int j = 0; j < orderlistcontent.size(); j++){
				goodsName.add(orderlistcontent.get(j).getTGoods().getGoodsName());
				amount.add(orderlistcontent.get(j).getOrderlistcontentNum());
				orderlistIdList.add(orderlistcontent.get(j).getTOrderlist().getOrderlistId());
			 }
			OrderDisplay orderDisplay = new OrderDisplay();
			orderDisplay.setAddress(address);
			orderDisplay.setPhoneNumber(phoneNumber);
			orderDisplay.setGoodsName(goodsName);
			orderDisplay.setAmount(amount);
			orderDisplay.setOrderlistId(OrderlistId);
			orderDisplay.setOrderlistOrderDate(orderlistOrderDate);
			orderDisplay.setOrderlistStatus(orderlistStatus);
			orderDisplay.setOrderlistTotalPrice(orderlistTotalPrice);
			setOrderDisplay(orderDisplay);
			orderlistDisplay.add(getOrderDisplay());
			}
		System.out.println(orderlistDisplay.size());
		//测试用
		for(int i = 0; i < orderlistDisplay.size(); i++){
			System.out.println(orderlistDisplay.get(i).getOrderlistId());
		}
		if(pageNum == 1) return SUCCESS;
		else return "jsonResult";
		}
		}
	
	/**
	 * 交易详情
	 * @author RichardChan
	 */
	@Action(value="userQueryTransactionInfo", results={
			@Result(name="success", location="/webpages/web/indent/indentdetail.jsp"),
			@Result(name="adminSuccess", location="/webpages/back/ordermanagement/orderdetail.jsp"),
			@Result(name="jsonMessage", type="json", params={"root", "jsonMessage"})})
	public String userQueryTransactionInfo() throws CShopException{
		Map session = ActionContext.getContext().getSession();
		if(session.get("userId") == null)
		{
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userQueryTransactionInfo");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		int orderlistId = Integer.parseInt(ServletActionContext.getRequest().getParameter("orderlistId"));
		//如果管理员页面调用action,传入一个fromAdmin ,值为1
		String fromAdmin = null;
		fromAdmin = ServletActionContext.getRequest().getParameter("fromAdmin");
		int userId = Integer.parseInt(session.get("userId").toString());
		//int userId = 1;
		//获取订单详情
		TOrderlist orderlist = tOrderlistService.getOrderlistById(orderlistId);
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
		if(fromAdmin==null)
			return SUCCESS;
		else 
			return "adminSuccess";
	}
	
	
	/**
	 * 更新数据库保持商品销售和订单销售量一致
	 * @author RichardChan
	 */
	/*
	@Action(value="updateDatabase", results={@Result(name="success", type="json", params={"root", "jsonMessage"})})
	public String updateDatabase(){
		List<TGoods> goodsList = tGoodsService.getAllGoods();
		if(goodsList != null){
			for(int i = 0; i < goodsList.size(); i++){
				TGoods goods = goodsList.get(i);
				List<TOrderlistcontent> orderlistContentList = tOrderlistcontentService.getOrderlistcontentByGoods(goods);
				if(orderlistContentList != null){
					int salesAmount = 0;
					for(int j = 0; j < orderlistContentList.size(); i++){
						salesAmount += orderlistContentList.get(j).getOrderlistcontentNum();
					}
					goods.setGoodsSales(salesAmount);
					tGoodsService.update(goods);
				}
			}
		}
		setJsonMessage("成功");
		return SUCCESS;
	}
	*/
	}