package yp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.model.TSecondcatalog;
import yp.model.view.SecondcatalogJsonView;
import yp.service.TSecondcatalogServiceI;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TSecondcatalogAction extends ActionSupport{
	private TSecondcatalogServiceI secondcatalogService;
	private HashMap<String,Object> returnJsonMap;//单值json返回通用
	private TSecondcatalog ASecondCatalogInfo;//二级目录详细信息
	private List<SecondcatalogJsonView> secondCatalogJsonList;//二级目录jsonlist
	private String jsonMessage; //返回json消息
	
	public String getJsonMessage() {
		return jsonMessage;
	}

	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}

	public TSecondcatalog getASecondCatalogInfo() {
		return ASecondCatalogInfo;
	}

	public void setASecondCatalogInfo(TSecondcatalog aSecondCatalogInfo) {
		ASecondCatalogInfo = aSecondCatalogInfo;
	}
	
	public HashMap<String, Object> getReturnJsonMap() {
		return returnJsonMap;
	}

	public void setReturnJsonMap(HashMap<String, Object> returnJsonMap) {
		this.returnJsonMap = returnJsonMap;
	}
	
	public List<SecondcatalogJsonView> getSecondCatalogJsonList() {
		return secondCatalogJsonList;
	}

	public void setSecondCatalogJsonList(List<SecondcatalogJsonView> secondCatalogJsonList) {
		this.secondCatalogJsonList = secondCatalogJsonList;
	}
	
	public TSecondcatalogServiceI getSecondcatalogService() {
		return secondcatalogService;
	}

	@Autowired
	public void setSecondcatalogService(TSecondcatalogServiceI secondcatalogService) {
		this.secondcatalogService = secondcatalogService;
	}
	
	/**
	 * 管理员进入二级目录修改页面，把点击修改的目录信息显示出来
	 * @param secondCatalogId
	 * 			二级目录id
	 * @return
	 * @author YP
	 * @throws AdminException 
	 */
	@Action(value="adminEnterSeCatalogModify",results = {
			@Result(name="success",location="/webpages/back/modifycategory/modifycategory.jsp"),
			@Result(name="error",type="json",params={"root","returnJsonMap"})
	})
	public String adminEnterSeCatalogModify() throws AdminException{
		String secondCatalogId = ServletActionContext.getRequest().getParameter("secondCatalogId");
		if(secondCatalogId==null){
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "传入二级目录id为空");
			
			//抛出异常
			AdminException exception = new AdminException("传入二级目录id为空!");
			exception.setExceptionLocation("TSecondcatalogAction:adminEnterSeCatalogModify");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
			//return ERROR;
		}
		ASecondCatalogInfo = secondcatalogService.getASeCatalogInfo(Integer.parseInt(secondCatalogId));
		if(ASecondCatalogInfo!=null){
			return "success";
		}else{
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "此目录不存在！");
//			return ERROR;
			//抛出异常
			AdminException exception = new AdminException("此目录不存在！");
			exception.setExceptionLocation("TSecondcatalogAction:adminEnterSeCatalogModify");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}	
	}
	
	/**
	 * 管理员添加新二级目录,相同目录插入出错(目录名已有，且在同一父目录下)
	 * @param catalogName
	 * 			目录名
	 * @param firstCatalogId
	 * 			父目录id
	 * @param catalogDescription
	 * 			目录描述
	 * @return success 或 error附加错误信息
	 * @author YP
	 * @throws AdminException 
	 */
	@Action(value = "adminAddGoodsSeCatalog",results ={
			@Result(name="success",type="redirectAction",location="adminEnterGoodsManage"),
			@Result(name="error",type="json",params={"root","returnJsonMap"})
	})
	public String adminAddGoodsSeCatalog() throws AdminException{
		String catalogName = ServletActionContext.getRequest().getParameter("catalogName");
		String firstCatalogId = ServletActionContext.getRequest().getParameter("firstCatalogId");
		String catalogDescription = ServletActionContext.getRequest().getParameter("catalogDescription");
		//检查参数是否完整(目录名非空、父目录非空、描述可以为空)
		if(catalogName == null || firstCatalogId == null){
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "传入的参数不完整！");
//			return ERROR;
			//异常抛出
			AdminException exception = new AdminException("您好像没填好必要的信息哦！传入的信息不完整呢！");
			exception.setExceptionLocation("TSecondcatalogAction:adminAddGoodsSeCatalog");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
		if(secondcatalogService.
				addASeCatalog(catalogName, Integer.parseInt(firstCatalogId), catalogDescription)==true){
			//returnJsonMap = new HashMap<String,String>();
			//returnJsonMap.put("status", "success");
			return "success";
		}else{
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "已有相同目录或其他错误！");
//			return ERROR;
			//异常抛出
			AdminException exception = new AdminException("尊敬的管理员：不好意思，数据库中已经有了同名的目录了，请您更换目录名！如果更换目录名后问题还没解决，请联系工程师！");
			exception.setExceptionLocation("TSecondcatalogAction:adminAddGoodsSeCatalog");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
	}
	
	/**
	 * 管理员修改二级目录
	 * @param seCatalogId
	 * 			二级目录id
	 * @param catalogName
	 * 			二级目录名字
	 * @param firstCatalogId
	 * 			父目录id
	 * @param catalogDescription
	 * 			目录描述
	 * @return success 或 error附加错误信息
	 * @author YP
	 * @throws AdminException 
	 */
	@Action(value = "adminModifyGoodsSeCatalog",results ={
			@Result(name="success",type = "redirectAction",location="adminEnterGoodsManage"),
			@Result(name="jsonMessage",type="json",params={"root","jsonMessage"})
	})
	public String adminModifyGoodsSeCatalog() throws AdminException{
		String seCatalogId = ServletActionContext.getRequest().getParameter("seCatalogId");
		String catalogName = ServletActionContext.getRequest().getParameter("catalogName");
		String firstCatalogId = ServletActionContext.getRequest().getParameter("firstCatalogId");
		String catalogDescription = ServletActionContext.getRequest().getParameter("catalogDescription");
		//检查参数是否完整(目录名非空、父目录非空、描述可以为空)
		if(seCatalogId==null || catalogName == null || firstCatalogId == null){
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "传入的参数不完整！");
//			return ERROR;
			//异常抛出
			AdminException exception = new AdminException("您好像没填好必要的信息哦！传入的信息不完整呢！");
			exception.setExceptionLocation("TSecondcatalogAction:adminModifyGoodsSeCatalog");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}
		if(secondcatalogService.
				modifySeCatalog(Integer.parseInt(seCatalogId), catalogName, Integer.parseInt(firstCatalogId), catalogDescription)==true){
			return SUCCESS;
		}else{
//			returnJsonMap = new HashMap<String,Object>();
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message", "未知原因的错误！");
//			return ERROR;
			//异常抛出
			AdminException exception = new AdminException("尊敬的管理员：您好！当前系统繁忙，操作失败，请重试！如问题长时间未解决，请联系工程师！");
			exception.setExceptionLocation("TSecondcatalogAction:adminModifyGoodsSeCatalog");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
	}
	
	/**
	 * 管理员根据目录id修改二级目录
	 * @param seCatalogId url中取值
	 * 			二级目录id
	 * @author 郑天然
	 * @throws AdminException 
	 */
	@Action(value = "adminDeleteSeGoodsCatalog",results ={
			@Result(name="success",type = "redirectAction",location="adminEnterGoodsManage"),
			@Result(name="error",type="json",params={"root","returnJsonMap"})
	})
	public String adminDeleteSeGoodsCatalog() throws AdminException{
		Integer deleteSeCatalog = Integer.parseInt(ServletActionContext.getRequest().getParameter("seCatalogId"));
		if(deleteSeCatalog==null){
			AdminException exception=new AdminException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		secondcatalogService.deleteSeCatalog(deleteSeCatalog);
		return "success";
	}

	/**
	 * 根据一级目录id获取二级目录json数据。用于管理员添加商品选择所属目录等
	 * @param firstCatalogId
	 * 			一级目录id
	 * @return returnJsonMap
	 * 			数据:secondCatalogList OR 错误信息:status,message
	 * @author YP
	 * @throws AdminException 
	 */
	@Action(value = "adminGetSeCatalogByFiC",results ={
			@Result(name="success",type="json",params={"root","returnJsonMap"}),
			@Result(name="error",type="json",params={"root","returnJsonMap"})
	})
	public String adminGetSeCatalogByFiC() throws AdminException{
		String firstCatalogId = ServletActionContext.getRequest().getParameter("firstCatalogId");
		//结果集
		returnJsonMap = new HashMap<String, Object>();
		//传入参数有误
		if(firstCatalogId==null){
//			returnJsonMap.put("status", "error");
//			returnJsonMap.put("message","传入的参数为空");
//			return ERROR;
			//异常抛出
			AdminException exception = new AdminException("您好像没填好必要的信息哦！传入的信息不完整呢！");
			exception.setExceptionLocation("TSecondcatalogAction:adminGetSeCatalogByFiC");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}

		List<TSecondcatalog> catalogList = secondcatalogService.getSecondcatalogList(Integer.parseInt(firstCatalogId));
		if(catalogList == null){
			//异常抛出
			AdminException exception = new AdminException("尊敬的管理员：您好！由于系统繁忙，您的请求未能完成，请重试！如长时间问题未解决，请联系工程师！");
			exception.setExceptionLocation("TSecondcatalogAction:adminGetSeCatalogByFiC");
			exception.setExceptionType(CShopExceptionI.DATABASE);		
			throw exception;
		}
		
		secondCatalogJsonList = new ArrayList<SecondcatalogJsonView>();
		for(int i=0;i<catalogList.size();i++){
			SecondcatalogJsonView secondcatalog = new SecondcatalogJsonView();
			secondcatalog.setSecondcatalogId(catalogList.get(i).getSecondcatalogId());
			secondcatalog.setSecondcatalogName(catalogList.get(i).getSecondcatalogName());
			secondCatalogJsonList.add(secondcatalog);
		}
		returnJsonMap.put("secondCatalogList", secondCatalogJsonList);
		return SUCCESS;
	}
	
	/**
	 * 判断二级目录名和父目录下的其他系列名是否重名
	 * @author RichardChan
	 */
	@Action(value="checkSecondCatalogName", results={@Result(name="jsonMessage",type="json",params={"root","jsonMessage"})})
	public String checkSecondCatalogName(){
		int seCatalogId = -1;
		String catalogName = "";
		int fiCatalogId = -1;
		if(ServletActionContext.getRequest().getParameter("seCatalogId") != null)
			seCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("seCatalogId").toString());
		if(ServletActionContext.getRequest().getParameter("catalogName") != null)
			catalogName = ServletActionContext.getRequest().getParameter("catalogName");
		if(ServletActionContext.getRequest().getParameter("fiCatalogId") != null)
			fiCatalogId = Integer.parseInt(ServletActionContext.getRequest().getParameter("fiCatalogId").toString());
		if(seCatalogId != -1)
		{
			if(secondcatalogService.checkSecondCatalogName(seCatalogId, catalogName) == false)
				setJsonMessage("重名");
			else
				setJsonMessage("成功");
		}
		else if(fiCatalogId != -1){
			if(secondcatalogService.add_checkSecondCatalogName(fiCatalogId, catalogName) == false)
			{
				setJsonMessage("重名");
			}
			else
			{
				setJsonMessage("成功");
				System.out.println("304");
			}
		}
		return "jsonMessage";
	}
}
