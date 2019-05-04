package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.storage.entity.Client;
import com.hycloud.auth.sso.storage.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HyClientDetailsService implements ClientDetailsService {
	@Autowired
	ClientMapper clientMapper;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Client client = clientMapper.selectByPrimaryKey(clientId);
		BaseClientDetails clientDetails = new BaseClientDetails();
		if(client == null) {
			throw new NoSuchClientException("No such client");
		}
		clientDetails.setClientId(clientId);
		clientDetails.setClientSecret(client.getClientSecret());
		clientDetails.setAuthorizedGrantTypes(getDefaultGrantTypes());
		clientDetails.setScope(getDefaultScopes());
		
		return clientDetails;
	}
	
	List<String> getDefaultGrantTypes(){
		List<String> grantTypes = new ArrayList<String>();
		grantTypes.add("password");
		grantTypes.add("authorization_code");
		grantTypes.add("refresh_token");
		return grantTypes;
	}

	List<String> getDefaultScopes(){
		List<String> scopes = new ArrayList<String>();
		scopes.add("all");
		return scopes;
	}
}
