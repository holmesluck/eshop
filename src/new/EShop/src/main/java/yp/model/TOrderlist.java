package yp.model;

import java.sql.Timestamp;
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
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TOrderlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_orderlist", catalog = "cshopdb")
public class TOrderlist implements java.io.Serializable {

	// Fields

	private Integer orderlistId;
	private TUser TUser;
	private TAddress TAddress;
	private Timestamp orderlistOrderdate;
	private Integer orderlistStatus;
	private Double orderlistTotalprice;
	private Set<TOrderlistcontent> TOrderlistcontents = new HashSet<TOrderlistcontent>(
			0);

	// Constructors

	/** default constructor */
	public TOrderlist() {
	}

	/** minimal constructor */
	public TOrderlist(Timestamp orderlistOrderdate, Double orderlistTotalprice) {
		this.orderlistOrderdate = orderlistOrderdate;
		this.orderlistTotalprice = orderlistTotalprice;
	}

	/** full constructor */
	public TOrderlist(TUser TUser, TAddress TAddress,
			Timestamp orderlistOrderdate, Integer orderlistStatus,
			Double orderlistTotalprice,
			Set<TOrderlistcontent> TOrderlistcontents) {
		this.TUser = TUser;
		this.TAddress = TAddress;
		this.orderlistOrderdate = orderlistOrderdate;
		this.orderlistStatus = orderlistStatus;
		this.orderlistTotalprice = orderlistTotalprice;
		this.TOrderlistcontents = TOrderlistcontents;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "orderlist_id", unique = true, nullable = false)
	public Integer getOrderlistId() {
		return this.orderlistId;
	}

	public void setOrderlistId(Integer orderlistId) {
		this.orderlistId = orderlistId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	public TAddress getTAddress() {
		return this.TAddress;
	}

	public void setTAddress(TAddress TAddress) {
		this.TAddress = TAddress;
	}

	@Column(name = "orderlist_orderdate", nullable = false, length = 19)
	public Timestamp getOrderlistOrderdate() {
		return this.orderlistOrderdate;
	}

	public void setOrderlistOrderdate(Timestamp orderlistOrderdate) {
		this.orderlistOrderdate = orderlistOrderdate;
	}

	@Column(name = "orderlist_status")
	public Integer getOrderlistStatus() {
		return this.orderlistStatus;
	}

	public void setOrderlistStatus(Integer orderlistStatus) {
		this.orderlistStatus = orderlistStatus;
	}

	@Column(name = "orderlist_totalprice", nullable = false, precision = 22, scale = 0)
	public Double getOrderlistTotalprice() {
		return this.orderlistTotalprice;
	}

	public void setOrderlistTotalprice(Double orderlistTotalprice) {
		this.orderlistTotalprice = orderlistTotalprice;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TOrderlist")
	public Set<TOrderlistcontent> getTOrderlistcontents() {
		return this.TOrderlistcontents;
	}

	public void setTOrderlistcontents(Set<TOrderlistcontent> TOrderlistcontents) {
		this.TOrderlistcontents = TOrderlistcontents;
	}

}