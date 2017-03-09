package yp.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 字符集编码拦截器
 * 
 * @author YP
 * 
 */
public class EncodingInterceptor extends AbstractInterceptor {

	private static final Logger logger = Logger.getLogger(EncodingInterceptor.class);

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();	
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setHeader("Content-type","text/html;charset=UTF-8");
		return actionInvocation.invoke();
		
	}

}
