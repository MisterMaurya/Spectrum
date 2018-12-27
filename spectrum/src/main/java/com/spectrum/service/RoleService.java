package com.spectrum.service;

import java.util.List;

import com.spectrum.entity.Role;

public interface RoleService {
  
  public List<Role> listRoles();
  
  public boolean isRoleExists(Integer roleId);
	

 
}
