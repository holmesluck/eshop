package yp.exception;

/**
 * 自定义异常接口
 * @author YP
 *
 */
public interface CShopExceptionI {
	
	/**
	 * 权限错误
	 */
	public static final String SECURITY = "权限错误";
	
	/**
	 * 参数错误
	 */
	public static final String PARAMETERS = "参数错误";
	
	/**
	 * 逻辑错误
	 */
	public static final String LOGICAL = "逻辑错误";
	
	/**
	 * 程序内部错误
	 */
	public static final String INTERNAL = "程序内部错误";
	
	/**
	 * 数据库错误
	 */
	public static final String DATABASE = "数据库错误";
}	
