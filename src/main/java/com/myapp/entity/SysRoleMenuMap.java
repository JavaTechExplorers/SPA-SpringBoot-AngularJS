package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sys_role_menu_map database table.
 * 
 */
@Entity
@Table(name="sys_role_menu_map")
@NamedQuery(name="SysRoleMenuMap.findAll", query="SELECT s FROM SysRoleMenuMap s")
public class SysRoleMenuMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_MENU_ID", unique=true, nullable=false)
	private int roleMenuId;

	@Column(name="CREATE_BY", nullable=false, length=45)
	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE", nullable=false)
	private Date createDate;

	@Column(name="MENU_ID", nullable=false)
	private int menuId;

	//bi-directional many-to-one association to SysRole
	@ManyToOne
	@JoinColumn(name="ROLE_ID", nullable=false)
	private SysRole sysRole;

	public SysRoleMenuMap() {
	}

	public int getRoleMenuId() {
		return this.roleMenuId;
	}

	public void setRoleMenuId(int roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}