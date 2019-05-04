package com.hycloud.auth.sso.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hycloud.auth.sso.service.UserService;
import com.hycloud.auth.sso.storage.entity.*;
import com.hycloud.auth.sso.storage.mapper.UserInfoMapper;
import com.hycloud.auth.sso.storage.mapper.UserMapper;
import com.hycloud.auth.sso.storage.mapper.UserRolesMapper;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Autowired
	UserRolesMapper userRolesMapper;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public ListResult<UserInfo> getUsers(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserInfo> list = userInfoMapper.selectByExample(new UserInfoExample());
		PageInfo<UserInfo> resPage = new PageInfo<UserInfo>(list);
		ListResult<UserInfo> res = new ListResult<UserInfo>();
		res.setItems(list);
		res.setTotal(resPage.getTotal());
		res.setRes("S");
		return res;
	}

	@Override
	public ItemResult<UserInfo> getUserInfo(String username) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(username);
		ItemResult<UserInfo> res = new ItemResult<UserInfo>();
		res.setItem(userInfo);
		res.setRes("S");
		return res;
	}

	@Override
	@Transactional
	public CommonResult addUser(User user) {
		CommonResult res = new CommonResult();
		if(user.getUsername() == null || user.getPassword() == null) {
			return new CommonResult("E", "Username and password can not be empty.");
		}
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setStatus(1);
			userMapper.insert(user);
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(user.getUsername());
			userInfoMapper.insertSelective(userInfo);
			
			UserRoles userRoles = new UserRoles();
			userRoles.setUsername(user.getUsername());
			userRoles.setRole("ROLE_USER");
			userRolesMapper.insertSelective(userRoles);
			
			res.setRes("S");
			res.setMsg("User added.");
		} catch (DuplicateKeyException e) {
			res.setRes("E");
			res.setMsg("User is already exist.");
		}
		return res;
	}

	@Override
	public CommonResult saveUserInfo(UserInfo userInfo) {
		CommonResult res = new CommonResult();
		userInfoMapper.updateByPrimaryKey(userInfo);
		res.setRes("S");
		res.setMsg("User information saved.");
		return res;
	}

	@Override
	@Transactional
	public CommonResult deleteUser(String username) {
		userMapper.deleteByPrimaryKey(username);
		userInfoMapper.deleteByPrimaryKey(username);
		UserRolesExample example = new UserRolesExample();
		UserRolesExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		userRolesMapper.deleteByExample(example);
		return new CommonResult("S", "User deleted.");
	}

	@Override
	public CommonResult changePassword(String username, String oldPassword, String newPassword) {
		CommonResult res = new CommonResult();
		User user = userMapper.selectByPrimaryKey(username);
		if(user != null) {
			if(passwordEncoder.matches(oldPassword, user.getPassword())) {
				user.setPassword(passwordEncoder.encode(newPassword));
				userMapper.updateByPrimaryKey(user);
				res.setRes("S");
				res.setMsg("Password changed.");
			} else {
				res.setRes("E");
				res.setMsg("Incorrect password");
			}
		} else {
			res.setRes("E");
			res.setMsg("Changed password failed.");
		}
		return res;
	}
	
}
