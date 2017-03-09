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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "cshopdb", uniqueConstraints = @UniqueConstraint(columnNames = "user_email"))
public class TUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userEmail;
	private String userPassword;
	private String userNickname;
	private TUserlevel TUserlevel;
	
	private Integer userRewardpoints;
	private Boolean userIsdel;
	private Set<TOrderlist> TOrderlists = new HashSet<TOrderlist>(0);
	private Set<TAddress> TAddresses = new HashSet<TAddress>(0);
	private Set<TComment> TComments = new HashSet<TComment>(0);
	private Set<TFavority> TFavorities = new HashSet<TFavority>(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(TUserlevel TUserlevel, String userEmail, String userPassword,
			String userNickname) {
		this.TUserlevel = TUserlevel;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
	}

	/** full constructor */
	public TUser(TUserlevel TUserlevel, String userEmail, String userPassword,
			String userNickname, Integer userRewardpoints, Boolean userIsdel,
			Set<TOrderlist> TOrderlists, Set<TAddress> TAddresses,
			Set<TComment> TComments, Set<TFavority> TFavorities) {
		this.TUserlevel = TUserlevel;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
		this.userRewardpoints = userRewardpoints;
		this.userIsdel = userIsdel;
		this.TOrderlists = TOrderlists;
		this.TAddresses = TAddresses;
		this.TComments = TComments;
		this.TFavorities = TFavorities;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_levelid", nullable = false)
	public TUserlevel getTUserlevel() {
		return this.TUserlevel;
	}

	public void setTUserlevel(TUserlevel TUserlevel) {
		this.TUserlevel = TUserlevel;
	}

	@Column(name = "user_email", unique = true, nullable = false, length = 45)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_password", nullable = false, length = 45)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_nickname", nullable = false, length = 45)
	public String getUserNickname() {
		return this.userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Column(name = "user_rewardpoints")
	public Integer getUserRewardpoints() {
		return this.userRewardpoints;
	}

	public void setUserRewardpoints(Integer userRewardpoints) {
		this.userRewardpoints = userRewardpoints;
	}

	@Column(name = "user_isdel")
	public Boolean getUserIsdel() {
		return this.userIsdel;
	}

	public void setUserIsdel(Boolean userIsdel) {
		this.userIsdel = userIsdel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TOrderlist> getTOrderlists() {
		return this.TOrderlists;
	}

	public void setTOrderlists(Set<TOrderlist> TOrderlists) {
		this.TOrderlists = TOrderlists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TAddress> getTAddresses() {
		return this.TAddresses;
	}

	public void setTAddresses(Set<TAddress> TAddresses) {
		this.TAddresses = TAddresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TComment> getTComments() {
		return this.TComments;
	}

	public void setTComments(Set<TComment> TComments) {
		this.TComments = TComments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser",orphanRemoval=true)
	public Set<TFavority> getTFavorities() {
		return this.TFavorities;
	}

	public void setTFavorities(Set<TFavority> TFavorities) {
		this.TFavorities = TFavorities;
	}

}