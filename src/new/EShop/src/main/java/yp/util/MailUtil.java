package yp.util;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.net.*;

public class MailUtil {
	public GenericXmlApplicationContext ctx = null;
	private JavaMailSender mailsender;
	private SimpleMailMessage message;
	
	
	public MailUtil(){
		ctx = new GenericXmlApplicationContext("classpath:spring-mail.xml");
	}
	/**
	 * 发送找回密码邮件
	 * @param dest
	 * 			用户邮箱
	 * 		  session
	 * 			提供sessionid以供找回session
	 * @param session
	 * 		  dest
	 * 		       目的账户
	 * @throws MessagingException 
	 * @throws CShopException 
	 */
	public void send(String dest,HttpSession session,HttpServletRequest request) throws MessagingException, CShopException{

		mailsender = (JavaMailSender)ctx.getBean("mailSender");
		MimeMessage mime = mailsender.createMimeMessage();
	    MimeMessageHelper helper;
		message = new SimpleMailMessage();
		
		Random randObject = new Random();
		Integer random;
		
		//try{
			//生成随机字符串
			random = randObject.nextInt();
			//随机字符串放入session中
			session.setAttribute("random", random);
			
			//获取本机ip
			InetAddress myIPaddress=null;
			try {
				myIPaddress=InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CShopException exception=new CShopException("无法获取本机ip");
	        	exception.setExceptionLocation("TUserAction:userRegistEmailSend");
	        	exception.setExceptionType(CShopExceptionI.INTERNAL);
	        	throw exception;	
			}
			//String myIP=myIPaddress.toString();
			//System.out.println(myIPaddress.getHostAddress().toString());
			
			//获取服务器端口
			Integer port=request.getServerPort();
			
			  helper = new MimeMessageHelper(mime, true, "utf-8");
	            helper.setFrom("ztrart81@163.com");//发送方
	            
	            
	            helper.setTo(dest);//接收方
	            
	           
	            helper.setSubject("密码找回");//设置邮件主题
	          
	            
	            String text2 = "http://"+myIPaddress.getHostAddress().toString()+":"+port.toString()+"/CShop/findPasswordModifyNow.action;jsessionid="+session.getId()+"?random="+random.toString();
	            String text3 ="亲爱的用户"+dest+"您好！您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。       "+
	            "要使用新的密码, 请使用以下链接启用密码。    "+"该链接在用户不做任何操作30分钟后失效  "+text2;
	           
	            helper.setText(text3);//设置邮箱文本为密码	
	            
	            mailsender.send(mime);//发送邮件

		//}catch(Exception e){
		//	throw new RuntimeException("收件人邮箱地址出错！");  
		//}
	}
	
	
	/**
	 * 发送链接确认邮件
	 * @param dest
	 * 			用户邮箱
	 * 		  session
	 * 			提供sessionid以供找回session
	 * @param session
	 * 		  dest
	 * 		       目的账户
	 * @throws MessagingException 
	 * @throws CShopException 
	 */
	public void sendEmailCheck(String dest,HttpSession session,HttpServletRequest request) throws MessagingException, CShopException{

		mailsender = (JavaMailSender)ctx.getBean("mailSender");
		MimeMessage mime = mailsender.createMimeMessage();
	    MimeMessageHelper helper;
		message = new SimpleMailMessage();
		
		Random randObject = new Random();
		Integer random;
		
		//try{
		
		//获取本机ip
			InetAddress myIPaddress=null;
			try {
				myIPaddress=InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CShopException exception=new CShopException("无法获取本机ip");
	        	exception.setExceptionLocation("TUserAction:userRegistEmailSend");
	        	exception.setExceptionType(CShopExceptionI.INTERNAL);
	        	throw exception;	
			}
			//String myIP=myIPaddress.toString();
			System.out.println(myIPaddress.getHostAddress().toString());
			
			//获取服务器端口
			Integer port=request.getServerPort();
			
			  helper = new MimeMessageHelper(mime, true, "utf-8");
	            helper.setFrom("ztrart81@163.com");//发送方
	            
	            helper.setTo(dest);//接收方
	            
	            
	            helper.setSubject("邮箱认证");//设置邮件主题
	          
	            
	            String text2 = "http://"+myIPaddress.getHostAddress().toString()+":"+port.toString()+"/CShop/userRegist.action;jsessionid="+session.getId();
	            String text3 ="尊敬的用户请点击下方连接以完成注册    "+text2;
	           
	            helper.setText(text3);//设置邮箱文本为密码	
	            
	            mailsender.send(mime);//发送邮件

		//}catch(Exception e){
		//	throw new RuntimeException("收件人邮箱地址出错！");  
	//	}
	}
}
