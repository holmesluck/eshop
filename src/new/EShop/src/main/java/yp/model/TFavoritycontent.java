package yp.model;

import javax.persistence.CascadeType;
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
 * TFavoritycontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_favoritycontent", catalog = "cshopdb")
public class TFavoritycontent implements java.io.Serializable {

	// Fields

	private Integer favoritycontentId;
	private TGoods TGoods;
	private TFavority TFavority;

	// Constructors

	/** default constructor */
	public TFavoritycontent() {
	}

	/** full constructor */
	public TFavoritycontent(TGoods TGoods, TFavority TFavority) {
		this.TGoods = TGoods;
		this.TFavority = TFavority;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "favoritycontent_id", unique = true, nullable = false)
	public Integer getFavoritycontentId() {
		return this.favoritycontentId;
	}

	public void setFavoritycontentId(Integer favoritycontentId) {
		this.favoritycontentId = favoritycontentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "favority_id")
	public TFavority getTFavority() {
		return this.TFavority;
	}

	public void setTFavority(TFavority TFavority) {
		this.TFavority = TFavority;
	}

}