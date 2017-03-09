package yp.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 管理员检验登录拦截器
 * @author YP
 *
 */
public class AdminAuthorityInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("admin enter");
//		Object userId = ServletActionContext.getRequest().getSession().getAttribute("adminId");
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		Object userId = session.get("adminId");
		if(userId == null){//管理员未登陆
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.accumulate("status", "noInput");
//			PrintWriter out = ServletActionContext.getResponse().getWriter();
//			out.write(jsonObject.toString());
			//out.println("<script language='javascript'>alert('您未登录！请先登录！')</script>");
			return "error03";
		}else{//用户已登录
			return invocation.invoke();
		}		
	}

}
