package yp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yp.model.TComment;
import yp.model.TFirstcatalog;
import yp.model.TGoods;


public interface TAdminGoodsServiceI {

	/**
	 * 管理员添加商品项
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
	 */
	public boolean adminAddGoods(String name,String firstId,String secondId,String description,String image,String model,
			String os,String processer,String memory,String hardDisk,String screenSize,String resolution,
			Double price,Integer stock ,Double discount);
	/**
	 * 管理员修改商品项 
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
	 */
	public boolean adminModifyGoods(Integer goodsId,String name,String firstId,String secondId,String description,String image,String model,
			String os,String processer,String memory,String hardDisk,String screenSize,String resolution,
			Double price,Integer stock ,Double discount);
	/**
	 * 管理员删除商品  ，数据库中将该商品的isDel 改为1 下架状态
	 * @return
	 * @author yu
	 */
	public boolean adminDeleteGoods(Integer goodsId);
	
	/**
	 * 根据商品 id返回商品对象
	 * @param goodsId
	 * @return
	 * @author yu
	 */
	public TGoods adminGetGoodsInfo(Integer goodsId);
	/**
	 * 根据商品id返回商品评论list
	 * @param goodsId
	 * @return
	 * @author yu
	 */
	public List<TComment> adminGetGoodsComment(Integer goodsId);
	
	/**
	 * 获得所有的一级目录
	 * @return
	 * @author yu
	 */
	public List<TFirstcatalog> adminGetFirstCatalogList();
	
}
