package yp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", schema = "test")
public class User implements java.io.Serializable {

	// Fields

	private String userName;
	private Integer password;
	private String description;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, Integer password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public User(String userName, Integer password, String description) {
		this.userName = userName;
		this.password = password;
		this.description = description;
	}

	// Property accessors
	@Id
	@Column(name = "user_name", unique = true, nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false)
	public Integer getPassword() {
		return this.password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}