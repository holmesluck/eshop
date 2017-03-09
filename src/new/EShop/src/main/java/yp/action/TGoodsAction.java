package yp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import yp.model.TGoods;
import yp.model.view.GoodsJsonView;
import yp.service.TGoodsServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TGoodsAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 354307790348307684L;
	private TGoodsServiceI tGoodsService;
	private TGoods aGoodsInfo;// 商品详情
	private List<TGoods> goodsList;// 商品列表(直取通用)
	private List<GoodsJsonView> goodsJsonList;// 商品列表(不含关联对象)(json返回通用)
	private HashMap<String,Object> returnJsonMap;//通用json数据构造Map
	
	private List<Double> discountPriceList;//折后价格

	public List<Double> getDiscountPriceList() {
		return discountPriceList;
	}

	public void setDiscountPriceList(List<Double> discountPriceList) {
		this.discountPriceList = discountPriceList;
	}

	public TGoods getaGoodsInfo() {
		return aGoodsInfo;
	}

	public void setaGoodsInfo(TGoods aGoodsInfo) {
		this.aGoodsInfo = aGoodsInfo;
	}

	public String getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(String searchConditions) {
		this.searchConditions = searchConditions;
	}

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
	}

	public String getSoldSort() {
		return soldSort;
	}

	public void setSoldSort(String soldSort) {
		this.soldSort = soldSort;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	/*****高搜post过来的参数******/
	private String searchConditions;
	private String priceSort;
	private String soldSort;
	private String page;
	/**************************/

	public TGoodsServiceI gettGoodsService() {
		return tGoodsService;
	}

	@Autowired
	public void settGoodsService(TGoodsServiceI tGoodsService) {
		this.tGoodsService = tGoodsService;
	}

	public List<TGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public TGoods getAGoodsInfo() {
		return aGoodsInfo;
	}

	public void setAGoodsInfo(TGoods aGoodsInfo) {
		this.aGoodsInfo = aGoodsInfo;
	}

	public List<GoodsJsonView> getGoodsJsonList() {
		return goodsJsonList;
	}

	public void setGoodsJsonList(List<GoodsJsonView> goodsJsonList) {
		this.goodsJsonList = goodsJsonList;
	}
	
	public HashMap<String,Object> getReturnJsonMap() {
		return returnJsonMap;
	}

	public void setReturnJsonMap(HashMap<String,Object> returnJsonMap) {
		this.returnJsonMap = returnJsonMap;
	}

	/**
	 * 普通用户查看商品详情
	 * 
	 * @param goodsId
	 *            商品id
	 * @return success |
	 * 			error:含json状态码及错误信息
	 * @author YP
	 * @throws CShopException 
	 * @throws AdminException 
	 */
	@Action(value = "showAGoodsInfo", results = { 
			@Result(name = "success", location = "/webpages/web/goodsinfo/goods_info.jsp"),
			@Result(name = "error", type = "json",params={"root","returnJsonMap"})
	})
	public String showAGoodsInfo() throws CShopException, AdminException {
		String goodId = ServletActionContext.getRequest().getParameter("goodsId");
		if(goodId==null){
			//异常抛出
			CShopException exception = new CShopException("您好像没有选择商品！传入商品ID为空哦！");
			exception.setExceptionLocation("TGoodsAction:showAGoodsInfo");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
		Integer goodsId = Integer.parseInt(goodId);
		aGoodsInfo = tGoodsService.getAGoodsInfo(goodsId);
		if(aGoodsInfo!=null){//成功获取
			return SUCCESS;
		}else{
			//异常抛出
			//CShopException exception = new CShopException("就在这一瞬间，此商品已被下架哦！");
			CShopException exception = new CShopException("就在这一瞬间，此商品已被下架哦！");
			exception.setExceptionLocation("TGoodsAction:showAGoodsInfo");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}	
	}

	/**
	 * 首页显示的子aciton，获取每个品牌的三台电脑
	 * 
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "getTop3FromEachBranch", results = {
			@Result(name = "success", location = "/webpages/web/home/home.jsp"),
			@Result(name = "input", type = "json", params = { "root",
					"goodsList" }) })
	public String getTop3FromEachBranch() throws CShopException {
		goodsList = tGoodsService.getTop3FromEachBranch();
//		if(goodsList == null){
//			//goodsList = new ArrayList<TGoods>();
//		 }
		if(goodsList==null){
			//异常抛出
			CShopException exception = new CShopException("出现了一个奇怪的景象：数据库取不到数据啦！管理员会很快处理好的！");
			exception.setExceptionLocation("TGoodsAction:getTop3FromEachBranch");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
		
		//控制精度,把控制了精度之后的折后价存入值栈中
		discountPriceList = new ArrayList<Double>();
		for(int i=0;i<goodsList.size();i++)
		{
			Double price=goodsList.get(i).getGoodsPrice();
			Double discount=goodsList.get(i).getGoodsDiscount();
			//result.
			Double result = round(discount*price,2,BigDecimal.ROUND_UP);
			discountPriceList.add(i,result);
		}
		System.out.println(discountPriceList);
		return SUCCESS;
	}

	/**
	 * 快速搜索，输入值有： searchWords(空格要做分词);//进队description模糊搜索
	 * priceSort("asc"|"desc"|"no"); soldSort("asc"|"desc"|"no")
	 * page(页码，为0，则结果重定向；否则为穿json数据)
	 * 
	 * @return 
	 * 		page(1+):goodsJsonList
	 * 
	 * @throws UnsupportedEncodingException
	 *             ：中文转码失败
	 * 
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "quickSearchGoods", results = {
			@Result(name = "redirectionResult", location = "/webpages/web/goodsquicksearch/goodsquciksearch.jsp"),
			@Result(name = "jsonResult", type = "json", params = { "root",
					"returnJsonMap" }), 
			@Result(name = "error", type = "json", params = { "root",
			"returnJsonMap" }) 
	})
	public String quickSearchGoods() throws UnsupportedEncodingException, CShopException {
		
		String searchWords = ServletActionContext.getRequest().getParameter(
				"searchWords");		
		//System.out.println(searchWords);
		String priceSort = ServletActionContext.getRequest().getParameter(
				"priceSort");
		String soldSort = ServletActionContext.getRequest().getParameter(
				"soldSort");
		String pageStr = ServletActionContext.getRequest()
				.getParameter("page");
		
		//如果需要，添加默认条件
		if(priceSort==null&&soldSort==null&&pageStr==null){
			priceSort = "no";
			soldSort = "no";
			pageStr = "0";
		}
		
		Integer page = Integer.parseInt(pageStr);
		if(page==0){//需要转码
			searchWords = new String(searchWords.getBytes("ISO8859_1"), "UTF-8");// 转码，解决get方法传参的中文乱码问题
		}
		
		// 只有搜索words不为空才搜索
		if (!(searchWords == null)/* && !(searchWords.equals(""))*/) {
			if(searchWords.equals("")){//让之无法取到数据
				searchWords = "%!%!%!%!%!%!%!%%!*****";
			}
			goodsList = tGoodsService.getQuickSearchGoods(searchWords,
					priceSort, soldSort, page);
			if(goodsList==null){
				//异常抛出
				CShopException exception = new CShopException("出现了一个奇怪的景象：数据库取不到数据啦！管理员会很快处理好的！");
				exception.setExceptionLocation("TGoodsAction:quickSearchGoods");
				exception.setExceptionType(CShopExceptionI.DATABASE);		
				throw exception;
			}
			
			//控制精度,把控制了精度之后的折后价存入值栈中
			discountPriceList = new ArrayList<Double>();
			for(int i=0;i<goodsList.size();i++)
			{
				Double price=goodsList.get(i).getGoodsPrice();
				Double discount=goodsList.get(i).getGoodsDiscount();
				//result.
				Double result = round(discount*price,2,BigDecimal.ROUND_UP);
				discountPriceList.add(i,result);
			}
			
		}else{
//			//报错
//			returnJsonMap = new HashMap<String, Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "搜索条件为空！");
//			return ERROR;
			//异常抛出
			CShopException exception = new CShopException("管理员把此搜索关掉了哦！前台毫无传入搜索条件的迹象！");
			exception.setExceptionLocation("TGoodsAction:quickSearchGoods");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
		
		//将此次搜索的关键词加入session
		//ServletActionContext.getRequest().getSession().setAttribute("searchWords", searchWords);
		Map session = ActionContext.getContext().getSession();
		session.put("searchWords", searchWords);
		if (page.equals(0)) {
			return "redirectionResult";
		} else {
			// 将goodsList转换成goodsJsonList
			goodsJsonList = new ArrayList<GoodsJsonView>();
			for (int i = 0; i < goodsList.size(); i++) {
				GoodsJsonView goods = new GoodsJsonView();
				goods.setGoodsId(goodsList.get(i).getGoodsId());
				goods.setGoodsDescription(goodsList.get(i)
						.getGoodsDescription());
				goods.setGoodsName(goodsList.get(i).getGoodsName());
				goods.setGoodsImage(goodsList.get(i).getGoodsImage());
				goods.setGoodsModel(goodsList.get(i).getGoodsModel());
				goods.setGoodsOperationSystem(goodsList.get(i)
						.getGoodsOperationSystem());
				goods.setGoodsProcessor(goodsList.get(i)
						.getGoodsProcessor());
				goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
				goods.setGoodsHarddiskCapacity(goodsList.get(i)
						.getGoodsHarddiskCapacity());
				goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
				goods.setGoodsResolutionDefinition(goodsList.get(i)
						.getGoodsResolutionDefinition());
				goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
				goods.setGoodsStock(goodsList.get(i).getGoodsStock());
				goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
				goods.setGoodsSales(goodsList.get(i).getGoodsSales());
				goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
				goods.setGoodsAveragemark(goodsList.get(i)
						.getGoodsAveragemark());
				goodsJsonList.add(goods);
			}
			returnJsonMap = new HashMap<String, Object>();
			returnJsonMap.put("goodsJsonList", goodsJsonList);
			return "jsonResult";
		}
	}

	/**
	 * 高级搜索商品。输入值有：
	 * 16个checkbox选值(以字符串中间逗号形式)
	 * searchConditions：联想1、宏基2、戴尔3、华硕4
	 * 					3000以下、3000-3999、4000-4999、5000以上
	 * 					酷睿i3	酷睿i5	酷睿i7 AMD
	 * 					内存1GB	2GB	4GB	8GB
	 * priceSort("asc"|"desc"|"no"); soldSort("asc"|"desc"|"no")
	 * page(页码，为0，则结果重定向；否则为穿json数据)
	 * @return
	 * 		page(1+):goodsJsonList
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "advancedSearchGoods", results = {
			@Result(name = "redirectionResult", location = "/webpages/web/goodsadvancesearch/advancesearch.jsp"),
			@Result(name = "jsonResult", type = "json", params = { "root",
					"returnJsonMap" }),
			@Result(name = "error", type = "json", params = { "root",
			"returnJsonMap" }) 
	})
	public String advancedSearchGoods() throws CShopException{
		//检验条件输入是否完备
		if(searchConditions==null||priceSort==null||soldSort==null||page==null){
			if(searchConditions==null&&priceSort==null&&soldSort==null&&page==null){//说明是初次进入，按默认状态
				searchConditions = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
				priceSort = "no";
				soldSort = "no";
				page = "0";
			}
		}
		goodsList = tGoodsService.getAdvancedSearchGoods(searchConditions, priceSort, soldSort, page);
		if(goodsList == null){//输入的条件有误
//			//报错
//			returnJsonMap = new HashMap<String, Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "输入条件有误！");
//			throw new CShopException("输入条件有误！");
//			//return ERROR;
			//异常抛出
			CShopException exception = new CShopException("出现了一个奇怪的景象：数据库取不到数据啦！管理员会很快处理好的！");
			exception.setExceptionLocation("TGoodsAction:advancedSearchGoods");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
		
		//控制精度,把控制了精度之后的折后价存入值栈中
		discountPriceList = new ArrayList<Double>();
		for(int i=0;i<goodsList.size();i++)
		{
			Double price=goodsList.get(i).getGoodsPrice();
			Double discount=goodsList.get(i).getGoodsDiscount();
			//result.
			Double result = round(discount*price,2,BigDecimal.ROUND_UP);
			discountPriceList.add(i,result);
		}
		
		
		if (page.equals("0")) {
			return "redirectionResult";
		} else {
			// 将goodsList转换成goodsJsonList
			goodsJsonList = new ArrayList<GoodsJsonView>();
			for (int i = 0; i < goodsList.size(); i++) {
				GoodsJsonView goods = new GoodsJsonView();
				goods.setGoodsId(goodsList.get(i).getGoodsId());
				goods.setGoodsDescription(goodsList.get(i)
						.getGoodsDescription());
				goods.setGoodsName(goodsList.get(i).getGoodsName());
				goods.setGoodsImage(goodsList.get(i).getGoodsImage());
				goods.setGoodsModel(goodsList.get(i).getGoodsModel());
				goods.setGoodsOperationSystem(goodsList.get(i)
						.getGoodsOperationSystem());
				goods.setGoodsProcessor(goodsList.get(i)
						.getGoodsProcessor());
				goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
				goods.setGoodsHarddiskCapacity(goodsList.get(i)
						.getGoodsHarddiskCapacity());
				goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
				goods.setGoodsResolutionDefinition(goodsList.get(i)
						.getGoodsResolutionDefinition());
				goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
				goods.setGoodsStock(goodsList.get(i).getGoodsStock());
				goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
				goods.setGoodsSales(goodsList.get(i).getGoodsSales());
				goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
				goods.setGoodsAveragemark(goodsList.get(i)
						.getGoodsAveragemark());
				goodsJsonList.add(goods);
			}
			returnJsonMap = new HashMap<String, Object>();
			returnJsonMap.put("goodsJsonList", goodsJsonList);
			return "jsonResult";
		}
	}
	
	/**
	 *管理员根据二级目录id与页面数获得相应商品列表
	 *@param secondCatalogId url传入
	 *			二级目录id
	 *		pageNum url传入
	 *			页面数
	 *@result goodsList 
	 *			如正常为商品列表
	 *			如不正常或不存在为空列表
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 */
	@Action(value = "adminEnterSeCatalogGoods", results = {
			@Result(name = "success", location = "/webpages/back/add_deletegoods/add_deletegoods.jsp"),
			@Result(name = "more", type = "json", params = { "root",
					"goodsJsonList" })})
	public String adminEnterSeCatalogGoods() throws IOException, AdminException{
		Integer pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		Integer secondCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("secondCatalogId"));
		//检查参数
				if(pageNum==null)
					pageNum=0;
				if(secondCatalogId==null){
					AdminException exception=new AdminException("网络问题导致数据丢失，请重新操作一次");
					exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
					exception.setExceptionType(CShopExceptionI.PARAMETERS);
					throw exception;
				}
		
		
		
		//pageNum为0时重定向回页面，大于0时返回json
		if(pageNum==0){
			goodsList = tGoodsService.getGoodListBySecondCatalogIdAndPage(secondCatalogId, pageNum,"no","no");
			System.out.println(goodsList);
			//控制精度,把控制了精度之后的折后价存入值栈中
			discountPriceList = new ArrayList<Double>();
			for(int i=0;i<goodsList.size();i++)
			{
				Double price=goodsList.get(i).getGoodsPrice();
				Double discount=goodsList.get(i).getGoodsDiscount();
				//result.
				Double result = round(discount*price,2,BigDecimal.ROUND_UP);
				discountPriceList.add(i,result);
			}
				return "success";
		}
		else if(pageNum>0){
			goodsList = tGoodsService.getGoodListBySecondCatalogIdAndPage(secondCatalogId, pageNum,"no","no");
			System.out.println(goodsList);
			// 将goodsList转换成goodsJsonList已作为json返回
						goodsJsonList = new ArrayList<GoodsJsonView>();
						for (int i = 0; i < goodsList.size(); i++) {
							GoodsJsonView goods = new GoodsJsonView();
							goods.setGoodsId(goodsList.get(i).getGoodsId());
							goods.setGoodsDescription(goodsList.get(i)
									.getGoodsDescription());
							goods.setGoodsName(goodsList.get(i).getGoodsName());
							goods.setGoodsImage(goodsList.get(i).getGoodsImage());
							goods.setGoodsModel(goodsList.get(i).getGoodsModel());
							goods.setGoodsOperationSystem(goodsList.get(i)
									.getGoodsOperationSystem());
							goods.setGoodsProcessor(goodsList.get(i)
									.getGoodsProcessor());
							goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
							goods.setGoodsHarddiskCapacity(goodsList.get(i)
									.getGoodsHarddiskCapacity());
							goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
							goods.setGoodsResolutionDefinition(goodsList.get(i)
									.getGoodsResolutionDefinition());
							goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
							goods.setGoodsStock(goodsList.get(i).getGoodsStock());
							goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
							goods.setGoodsSales(goodsList.get(i).getGoodsSales());
							goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
							goods.setGoodsAveragemark(goodsList.get(i)
									.getGoodsAveragemark());
							goodsJsonList.add(goods);
										
						}
						System.out.println(goodsJsonList);
						//如要检查显示json则取消下面注释
					    JSONObject json = new JSONObject();
						json.accumulate("goodsJsonList", goodsJsonList);
				     	//解决乱码问题
						/*HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
						response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

						PrintWriter out = response.getWriter(); 
						out.write(json.toString());
						out.flush();
						out.close();*/
						//json返回			
						return "more";		
		}

		return "ERROR";	
	}
	
	/**
	 *管理员根据一级目录id与页面数获得相应商品列表
	 *@param firstCatalogId url传入
	 *			一级目录id
	 *		pageNum url传入
	 *			页面数
	 *@result goodsList 
	 *			如正常为商品列表
	 *			如不正常或不存在为空列表
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 */
	@Action(value = "adminEnterFiCatalogGoods", results = {
			@Result(name = "success", location = "/webpages/web/goodsquicksearch/goodsquciksearch.jsp"),
			@Result(name = "more", type = "json", params = { "root",
					"goodsJsonList" })})
	public String adminEnterFiCatalogGoods() throws IOException, AdminException{
		Integer pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		Integer firstCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("firstCatalogId"));
		//检查参数
		if(pageNum==null)
			pageNum=0;
		if(firstCatalogId==null){
			AdminException exception=new AdminException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		
		//pageNum为0时重定向回页面，大于0时返回json
		if(pageNum==0){
			
			goodsList = tGoodsService.getGoodListByFirstCatalogIdAndPage(firstCatalogId, pageNum,"no","no");
			System.out.println(goodsList);
			//控制精度,把控制了精度之后的折后价存入值栈中
			discountPriceList = new ArrayList<Double>();
			for(int i=0;i<goodsList.size();i++)
			{
				Double price=goodsList.get(i).getGoodsPrice();
				Double discount=goodsList.get(i).getGoodsDiscount();
				//result.
				Double result = round(discount*price,2,BigDecimal.ROUND_UP);
				discountPriceList.add(i,result);
			}
				return "success";
		}
		else if(pageNum>0){
			goodsList = tGoodsService.getGoodListByFirstCatalogIdAndPage(firstCatalogId, pageNum,"no","no");
			
			// 将goodsList转换成goodsJsonList来作为json返回
						goodsJsonList = new ArrayList<GoodsJsonView>();
						for (int i = 0; i < goodsList.size(); i++) {
							GoodsJsonView goods = new GoodsJsonView();
							goods.setGoodsId(goodsList.get(i).getGoodsId());
							goods.setGoodsDescription(goodsList.get(i)
									.getGoodsDescription());
							goods.setGoodsName(goodsList.get(i).getGoodsName());
							goods.setGoodsImage(goodsList.get(i).getGoodsImage());
							goods.setGoodsModel(goodsList.get(i).getGoodsModel());
							goods.setGoodsOperationSystem(goodsList.get(i)
									.getGoodsOperationSystem());
							goods.setGoodsProcessor(goodsList.get(i)
									.getGoodsProcessor());
							goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
							goods.setGoodsHarddiskCapacity(goodsList.get(i)
									.getGoodsHarddiskCapacity());
							goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
							goods.setGoodsResolutionDefinition(goodsList.get(i)
									.getGoodsResolutionDefinition());
							goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
							goods.setGoodsStock(goodsList.get(i).getGoodsStock());
							goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
							goods.setGoodsSales(goodsList.get(i).getGoodsSales());
							goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
							goods.setGoodsAveragemark(goodsList.get(i)
									.getGoodsAveragemark());
							goodsJsonList.add(goods);
				
						}
						System.out.println(goodsJsonList);
						//如要检查显示json取消下面注释
						//显示json
					    //JSONObject json = new JSONObject();
						//json.accumulate("goodsJsonList", goodsJsonList);
				     	//解决乱码问题
						/*HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
						response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

						PrintWriter out = response.getWriter(); 
						out.write(json.toString());
						out.flush();
						out.close();*/
						//json返回			
						//json返回			
						return "more";
		}
		return "ERROR";
	}

	
	
	
	
	/**
	 *普通用户根据二级目录id与页面数获得相应商品列表
	 *@param secondCatalogId url传入
	 *			二级目录id
	 *		pageNum url传入
	 *			页面数
	 *		priceSort burl传入
	 *			价格排序方式
	 *		soldSort url传入
	 *			销量排序方式
	 *@result goodsList 
	 *			如正常为商品列表
	 *			如不正常或不存在为空列表
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 */
	@Action(value = "enterSeCatalogGoods", results = {
			@Result(name = "success", location = "/webpages/web/seriesgoodslist/seriersgoodslist.jsp"),
			@Result(name = "more", type = "json", params = { "root",
					"returnJsonMap" })})
	public String enterSeCatalogGoods() throws IOException, CShopException{
		Integer pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		Integer secondCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("secondCatalogId"));
		//检查参数
		if(pageNum==null)
			pageNum=0;
		if(secondCatalogId==null){
			CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		
		
		//接收排序方式
		String priceSort = ServletActionContext.getRequest().getParameter(
				"priceSort");
		String soldSort = ServletActionContext.getRequest().getParameter(
				"soldSort");
		
		if(priceSort==null){
			priceSort = "no";
		}
		if(soldSort==null){
			soldSort = "no";
		}
		
		//pageNum为0时重定向回页面，大于0时返回json
		if(pageNum==0){
			goodsList = tGoodsService.getGoodListBySecondCatalogIdAndPage(secondCatalogId, pageNum,priceSort,soldSort);
			System.out.println(goodsList);
			//控制精度,把控制了精度之后的折后价存入值栈中
			discountPriceList = new ArrayList<Double>();
			for(int i=0;i<goodsList.size();i++)
			{
				Double price=goodsList.get(i).getGoodsPrice();
				Double discount=goodsList.get(i).getGoodsDiscount();
				//result.
				Double result = round(discount*price,2,BigDecimal.ROUND_UP);
				discountPriceList.add(i,result);
			}
				return "success";
		}
		else if(pageNum>0){
			goodsList = tGoodsService.getGoodListBySecondCatalogIdAndPage(secondCatalogId, pageNum,priceSort,soldSort);
			System.out.println(goodsList);
			// 将goodsList转换成goodsJsonList已作为json返回
						goodsJsonList = new ArrayList<GoodsJsonView>();
						for (int i = 0; i < goodsList.size(); i++) {
							GoodsJsonView goods = new GoodsJsonView();
							goods.setGoodsId(goodsList.get(i).getGoodsId());
							goods.setGoodsDescription(goodsList.get(i)
									.getGoodsDescription());
							goods.setGoodsName(goodsList.get(i).getGoodsName());
							goods.setGoodsImage(goodsList.get(i).getGoodsImage());
							goods.setGoodsModel(goodsList.get(i).getGoodsModel());
							goods.setGoodsOperationSystem(goodsList.get(i)
									.getGoodsOperationSystem());
							goods.setGoodsProcessor(goodsList.get(i)
									.getGoodsProcessor());
							goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
							goods.setGoodsHarddiskCapacity(goodsList.get(i)
									.getGoodsHarddiskCapacity());
							goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
							goods.setGoodsResolutionDefinition(goodsList.get(i)
									.getGoodsResolutionDefinition());
							goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
							goods.setGoodsStock(goodsList.get(i).getGoodsStock());
							goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
							goods.setGoodsSales(goodsList.get(i).getGoodsSales());
							goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
							goods.setGoodsAveragemark(goodsList.get(i)
									.getGoodsAveragemark());
							goodsJsonList.add(goods);
										
						}
						System.out.println(goodsJsonList);
						//如要检查显示json则取消下面注释
					    //JSONObject json = new JSONObject();
						//json.accumulate("goodsJsonList", goodsJsonList);
				     	//解决乱码问题
						/*HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
						response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

						PrintWriter out = response.getWriter(); 
						out.write(json.toString());
						out.flush();
						out.close();*/
						//json返回	
						returnJsonMap = new HashMap<String, Object>();
						returnJsonMap.put("goodsJsonList", goodsJsonList);
						return "more";		
		}

		return "ERROR";	
	}
	
	/**
	 *普通用户根据一级目录id与页面数获得相应商品列表
	 *@param firstCatalogId url传入
	 *			一级目录id
	 *		pageNum url传入
	 *			页面数
	 *		priceSort url传入
	 *			价格排序方式
	 *		soldSort url传入
	 *			销量排序方式
	 *@result goodsList 
	 *			如正常为商品列表
	 *			如不正常或不存在为空列表
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 */
	@Action(value = "enterFiCatalogGoods", results = {
			@Result(name = "success", location = "/webpages/web/firstCategorygoodslist/firstCategorygoodslist.jsp"),
			@Result(name = "more", type = "json", params = { "root",
					"returnJsonMap" })})
	public String enterFiCatalogGoods() throws IOException, CShopException{
		Integer pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		Integer firstCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("firstCatalogId"));
		//检查参数
		if(pageNum==null)
			pageNum=0;
		if(firstCatalogId==null){
			CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		//接收排序方式
				String priceSort = ServletActionContext.getRequest().getParameter(
						"priceSort");
				String soldSort = ServletActionContext.getRequest().getParameter(
						"soldSort");
				
				if(priceSort==null){
					priceSort = "no";
				}
				if(soldSort==null){
					soldSort = "no";
				}
		
		//pageNum为0时重定向回页面，大于0时返回json
		if(pageNum==0){
			
			goodsList = tGoodsService.getGoodListByFirstCatalogIdAndPage(firstCatalogId, pageNum,priceSort,soldSort);
			System.out.println(goodsList);
			//控制精度,把控制了精度之后的折后价存入值栈中
			discountPriceList = new ArrayList<Double>();
			for(int i=0;i<goodsList.size();i++)
			{
				Double price=goodsList.get(i).getGoodsPrice();
				Double discount=goodsList.get(i).getGoodsDiscount();
				//result.
				Double result = round(discount*price,2,BigDecimal.ROUND_UP);
				discountPriceList.add(i,result);
			}
				return "success";
		}
		else if(pageNum>0){
			goodsList = tGoodsService.getGoodListByFirstCatalogIdAndPage(firstCatalogId, pageNum,priceSort,soldSort);
			
			// 将goodsList转换成goodsJsonList来作为json返回
						goodsJsonList = new ArrayList<GoodsJsonView>();
						for (int i = 0; i < goodsList.size(); i++) {
							GoodsJsonView goods = new GoodsJsonView();
							goods.setGoodsId(goodsList.get(i).getGoodsId());
							goods.setGoodsDescription(goodsList.get(i)
									.getGoodsDescription());
							goods.setGoodsName(goodsList.get(i).getGoodsName());
							goods.setGoodsImage(goodsList.get(i).getGoodsImage());
							goods.setGoodsModel(goodsList.get(i).getGoodsModel());
							goods.setGoodsOperationSystem(goodsList.get(i)
									.getGoodsOperationSystem());
							goods.setGoodsProcessor(goodsList.get(i)
									.getGoodsProcessor());
							goods.setGoodsMemory(goodsList.get(i).getGoodsMemory());
							goods.setGoodsHarddiskCapacity(goodsList.get(i)
									.getGoodsHarddiskCapacity());
							goods.setGoodsScreenSize(goodsList.get(i).getGoodsScreenSize());
							goods.setGoodsResolutionDefinition(goodsList.get(i)
									.getGoodsResolutionDefinition());
							goods.setGoodsPrice(goodsList.get(i).getGoodsPrice());
							goods.setGoodsStock(goodsList.get(i).getGoodsStock());
							goods.setGoodsDiscount(goodsList.get(i).getGoodsDiscount());
							goods.setGoodsSales(goodsList.get(i).getGoodsSales());
							goods.setGoodsIsdel(goodsList.get(i).getGoodsIsdel());
							goods.setGoodsAveragemark(goodsList.get(i)
									.getGoodsAveragemark());
							goodsJsonList.add(goods);
				
						}
						System.out.println(goodsJsonList);
						//如要检查显示json取消下面注释
						//显示json
					    JSONObject json = new JSONObject();
						json.accumulate("goodsJsonList", goodsJsonList);
				     	//解决乱码问题
						/*HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
						response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

						PrintWriter out = response.getWriter(); 
						out.write(json.toString());
						out.flush();
						out.close();*/
						//json返回			
						//json返回
						returnJsonMap = new HashMap<String, Object>();
						returnJsonMap.put("goodsJsonList", goodsJsonList);
						return "more";
		}
		return "ERROR";
	}
	
	/**
	 * 管理员进入商品详情页面
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "adminGetAGoodsInfo", results = { 
			@Result(name = "success", location = "/webpages/back/goodsdetailsview/goodsdetailview.jsp"),
			@Result(name = "error", type = "json",params={"root","returnJsonMap"})
	})
	public String adminGetAGoodsInfo() throws CShopException{
		String goodId = ServletActionContext.getRequest().getParameter("goodsId");
		if(goodId==null){
			//异常抛出
			CShopException exception = new CShopException("您好像没有选择好商品！传入商品ID为空哦！");
			exception.setExceptionLocation("TGoodsAction:adminGetAGoodsInfo");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
		Integer goodsId = Integer.parseInt(goodId);
		aGoodsInfo = tGoodsService.getAGoodsInfo(goodsId);
		if(aGoodsInfo!=null){//成功获取
			return SUCCESS;
		}else{
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message","此商品已下架！");
//			return ERROR;
			//异常抛出
			CShopException exception = new CShopException("出现了一个奇怪的景象：数据库取不到数据啦！管理员会很快处理好的！");
			exception.setExceptionLocation("TGoodsAction:adminGetAGoodsInfo");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}	
	}
	
	
	public static double round(double value,int scale,int roundingMode){
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale,roundingMode);
		double d = bd.doubleValue();
		bd=null;
		return d;
	}
}
