package com.company.shopping.bean;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_goods_type", catalog = "shopping")
public class GType implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String typename;
	private String note;

	// Constructors

	/** default constructor */
	public GType() {
	}

	/** minimal constructor */
	public GType(Integer pid, String typename) {
		this.pid = pid;
		this.typename = typename;
	}

	/** full constructor */
	public GType(Integer pid, String typename, String note) {
		this.pid = pid;
		this.typename = typename;
		this.note = note;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "pid", nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "typename", nullable = false, length = 30)
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}