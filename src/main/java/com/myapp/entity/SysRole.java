package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sys_role database table.
 * 
 */
@Entity
@Table(name="sys_role")
@NamedQuery(name="SysRole.findAll", query="SELECT s FROM SysRole s")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID", unique=true, nullable=false)
	private int roleId;

	@Column(name="CREATED_BY", nullable=false, length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", nullable=false)
	private Date createdDate;

	@Column(name="ROLE_DESC", length=255)
	private String roleDesc;

	@Column(name="ROLE_NAME", nullable=false, length=255)
	private String roleName;

	//bi-directional many-to-one association to SysRoleMenuMap
	@OneToMany(mappedBy="sysRole")
	private List<SysRoleMenuMap> sysRoleMenuMaps;

	public SysRole() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SysRoleMenuMap> getSysRoleMenuMaps() {
		return this.sysRoleMenuMaps;
	}

	public void setSysRoleMenuMaps(List<SysRoleMenuMap> sysRoleMenuMaps) {
		this.sysRoleMenuMaps = sysRoleMenuMaps;
	}

	public SysRoleMenuMap addSysRoleMenuMap(SysRoleMenuMap sysRoleMenuMap) {
		getSysRoleMenuMaps().add(sysRoleMenuMap);
		sysRoleMenuMap.setSysRole(this);

		return sysRoleMenuMap;
	}

	public SysRoleMenuMap removeSysRoleMenuMap(SysRoleMenuMap sysRoleMenuMap) {
		getSysRoleMenuMaps().remove(sysRoleMenuMap);
		sysRoleMenuMap.setSysRole(null);

		return sysRoleMenuMap;
	}

}