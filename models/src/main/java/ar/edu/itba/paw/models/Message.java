package ar.edu.itba.paw.models;

import java.util.Date;

public class Message {
	private String body;
	private int senderId;
	private int receiverId;
	private Date createdAt;
	
	public Message(String body, int senderId, int receiverId) {
		this.body = body;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.createdAt = new Date();
	}
	
	public String getBody() {
		return body;
	}

	public int getSenderId() {
		return senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}
