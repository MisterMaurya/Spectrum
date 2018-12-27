package com.spectrum.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.spectrum.constants.DBconstants;
import com.spectrum.enums.IsActive;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = DBconstants.USER_MASTER)
public class User {

	private int userId;
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String password;
	private String employeeId;
	private String username;
	private String contact;
	private Set<Role> role = new HashSet<>(0);
	private IsActive isEnabled = IsActive.Y;
	private Date createdOn;
	private Date modifiedOn;
	private int createdBy;
	private int modifiedBy;
	private Date lastLogin;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DBconstants.USER_ID, unique = true, length = 11)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@NotNull
	@Column(name = DBconstants.PREFIX, unique = false, length = 11)
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@NotNull
	@Column(name = DBconstants.FIRST_NAME, unique = false, length = 200)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = DBconstants.MIDDLE_NAME, unique = false, length = 200)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@NotNull
	@Column(name = DBconstants.LAST_NAME, unique = false, length = 200)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotNull
	@Column(name = DBconstants.EMAIL, unique = true, length = 300)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@Column(name = DBconstants.PASSWORD, updatable = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Column(name = DBconstants.EMPLOYEE_ID, unique = true, length = 300)
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@NotNull
	@Column(name = DBconstants.USERNAME, unique = true, length = 300)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Column(name = DBconstants.CONTACT, unique = false, length = 15)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = DBconstants.USER_ROLE_MAP, joinColumns = {
			@JoinColumn(name = DBconstants.USER_ID) }, inverseJoinColumns = { @JoinColumn(name = DBconstants.ROLE_ID) })
	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = DBconstants.IS_ENABLED, columnDefinition = "varchar(10) default 'Y'", nullable = false)
	public IsActive getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(IsActive isEnabled) {
		this.isEnabled = isEnabled;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = DBconstants.CREATED_ON)
	@JsonProperty(access = Access.WRITE_ONLY)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = new Date();
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = DBconstants.MODIFIED_ON)
	@ApiModelProperty(hidden = true)
	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = null;
	}

	@Column(name = DBconstants.CREATED_BY, length = 11)
	@ApiModelProperty(hidden = true)
	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	@Column(name = DBconstants.MODIFIED_BY, length = 11)
	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(hidden = true)
	@Column(name = DBconstants.LAST_LOGIN, length = 11)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = null;
	}

}
