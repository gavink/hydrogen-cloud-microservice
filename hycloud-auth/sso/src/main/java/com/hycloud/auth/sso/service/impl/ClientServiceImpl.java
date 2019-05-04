package com.hycloud.auth.sso.service.impl;

import com.github.pagehelper.PageHelper;
import com.hycloud.auth.sso.service.ClientService;
import com.hycloud.auth.sso.storage.entity.Client;
import com.hycloud.auth.sso.storage.entity.ClientExample;
import com.hycloud.auth.sso.storage.mapper.ClientMapper;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientMapper clientMapper;

	@Override
	public ItemResult<Client> getClientById(String clientId) {
		ItemResult<Client> res = new ItemResult<Client>();
		Client client = clientMapper.selectByPrimaryKey(clientId);
		if(client != null) {
			res.setItem(client);
			res.setRes("S");
		} else {
			res.setRes("E");
			res.setMsg("No such client.");
		}
		return res;
	}

	@Override
	public ListResult<Client> getClients(int pageNum, int pageSize) {
		ListResult<Client> res = new ListResult<Client>();
		ClientExample example = new ClientExample();
		PageHelper.startPage(pageNum, pageSize);
		List<Client> clients = clientMapper.selectByExample(example);
		res.setItems(clients);
		res.setRes("S");
		res.setTotal(clientMapper.countByExample(example));
		return res;
	}

	@Override
	public CommonResult saveClient(Client client) {
		CommonResult res = new CommonResult();
		int c = clientMapper.updateByPrimaryKey(client);
		if(c == 1) {
			res.setRes("S");
			res.setMsg("Client info saved.");
		} else {
			res.setRes("E");
			res.setMsg("Save client failed.");
		}
		return res;
	}

	@Override
	public CommonResult addClient(Client client) {
		CommonResult res = new CommonResult();
		try {
			clientMapper.insertSelective(client);
			res.setRes("S");
			res.setMsg("Client added.");
		} catch(DuplicateKeyException e) {
			res.setRes("E");
			res.setMsg("Client is already exist.");
		}
		
		return res;
	}

	@Override
	public CommonResult delete(String clientId) {
		CommonResult res = new CommonResult();
		int c = clientMapper.deleteByPrimaryKey(clientId);
		if(c == 1) {
			res.setRes("S");
			res.setMsg("Client deleted.");
		} else {
			res.setRes("E");
			res.setMsg("Delete client failed.");
		}
		return res;
	}
	
	

}
