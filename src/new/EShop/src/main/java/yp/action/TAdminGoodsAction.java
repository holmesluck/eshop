package yp.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

//import org.apache.struts.upload.FormFile;

import com.opensymphony.xwork2.ActionSupport;

import yp.exception.CShopExceptionI;
import yp.exception.impl.AdminException;
import yp.exception.impl.CShopException;
import yp.model.TComment;
import yp.model.TFirstcatalog;
import yp.model.TGoods;
import yp.service.TAdminGoodsServiceI;

@ParentPackage("cshop-package")
@Namespace("/")
public class TAdminGoodsAction  extends ActionSupport{

	TAdminGoodsServiceI tAdminGoodsService;
	List<TComment> commentList = null;
	TGoods goodsInfo = null;
	List<TFirstcatalog> firstCatalogList = null;
	
	Integer goodsId;
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}



	private static final int BUFFER_SIZE = 16 * 1024;  
	String status;
	
	//用于文件上传
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	
	public String getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	
	
	//二级目录id 用于传参
	Integer secondcatalogId;
	//pageNum 用于传参
	Integer pageNum;
	public List<TComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<TComment> commentList) {
		this.commentList = commentList;
	}
	public TGoods getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(TGoods goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public List<TFirstcatalog> getFirstCatalogList() {
		return firstCatalogList;
	}
	public void setFirstCatalogList(List<TFirstcatalog> firstCatalogList) {
		this.firstCatalogList = firstCatalogList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TAdminGoodsServiceI gettAdminGoodsService() {
		return tAdminGoodsService;
	}
	@Autowired
	public void settAdminGoodsService(TAdminGoodsServiceI tAdminGoodsService) {
		this.tAdminGoodsService = tAdminGoodsService;
	}

	public Integer getSecondcatalogId() {
		return secondcatalogId;
	}
	public void setSecondcatalogId(Integer secondcatalogId) {
		this.secondcatalogId = secondcatalogId;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * 管理员添加商品项 ,尼玛参数有点多啊
	 * 传入参数：
	 * 商品名         goodsName
	 * 一级目录    goodsFirstCatalogId 
	 * 二级目录    goodsSecondCatalogId
	 * 商品描述    goodsDescription
	 * 商品图片    goodsImage
	 * 型号            goodsModel
	 * 操作系统    goodsOperationSystem
	 * 处理器        goodsProcesser
	 * 内存            goodsMemory
	 * 硬盘容量    goodsHarddiskCapacity
	 * 屏幕尺寸    goodsScreenSize
	 * 分辨率        goodsResolutionDefinition
	 * 市场价格    goodsPrice
	 * 库存量        goodsStock
	 * 折扣            goodsDiscount
	 * 
	 * @author yu
	 * @return
	 * @throws IOException 
	 */
	@Action(value = "adminAddGoods",
			interceptorRefs= {
			@InterceptorRef("myStack"),
			@InterceptorRef("fileUploadStack")},
			results = {
			@Result(name = "success",type="redirectAction",location = "showAllGoods",params={"secondcatalogId","${secondcatalogId}",
					"pageNum","0"}),
					@Result(name = "error",type= "redirectAction",location = "showAllGoods",params={"secondcatalogId","${secondcatalogId}",
							"pageNum","0"})})
	public String adminAddGoods() throws IOException{
		String goodsName = ServletActionContext.getRequest().getParameter("goodsName");
		System.out.println("g:"+goodsName);
		String goodsFirstCatalogId = ServletActionContext.getRequest().getParameter("goodsFirstCatalogId");
	
		String goodsSecondCatalogId = ServletActionContext.getRequest().getParameter("goodsSecondCatalogId");
		String goodsDescription = ServletActionContext.getRequest().getParameter("goodsDescription");
		//String goodsImage = ServletActionContext.getRequest().getParameter("goodsImage");
		String goodsModel = ServletActionContext.getRequest().getParameter("goodsModel");
		String goodsOperationSystem = ServletActionContext.getRequest().getParameter("goodsOperationSystem");
		String goodsProcesser = ServletActionContext.getRequest().getParameter("goodsProcesser");
		String goodsMemory = ServletActionContext.getRequest().getParameter("goodsMemory");
		String goodsHarddiskCapacity = ServletActionContext.getRequest().getParameter("goodsHarddiskCapacity");
		String goodsScreenSize = ServletActionContext.getRequest().getParameter("goodsScreenSize");
		String goodsResolutionDefinition = ServletActionContext.getRequest().getParameter("goodsResolutionDefinition");
		Double goodsPrice = null ;
		Integer goodsStock = null ;
		Double goodsDiscount = null;
		if( ServletActionContext.getRequest().getParameter("goodsPrice")!=null )
			goodsPrice = Double.parseDouble( ServletActionContext.getRequest().getParameter("goodsPrice") );
		if(ServletActionContext.getRequest().getParameter("goodsStock")!=null )
			goodsStock = Integer.valueOf(ServletActionContext.getRequest().getParameter("goodsStock"));
		if(ServletActionContext.getRequest().getParameter("goodsDiscount")!=null)
			goodsDiscount = Double.parseDouble(ServletActionContext.getRequest().getParameter("goodsDiscount"));

		//文件上传
		//String storageFileName=new String( myFileFileName.getBytes("ISO8859-1"),"utf-8");
		String storageFileName = myFileFileName;  
        System.out.println("FileName: " + myFileFileName);  
        System.out.println("ContentType: " + myFileContentType);  
        System.out.println("File: " + myFile);  
        System.out.println(goodsName);
        
        //用户没有选择文件
        if(myFile==null)
        	return "error";
        //判断文件类型
        if(!myFileContentType.toString().equals("image/jpeg")&&!myFileContentType.toString().equals("image/png")&&!myFileContentType.toString().equals("image/bmp")){
        	status = "error";
        	return "error";
        	}
        
        //决定在那个目录
        String direct=null;
        switch(Integer.parseInt(goodsFirstCatalogId))
        {
        case 1:
        	direct="lenovo/";
        	break;
        case 2:
        	direct="Acer/";
        	break;
        case 3:
        	direct="Dell/";
        	break;
        case 4:
        	direct="ASUS/";
        	break;
        };
        
        File storageFile = new File(ServletActionContext.getServletContext()  
                .getRealPath("/resources/image/"+ direct)+"/"+storageFileName); 
        //File storageFile = new File("/F:"+storageFileName);
        System.out.println(storageFile);
        copy(myFile, storageFile); 
        
        String goodsImage = "/image/"+direct+storageFileName;
       // System.out.println("fuck");
        
      //为action传参数做准备
      		secondcatalogId=Integer.parseInt(goodsSecondCatalogId);
      		pageNum=0;
      		
		if(tAdminGoodsService.adminAddGoods(goodsName, goodsFirstCatalogId,goodsSecondCatalogId, goodsDescription, goodsImage, goodsModel,
				goodsOperationSystem, goodsProcesser, goodsMemory, goodsHarddiskCapacity, goodsScreenSize, 
				goodsResolutionDefinition, goodsPrice, goodsStock, goodsDiscount)){
			System.out.println("fuck");
			status="success";
			return "success";}
		else{
			System.out.println("fuck");
			status = "error";
			return "error";
		}
	}
	/**
	 * 管理员删除商品  ，数据库中将该商品的isDel 改为1 下架状态
	 * 
	 * 传入参数 goodsId  商品id
	 * @return  返回json ： String类型 status   成功值为： success   失败值为  error
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "adminDeleteGoods",results = {
			@Result(name = "success",type ="json",params={"root","status"}),
			@Result(name = "error",location = "/webpages/userRegister/error.jsp")})
	public String adminDeleteGoods() throws AdminException{
		String goodsId = ServletActionContext.getRequest().getParameter("goodsId");
		if(goodsId!=null){
			if(tAdminGoodsService.adminDeleteGoods(Integer.valueOf(goodsId))){
				status="success";
				return "success";
			}
			else{
				//抛出异常
				AdminException exception=new AdminException("啊咧类，出错了哦");
	        	exception.setExceptionLocation("TAdminGoodsAction:adminDeleteGoods");
	        	exception.setExceptionType(CShopExceptionI.INTERNAL);
	        	throw exception;
			}
		}
		System.out.println("没有传入商品id");
		return "error";
	}
	/**
	 * 管理员点击修改商品，点击商品名，点击商品图片 都调用此action
	 * 传入参数 为 goodsId 
	 * 提供前台商品信息和相应评论
	 * @return
	 * @author yu
	 * @throws CShopException 
	 */
	@Action(value = "adminGetGoodsInfo",results = {
			@Result(name = "success",location = "/webpages/back/modifygoods/modifygoods.jsp"),
			@Result(name = "error",location = "/webpages/userRegister/error.jsp")})
	public String adminGetGoodsInfo() throws AdminException{
		String goodsId = ServletActionContext.getRequest().getParameter("goodsId");
		if(goodsId==null){
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TAdminGoodsAction:adminGetGoodsInfo");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		goodsInfo = tAdminGoodsService.adminGetGoodsInfo(Integer.valueOf(goodsId));
		commentList = tAdminGoodsService.adminGetGoodsComment(Integer.valueOf(goodsId));
		firstCatalogList = tAdminGoodsService.adminGetFirstCatalogList();
		//测试
		System.out.println(goodsInfo.getGoodsName());
		for(int i=0;i<commentList.size();i++){
			System.out.println(commentList.get(i).getCommentContent());
		}
		for(int i=0;i<firstCatalogList.size();i++){
			System.out.println(firstCatalogList.get(i).getFirstcatalogName());
		}
		
		return "success";
	}

	/**
	 * 管理员修改商品项 
	 * 传入参数：
	 * 商品id  goodsId
	 * 商品名         goodsName
	 * 一级目录    goodsFirstCatalogId 
	 * 二级目录    goodsSecondCatalogId
	 * 商品描述    goodsDescription
	 * 商品图片    goodsImage
	 * 型号            goodsModel
	 * 操作系统    goodsOperationSystem
	 * 处理器        goodsProcesser
	 * 内存            goodsMemory
	 * 硬盘容量    goodsHarddiskCapacity
	 * 屏幕尺寸    goodsScreenSize
	 * 分辨率        goodsResolutionDefinition
	 * 市场价格    goodsPrice
	 * 库存量        goodsStock
	 * 折扣            goodsDiscount
	 * 
	 * @author yu
	 * @return
	 * @throws CShopException 
	 */
	@Action(value = "adminModifyGoods",
			interceptorRefs= {
			@InterceptorRef("myStack"),
			@InterceptorRef("fileUploadStack")},
			results = {
			@Result(name = "success",type="redirectAction",location = "showAllGoods",params={"secondcatalogId","${secondcatalogId}",
					"pageNum","0"}),
			@Result(name = "error",type= "redirectAction",location = "adminGetGoodsInfo",params={"goodsId","${goodsId}",
					"pageNum","0"})})
	public String adminModifyGoods() throws AdminException{
		//Integer goodsId=null;
		if(ServletActionContext.getRequest().getParameter("goodsId")!=null)
			goodsId = Integer.valueOf(ServletActionContext.getRequest().getParameter("goodsId"));
		if(goodsId==null){
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TAdminGoodsAction:adminModifyGoods");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		String goodsImage = null;
		String goodsName = ServletActionContext.getRequest().getParameter("goodsName");
		String goodsFirstCatalogId = ServletActionContext.getRequest().getParameter("goodsFirstCatalogId");
		String goodsSecondCatalogId = ServletActionContext.getRequest().getParameter("goodsSecondCatalogId");
		String goodsDescription = ServletActionContext.getRequest().getParameter("goodsDescription");
		String oldGoodsImage = ServletActionContext.getRequest().getParameter("oldGoodsImage");
		String goodsModel = ServletActionContext.getRequest().getParameter("goodsModel");
		String goodsOperationSystem = ServletActionContext.getRequest().getParameter("goodsOperationSystem");
		String goodsProcesser = ServletActionContext.getRequest().getParameter("goodsProcesser");
		String goodsMemory = ServletActionContext.getRequest().getParameter("goodsMemory");
		String goodsHarddiskCapacity = ServletActionContext.getRequest().getParameter("goodsHarddiskCapacity");
		String goodsScreenSize = ServletActionContext.getRequest().getParameter("goodsScreenSize");
		String goodsResolutionDefinition = ServletActionContext.getRequest().getParameter("goodsResolutionDefinition");
		Double goodsPrice = null ;
		Integer goodsStock = null ;
		Double goodsDiscount = null;
		try{
			if( ServletActionContext.getRequest().getParameter("goodsPrice")!=null )
				goodsPrice = Double.parseDouble( ServletActionContext.getRequest().getParameter("goodsPrice") );
			if(ServletActionContext.getRequest().getParameter("goodsStock")!=null )
				goodsStock = Integer.valueOf(ServletActionContext.getRequest().getParameter("goodsStock"));
			if(ServletActionContext.getRequest().getParameter("goodsDiscount")!=null)
				goodsDiscount = Double.parseDouble(ServletActionContext.getRequest().getParameter("goodsDiscount"));
		
		}catch(Exception e){
			AdminException exception=new AdminException("/n"+"啊咧咧，出错了哦,参数格式不对~亲");
        	exception.setExceptionLocation("TAdminGoodsAction:adminModifyGoods");
        	exception.setExceptionType(CShopExceptionI.PARAMETERS);
        	throw exception;
		}
		
		
		//文件上传
		if(myFile!=null){
				System.out.println("myFile:"+myFile);
				String storageFileName = myFileFileName;  
		        System.out.println("FileName: " + myFileFileName);  
		        System.out.println("ContentType: " + myFileContentType);  
		        System.out.println("File: " + myFile);  
		        System.out.println(goodsName);
		       
		        //判断文件类型
		        if(!myFileContentType.toString().equals("image/jpeg")&&!myFileContentType.toString().equals("image/png")&&!myFileContentType.toString().equals("image/bmp")){
		        	status = "error";
		        	System.out.println("it's wrong");
		        	return "error";
		        	}
		        
		        //决定在那个目录
		        String direct=null;
		        switch(Integer.parseInt(goodsFirstCatalogId))
		        {
		        case 1:
		        	direct="lenovo/";
		        	break;
		        case 2:
		        	direct="Acer/";
		        	break;
		        case 3:
		        	direct="Dell/";
		        	break;
		        case 4:
		        	direct="ASUS/";
		        	break;
		        };
		        
		       
		        
		        File storageFile = new File(ServletActionContext.getServletContext()  
		                .getRealPath("/resources/image/"+ direct)+"/"+storageFileName); 
		        //File storageFile = new File("/F:"+storageFileName);
		        System.out.println(storageFile);
		        copy(myFile, storageFile); 
		        
		        
		        goodsImage = "/image/"+direct+storageFileName;
		        System.out.println("fuck");
		}
		//等于空
		else{
			 goodsImage = oldGoodsImage;
		}
		
		//为action传参数做准备
		secondcatalogId=tAdminGoodsService.adminGetGoodsInfo(goodsId).getTSecondcatalog().getSecondcatalogId();
		pageNum=0;
		if(secondcatalogId==null){
			//抛出异常
			AdminException exception=new AdminException("啊咧咧，出错了哦");
        	exception.setExceptionLocation("TAdminGoodsAction:adminModifyGoods");
        	exception.setExceptionType(CShopExceptionI.INTERNAL);
        	throw exception;
		}
		if(tAdminGoodsService.adminModifyGoods(goodsId,goodsName, goodsFirstCatalogId,goodsSecondCatalogId, goodsDescription, goodsImage, goodsModel,
				goodsOperationSystem, goodsProcesser, goodsMemory, goodsHarddiskCapacity, goodsScreenSize, 
				goodsResolutionDefinition, goodsPrice, goodsStock, goodsDiscount))
			return "success";
		else{
			//抛出异常
			AdminException exception=new AdminException("啊咧类，出错了哦");
        	exception.setExceptionLocation("TAdminGoodsAction:adminModifyGoods");
        	exception.setExceptionType(CShopExceptionI.INTERNAL);
        	throw exception;
		}
		
	}
	
	
	
	public void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
