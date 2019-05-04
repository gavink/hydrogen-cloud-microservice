package com.hycloud.auth.sso.controller;

import com.hycloud.auth.sso.service.AuthService;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	DefaultTokenServices tokenService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, @RequestParam(value="error", required=false) String error) {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		logger.info("\n\n");
		logger.info("********** User login **********");
		logger.info("username: " + username + ", password: " + password);
		logger.info("********** ********** **********");
		logger.info("\n\n");
		
		UsernamePasswordAuthenticationToken authRequest = 
				new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			Authentication authentication = authenticationManager.authenticate(authRequest);
			SecurityContext ctx = SecurityContextHolder.getContext();
			ctx.setAuthentication(authentication);
		} catch (AuthenticationException e) {
			String err;
			try {
				err = URLEncoder.encode(e.getMessage(), "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
				err = "登陆失败";
			}
			return "redirect:/login?error=" + err;
		}
		
		return "redirect:/index";
	}
	
	@RequestMapping("/oauth/revoke-token")
	@ResponseBody
	public CommonResult logout(@RequestHeader(value="Authorization", required=false) String authorization,
			@RequestParam(value="access_token", required=false) String accessToken) {
		try {
			String token = null;
			if(authorization != null) {
				String[] auth = authorization.split(" ");
				if(auth.length != 2) throw new RuntimeException("Invalid authorization header: " + authorization);
				token = auth[1];
			} else if(accessToken != null) {
				token = accessToken;
			} else {
				throw new RuntimeException("You have not logged in.");
			}
			tokenService.revokeToken(token);
			return new CommonResult("S", "Logged out successfully.");
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new CommonResult("E", e.getMessage());
		}
		
	}
	
	@Autowired
	AuthService authService;
	
	@RequestMapping("/api/authtree")
	@ResponseBody
	public ItemResult<?> getAuthTree(){
		return authService.getAuthTree();
	}
	
	@RequestMapping("/api/menus")
	@ResponseBody
	public ListResult<?> getMenus(@AuthenticationPrincipal UserDetails user){
		return authService.getMenus(user.getUsername());
	}

}
