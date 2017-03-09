package yp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import yp.exception.CShopExceptionI;
import yp.exception.impl.CShopException;
import yp.model.TAddress;
import yp.model.view.AddressJsonView;
import yp.model.view.GoodsJsonView;
import yp.service.TUserAddressServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage("cshop-package")
@Namespace("/")
public class TUserAddressAction extends ActionSupport {
	TUserAddressServiceI userAddressService;
	List<TAddress> addressList;
	List<AddressJsonView> addressJsonViewList;
	//Integer addressId;
	public List<AddressJsonView> getAddressJsonViewList() {
		return addressJsonViewList;
	}

	public void setAddressJsonViewList(List<AddressJsonView> addressJsonViewList) {
		this.addressJsonViewList = addressJsonViewList;
	}

	//状态码
	String status;
	
	public List<TAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<TAddress> addressList) {
		this.addressList = addressList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TUserAddressServiceI getUserAddressService() {
		return userAddressService;
	}
	
	@Autowired
	public void setUserAddressService(TUserAddressServiceI userAddressService) {
		this.userAddressService = userAddressService;
	}

	/**
	 * 根据用户id删除地址
	 * 
	 * @param userId url传入
	 *            用户Id
	 * @return Integer status
	 * @author 郑天然
	 * @throws CShopException 
	 */
	@Action(value = "userDeleteAddress", results = { @Result(name = "success", type="json",params = {"root","status"}),
			@Result(name = "error", type="json",params = {"root","status"})})
	public String userDeleteAddress() throws CShopException
	{
		Map session = ActionContext.getContext().getSession();
		//传入参数，地址id 通过url传进来
		Integer addressId = Integer.parseInt(ServletActionContext.getRequest().getParameter("addressId")); 
		//检测参数
		if(addressId==null){
			CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		if(userAddressService.deleteAddress(addressId)>0){
			//为了去除一个奇怪的bug而清理session";
			TAddress  address = (TAddress) session.get("defaultAddress");
			//TAddress  address = (TAddress) ServletActionContext.getRequest().getSession().getAttribute("defaultAddress");
			if(address!=null){
				System.out.println(address);
				if(addressId.equals(address.getAddressId())){
					try{
						session.remove("defaultAddress");
					}
					catch(Exception e)
					{
						System.out.println("fuck");
					}
				}
			}
			status="success";
			return "success";
		}
		else{
			status="error";
			return "error";
			}
	}
	
	/**
	 * 根据地址id修改地址
	 * @param addressId 从url传
	 *            地址Id
	 *        addressLinkman url传
	 *            收件人
	 *        addressAddress url
	 *            地址
	 *        addressPhone url
	 *            电话
	 *        addressPostcode url
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然
	 * @throws CShopException 
	 */
	@Action(value = "userModifyAddress", results = { @Result(name = "success", type="json",params = {"root","status"}),
			@Result(name = "error", location = "/webpages/web/personalInfo/personalInfo.jsp") })
	public String userModifyAddress() throws CShopException
	{
		//传入参数，地址id 通过url传进来
		Integer addressId = Integer.parseInt(ServletActionContext.getRequest().getParameter("addressId")); 
		String addressLinkman = ServletActionContext.getRequest().getParameter("addressLinkman");
		String addressAddress = ServletActionContext.getRequest().getParameter("addressAddress");
		String addressPhone = ServletActionContext.getRequest().getParameter("addressPhone");
		String addressPostcode = ServletActionContext.getRequest().getParameter("addressPostcode");
		//检测参数
		if(addressLinkman==null||addressAddress==null||addressPhone==null||addressPostcode==null||addressId==null){
			CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userModifyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		//修改
		if(userAddressService.modifyAddress(addressId, addressLinkman, addressAddress, addressPhone, addressPostcode)){
			status="success";
			return "success";
		}
		status="error";
		return "error";
	}
	
	/**
	 * 添加地址
	 * @param 
	 * 		userid session传
	 * 			用户id
	 * 		addressLinkman url传
	 *            收件人
	 *        addressAddress url
	 *            地址
	 *        addressPhone url
	 *            电话
	 *        addressPostcode url
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 */
	@Action(value = "userAddAddress", results = { @Result(name = "result",type="json",params={"root","status"} )})
	public String userAddAddress() throws IOException, CShopException
	{
		//用户id
		//获得session
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();
						
		String userid = session.get("userId").toString();
		//检测参数
		if(userid==null){
			CShopException exception=new CShopException("session已失效，请用户重新登录");
			exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		int tuserid = Integer.valueOf(userid);
		
		//Integer tuserid = Integer.parseInt(ServletActionContext.getRequest().getParameter("userid")); 
		//剩下4个参数
		String addressLinkman = ServletActionContext.getRequest().getParameter("addressLinkman");
		String addressAddress = ServletActionContext.getRequest().getParameter("addressAddress");
		String addressPhone = ServletActionContext.getRequest().getParameter("addressPhone");
		String addressPostcode = ServletActionContext.getRequest().getParameter("addressPostcode");
		//检测参数
		if(addressLinkman==null||addressAddress==null||addressPhone==null||addressPostcode==null){
			CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		
		if(userAddressService.addAddress(tuserid,addressLinkman, addressAddress, addressPhone, addressPostcode)){
			addressList=userAddressService.getAddressList(tuserid);
			//addressId=user
			System.out.println(addressList);
			System.out.println(addressList.get(0));
			status="success";
		}
		else{
			try{	
			addressList=userAddressService.getAddressList(tuserid);
			}
			catch(HibernateException e){
				CShopException exception=new CShopException("啊咧，系统出错了，请重新操作");
				exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
				exception.setExceptionType(CShopExceptionI.DATABASE);
				throw exception;
			}
			status="error";
		}
		//返回退出信息
	    JSONObject json = new JSONObject();
		json.accumulate("status", status);
     	//解决乱码问题 在返回页面显示json
		/*HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

		PrintWriter out = response.getWriter(); 
		out.write(json.toString());
		out.flush();
		out.close();*/
		return "result";
	}
	
	/**
	 * 查看地址链表
	 * @author 郑天然
	 * @throws IOException 
	 * @throws CShopException 
	 * 
	*/
	@Action(value = "userGetAddress", results = { @Result(name = "result", location="/webpages/web/settlement/settlement.jsp")})
	public String userGetAddress() throws IOException, CShopException
	{
		//用户id
				//获得session
				ActionContext context = ActionContext.getContext();
				Map session = context.getSession();
								
				String userid = session.get("userId").toString();
				//检测session
				if(userid==null){
					CShopException exception=new CShopException("session已失效，请用户重新登录");
					exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
					exception.setExceptionType(CShopExceptionI.PARAMETERS);
					throw exception;
				}
				int tuserid = Integer.valueOf(userid);
		try{	
		addressList=userAddressService.getAddressList(tuserid);
		}
		catch(HibernateException e){
			CShopException exception=new CShopException("啊咧，系统出错了，请重新操作");
			exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
			exception.setExceptionType(CShopExceptionI.DATABASE);
			throw exception;
		}
		
		addressJsonViewList = new ArrayList<AddressJsonView>();
		for (int i = 0; i < addressList.size(); i++) {
			AddressJsonView addressJson = new AddressJsonView();
			TAddress tAddress = addressList.get(i);
			addressJson.setAddressAddress(tAddress.getAddressAddress());
			addressJson.setAddressId(tAddress.getAddressId());
			addressJson.setAddressIsdefault(tAddress.getAddressIsdefault());
			addressJson.setAddressIsdel(tAddress.getAddressIsdel());
			addressJson.setAddressLinkman(tAddress.getAddressLinkman());
			addressJson.setAddressPhone(tAddress.getAddressPhone());
			addressJson.setAddressPostcode(tAddress.getAddressPostcode());
			addressJson.setUserId(tuserid);
			addressJsonViewList.add(addressJson);
			System.out.println(i);
		}
		
		//返回退出信息
	    /*JSONObject json = new JSONObject();
		json.accumulate("addressJsonViewList", addressJsonViewList);*/
     	//解决乱码问题 在返回页面显示json
		/*HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF_8");//设置Response的编码方式为UTF-8
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

		PrintWriter out = response.getWriter(); 
		out.write(json.toString());
		out.flush();
		out.close();*/
		//status="success";
		//System.out.println("lalallllllllllll");
		return "result";
	}
	/**
	 * 根据传入id将其设为默认地址，原默认地址设为非默认
	 * @param addressId url传入
	 * 			地址id
	 * 		
	 * @author 郑天然
	 * @throws CShopException 
	*/
	@Action(value = "userModifyDefault", results = { @Result(name = "success",type="json",params={"root","status"}),
			@Result(name = "error", type="json",params={"root","status"})})
	public String userModifyDefault() throws CShopException
	{
		Integer addressId =Integer.parseInt(ServletActionContext.getRequest().getParameter("addressId"));
		//检测参数
		if(addressId==null){
			CShopException exception=new CShopException("系统问题导致数据丢失，请重新操作一次");
			exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
			exception.setExceptionType(CShopExceptionI.PARAMETERS);
			throw exception;
		}
		
		if(userAddressService.updateDefaultAddress(addressId)){
			status="success";
			return "success";
		}
		status="error";
		return "error";
	}
	
	/**
	 * 订单确认页面
	 * 根据地址id修改地址
	 * @param addressId 从url传
	 *            地址Id
	 *        addressLinkman url传
	 *            收件人
	 *        addressAddress url
	 *            地址
	 *        addressPhone url
	 *            电话
	 *        addressPostcode url
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然
	 * @throws CShopException 
	 */
	@Action(value = "userModifyBuyAddress", results = { @Result(name = "success", location = "/webpages/web/personalInfo/personalInfo.jsp"),
			@Result(name = "error", location = "/webpages/web/personalInfo/personalInfo.jsp") })
	public String userModifyBuyAddress() throws CShopException{
		//传入参数，地址id 通过url传进来
				Integer addressId = Integer.parseInt(ServletActionContext.getRequest().getParameter("addressId")); 
				String addressLinkman = ServletActionContext.getRequest().getParameter("addressLinkman");
				String addressAddress = ServletActionContext.getRequest().getParameter("addressAddress");
				String addressPhone = ServletActionContext.getRequest().getParameter("addressPhone");
				String addressPostcode = ServletActionContext.getRequest().getParameter("addressPostcode");
				
				//检测参数
				if(addressLinkman==null||addressAddress==null||addressPhone==null||addressPostcode==null||addressId==null){
					CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
					exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
					exception.setExceptionType(CShopExceptionI.PARAMETERS);
					throw exception;
				}
				
				//修改
				if(userAddressService.modifyAddress(addressId, addressLinkman, addressAddress, addressPhone, addressPostcode)){
					status="success";
					return "success";
				}
				status="error";
				return "error";
	}
	
	/**
	 * 在订单确认页面上
	 * 添加地址 
	 * @param 
	 * 		userid session传
	 * 			用户id
	 * 		addressLinkman url传
	 *            收件人
	 *        addressAddress url
	 *            地址
	 *        addressPhone url
	 *            电话
	 *        addressPostcode url
	 *            邮编
	 * @return boolean 成功与否
	 * @author 郑天然
	 * @throws CShopException 
	 */
	@Action(value = "userAddBuyAddress", results = { @Result(name = "success", location = "/webpages/web/personalInfo/personalInfo.jsp"),
			@Result(name = "error", location = "/webpages/web/personalInfo/personalInfo.jsp") })
	public String userAddBuyAddress() throws CShopException{
		//用户id
				//获得session
				ActionContext context = ActionContext.getContext();
				Map session = context.getSession();
								
				String userid = session.get("userId").toString();
				//检测session
				if(userid==null){
					CShopException exception=new CShopException("session已失效，请用户重新登录");
					exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
					exception.setExceptionType(CShopExceptionI.PARAMETERS);
					throw exception;
				}
				int tuserid = Integer.valueOf(userid);
				
				//剩下4个参数
				String addressLinkman = ServletActionContext.getRequest().getParameter("addressLinkman");
				String addressAddress = ServletActionContext.getRequest().getParameter("addressAddress");
				String addressPhone = ServletActionContext.getRequest().getParameter("addressPhone");
				String addressPostcode = ServletActionContext.getRequest().getParameter("addressPostcode");
				//检测参数
				if(addressLinkman==null||addressAddress==null||addressPhone==null||addressPostcode==null){
					CShopException exception=new CShopException("网络问题导致数据丢失，请重新操作一次");
					exception.setExceptionLocation("TUserAddressAction:userAddBuyAddress");
					exception.setExceptionType(CShopExceptionI.PARAMETERS);
					throw exception;
				}
				
				
				if(userAddressService.addAddress(tuserid,addressLinkman, addressAddress, addressPhone, addressPostcode)){
					addressList=userAddressService.getAddressList(tuserid);
					
					System.out.println(addressList);
					System.out.println(addressList.get(0));
					status="success";
				return "success";
				}
				else{
					addressList=userAddressService.getAddressList(tuserid);
					status="error";
					return "error";
				}
	}
}


