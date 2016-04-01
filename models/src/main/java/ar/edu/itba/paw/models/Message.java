package ar.edu.itba.paw.models;

import java.util.Date;

public class Message {
	private Long id;
	private String body;
	private int senderId;
	private int receiverId;
	private Date createdAt;

	public Message(Long id, String body, int senderId, int receiverId, Date createdAt) {
		this.id = id;
		this.body = body;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
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
