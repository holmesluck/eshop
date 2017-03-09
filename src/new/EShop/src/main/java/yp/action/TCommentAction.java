package yp.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.service.TCommentServiceI;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TCommentAction extends ActionSupport {
	//状态码
	String status;
	TCommentServiceI tCommentService;
	Integer goodsId;
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TCommentServiceI gettCommentService() {
		return tCommentService;
	}
	@Autowired
	public void settCommentService(TCommentServiceI tCommentService) {
		this.tCommentService = tCommentService;
	}
	/**
	 * 删除评论
	 * @param comemntId url取值
	 * 			评论id
	 * @return status
	 * 			状态码
	 * @author 郑天然
	 * @throws AdminException 
	 */
	@Action(value = "adminDeleteGoodsComment", results ={@Result(name = "result", type="redirectAction",location="adminGetAGoodsInfo",params={"goodsId","${goodsId}"})})
	public String adminDeleteGoodsComment() throws AdminException{
		goodsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("goodsId"));
		Integer commentId = Integer.parseInt(ServletActionContext.getRequest().getParameter("commentId"));
		if(goodsId==null||commentId==null){
			AdminException exception=new AdminException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		if(tCommentService.deleteCommentById(commentId))
			status="success";
		else{
			status="false";
		}
		return "result";
	}
	
}
