package yp.interceptor;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import yp.model.TUser;
import yp.service.TUserServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class CookiesLoginInterceptor extends MethodFilterInterceptor {
	private TUserServiceI tUserService;

	public TUserServiceI gettUserService() {
		return tUserService;
	}
	
	@Autowired
	public void settUserService(TUserServiceI tUserService) {
		this.tUserService = tUserService;
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		Object userid = session.get("userId");
		//Object userid = ServletActionContext.getRequest().getSession().getAttribute("userId");
		if (userid == null) {// 普通用户未登陆
			// 尝试cookies登录
			int userId = -10;
			String userNickname = null;
			String userLevel = null;

			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("userId")
							&& cookies[i].getValue() != null) {
						userId = Integer.parseInt(cookies[i].getValue());
					}
				}
			}
			System.out.println("cookies：" + userId);
			// 可登陆即登陆
			if (userId != -10) {
				TUser user = tUserService.getUserInfoById(userId);
				userNickname = user.getUserNickname();
				userLevel = user.getTUserlevel().getUserlevelName();
//				ServletActionContext.getRequest().getSession()
//						.setAttribute("userId", userId);
//				ServletActionContext.getRequest().getSession()
//						.setAttribute("userNickname", userNickname);
//				ServletActionContext.getRequest().getSession()
//						.setAttribute("userLevel", userLevel);
				session.put("userId", userId);
				session.put("userNickname", userNickname);
				session.put("userLevel", userLevel);
			}
		}
		return invocation.invoke();
	}
}
