package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.storage.entity.Role;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;

public interface RoleService {
	ListResult<Role> getUsers(int pageNum, int pageSize);
	ItemResult<Role> getRole(String role);
	CommonResult addRole(Role role);
	CommonResult deleteRole(String role);
}
