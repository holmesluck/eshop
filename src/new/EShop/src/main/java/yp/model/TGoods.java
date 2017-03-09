package yp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TGoods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_goods", catalog = "cshopdb")
public class TGoods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private TFirstcatalog TFirstcatalog;
	private TSecondcatalog TSecondcatalog;
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
	private Set<TFavoritycontent> TFavoritycontents = new HashSet<TFavoritycontent>(
			0);
	private Set<TOrderlistcontent> TOrderlistcontents = new HashSet<TOrderlistcontent>(
			0);
	private Set<TComment> tComments = new HashSet<TComment>(0);

	// Constructors

	/** default constructor */
	public TGoods() {
	}

	/** minimal constructor */
	public TGoods(String goodsName, String goodsDescription, Double goodsPrice,
			Integer goodsStock) {
		this.goodsName = goodsName;
		this.goodsDescription = goodsDescription;
		this.goodsPrice = goodsPrice;
		this.goodsStock = goodsStock;
	}

	/** full constructor */
	public TGoods(TFirstcatalog TFirstcatalog, TSecondcatalog TSecondcatalog,
			String goodsName, String goodsDescription, String goodsImage,
			String goodsModel, String goodsOperationSystem,
			String goodsProcessor, String goodsMemory,
			String goodsHarddiskCapacity, String goodsScreenSize,
			String goodsResolutionDefinition, Double goodsPrice,
			Integer goodsStock, Double goodsDiscount, Integer goodsSales,
			Boolean goodsIsdel, Double goodsAveragemark,
			Set<TFavoritycontent> TFavoritycontents,
			Set<TOrderlistcontent> TOrderlistcontents, Set<TComment> TComments) {
		this.TFirstcatalog = TFirstcatalog;
		this.TSecondcatalog = TSecondcatalog;
		this.goodsName = goodsName;
		this.goodsDescription = goodsDescription;
		this.goodsImage = goodsImage;
		this.goodsModel = goodsModel;
		this.goodsOperationSystem = goodsOperationSystem;
		this.goodsProcessor = goodsProcessor;
		this.goodsMemory = goodsMemory;
		this.goodsHarddiskCapacity = goodsHarddiskCapacity;
		this.goodsScreenSize = goodsScreenSize;
		this.goodsResolutionDefinition = goodsResolutionDefinition;
		this.goodsPrice = goodsPrice;
		this.goodsStock = goodsStock;
		this.goodsDiscount = goodsDiscount;
		this.goodsSales = goodsSales;
		this.goodsIsdel = goodsIsdel;
		this.goodsAveragemark = goodsAveragemark;
		this.TFavoritycontents = TFavoritycontents;
		this.TOrderlistcontents = TOrderlistcontents;
		this.tComments = TComments;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "goods_id", unique = true, nullable = false)
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firstcatalog_id")
	public TFirstcatalog getTFirstcatalog() {
		return this.TFirstcatalog;
	}

	public void setTFirstcatalog(TFirstcatalog TFirstcatalog) {
		this.TFirstcatalog = TFirstcatalog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondcatalog_id")
	public TSecondcatalog getTSecondcatalog() {
		return this.TSecondcatalog;
	}

	public void setTSecondcatalog(TSecondcatalog TSecondcatalog) {
		this.TSecondcatalog = TSecondcatalog;
	}

	@Column(name = "goods_name", nullable = false, length = 45)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_description", nullable = false, length = 200)
	public String getGoodsDescription() {
		return this.goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	@Column(name = "goods_image", length = 45)
	public String getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	@Column(name = "goods_model", length = 45)
	public String getGoodsModel() {
		return this.goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	@Column(name = "goods_operation_system", length = 45)
	public String getGoodsOperationSystem() {
		return this.goodsOperationSystem;
	}

	public void setGoodsOperationSystem(String goodsOperationSystem) {
		this.goodsOperationSystem = goodsOperationSystem;
	}

	@Column(name = "goods_processor", length = 45)
	public String getGoodsProcessor() {
		return this.goodsProcessor;
	}

	public void setGoodsProcessor(String goodsProcessor) {
		this.goodsProcessor = goodsProcessor;
	}

	@Column(name = "goods_memory", length = 45)
	public String getGoodsMemory() {
		return this.goodsMemory;
	}

	public void setGoodsMemory(String goodsMemory) {
		this.goodsMemory = goodsMemory;
	}

	@Column(name = "goods_harddisk_capacity", length = 45)
	public String getGoodsHarddiskCapacity() {
		return this.goodsHarddiskCapacity;
	}

	public void setGoodsHarddiskCapacity(String goodsHarddiskCapacity) {
		this.goodsHarddiskCapacity = goodsHarddiskCapacity;
	}

	@Column(name = "goods_screen_size", length = 20)
	public String getGoodsScreenSize() {
		return this.goodsScreenSize;
	}

	public void setGoodsScreenSize(String goodsScreenSize) {
		this.goodsScreenSize = goodsScreenSize;
	}

	@Column(name = "goods_resolution_definition", length = 45)
	public String getGoodsResolutionDefinition() {
		return this.goodsResolutionDefinition;
	}

	public void setGoodsResolutionDefinition(String goodsResolutionDefinition) {
		this.goodsResolutionDefinition = goodsResolutionDefinition;
	}

	@Column(name = "goods_price", nullable = false, precision = 22, scale = 0)
	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "goods_stock", nullable = false)
	public Integer getGoodsStock() {
		return this.goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	@Column(name = "goods_discount", precision = 22, scale = 0)
	public Double getGoodsDiscount() {
		return this.goodsDiscount;
	}

	public void setGoodsDiscount(Double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	@Column(name = "goods_sales")
	public Integer getGoodsSales() {
		return this.goodsSales;
	}

	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}

	@Column(name = "goods_isdel")
	public Boolean getGoodsIsdel() {
		return this.goodsIsdel;
	}

	public void setGoodsIsdel(Boolean goodsIsdel) {
		this.goodsIsdel = goodsIsdel;
	}

	@Column(name = "goods_averagemark", precision = 22, scale = 0)
	public Double getGoodsAveragemark() {
		return this.goodsAveragemark;
	}

	public void setGoodsAveragemark(Double goodsAveragemark) {
		this.goodsAveragemark = goodsAveragemark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TGoods")
	public Set<TFavoritycontent> getTFavoritycontents() {
		return this.TFavoritycontents;
	}

	public void setTFavoritycontents(Set<TFavoritycontent> TFavoritycontents) {
		this.TFavoritycontents = TFavoritycontents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TGoods")
	public Set<TOrderlistcontent> getTOrderlistcontents() {
		return this.TOrderlistcontents;
	}

	public void setTOrderlistcontents(Set<TOrderlistcontent> TOrderlistcontents) {
		this.TOrderlistcontents = TOrderlistcontents;
	}

	@OrderBy("commentDate DESC")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TGoods")
	public Set<TComment> getTComments() {
		return this.tComments;
	}

	public void setTComments(Set<TComment> TComments) {
		this.tComments = TComments;
	}

}