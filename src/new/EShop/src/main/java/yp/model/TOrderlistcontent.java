package yp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TOrderlistcontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_orderlistcontent", catalog = "cshopdb")
public class TOrderlistcontent implements java.io.Serializable {

	// Fields

	private Integer orderlistcontentId;
	private TOrderlist TOrderlist;
	private TGoods TGoods;
	private Integer orderlistcontentNum;
	private Double orderlistcontentOriginalprice;
	private Double orderlistcontentPrice;
	private Double orderlistcontentTotalprice;

	// Constructors

	/** default constructor */
	public TOrderlistcontent() {
	}

	/** minimal constructor */
	public TOrderlistcontent(Integer orderlistcontentNum,
			Double orderlistcontentOriginalprice, Double orderlistcontentPrice,
			Double orderlistcontentTotalprice) {
		this.orderlistcontentNum = orderlistcontentNum;
		this.orderlistcontentOriginalprice = orderlistcontentOriginalprice;
		this.orderlistcontentPrice = orderlistcontentPrice;
		this.orderlistcontentTotalprice = orderlistcontentTotalprice;
	}

	/** full constructor */
	public TOrderlistcontent(TOrderlist TOrderlist, TGoods TGoods,
			Integer orderlistcontentNum, Double orderlistcontentOriginalprice,
			Double orderlistcontentPrice, Double orderlistcontentTotalprice) {
		this.TOrderlist = TOrderlist;
		this.TGoods = TGoods;
		this.orderlistcontentNum = orderlistcontentNum;
		this.orderlistcontentOriginalprice = orderlistcontentOriginalprice;
		this.orderlistcontentPrice = orderlistcontentPrice;
		this.orderlistcontentTotalprice = orderlistcontentTotalprice;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "orderlistcontent_id", unique = true, nullable = false)
	public Integer getOrderlistcontentId() {
		return this.orderlistcontentId;
	}

	public void setOrderlistcontentId(Integer orderlistcontentId) {
		this.orderlistcontentId = orderlistcontentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderlist_id")
	public TOrderlist getTOrderlist() {
		return this.TOrderlist;
	}

	public void setTOrderlist(TOrderlist TOrderlist) {
		this.TOrderlist = TOrderlist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	@Column(name = "orderlistcontent_num", nullable = false)
	public Integer getOrderlistcontentNum() {
		return this.orderlistcontentNum;
	}

	public void setOrderlistcontentNum(Integer orderlistcontentNum) {
		this.orderlistcontentNum = orderlistcontentNum;
	}

	@Column(name = "orderlistcontent_originalprice", nullable = false, precision = 22, scale = 0)
	public Double getOrderlistcontentOriginalprice() {
		return this.orderlistcontentOriginalprice;
	}

	public void setOrderlistcontentOriginalprice(
			Double orderlistcontentOriginalprice) {
		this.orderlistcontentOriginalprice = orderlistcontentOriginalprice;
	}

	@Column(name = "orderlistcontent_price", nullable = false, precision = 22, scale = 0)
	public Double getOrderlistcontentPrice() {
		return this.orderlistcontentPrice;
	}

	public void setOrderlistcontentPrice(Double orderlistcontentPrice) {
		this.orderlistcontentPrice = orderlistcontentPrice;
	}

	@Column(name = "orderlistcontent_totalprice", nullable = false, precision = 22, scale = 0)
	public Double getOrderlistcontentTotalprice() {
		return this.orderlistcontentTotalprice;
	}

	public void setOrderlistcontentTotalprice(Double orderlistcontentTotalprice) {
		this.orderlistcontentTotalprice = orderlistcontentTotalprice;
	}

}