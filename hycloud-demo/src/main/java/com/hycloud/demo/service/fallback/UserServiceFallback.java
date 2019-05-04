package com.hycloud.demo.service.fallback;

import org.springframework.stereotype.Component;

import com.hycloud.common.result.ListResult;
import com.hycloud.demo.pojo.UserInfo;
import com.hycloud.demo.service.UserService;

@Component
public class UserServiceFallback implements UserService {

	@Override
	public ListResult<UserInfo> getUsers(int page, int size) {
		ListResult<UserInfo> res = new ListResult<UserInfo>();
		res.setRes("E");
		res.setMsg("Call user service failed.");
		return res;
	}

}
