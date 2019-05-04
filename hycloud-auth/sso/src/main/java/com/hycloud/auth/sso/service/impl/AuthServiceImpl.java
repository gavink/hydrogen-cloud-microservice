package com.hycloud.auth.sso.service.impl;

import com.hycloud.auth.sso.service.AuthService;
import com.hycloud.auth.sso.storage.entity.Auth;
import com.hycloud.auth.sso.storage.entity.Menu;
import com.hycloud.auth.sso.storage.mapper.AuthMapper;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthMapper authMapper;

	@Override
	public ItemResult<Auth> getAuthTree() {
		ItemResult<Auth> res = new ItemResult<Auth>();
		Auth authTree = authMapper.getAuthTree(-1);
		res.setItem(authTree);
		res.setRes("S");
		return res;
	}

	@Override
	public ListResult<Menu> getMenus(String username) {
		List<Menu> menus = authMapper.getMenus(username);
		ListResult<Menu> res = new ListResult<Menu>();
		res.setItems(menus);
		res.setTotal(menus.size());
		res.setRes("S");
		return res;
	}

}
