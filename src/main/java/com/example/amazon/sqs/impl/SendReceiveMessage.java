package com.example.amazon.sqs.impl;

import java.util.List;

public interface SendReceiveMessage {

	void deleteQueue();

	void createQueue();

	List<String> readMessages();

	void sendMessage(String msg);

	void purgeQueue();

}
