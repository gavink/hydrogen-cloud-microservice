package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.storage.entity.User;
import com.hycloud.auth.sso.storage.entity.UserInfo;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;

public interface UserService {
	ListResult<UserInfo> getUsers(int pageNum, int pageSize);
	ItemResult<UserInfo> getUserInfo(String username);
	CommonResult addUser(User user);
	CommonResult saveUserInfo(UserInfo userInfo);
	CommonResult deleteUser(String username);
	CommonResult changePassword(String username, String oldPassword, String newPassword);
}
