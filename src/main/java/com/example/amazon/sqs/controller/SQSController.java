package com.example.amazon.sqs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.amazon.sqs.impl.SendReceiveMessage;

@RestController
@RequestMapping("queue")
public class SQSController {

	@Autowired
	private SendReceiveMessage queueService;
	
	@PostMapping("createQueue")
	public void createQueue(){
		queueService.createQueue();
	}
	
	@PostMapping("sendMessage")
	public void sendMessage(@RequestBody String msg){
		queueService.sendMessage(msg);
	}
	
	@GetMapping("receiveMessage")
	public List<String> receiveMessage(){
		return queueService.readMessages();
	}
	
	@PostMapping("purgeQueue")
	public void purgeQueue(){
		queueService.purgeQueue();
	}
	
	@DeleteMapping("deleteQueue")
	public void deleteQueue(){
		queueService.deleteQueue();
	}
}
