package yp.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.service.TAdminUserLevelServiceI;

@ParentPackage("cshop-package")
@Namespace("/")
public class TAdminUserLevelAction extends ActionSupport{
	
	TAdminUserLevelServiceI tAdminUserLevelService = null;
	
	
	
	
	public TAdminUserLevelServiceI gettAdminUserLevelService() {
		return tAdminUserLevelService;
	}
	@Autowired
	public void settAdminUserLevelService(
			TAdminUserLevelServiceI tAdminUserLevelService) {
		this.tAdminUserLevelService = tAdminUserLevelService;
	}
	
	/**
	 * 管理员修改会员等级
	 * 传入参数为  等级id：userLevelId  和    折扣：discount  
	 * @return
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "adminModifyUserLevel",results = {
			@Result(name = "success",type="redirectAction",location = "adminGetUserLevel"),
			@Result(name = "error",type="redirectAction",location = "adminGetUserLevel")})
	public String adminModifyUserLevel() throws AdminException{
		
		Double discount =null;
		Integer userLevelId = null;
		
		String strId = ServletActionContext.getRequest().getParameter("userLevelId");
		String strDiscount = ServletActionContext.getRequest().getParameter("discount");
		
		if(strId==null || strDiscount==null){
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TAdminUserLevelAction:adminModifyUserLevel");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		discount = Double.parseDouble(strDiscount);
		if(discount>100||discount<0){
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦,折扣要在0到100之间~亲");
        	exception.setExceptionLocation("TAdminUserLevelAction:adminModifyUserLevel");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		discount=(discount/100);
		userLevelId = Integer.valueOf(strId);
		
		if(tAdminUserLevelService.adminModifyUserLevel(userLevelId, discount) )
			return "success";
		else{
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TAdminUserLevelAction:adminModifyUserLevel");
        	exception.setExceptionType(CShopExceptionI.DATABASE);
        	throw exception;
		}
		
	}

	

}
