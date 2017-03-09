package yp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * TUserlevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_userlevel", catalog = "cshopdb", uniqueConstraints = @UniqueConstraint(columnNames = "userlevel_name"))
public class TUserlevel implements java.io.Serializable {

	// Fields

	private Integer userlevelId;
	private String userlevelName;
	private String userlevelLimits;
	private Double userlevelDiscount;
	private Set<TUser> TUsers = new HashSet<TUser>(0);

	// Constructors

	/** default constructor */
	public TUserlevel() {
	}

	/** minimal constructor */
	public TUserlevel(String userlevelName, String userlevelLimits) {
		this.userlevelName = userlevelName;
		this.userlevelLimits = userlevelLimits;
	}

	/** full constructor */
	public TUserlevel(String userlevelName, String userlevelLimits,
			Double userlevelDiscount, Set<TUser> TUsers) {
		this.userlevelName = userlevelName;
		this.userlevelLimits = userlevelLimits;
		this.userlevelDiscount = userlevelDiscount;
		this.TUsers = TUsers;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userlevel_id", unique = true, nullable = false)
	public Integer getUserlevelId() {
		return this.userlevelId;
	}

	public void setUserlevelId(Integer userlevelId) {
		this.userlevelId = userlevelId;
	}

	@Column(name = "userlevel_name", unique = true, nullable = false, length = 45)
	public String getUserlevelName() {
		return this.userlevelName;
	}

	public void setUserlevelName(String userlevelName) {
		this.userlevelName = userlevelName;
	}

	@Column(name = "userlevel_limits", nullable = false, length = 45)
	public String getUserlevelLimits() {
		return this.userlevelLimits;
	}

	public void setUserlevelLimits(String userlevelLimits) {
		this.userlevelLimits = userlevelLimits;
	}

	@Column(name = "userlevel_discount", precision = 22, scale = 0)
	public Double getUserlevelDiscount() {
		return this.userlevelDiscount;
	}

	public void setUserlevelDiscount(Double userlevelDiscount) {
		this.userlevelDiscount = userlevelDiscount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUserlevel")
	public Set<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set<TUser> TUsers) {
		this.TUsers = TUsers;
	}

}