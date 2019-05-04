package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.storage.entity.Auth;
import com.hycloud.auth.sso.storage.entity.Menu;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;

public interface AuthService {
	ItemResult<Auth> getAuthTree();
	ListResult<Menu> getMenus(String username);
}
