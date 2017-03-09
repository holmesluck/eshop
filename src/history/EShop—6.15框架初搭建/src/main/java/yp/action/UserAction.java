package yp.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import yp.model.User;
import yp.service.UserServiceI;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("userPacket")
@Namespace("/")
public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5299436654716493201L;

	private String userName;
	private String password;
	private String description;
	private UserServiceI userService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@Action(value = "userRegister",results = {
			@Result(name = "input", location = "/webpages/userRegister/error.jsp"),
			@Result(name = "success", location = "/webpages/userRegister/success.jsp") })
	public String userRegister() {
		// System.out.print("ActionLO");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(Integer.parseInt(password));
		user.setDescription(description);
		if (true == userService.addUser(user)) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	@Action(value = "userLogin",results = {
			@Result(name = "input", location = "/webpages/userRegister/error.jsp"),
			@Result(name = "success", location = "/webpages/userRegister/success.jsp") })
	public String login(){
		return SUCCESS;
	}
}
