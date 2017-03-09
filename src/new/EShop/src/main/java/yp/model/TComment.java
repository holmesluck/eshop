package yp.model;

import java.sql.Timestamp;
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
 * TComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_comment", catalog = "cshopdb")
public class TComment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private TUser tUser;
	private TGoods TGoods;
	private Timestamp commentDate;
	private Short commentMark;
	private String commentContent;

	// Constructors

	/** default constructor */
	public TComment() {
	}

	/** minimal constructor */
	public TComment(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	/** full constructor */
	public TComment(TUser TUser, TGoods TGoods, Timestamp commentDate,
			Short commentMark, String commentContent) {
		this.tUser = TUser;
		this.TGoods = TGoods;
		this.commentDate = commentDate;
		this.commentMark = commentMark;
		this.commentContent = commentContent;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public TUser getTUser() {
		return this.tUser;
	}

	public void setTUser(TUser TUser) {
		this.tUser = TUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	@Column(name = "comment_date", nullable = false, length = 19)
	public Timestamp getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	@Column(name = "comment_mark")
	public Short getCommentMark() {
		return this.commentMark;
	}

	public void setCommentMark(Short commentMark) {
		this.commentMark = commentMark;
	}

	@Column(name = "comment_content", length = 300)
	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

}