package com.hycloud.demo.service;

import com.hycloud.common.result.ListResult;
import com.hycloud.demo.pojo.UserInfo;
import com.hycloud.demo.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="hycloud-sso",fallback=UserServiceFallback.class)
public interface UserService {
	
	@GetMapping("/api/users")
	public ListResult<UserInfo> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
}
