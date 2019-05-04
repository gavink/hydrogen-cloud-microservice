package com.hycloud.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignAuthRequestInterceptor implements RequestInterceptor {

	public FeignAuthRequestInterceptor() {}

	@Override
	public void apply(RequestTemplate template) {
		String token = null;
		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
		if(attrs != null) {
			HttpServletRequest request = ((ServletRequestAttributes) attrs).getRequest();
			token = request.getHeader("Authorization");
			if (token != null) {
				template.header("Authorization", token);
			} else {
				token = request.getParameter("access_token");
				if(token != null) {
					token = "Bearer " + token;
					template.header("Authorization", token);
				}
			}
		}

	}

}
