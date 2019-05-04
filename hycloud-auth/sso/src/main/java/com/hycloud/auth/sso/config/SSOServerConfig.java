package com.hycloud.auth.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix="hycloud.auth")
public class SSOServerConfig {
	
	int tokenExpire = 30 * 60;

	int refreshTokenExpire = 6 * 60 * 60;

	public int getTokenExpire() {
		return tokenExpire;
	}

	public void setTokenExpire(int tokenExpire) {
		this.tokenExpire = tokenExpire;
	}

	public int getRefreshTokenExpire() {
		return refreshTokenExpire;
	}

	public void setRefreshTokenExpire(int refreshTokenExpire) {
		this.refreshTokenExpire = refreshTokenExpire;
	}
}
