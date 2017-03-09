package yp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * TAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_admin", catalog = "cshopdb", uniqueConstraints = @UniqueConstraint(columnNames = "admin_name"))
public class TAdmin implements java.io.Serializable {

	// Fields

	private Integer adminId;
	private String adminName;
	private String adminPassword;

	// Constructors

	/** default constructor */
	public TAdmin() {
	}

	/** full constructor */
	public TAdmin(String adminName, String adminPassword) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "admin_id", unique = true, nullable = false)
	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Column(name = "admin_name", unique = true, nullable = false, length = 45)
	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "admin_password", nullable = false, length = 45)
	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}