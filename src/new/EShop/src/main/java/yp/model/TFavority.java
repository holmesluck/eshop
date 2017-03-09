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
 * TFavority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_favority", catalog = "cshopdb", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class TFavority implements java.io.Serializable {

	// Fields

	private Integer favorityId;
	private TUser TUser;
	private Set<TFavoritycontent> TFavoritycontents = new HashSet<TFavoritycontent>(
			0);

	// Constructors

	/** default constructor */
	public TFavority() {
	}

	/** full constructor */
	public TFavority(TUser TUser, Set<TFavoritycontent> TFavoritycontents) {
		this.TUser = TUser;
		this.TFavoritycontents = TFavoritycontents;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "favority_id", unique = true, nullable = false)
	public Integer getFavorityId() {
		return this.favorityId;
	}

	public void setFavorityId(Integer favorityId) {
		this.favorityId = favorityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", unique = true)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TFavority")
	public Set<TFavoritycontent> getTFavoritycontents() {
		return this.TFavoritycontents;
	}

	public void setTFavoritycontents(Set<TFavoritycontent> TFavoritycontents) {
		this.TFavoritycontents = TFavoritycontents;
	}

}