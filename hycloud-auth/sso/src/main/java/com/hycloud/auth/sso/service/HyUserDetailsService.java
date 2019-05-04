package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.pojo.HyUserDetails;
import com.hycloud.auth.sso.storage.entity.User;
import com.hycloud.auth.sso.storage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyUserDetailsService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.selectByPrimaryKey(username);
		if(user == null) throw new UsernameNotFoundException("User not found！");
		List<SimpleGrantedAuthority> auths = userMapper.getRolesByUsername(username);

		HyUserDetails userDetails = new HyUserDetails(user.getUsername(), user.getPassword(), user.getStatus(), auths);
		return userDetails;
	}

}
