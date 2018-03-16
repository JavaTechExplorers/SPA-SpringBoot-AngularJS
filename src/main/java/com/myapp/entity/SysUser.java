package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name="sys_user")
@NamedQuery(name="SysUser.findAll", query="SELECT s FROM SysUser s")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Column(name="CREATED_BY", nullable=false, length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", nullable=false)
	private Date createdDate;

	@Column(name="FIRST_NAME", nullable=false, length=255)
	private String firstName;

	@Column(name="LAST_NAME", nullable=false, length=255)
	private String lastName;

	@Column(name="MAIL_ID", length=255)
	private String mailId;

	@Column(name="PHONE_NUM", length=45)
	private String phoneNum;

	@Column(name="USER_NAME", nullable=false, length=10)
	private String username;

	@Column(name="USER_PASSWORD", nullable=false, length=255)
	private String password;

	//bi-directional many-to-one association to SysUserRoleMap
	@OneToMany(mappedBy="sysUser")
	private List<SysUserRoleMap> sysUserRoleMaps;

	public SysUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SysUserRoleMap> getSysUserRoleMaps() {
		return this.sysUserRoleMaps;
	}

	public void setSysUserRoleMaps(List<SysUserRoleMap> sysUserRoleMaps) {
		this.sysUserRoleMaps = sysUserRoleMaps;
	}

	public SysUserRoleMap addSysUserRoleMap(SysUserRoleMap sysUserRoleMap) {
		getSysUserRoleMaps().add(sysUserRoleMap);
		sysUserRoleMap.setSysUser(this);

		return sysUserRoleMap;
	}

	public SysUserRoleMap removeSysUserRoleMap(SysUserRoleMap sysUserRoleMap) {
		getSysUserRoleMaps().remove(sysUserRoleMap);
		sysUserRoleMap.setSysUser(null);

		return sysUserRoleMap;
	}

}