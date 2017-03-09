package yp.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import yp.model.TUserlevel;
import yp.service.TUserlevelServiceI;
import yp.service.impl.TUserlevelServiceImpl;

@ParentPackage("cshop-package")
@Namespace("/")
public class TUserLevelAction extends ActionSupport {

	List<TUserlevel> userLevelList;
	TUserlevelServiceI tUserlevelServiceI;
	public TUserlevelServiceI gettUserlevelServiceI() {
		return tUserlevelServiceI;
	}
	@Autowired
	public void settUserlevelServiceI(TUserlevelServiceI tUserlevelServiceI) {
		this.tUserlevelServiceI = tUserlevelServiceI;
	}
	public List<TUserlevel> getUserLevelList() {
		return userLevelList;
	}
	public void setUserLevelList(List<TUserlevel> userLevel) {
		this.userLevelList = userLevel;
	}
	/**
	 *获取用户等级列表
	 *
	 *@result  userLevelList
	 *			用户等级列表
	 * @author 郑天然
	 */
	@Action(value = "adminGetUserLevel", results = { @Result(name = "success", location = "/webpages/back/grades_marks_management/membermanagement.jsp"),
			@Result(name = "error", location = "/webpages/userRegister/error.jsp") })
	public String adminGetUserLevel(){
		userLevelList = tUserlevelServiceI.getUserlevelList();
		System.out.println(userLevelList);
		return "success";
	}
}
