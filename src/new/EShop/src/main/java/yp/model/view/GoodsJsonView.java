package yp.model.view;

/**
 * 普通商品视图，不含关联模型，用作json对象视图
 * 
 * @author YP
 * 
 */
public class GoodsJsonView {
	private Integer goodsId;
	private String goodsName;
	private String goodsDescription;
	private String goodsImage;
	private String goodsModel;
	private String goodsOperationSystem;
	private String goodsProcessor;
	private String goodsMemory;
	private String goodsHarddiskCapacity;
	private String goodsScreenSize;
	private String goodsResolutionDefinition;
	private Double goodsPrice;
	private Integer goodsStock;
	private Double goodsDiscount;
	private Integer goodsSales;
	private Boolean goodsIsdel;
	private Double goodsAveragemark;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getGoodsOperationSystem() {
		return goodsOperationSystem;
	}

	public void setGoodsOperationSystem(String goodsOperationSystem) {
		this.goodsOperationSystem = goodsOperationSystem;
	}

	public String getGoodsProcessor() {
		return goodsProcessor;
	}

	public void setGoodsProcessor(String goodsProcessor) {
		this.goodsProcessor = goodsProcessor;
	}

	public String getGoodsMemory() {
		return goodsMemory;
	}

	public void setGoodsMemory(String goodsMemory) {
		this.goodsMemory = goodsMemory;
	}

	public String getGoodsHarddiskCapacity() {
		return goodsHarddiskCapacity;
	}

	public void setGoodsHarddiskCapacity(String goodsHarddiskCapacity) {
		this.goodsHarddiskCapacity = goodsHarddiskCapacity;
	}

	public String getGoodsScreenSize() {
		return goodsScreenSize;
	}

	public void setGoodsScreenSize(String goodsScreenSize) {
		this.goodsScreenSize = goodsScreenSize;
	}

	public String getGoodsResolutionDefinition() {
		return goodsResolutionDefinition;
	}

	public void setGoodsResolutionDefinition(String goodsResolutionDefinition) {
		this.goodsResolutionDefinition = goodsResolutionDefinition;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Double getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(Double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	public Integer getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}

	public Boolean getGoodsIsdel() {
		return goodsIsdel;
	}

	public void setGoodsIsdel(Boolean goodsIsdel) {
		this.goodsIsdel = goodsIsdel;
	}

	public Double getGoodsAveragemark() {
		return goodsAveragemark;
	}

	public void setGoodsAveragemark(Double goodsAveragemark) {
		this.goodsAveragemark = goodsAveragemark;
	}
}
