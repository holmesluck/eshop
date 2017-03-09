package yp.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 普通用户检验登录拦截器
 * @author YP
 *
 */
public class UserAuthorityInterceptor extends MethodFilterInterceptor{
//	private HashMap<String,String> resultMap;
//	
//	public HashMap<String, String> getResultMap() {
//		return resultMap;
//	}
//
//	public void setResultMap(HashMap<String, String> resultMap) {
//		this.resultMap = resultMap;
//	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("user enter");
		//Object userId = ServletActionContext.getRequest().getSession().getAttribute("userId");
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();		
		Object userId = session.get("userId");
		if(userId == null){//普通用户未登陆
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.accumulate("status", "noInput");
//			PrintWriter out = ServletActionContext.getResponse().getWriter();
			//out.write(jsonObject.toString());
//			out.write("<script language='javascript'>alert('您未登录！请先登录！')</script>");
//			resultMap = new HashMap<String, String>();
//			resultMap.put("status", "ok");
			//ServletActionContext.getRequest().setAttribute("msg", "您没有访问此功能的权限！");		
			return "error01";
		}else{//用户已登录
			return invocation.invoke();
		}		
	}
	
}
