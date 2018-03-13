package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sys_user_role_map database table.
 * 
 */
@Entity
@Table(name="sys_user_role_map")
@NamedQuery(name="SysUserRoleMap.findAll", query="SELECT s FROM SysUserRoleMap s")
public class SysUserRoleMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ROLE_ID", unique=true, nullable=false)
	private int userRoleId;

	@Column(name="CREATE_BY", length=45)
	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="ROLE_ID", nullable=false)
	private int roleId;

	//bi-directional many-to-one association to SysUser
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private SysUser sysUser;

	public SysUserRoleMap() {
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
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

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}