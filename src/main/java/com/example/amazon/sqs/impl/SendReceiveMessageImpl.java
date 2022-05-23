package com.example.amazon.sqs.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Service
public class SendReceiveMessageImpl implements SendReceiveMessage {
	private static final String QUEUE_NAME = "MyQueue";

	@Autowired
	AmazonSQS sqs;

	@Override
	public void sendMessage(String msg) {
		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();

		SendMessageRequest messageRequest = new SendMessageRequest()
				.withQueueUrl(queueUrl)
				.withMessageBody(msg);

		sqs.sendMessage(messageRequest);
	}

	@Override
	public List<String> readMessages() {
		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();

		// receive messages from the queue
		List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();

		List<String> content = new ArrayList<>();
		
		// delete messages from the queue
		for (Message m : messages) {
			content.add(m.getBody());
			sqs.deleteMessage(queueUrl, m.getReceiptHandle());
		}

		return content;
	}

	@Override
	public void createQueue() {
		try {
			sqs.createQueue(QUEUE_NAME);
		} catch (AmazonSQSException e) {
			if (!e.getErrorCode().equals("QueueAlreadyExists")) {
				throw e;
			}
		}
	}

	@Override
	public void purgeQueue() {
		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
		
		PurgeQueueRequest purgeQueueRequest = new PurgeQueueRequest(queueUrl);
		sqs.purgeQueue(purgeQueueRequest);
	}
	
	@Override
	public void deleteQueue() {
		sqs.deleteQueue(QUEUE_NAME);
	}

}
