package com.hycloud.auth.sso.controller;

import com.hycloud.auth.sso.service.ClientService;
import com.hycloud.auth.sso.storage.entity.Client;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/clients")
	public ListResult<?> getClients(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
		return clientService.getClients(pageNum, pageSize);
	}
	
	@GetMapping("/clients/{clientId}")
	public ItemResult<?> getClient(@PathVariable("clientId") String clientId){
		return clientService.getClientById(clientId);
	}
	
	@PostMapping("/clients")
	public CommonResult addClient(@RequestBody Client client){
		return clientService.addClient(client);
	}
	
	@PutMapping("/clients")
	public CommonResult saveClient(@RequestBody Client client){
		return clientService.saveClient(client);
	}
	
	@DeleteMapping("/clients/{clientId}")
	public CommonResult saveClient(@PathVariable("clientId") String clientId){
		return clientService.delete(clientId);
	}
}
