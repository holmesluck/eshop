package yp.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;
import yp.model.TFavority;
import yp.model.TFavoritycontent;
import yp.model.view.GoodsJsonView;
import yp.service.TFavorityServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TFavorityAction extends ActionSupport{
	
	
	private TFavorityServiceI favoritySeviceI;
	private TFavority tFavority;
	private TFavoritycontent tFavoritycontent;
	private int isAddSuccessJson = 0;
	private int isDeleteSuccessJson = 0;
	private List<GoodsJsonView> goodsJsonList;// 商品列表(不含关联对象)(json返回通用)
	private HashMap<String,Object> returnJsonMap;//通用json数据构造Map
	
	public List<GoodsJsonView> getGoodsJsonList() {
		return goodsJsonList;
	}
	public void setGoodsJsonList(List<GoodsJsonView> goodsJsonList) {
		this.goodsJsonList = goodsJsonList;
	}
	
	public HashMap<String, Object> getReturnJsonMap() {
		return returnJsonMap;
	}
	public void setReturnJsonMap(HashMap<String, Object> returnJsonMap) {
		this.returnJsonMap = returnJsonMap;
	}
	public int getIsDeleteSuccessJson() {
		return isDeleteSuccessJson;
	}
	public void setIsDeleteSuccessJson(int isDeleteSuccessJson) {
		this.isDeleteSuccessJson = isDeleteSuccessJson;
	}
	/**
	 * 收藏夹商品列表
	 * @author yu
	 */
	private List<TFavoritycontent> listFavority;
	
	/**
	 * 系统提示给用户的String 类型信息
	 * @author yu
	 */
	private String strInfo = null;
	
	public int getIsAddSuccessJson() {
		return isAddSuccessJson;
	}
	public void setIsAddSuccessJson(int isAddSuccessJson) {
		this.isAddSuccessJson = isAddSuccessJson;
	}
	public String getStrInfo() {
		return strInfo;
	}
	public void setStrInfo(String strInfo) {
		this.strInfo = strInfo;
	}
	public List<TFavoritycontent> getListFavority() {
		return listFavority;
	}
	public void setListFavority(List<TFavoritycontent> listFavority) {
		this.listFavority = listFavority;
	}
	public TFavorityServiceI getFavoritySeviceI() {
		return favoritySeviceI;
	}
	@Autowired
	public void setFavoritySeviceI(TFavorityServiceI favoritySeviceI) {
		this.favoritySeviceI = favoritySeviceI;
	}
	public TFavority gettFavority() {
		return tFavority;
	}
	public void settFavority(TFavority tFavority) {
		this.tFavority = tFavority;
	}
	public TFavoritycontent gettFavoritycontent() {
		return tFavoritycontent;
	}
	public void settFavoritycontent(TFavoritycontent tFavoritycontent) {
		this.tFavoritycontent = tFavoritycontent;
	}

	/**
	 * 用户加入商品到收藏夹,成功则加入购物车，失败则提示用户。
	 * @param goodid 
	 * @return  返回json  ：  int 型  isAddSuccessJson  ,1 表示成功加入收藏夹，0表示已经在收藏夹中
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "userAddToFavority",results = {
			@Result(name = "error",location = "/webpages/userRegister/error.jsp"),
			@Result(name = "jsonResult",type = "json",params={"root","isAddSuccessJson"})})
	public String userAddToFavority() throws CShopException{
		//从URL获得商品id
		if(ServletActionContext.getRequest().getParameter("goodsId")==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TFavorityAction:userAddToFavority");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		Integer goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		//从session中获得用户id
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		if(session.get("userId")==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TFavorityAction:userAddToFavority");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		String userId_t = session.get("userId").toString();
		int userId = Integer.valueOf(userId_t);
		//从url传入userId值(测试用)
		//int userId = Integer.parseInt(ServletActionContext.getRequest().getParameter("userId"));
		if(favoritySeviceI.addToFavority(goodsId,userId)){
			strInfo = "商品成功添加到购物车";
			System.out.println(strInfo);
			isAddSuccessJson = 1;
			return "jsonResult";
		}	
		else{
			strInfo = "商品已添加到购物车";
			System.out.println(strInfo);
			isAddSuccessJson = 0;
			return "jsonResult";
		}
	}
	
	/**
	 * 用户进入收藏夹
	 * @param   page 
	 * @return 收藏夹商品   List类型  、json数据
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "userEnterFavority",results = {
			@Result(name = "success",location = "/webpages/web/favorite/favorite.jsp"),
			@Result(name = "jsonResult", type = "json", params = { "root",
			"returnJsonMap" }) 
			})
	public String userEnterFavority() throws CShopException{
		//从session中获得用户id
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		String userId_t = session.get("userId").toString();
		//String userId_t = ServletActionContext.getRequest().getSession().getAttribute("userId").toString();
		if(userId_t==null){
			//抛出异常
			CShopException exception=new CShopException("啊咧咧，长时间未响应，出错了哦");
        	exception.setExceptionLocation("TFavorityAction:userEnterFavority");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		int userId = Integer.valueOf(userId_t);
		//获得传入的page参数
		Integer page = 0;
		String strPage = ServletActionContext.getRequest().getParameter("page");
		if(strPage!=null)
			page = Integer.valueOf(strPage);
		
		listFavority = favoritySeviceI.enterFavority(userId,page);
		if(page==0){
			strInfo = "进入收藏夹页面";
			System.out.println(strInfo);
			return SUCCESS;
		}
		else{
			// 将goodsList转换成goodsJsonList
			goodsJsonList = new ArrayList<GoodsJsonView>();
			for (int i = 0; i < listFavority.size(); i++) {
				GoodsJsonView goods = new GoodsJsonView();
				goods.setGoodsId(listFavority.get(i).getTGoods().getGoodsId());
				goods.setGoodsDescription(listFavority.get(i).getTGoods()
					.getGoodsDescription());
				goods.setGoodsName(listFavority.get(i).getTGoods().getGoodsName());
				goods.setGoodsImage(listFavority.get(i).getTGoods().getGoodsImage());
				goods.setGoodsModel(listFavority.get(i).getTGoods().getGoodsModel());
				goods.setGoodsOperationSystem(listFavority.get(i).getTGoods()
					.getGoodsOperationSystem());
				goods.setGoodsProcessor(listFavority.get(i).getTGoods()
					.getGoodsProcessor());
				goods.setGoodsMemory(listFavority.get(i).getTGoods().getGoodsMemory());
				goods.setGoodsHarddiskCapacity(listFavority.get(i).getTGoods()
					.getGoodsHarddiskCapacity());
				goods.setGoodsScreenSize(listFavority.get(i).getTGoods().getGoodsScreenSize());
				goods.setGoodsResolutionDefinition(listFavority.get(i).getTGoods()
					.getGoodsResolutionDefinition());
				goods.setGoodsPrice(listFavority.get(i).getTGoods().getGoodsPrice());
				goods.setGoodsStock(listFavority.get(i).getTGoods().getGoodsStock());
				goods.setGoodsDiscount(listFavority.get(i).getTGoods().getGoodsDiscount());
				goods.setGoodsSales(listFavority.get(i).getTGoods().getGoodsSales());
				goods.setGoodsIsdel(listFavority.get(i).getTGoods().getGoodsIsdel());
				goods.setGoodsAveragemark(listFavority.get(i).getTGoods()
					.getGoodsAveragemark());
				goodsJsonList.add(goods);
			}
			returnJsonMap = new HashMap<String, Object>();
			returnJsonMap.put("goodsJsonList", goodsJsonList);
			return "jsonResult";
		}
	}
	/**
	 * 用户将商品从收藏夹删除
	 * @param  无（商品id）
	 * @return 返回json ： int 型  isDeleteSuccessJson 1表示删除成功  (已修改 ：重定向页面)
	 * @author yu
	 */
	@Action(value= "userDeleteFromFavority",results = {
			//@Result(name = "jsonResult",type = "json",params={"root","isDeleteSuccessJson"})
			@Result(name = "success",type = "redirectAction",location = "userEnterFavority")
			})
	public String userDeleteFromFavority(){
		//获得商品id
		Integer goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		//从session中获得用户id
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		String userId_t = session.get("userId").toString();
		int userId = Integer.valueOf(userId_t);
		//从url传入userId值(测试用)
		//int userId = Integer.parseInt(ServletActionContext.getRequest().getParameter("userId"));
		favoritySeviceI.deleteFromFavority(goodsId, userId);
		strInfo = "商品成功从收藏夹删除";
		System.out.println(strInfo);
		isDeleteSuccessJson =1;
		return "success";
	}
	
}
