package yp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.model.TFirstcatalog;
import yp.model.TSecondcatalog;
import yp.service.TFirstcatalogServiceI;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TFirstcatalogAction extends ActionSupport {
	private TFirstcatalogServiceI tFirstcatalogService;
	private List<TFirstcatalog> catalogList;// 一级目录列表
	private HashMap<String,String> returnJsonMap;//单值json返回通用

	public List<TFirstcatalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<TFirstcatalog> catalogList) {
		this.catalogList = catalogList;
	}

	public TFirstcatalogServiceI gettFirstcatalogService() {
		return tFirstcatalogService;
	}

	@Autowired
	public void settFirstcatalogService(
			TFirstcatalogServiceI tFirstcatalogService) {
		this.tFirstcatalogService = tFirstcatalogService;
	}

	/**
	 * 首页action,从此action中到处两个等级的目录，然后调用action链到TGoodsAction中的“
	 * getTop3FromEachBranch.action”，获取若干商品
	 * 
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "showHomePage", results = { @Result(name = "success", type = "chain", location = "getTop3FromEachBranch") })
	public String showHomePage() throws CShopException {
		catalogList = tFirstcatalogService.getFirstcatalogList();
		if(catalogList==null){
			//异常抛出
			CShopException exception = new CShopException("百年难得一遇的奇景！没有查到任何数据哦！");
			exception.setExceptionLocation("TFirstcatalogAction:showHomePage");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
		return SUCCESS;
	}
	
	/**
	 * 管理员点击“商品管理”,显示所有的目录
	 * @return
	 * @author YP
	 * @throws AdminException 
	 */
	@Action(value="adminEnterGoodsManage",results={@Result(name="success",location="/webpages/back/add_deletecategory/add_deletecategory.jsp")})
	public String adminEnterGoodsManage() throws AdminException{
		catalogList = tFirstcatalogService.getFirstcatalogList();
		//System.out.println(catalogList.get(0).getFirstcatalogName());
		if(catalogList != null){
			return "success";
		}else{
//			returnJsonMap = new HashMap<String,String>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "未知的错误！");
			//异常抛出
			AdminException exception = new AdminException("百年难得一遇的奇景！没有查到任何数据哦！");
			exception.setExceptionLocation("TFirstcatalogAction:adminEnterGoodsManage");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
		
	}

}
