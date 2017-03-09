package yp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import yp.util.AESEncrypt;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;
import yp.model.TUser;
import yp.service.TUserServiceI;
import yp.service.TUserlevelServiceI;
import yp.util.MailUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cshop-package")
@Namespace("/")
public class TUserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 354307790348307684L;
	private TUserlevelServiceI TUserlevelService;
	private TUserServiceI TUserService;
	private TUser tUser;
	private String status; // 登录状态信息；通用状态码
	private boolean registed; // 邮箱是否已被注册
	private boolean regist; // 注册返回状态
	private boolean logout; // 退出状态码
	private HashMap<String, String> returnJsonMap; // 构造的json类型Map，用于返回给前台

	public HashMap<String, String> getReturnJsonMap() {
		return returnJsonMap;
	}

	public void setReturnJsonMap(HashMap<String, String> returnJsonMap) {
		this.returnJsonMap = returnJsonMap;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

	public boolean isRegist() {
		return regist;
	}

	public void setRegist(boolean regist) {
		this.regist = regist;
	}

	public TUser getTUser() {
		return tUser;
	}

	public void setTUser(TUser tUser) {
		this.tUser = tUser;
	}

	public TUserlevelServiceI getTUserlevelService() {
		return TUserlevelService;
	}

	@Autowired
	public void setTUserlevelService(TUserlevelServiceI tUserlevelService) {
		TUserlevelService = tUserlevelService;
	}

	public boolean isRegisted() {
		return registed;
	}

	public void setRegisted(boolean registed) {
		this.registed = registed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TUserServiceI getTUserService() {
		return TUserService;
	}

	@Autowired
	public void setTUserService(TUserServiceI TUserService) {
		this.TUserService = TUserService;
	}

	/**
	 * 登录
	 * 
	 * 
	 * @author 陈荣强
	 */

	@Action(value = "userLogin", results = { @Result(name = "success", type = "json", params = {
			"root", "status" }) })
	public String loginUser() throws IOException, CShopException {

		String userEmail = ServletActionContext.getRequest().getParameter(
				"userEmail");
		String userPassword = ServletActionContext.getRequest().getParameter(
				"userPassword");
		userPassword = AESEncrypt.encrypt(userPassword);
		String remember = ServletActionContext.getRequest().getParameter(
				"remember");
		System.out.println(remember);
		Map session = ActionContext.getContext().getSession();
		// 异常判断及处理
		if (userEmail.equals("") || userPassword.equals("")
				|| remember.equals("")) {
			CShopException exception = new CShopException("用户名或密码未填");
			exception.setExceptionLocation("TUserAction:userLogin");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		int userId;
		HttpServletResponse response = ServletActionContext.getResponse();
		setStatus(TUserService.userLogin(userEmail, userPassword));

		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			{
				for (int i = 0; i < cookies.length; i++) {
					System.out.println(i + ":" + cookies[i].getName() + ":"
							+ cookies[i].getValue());
				}
			}
		}

		if (getStatus().equals("成功") == true) {
			// 登录成功，将用户Id写入Session
			userId = TUserService.findUserIdByEmail(userEmail);
//			ServletActionContext.getRequest().getSession()
//					.setAttribute("userId", userId);
			session.put("userId", userId);
			// 将user放入request对象里
			TUser user = TUserService.getUserInfoById(userId);
//			ServletActionContext.getRequest().getSession()
//					.setAttribute("userNickname", user.getUserNickname());
			session.put("userNickname", user.getUserNickname());
////			ServletActionContext
////					.getRequest()
////					.getSession()
////					.setAttribute("userLevel",
////							user.getTUserlevel().getUserlevelName());
//			System.out.println(ServletActionContext.getRequest().getSession()
//					.getAttribute("userId"));
			session.put("userLevel", user.getTUserlevel().getUserlevelName());
			// 登录成功且勾选“记住我7天”单选框，将会员Id写入Cookie中并设置生存期为7天
			if (remember.equals("checked")) {
				userId = TUserService.getUserInfoByUserEmail(userEmail)
						.getUserId();
				Cookie userIdCookie = new Cookie("userId", userId + "");
				userIdCookie.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(userIdCookie);
			}
		}

		// 返回登录状态信息
		JSONObject json = new JSONObject();
		json.accumulate("status", getStatus());
		// 解决乱码问题
		response.setCharacterEncoding("UTF_8");// 设置Response的编码方式为UTF-8
		response.setHeader("Content-type", "text/html;charset=UTF-8");// 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return SUCCESS;
	}

	/**
	 * 注册时检测邮箱是否已被注册
	 * 
	 * 
	 * @author 陈荣强
	 */
	@Action(value = "userRegistCheckEmail", results = { @Result(name = "success", type = "json", params = {
			"root", "registed" }) })
	public String registCheckEmailUser() throws IOException {
		String userEmail = ServletActionContext.getRequest().getParameter(
				"userEmail");
		setRegisted(TUserService.isRegisted(userEmail));

		// 返回邮箱是否被注册
		JSONObject json = new JSONObject();
		json.accumulate("registed", isRegisted());
		// 解决乱码问题
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF_8");// 设置Response的编码方式为UTF-8
		response.setHeader("Content-type", "text/html;charset=UTF-8");// 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return SUCCESS;
	}

	/**
	 * 注册成功
	 * 
	 * 
	 * @author 陈荣强
	 * 
	 */
	@Action(value = "userRegist", results = { @Result(name = "success", type = "redirectAction", location = "showHomePage") })
	public String registUser() throws IOException, CShopException {
		Map session = ActionContext.getContext().getSession();
		String userEmail = session.get("userEmail").toString();
		String userPassword = session.get("userPassword").toString();
		userPassword = AESEncrypt.encrypt(userPassword);
		String userNickname = session.get("userNickname").toString();
		// 异常判断和处理
		if (userEmail.equals("") || userPassword.equals("")
				|| userNickname.equals("")) {
			CShopException exception = new CShopException("用户名或者密码或者昵称为空");
			exception.setExceptionLocation("TUserAction:userRegist");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		tUser = new TUser();
		tUser.setUserEmail(userEmail);
		tUser.setUserPassword(userPassword);
		tUser.setUserNickname(userNickname);
		tUser.setUserRewardpoints(0);
		tUser.setUserIsdel(false);
		tUser.setTUserlevel(TUserlevelService.getUserlevelById(1));
		if (TUserService.addUser(tUser) == true) {
			int userId = tUser.getUserId();
			// 将user放入request对象里
			session.put("userId", userId);
			TUser user = TUserService.getUserInfoById(userId);
			session.put("userLevel",
							user.getTUserlevel().getUserlevelName());
			// 销毁不必要的session属性
			session.remove("userEmail");
			session.remove("userPassword");
//			System.out.println(ServletActionContext.getRequest().getSession()
//					.getAttribute("userId"));
			setRegist(true);

		} else
			setRegist(false);

		// 返回注册信息
		/*
		 * JSONObject json = new JSONObject(); json.accumulate("regist",
		 * isRegist()); //解决乱码问题 HttpServletResponse response =
		 * ServletActionContext.getResponse();
		 * response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		 * response
		 * .setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头
		 * ，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用
		 * 
		 * PrintWriter out = response.getWriter(); out.write(json.toString());
		 * out.flush(); out.close();
		 */
		return SUCCESS;
	}

	/**
	 * 退出登录
	 * 
	 * 
	 * @author 陈荣强
	 */
	@Action(value = "userLogout", results = { @Result(name = "success", type = "json", params = {
			"root", "logout" }) })
	public String userLogout() throws IOException {
		Map session = ActionContext.getContext().getSession();
		session.put("userId", null);
		session.put("user", null);
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();

		if (cookies != null) {
			{
				HttpServletResponse response = ServletActionContext
						.getResponse();
				for (int i = 0; i < cookies.length; i++) {
					System.out.println(cookies[i].getName());
					if (cookies[i].getName().equals("userId")
							|| cookies[i].getName().equals("userNickname")
							|| cookies[i].getName().equals("userLevel")) {
						System.out.println(i + ":" + cookies[i].getName());
						// cookies[i].setValue(null);
						Cookie cookie = new Cookie(cookies[i].getName(), null);
						// cookies[i].setMaxAge(0);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		setLogout(true);
		// 返回退出信息
		JSONObject json = new JSONObject();
		json.accumulate("logout", isLogout());
		// 解决乱码问题
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF_8");// 设置Response的编码方式为UTF-8
		response.setHeader("Content-type", "text/html;charset=UTF-8");// 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return SUCCESS;
	}

	/**
	 * 修改密码时，检查输入的原密码是否正确
	 * 
	 * @return status "right" or "error"
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "userModifyCheckOldPassword", results = { @Result(name = "success", type = "json", params = {
			"root", "returnJsonMap" }) })
	public String userModifyCheckOldPassword() throws CShopException {
		Map session = ActionContext.getContext().getSession();
		Object id = session.get("userId");
		if (id == null) {
			//异常抛出
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userModifyCheckOldPassword");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		Integer userId = Integer.parseInt(id.toString());
		String password = ServletActionContext.getRequest().getParameter(
				"password");
		returnJsonMap = new HashMap<String, String>();
		// 用户未登陆或者密码未输入返回错误
		if (password == null || password.equals("")) {
//			status = "error 02";// error
//			returnJsonMap.put("status", status);
//			return SUCCESS;
			//异常抛出
			CShopException exception = new CShopException("您好像没填写好密码哦！没有看到您的密码呢！");
			exception.setExceptionLocation("TUserAction:userModifyCheckOldPassword");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}

		password = AESEncrypt.encrypt(password);// 密码加密
		boolean checkResult = TUserService.checkPrimaryPassword(userId,
				password);

		if (checkResult == true) {
			status = "right";// 原密码正确
		} else {
			status = "error";// 原密码错误
		}
		returnJsonMap.put("status", status);
		return SUCCESS;
	}

	/**
	 * 用户修改密码
	 * 
	 * @return status “密码修改成功” or "密码修改失败"
	 * @author YP
	 * @throws CShopException 
	 */
	@Action(value = "userModifyPassword", results = { @Result(name = "success", type = "json", params = {
			"root", "returnJsonMap" }) })
	public String userModifyPassword() throws CShopException {
		Map session = ActionContext.getContext().getSession();
		Object id = session.get("userId");
		if (id == null) {
			//异常抛出
			CShopException exception = new CShopException("该用户貌似未登录的哦！");
			exception.setExceptionLocation("TUserAction:userModifyPassword");
			exception.setExceptionType(CShopExceptionI.INTERNAL);		
			throw exception;
		}
		Integer userId = Integer.parseInt(id.toString());
		String password = ServletActionContext.getRequest().getParameter(
				"password");
		returnJsonMap = new HashMap<String, String>();

		// 用户未登陆或者密码未输入返回错误
		if (userId == null || password == null || password.equals("")) {
//			status = "error 02";// error
//			returnJsonMap.put("status", status);
//			return SUCCESS;
			//异常抛出
			CShopException exception = new CShopException("您填写的信息有误哦！貌似没检测到您传入的信息！如有问题，请联系管理员！");
			exception.setExceptionLocation("TUserAction:userModifyPassword");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);		
			throw exception;
		}

		password = AESEncrypt.encrypt(password);// 密码加密
		if (TUserService.modifyPassword(userId, password) == true) {
			status = "密码修改成功";// 修改成功
		} else {
			status = "密码修改失败";// 修改失败
		}
		returnJsonMap.put("status", status);
		return SUCCESS;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 *            session传入 用户Id
	 * @return tUser 值栈 用户对象
	 * @author 郑天然
	 * @throws CShopException
	 */
	@Action(value = "userGetPersonalInfo", results = {
			@Result(name = "success", location = "/webpages/web/personalInfo/personalInfo.jsp"),
			@Result(name = "error", location = "/webpages/userRegister/error.jsp") })
	public String userGetPersonalInfo() throws CShopException {
		// 若userid从session传入，取消一下几行注释
		// 参数名userid
		// 获得session

		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();

		String userid = session.get("userId").toString();
		// 检测session
		if (userid == null) {
			CShopException exception = new CShopException("session已失效，请用户重新登录");
			exception.setExceptionLocation("TUserAction:userGetPersonalInfo");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		int tuserid = Integer.valueOf(userid);

		// 若userid从url传入取消改行注释
		// Integer tuserid =
		// Integer.parseInt(ServletActionContext.getRequest().getParameter("userid"));

		// 获得用户用例
		tUser = TUserService.getUserObject(tuserid);
		// 检测是否有该用户
		if (tUser == null) {
			CShopException exception = new CShopException("您的用户可能已被冻结，请联系管理员");
			exception.setExceptionLocation("TUserAction:userGetPersonalInfo");
			exception.setExceptionType(CShopExceptionI.DATABASE);
			throw exception;
		}
		// userEmail = tUser.getUserEmail();
		System.out.println(tUser.getUserEmail());

		// 需抛出异常
		// 是否有用户id
		if (tUser != null)
			return "success";
		return "error";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userId
	 *            session传入 用户Id userNickName session传入 用户昵称
	 * @return tUser 值栈 用户对象
	 * @author 郑天然
	 * @throws IOException
	 * @throws CShopException
	 */
	@Action(value = "userModifyUserNickName", results = { @Result(name = "result", type = "json", params = {
			"root", "status" }) })
	public String userModifyUserNickName() throws IOException, CShopException {
		// 若userid与userNickName从session传入则取消一下注释
		// 参数名userid userNickName
		// 获得session

		// 需抛出异常
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();

		String userid = session.get("userId").toString();
		if (userid == null) {
			CShopException exception = new CShopException("session已失效，请用户重新登录");
			exception
					.setExceptionLocation("TUserAction:userModifyUserNickName");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		int tuserid = Integer.valueOf(userid);

		// Integer tuserid =
		// Integer.parseInt(ServletActionContext.getRequest().getParameter("userid"));
		// 从url中获取昵称
		HttpServletRequest request = ServletActionContext.getRequest();
		String userNickName = new String(request.getParameter("userNickName")
				.getBytes("ISO8859_1"), "UTF-8");// 转码，解决get方法传参的中文乱码问题

		System.out.println(userNickName);
		// 修改名字
		System.out.println("1" + userNickName);
		if(userNickName.equals(""))
			userNickName = TUserService.getUserInfoById(tuserid).getUserEmail();
		System.out.println("2" + userNickName);
		if (TUserService.modifyUserNickName(tuserid, userNickName)) {
			// 返回用户object
			if (tUser != null)
				tUser.setUserNickname(userNickName);
			else {
				tUser = TUserService.getUserObject(tuserid);
			}
			// 判断get是否成功
			if (tUser != null) {
				status = "success";
				session.put("userNickname", tUser.getUserNickname());
//				System.out.println(ServletActionContext.getRequest()
//						.getSession().getAttribute("userNickname"));
			} else {
				status = "error";
			}
		} else {
			status = "error";
		}
		// 返回退出信息
		JSONObject json = new JSONObject();
		json.accumulate("status", status);
		// 解决乱码问题 在返回页面显示json
		/*
		 * HttpServletResponse response = ServletActionContext.getResponse();
		 * response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		 * response
		 * .setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头
		 * ，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用
		 * 
		 * PrintWriter out = response.getWriter(); out.write(json.toString());
		 * out.flush(); out.close();
		 */
		return "result";
	}

	/**
	 * 删除会员
	 * 
	 * @param userId
	 *            url传入 用户Id
	 * @return boolean
	 * 
	 * @author 郑天然
	 * @throws IOException
	 */
	@Action(value = "adminDeleteUser", results = { @Result(name = "result", type = "redirectAction", location = "adminGetUserLevel") })
	public String adminDeleteUser() {
		Integer userId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("userId"));
		// 检验有无id参数
		if (userId == null)
			userId = 0;
		if (TUserService.deleteUser(userId))
			status = "success";
		else
			status = "error";
		return "result";
	}

	/**
	 * 发送找回密码邮件 将邮件发到用户邮箱中如果有的话
	 * 
	 * @param userEmail
	 *            url传入 用户邮箱
	 * @return success 从定向到主页 error 返回json错误
	 * 
	 * @author 郑天然
	 * 
	 */
	@Action(value = "findPasswordSendMail", results = {
			@Result(name = "success", type = "json", params = { "root",
					"returnJsonMap" }),
			@Result(name = "error", type = "json", params = { "root",
					"returnJsonMap" }) })
	public String findPasswordSendMail() {
		Map session = ActionContext.getContext().getSession();
		String userEmail = ServletActionContext.getRequest()
				.getParameter("userEmail").toString();
		//
		
		TUser tUser = TUserService.getUserInfoByUserEmail(userEmail);
		if (tUser != null) {
			//把userid放入session供修改密码使用
			//ServletActionContext.getRequest().getSession().setAttribute("userId", tUser.getUserId());
			session.put("userEmail",  tUser.getUserEmail());
			MailUtil mailUtil = new MailUtil();
			try {
				mailUtil.send(userEmail, ServletActionContext.getRequest()
						.getSession(), ServletActionContext.getRequest());
			} catch (Exception e) {
				// status = "用户邮箱地址有错";
				returnJsonMap = new HashMap<String, String>();
				returnJsonMap.put("status", "用户邮箱地址有错");
				return "error";
			}
			returnJsonMap = new HashMap<String, String>();
			returnJsonMap.put("status", "success");
			return "success";
		}
		// status = "用户名不存在";
		returnJsonMap = new HashMap<String, String>();
		returnJsonMap.put("status", "用户名不存在");
		return "error";
	}

	/**
	 * 处理找回密码的邮箱链接
	 * 
	 * @param random
	 *            随机字符串，session传入
	 * @return success 修改密码页面 error 返回json错误
	 * 
	 * @author 郑天然
	 * @throws CShopException
	 * 
	 */
	@Action(value = "findPasswordModifyNow", results = {
			@Result(name = "success", location = "/webpages/web/retrievePassword/retrievePassword.jsp"),
			@Result(name = "error", location = "/webpages/web/userRegister/error.jsp") })
	public String findPasswordModifyNow() throws CShopException {
		Map session = ActionContext.getContext().getSession();
		Integer random = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("random").toString());
		if (random == null) {
			// 抛出异常
			CShopException exception = new CShopException("链接已失效，请重点返回密码");
			exception.setExceptionLocation("TUserAction:findPasswordModifyNow");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}

		Integer srandom = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("random").toString());
		String userEmail = ServletActionContext.getRequest().getSession().getAttribute("userEmail").toString();
		
		tUser=TUserService.getUserInfoByUserEmail(userEmail);
		session.put("userId", tUser.getUserId());
		session.put("userNickname", tUser.getUserNickname());
		session.put("userLevel", tUser.getTUserlevel().getUserlevelName());
		if (random.equals(srandom)) {
			return "success";
		} else {// 抛出异常
			CShopException exception = new CShopException("链接已失效，请重点返回密码");
			exception.setExceptionLocation("TUserAction:findPasswordModifyNow");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
	}

	/**
	 * 注册邮箱验证
	 * 
	 * @author 郑天然
	 * @throws CShopException
	 * 
	 */
	@Action(value = "userRegistEmailSend", results = {
			@Result(name = "success", type = "json", params = { "root",
					"regist" }),
			@Result(name = "errors", type = "json", params = { "root", "status" }) })
	public String registEmailCheck() throws IOException, CShopException {
		Map session = ActionContext.getContext().getSession();
		// 获取参数
		String userEmail = ServletActionContext.getRequest().getParameter(
				"userEmail");
		String userPassword = ServletActionContext.getRequest().getParameter(
				"userPassword");
		// 抛出异常
		if (userEmail == null || userPassword == null) {
			CShopException exception = new CShopException("用户名或密码未填");
			exception.setExceptionLocation("TUserAction:userRegistEmailSend");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}

		String userNickname = "";
		if (ServletActionContext.getRequest().getParameter("userNickname")
				.equals(""))
			userNickname = userEmail;
		else
			userNickname = ServletActionContext.getRequest().getParameter(
					"userNickname");

		// 放入session
//		ServletActionContext.getRequest().getSession()
//				.setAttribute("userEmail", userEmail);
//		ServletActionContext.getRequest().getSession()
//				.setAttribute("userPassword", userPassword);
//		ServletActionContext.getRequest().getSession()
//				.setAttribute("userNickname", userNickname);
		session.put("userEmail", userEmail);
		session.put("userPassword", userPassword);
		session.put("userNickname", userNickname);

		// 用户到时找回session
		HttpSession session2 = ServletActionContext.getRequest().getSession();
		// 用于获得服务器端口号
		HttpServletRequest request = ServletActionContext.getRequest();

		// 发邮件
		MailUtil mail = new MailUtil();
		try {
			mail.sendEmailCheck(userEmail, session2, request);
		} catch (Exception e) {
			status = e.toString();
			return "error";
		}
		status = "success";
		setRegist(true);
		return "success";
	}
}
