package com.hycloud.auth.sso.controller;

import com.hycloud.auth.sso.service.RoleService;
import com.hycloud.auth.sso.storage.entity.Role;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@GetMapping("/roles")
	public ListResult<?> getRoles(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
		return roleService.getUsers(pageNum, pageSize);
	}
	
	@GetMapping("/roles/{role}")
	public ItemResult<?> getRoleInfo(@PathVariable("role") String role){
		return roleService.getRole(role);
	}
	
	@PostMapping("/roles")
	public CommonResult addRole(@RequestBody Role role){
		return roleService.addRole(role);
	}
	
	@DeleteMapping("/roles/{role}")
	public CommonResult deleteRole(@PathVariable String role){
		return roleService.deleteRole(role);
	}
}
