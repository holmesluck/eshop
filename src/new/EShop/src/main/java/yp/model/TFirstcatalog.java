package yp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * TFirstcatalog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_firstcatalog", catalog = "cshopdb", uniqueConstraints = @UniqueConstraint(columnNames = "firstcatalog_name"))
public class TFirstcatalog implements java.io.Serializable {

	// Fields

	private Integer firstcatalogId;
	private String firstcatalogName;
	private Set<TGoods> TGoodses = new HashSet<TGoods>(0);
	private Set<TSecondcatalog> TSecondcatalogs = new HashSet<TSecondcatalog>(0);
	
	// Constructors

	/** default constructor */
	public TFirstcatalog() {
	}

	/** minimal constructor */
	public TFirstcatalog(String firstcatalogName) {
		this.firstcatalogName = firstcatalogName;
	}

	/** full constructor */
	public TFirstcatalog(String firstcatalogName, Set<TGoods> TGoodses,
			Set<TSecondcatalog> TSecondcatalogs) {
		this.firstcatalogName = firstcatalogName;
		this.TGoodses = TGoodses;
		this.TSecondcatalogs = TSecondcatalogs;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "firstcatalog_id", unique = true, nullable = false)
	public Integer getFirstcatalogId() {
		return this.firstcatalogId;
	}

	public void setFirstcatalogId(Integer firstcatalogId) {
		this.firstcatalogId = firstcatalogId;
	}

	@Column(name = "firstcatalog_name", unique = true, nullable = false, length = 45)
	public String getFirstcatalogName() {
		return this.firstcatalogName;
	}

	public void setFirstcatalogName(String firstcatalogName) {
		this.firstcatalogName = firstcatalogName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TFirstcatalog")
	public Set<TGoods> getTGoodses() {
		return this.TGoodses;
	}

	public void setTGoodses(Set<TGoods> TGoodses) {
		this.TGoodses = TGoodses;
	}
	
	@OrderBy("secondcatalogId")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "TFirstcatalog")
	public Set<TSecondcatalog> getTSecondcatalogs() {
		return this.TSecondcatalogs;
	}

	public void setTSecondcatalogs(Set<TSecondcatalog> TSecondcatalogs) {
		this.TSecondcatalogs = TSecondcatalogs;
	}

}