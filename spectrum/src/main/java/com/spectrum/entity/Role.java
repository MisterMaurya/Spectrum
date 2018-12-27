package com.spectrum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.spectrum.constants.DBconstants;

@Entity
@Table(name = DBconstants.ROLE_MASTER)
public class Role {

	private int roleId;
	private String roleName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DBconstants.ROLE_ID, updatable = false)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@NotNull
	@Column(name = DBconstants.ROLE_NAME, unique = true, length = 200)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}