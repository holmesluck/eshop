package yp.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.model.TAdmin;
import yp.model.TFirstcatalog;
import yp.model.TGoods;
import yp.model.TOrderlistcontent;
import yp.model.TSecondcatalog;
import yp.model.TUser;
import yp.service.TAdminServiceI;
import yp.service.TFirstcatalogServiceI;
import yp.service.TGoodsServiceI;
import yp.service.TOrderlistServiceI;
import yp.service.TOrderlistcontentServiceI;
import yp.service.TSecondcatalogServiceI;
import yp.service.TUserServiceI;
import yp.util.AESEncrypt;

@ParentPackage("cshop-package")
@Namespace("/")
public class TAdminAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TAdminServiceI tAdminService;
	private TOrderlistServiceI tOrderlistService;
	private TFirstcatalogServiceI tFirstcatalogService;
	private TSecondcatalogServiceI tSecondcatalogService;
    private TUserServiceI tUserService;
	private TGoodsServiceI tGoodsService;
	private TOrderlistcontentServiceI  tOrderlistcontentService;
	private String adminLogin;    //登录状态
	private String adminLogout; //登出状态
	private String jsonMessage;  //json消息
	private List<String> catalogList;  //用于保存一级目录及其二级目录名
	private List<Integer> salesAmount;  //用于保存目录下的销量
	private List<Double> sales;  //用于保存销售额
	private List<TGoods> goods;  //显示所有商品
	private List<Double> discountPrice; //折后价
	private TUser tUser;//查询会员
	
	public TUser getTUser() {
		return tUser;
	}
	public void setTUser(TUser tUser) {
		this.tUser = tUser;
	}
	public TUser gettUser() {
		return tUser;
	}
	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}
	public List<Double> getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(List<Double> discountPrice) {
		this.discountPrice = discountPrice;
	}
	public List<TGoods> getGoods() {
		return goods;
	}
	public void setGoods(List<TGoods> goods) {
		this.goods = goods;
	}
	public List<Integer> getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(List<Integer> salesAmount) {
		this.salesAmount = salesAmount;
	}
	public List<Double> getSales() {
		return sales;
	}
	public void setSales(List<Double> sales) {
		this.sales = sales;
	}
	
	public List<String> getCatalogList() {
		return catalogList;
	}
	public void setCatalogList(List<String> catalogList) {
		this.catalogList = catalogList;
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
	public TFirstcatalogServiceI gettFirstcatalogService() {
		return tFirstcatalogService;
	}
	@Autowired
	public void settFirstcatalogService(TFirstcatalogServiceI tFirstcatalogService) {
		this.tFirstcatalogService = tFirstcatalogService;
	}
	public TSecondcatalogServiceI gettSecondcatalogService() {
		return tSecondcatalogService;
	}
	@Autowired
	public void settSecondcatalogService(
			TSecondcatalogServiceI tSecondcatalogService) {
		this.tSecondcatalogService = tSecondcatalogService;
	}
	public TGoodsServiceI gettGoodsService() {
		return tGoodsService;
	}
	@Autowired
	public void settGoodsService(TGoodsServiceI tGoodsService) {
		this.tGoodsService = tGoodsService;
	}
	public String getJsonMessage() {
		return jsonMessage;
	}
	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}
	public TUserServiceI gettUserService() {
		return tUserService;
	}
	@Autowired
	public void settUserService(TUserServiceI tUserService) {
		this.tUserService = tUserService;
	}
	public String getAdminLogout() {
		return adminLogout;
	}
	public void setAdminLogout(String adminLogout) {
		this.adminLogout = adminLogout;
	}
	
	public TAdminServiceI gettAdminService() {
		return tAdminService;
	}
	@Autowired
	public void settAdminService(TAdminServiceI tAdminService) {
		this.tAdminService = tAdminService;
	}
	
	public String getAdminLogin() {
		return adminLogin;
	}
	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}
	
	/**
	 * 进入管理员登录页面
	 * @author RichardChan
	 */
	@Action(value="enterAdminLogin", results={@Result(name="success", location="/webpages/back/adminlogin/adminlogin.jsp")})
	public String enterAdminLogin(){
		return SUCCESS;
	}
	
	/**
	 * 管理员登录
	 * @author RichardChan
	 */
	@Action(value = "adminLogin", results = {@Result(name = "success", type="json", params={"root", "adminLogin"})})
	public String loginAdmin() throws AdminException{
		String adminName = ServletActionContext.getRequest().getParameter("adminName");
		String adminPassword = ServletActionContext.getRequest().getParameter("adminPassword");
		if (adminName.equals("") || adminPassword.equals("")) {
			AdminException exception = new AdminException("管理员用户名或密码不幸被大灰狼拐卖了...");
			exception.setExceptionLocation("TAdminAction:adminLogin");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		adminPassword=AESEncrypt.encrypt(adminPassword);
		setAdminLogin(tAdminService.adminLogin(adminName, adminPassword));
		if(getAdminLogin().equals("成功")){
			int adminId = tAdminService.getAdminIdByAdminName(adminName);
			TAdmin admin = tAdminService.getAdminById(adminId);
//			ServletActionContext.getRequest().getSession().setAttribute("adminId", adminId);
//			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
			Map session = ActionContext.getContext().getSession();
			session.put("adminId", adminId);
			session.put("admin", admin);
		}
		return SUCCESS;
	}
	
	/**
	 * 管理员退出登录
	 * @author RichardChan
	 */
	@Action(value="adminLogout", results={@Result(name="success", location="/webpages/back/adminlogin/adminlogin.jsp")})
	public String adminLogout(){
		//ServletActionContext.getRequest().getSession().removeAttribute("adminId");
		Map session = ActionContext.getContext().getSession();
		session.remove("adminId");
		setAdminLogout("成功");
		return SUCCESS;
	}
	
	/**
	 * 管理员查询会员
	 * @author RichardChan
	 * @throws AdminException 
	 */
	@Action(value="adminSearchUser", results={@Result(name="success",location="/webpages/back/grades_marks_management/membersearch.jsp")})
	public String adminSearchUser() throws AdminException{
		int userId = Integer.parseInt(ServletActionContext.getRequest().getParameter("userId"));
		if (ServletActionContext.getRequest().getParameter("userId") == null || ServletActionContext.getRequest().getParameter("userId").equals("")) {
			AdminException exception = new AdminException("会员Id不幸被大灰狼拐卖了...");
			exception.setExceptionLocation("TAdminAction:adminSearchUser");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		if(tUserService.getUserInfoById(userId) == null)
			setJsonMessage("会员不存在");
		else{
			setJsonMessage("成功");
			tUser=tUserService.getUserInfoById(userId);
			//System.out.println("fuck");
			ServletActionContext.getRequest().setAttribute("user", tUserService.getUserInfoById(userId));
			ServletActionContext.getRequest().setAttribute("userLevel", tUserService.getUserInfoById(userId).getTUserlevel().getUserlevelName());
		}
		return "success";
	}
	
	/**
	 * 销售统计查询
	 *@author RichardChan 
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	@Action(value="adminSalesStatistics", results={@Result(name="success",location="/webpages/back/salesvolumeview/salesvolume.jsp")})
	public String adminSalesStatistics() throws ParseException{
		Date nowTime = new Date(System.currentTimeMillis());
		Timestamp endTime = new Timestamp(nowTime.getTime());
		Timestamp startTime = new Timestamp(nowTime.getTime());
		//时间转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//判断会员是否选择查询时间
			if(ServletActionContext.getRequest().getParameter("startDate") != null){
				if(ServletActionContext.getRequest().getParameter("startDate").equals("") == false){
				Date StartTime =sdf.parse(ServletActionContext.getRequest().getParameter("startDate"));
				startTime = new Timestamp(StartTime.getTime());
				}
				else{
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
		setCatalogList(new ArrayList<String>());    
		setSalesAmount(new ArrayList<Integer>()); 
		setSales(new ArrayList<Double>());
		List<TFirstcatalog> firstCatalogList = tFirstcatalogService.getFirstcatalogList();
		int salesTotalAmount = 0;
		double totalSales = 0;
		for(int i = 0; i < firstCatalogList.size(); i++)
		{
			System.out.println("一级目录:" + firstCatalogList.get(i).getFirstcatalogName());
			catalogList.add(firstCatalogList.get(i).getFirstcatalogName());
			int SalesTotalAmountInFC = 0;
			double TotalSalesInFC = 0;
			//获取一级目录下的所有商品
			List<TGoods> goodsInFirstCatalog = tGoodsService.getGoodListByFirstCatalog(firstCatalogList.get(i).getFirstcatalogId());
			System.out.println("共有商品:" + goodsInFirstCatalog.size());
			if(goodsInFirstCatalog.size() >= 1){
				for(int j = 0; j < goodsInFirstCatalog.size(); j++){
					//获取一级目录下商品的订单记录
					List<TOrderlistcontent> goodsOrderlist = tOrderlistcontentService.getOrderlistcontentByGoods(goodsInFirstCatalog.get(j), startTime, endTime);
					if(goodsOrderlist != null){
						for(int k = 0; k < goodsOrderlist.size(); k++){
							//计算一级目录下所有商品的销售量总和
							SalesTotalAmountInFC += goodsOrderlist.get(k).getOrderlistcontentNum();
							//商品销售总额
							TotalSalesInFC += goodsOrderlist.get(k).getOrderlistcontentTotalprice();
						}
					}
				}
			}
			salesAmount.add(SalesTotalAmountInFC);
			salesTotalAmount += SalesTotalAmountInFC;
			sales.add(TotalSalesInFC);
			totalSales += TotalSalesInFC;
			//获取一级目录下的二级目录
			List<TSecondcatalog> secondCatalogList = new ArrayList<TSecondcatalog>();
			secondCatalogList = tSecondcatalogService.getSecondcatalogList(firstCatalogList.get(i));
			if(secondCatalogList != null){
				for(int j = 0; j < secondCatalogList.size(); j++){
					System.out.println("二级目录:" + secondCatalogList.get(j).getSecondcatalogName());
					catalogList.add(secondCatalogList.get(j).getSecondcatalogName());
					//获取二级目录下的所有商品
					List<TGoods> goodsInSC = tGoodsService.getGoodsBySecondCatalog(secondCatalogList.get(j).getSecondcatalogId());
					System.out.println("共有商品:" + goodsInSC.size());
					int SalesAmountInSC = 0;
					double SalesInSC = 0;
					if(goodsInSC != null){
						for(int k = 0; k < goodsInSC.size(); k++){
							List<TOrderlistcontent> goodsOrderlistcontentInSC = tOrderlistcontentService.getOrderlistcontentByGoods(goodsInSC.get(k), startTime, endTime);
							if(goodsOrderlistcontentInSC != null){
								for(int m = 0; m < goodsOrderlistcontentInSC.size(); m++){
									SalesAmountInSC += goodsOrderlistcontentInSC.get(m).getOrderlistcontentNum();
									SalesInSC += goodsOrderlistcontentInSC.get(m).getOrderlistcontentTotalprice();
								}
							}
						}
					}
					salesAmount.add(SalesAmountInSC);
					sales.add(SalesInSC);
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("salesTotalAmount",salesTotalAmount);
		ServletActionContext.getRequest().setAttribute("totalSales",totalSales);
		ServletActionContext.getRequest().setAttribute("catalogList", getCatalogList());
		ServletActionContext.getRequest().setAttribute("salesAmount",getSalesAmount());
		ServletActionContext.getRequest().setAttribute("sales",getSales());
		//测试
		for(int i = 0; i < catalogList.size(); i++)
		{
			System.out.println(catalogList.get(i));
			System.out.println(salesAmount.get(i));
			System.out.println(sales.get(i));
		}
		return SUCCESS;
	}
	
	/**
	 * 显示二级目录下所有商品
	 * @author RichardChan
	 * @return
	 * @throws AdminException 
	 */
	@Action(value="showAllGoods", results={@Result(name="success",location="/webpages/back/add_deletegoods/add_deletegoods.jsp"),
										   @Result(name="jsonResult", type="json", params={"root", "goods"}),
										   @Result(name="jsonMessage", type="json", params={"root", "jsonMessage"})})
	public String adminGetSeCGoods() throws AdminException{
		int pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		int secondCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("secondcatalogId"));
		if (ServletActionContext.getRequest().getParameter("pageNum") == null || ServletActionContext.getRequest().getParameter("pageNum").equals("")) {
			AdminException exception = new AdminException("有参数不幸被大灰狼拐卖了...");
			exception.setExceptionLocation("TAdminAction:adminSearchUser");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		if (ServletActionContext.getRequest().getParameter("secondcatalogId") == null || ServletActionContext.getRequest().getParameter("secondcatalogId").equals("")) {
			AdminException exception = new AdminException("有参数不幸被大灰狼拐卖了...");
			exception.setExceptionLocation("TAdminAction:adminSearchUser");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		System.out.println(pageNum+"a"+secondCatalogId);
		System.out.println("lalaallallallalalalaalla");
		List<TGoods> goodsList = tGoodsService.getGoods(pageNum, secondCatalogId);
		setGoods(new ArrayList<TGoods>());
		setDiscountPrice(new ArrayList<Double>());
		if(goodsList != null)
		{
			for(int i = 0; i < goodsList.size(); i++){
				goods.add(goodsList.get(i));
				discountPrice.add(goodsList.get(i).getGoodsPrice() * goodsList.get(i).getGoodsDiscount());
			}
		}
		if(goodsList == null || goodsList.size() < 1){
			return SUCCESS;
		}
		if(pageNum == 0)
			return SUCCESS;
		else
			return "jsonResult";
	}
}
