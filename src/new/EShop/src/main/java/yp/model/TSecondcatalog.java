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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * TSecondcatalog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_secondcatalog", catalog = "cshopdb")
public class TSecondcatalog implements java.io.Serializable{

	// Fields

	private Integer secondcatalogId;
	private TFirstcatalog TFirstcatalog;
	private String secondcatalogDescription;
	private String secondcatalogName;
	private Set<TGoods> TGoodses = new HashSet<TGoods>(0);

	// Constructors

	/** default constructor */
	public TSecondcatalog() {
	}

	/** minimal constructor */
	public TSecondcatalog(String secondcatalogName) {
		this.secondcatalogName = secondcatalogName;
	}

	/** full constructor */
	public TSecondcatalog(TFirstcatalog TFirstcatalog,
			String secondcatalogDescription, String secondcatalogName,
			Set<TGoods> TGoodses) {
		this.TFirstcatalog = TFirstcatalog;
		this.secondcatalogDescription = secondcatalogDescription;
		this.secondcatalogName = secondcatalogName;
		this.TGoodses = TGoodses;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "secondcatalog_id", unique = true, nullable = false)
	public Integer getSecondcatalogId() {
		return this.secondcatalogId;
	}

	public void setSecondcatalogId(Integer secondcatalogId) {
		this.secondcatalogId = secondcatalogId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondcatalog_parentid")
	public TFirstcatalog getTFirstcatalog() {
		return this.TFirstcatalog;
	}

	public void setTFirstcatalog(TFirstcatalog TFirstcatalog) {
		this.TFirstcatalog = TFirstcatalog;
	}

	@Column(name = "secondcatalog_description", length = 200)
	public String getSecondcatalogDescription() {
		return this.secondcatalogDescription;
	}

	public void setSecondcatalogDescription(String secondcatalogDescription) {
		this.secondcatalogDescription = secondcatalogDescription;
	}

	@Column(name = "secondcatalog_name", nullable = false, length = 45)
	public String getSecondcatalogName() {
		return this.secondcatalogName;
	}

	public void setSecondcatalogName(String secondcatalogName) {
		this.secondcatalogName = secondcatalogName;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "TSecondcatalog")
	public Set<TGoods> getTGoodses() {
		return this.TGoodses;
	}

	public void setTGoodses(Set<TGoods> TGoodses) {
		this.TGoodses = TGoodses;
	}

}