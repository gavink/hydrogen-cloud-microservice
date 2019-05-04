package com.hycloud.auth.sso.service;

import com.hycloud.auth.sso.storage.entity.Client;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;

public interface ClientService {
	ItemResult<Client> getClientById(String clientId);
	ListResult<Client> getClients(int pageNum, int pageSize);
	CommonResult saveClient(Client client);
	CommonResult addClient(Client client);
	CommonResult delete(String clientId);
}
