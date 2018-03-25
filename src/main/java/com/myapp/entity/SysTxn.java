package com.myapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the sys_txn database table.
 * 
 */
@Entity
@Table(name = "sys_txn")
@NamedQuery(name = "SysTxn.findAll", query = "SELECT s FROM SysTxn s")
public class SysTxn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TXN_ID", unique = true, nullable = false)
	private int txnId;

	@Column(name = "CREATED_BY", nullable = false, length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "ERROR_INFO", length = 4000)
	private String errorInfo;

	@Column(name = "SERVICE_CODE", nullable = false)
	private int serviceCode;

	@Column(name = "SERVICE_METHOD", nullable = false, length = 255)
	private String serviceMethod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME", nullable = false)
	private Date startTime;

	@Column(name = "USER_NAME", nullable = false, length = 255)
	private String userName;

	public SysTxn() {
	}

	public int getTxnId() {
		return this.txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
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

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getErrorInfo() {
		return this.errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public int getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceMethod() {
		return this.serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}