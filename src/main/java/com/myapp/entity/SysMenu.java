package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sys_menu database table.
 * 
 */
@Entity
@Table(name="sys_menu")
@NamedQuery(name="SysMenu.findAll", query="SELECT s FROM SysMenu s")
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MENU_ID", unique=true, nullable=false)
	private int menuId;

	@Column(name="CREATED_BY", nullable=false, length=10)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", nullable=false)
	private Date createdDate;

	@Column(name="DISPLAY_NAME", nullable=false, length=45)
	private String displayName;

	@Column(name="MENU_CODE", nullable=false, length=6)
	private String menuCode;

	@Column(name="MENU_NAME", nullable=false, length=45)
	private String menuName;

	@Column(nullable=false, length=45)
	private String url;

	public SysMenu() {
	}

	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}