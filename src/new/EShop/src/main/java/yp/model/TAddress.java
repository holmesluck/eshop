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
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_address", catalog = "cshopdb")
public class TAddress implements java.io.Serializable {

	// Fields

	private Integer addressId;
	private TUser TUser;
	private String addressAddress;
	private String addressPostcode;
	private String addressPhone;
	private String addressLinkman;
	private Boolean addressIsdefault;
	private Boolean addressIsdel;
	private Set<TOrderlist> TOrderlists = new HashSet<TOrderlist>(0);

	// Constructors

	/** default constructor */
	public TAddress() {
	}

	/** minimal constructor */
	public TAddress(String addressAddress, String addressPostcode,
			String addressPhone, String addressLinkman) {
		this.addressAddress = addressAddress;
		this.addressPostcode = addressPostcode;
		this.addressPhone = addressPhone;
		this.addressLinkman = addressLinkman;
	}

	/** full constructor */
	public TAddress(TUser TUser, String addressAddress, String addressPostcode,
			String addressPhone, String addressLinkman,
			Boolean addressIsdefault, Boolean addressIsdel,
			Set<TOrderlist> TOrderlists) {
		this.TUser = TUser;
		this.addressAddress = addressAddress;
		this.addressPostcode = addressPostcode;
		this.addressPhone = addressPhone;
		this.addressLinkman = addressLinkman;
		this.addressIsdefault = addressIsdefault;
		this.addressIsdel = addressIsdel;
		this.TOrderlists = TOrderlists;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "address_id", unique = true, nullable = false)
	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "address_address", nullable = false, length = 45)
	public String getAddressAddress() {
		return this.addressAddress;
	}

	public void setAddressAddress(String addressAddress) {
		this.addressAddress = addressAddress;
	}

	@Column(name = "address_postcode", nullable = false, length = 45)
	public String getAddressPostcode() {
		return this.addressPostcode;
	}

	public void setAddressPostcode(String addressPostcode) {
		this.addressPostcode = addressPostcode;
	}

	@Column(name = "address_phone", nullable = false, length = 45)
	public String getAddressPhone() {
		return this.addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	@Column(name = "address_linkman", nullable = false, length = 45)
	public String getAddressLinkman() {
		return this.addressLinkman;
	}

	public void setAddressLinkman(String addressLinkman) {
		this.addressLinkman = addressLinkman;
	}

	@Column(name = "address_isdefault")
	public Boolean getAddressIsdefault() {
		return this.addressIsdefault;
	}

	public void setAddressIsdefault(Boolean addressIsdefault) {
		this.addressIsdefault = addressIsdefault;
	}

	@Column(name = "address_isdel")
	public Boolean getAddressIsdel() {
		return this.addressIsdel;
	}

	public void setAddressIsdel(Boolean addressIsdel) {
		this.addressIsdel = addressIsdel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TAddress")
	public Set<TOrderlist> getTOrderlists() {
		return this.TOrderlists;
	}

	public void setTOrderlists(Set<TOrderlist> TOrderlists) {
		this.TOrderlists = TOrderlists;
	}

}