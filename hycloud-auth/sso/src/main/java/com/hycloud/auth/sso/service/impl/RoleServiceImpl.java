package com.hycloud.auth.sso.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hycloud.auth.sso.service.RoleService;
import com.hycloud.auth.sso.storage.entity.Role;
import com.hycloud.auth.sso.storage.entity.RoleExample;
import com.hycloud.auth.sso.storage.mapper.RoleMapper;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public ListResult<Role> getUsers(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> list = roleMapper.selectByExample(new RoleExample());
		PageInfo<Role> resPage = new PageInfo<Role>(list);
		ListResult<Role> res = new ListResult<Role>();
		res.setItems(list);
		res.setTotal(resPage.getTotal());
		res.setRes("S");
		return res;
	}

	@Override
	public ItemResult<Role> getRole(String role) {
		ItemResult<Role> res = new ItemResult<Role>();
		res.setItem(roleMapper.selectByPrimaryKey(role));
		res.setRes("S");
		return res;
	}

	@Override
	public CommonResult addRole(Role role) {
		CommonResult res = new CommonResult();
		roleMapper.insertSelective(role);
		res.setRes("S");
		res.setMsg("Role added.");
		return res;
	}

	@Override
	public CommonResult deleteRole(String role) {
		CommonResult res = new CommonResult();
		roleMapper.deleteByPrimaryKey(role);
		res.setRes("S");
		res.setMsg("Role deleted.");
		return res;
	}

}
