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

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof Message)) {
			return false;
		}

		Message otherMessage = (Message) other;

		if (!(id == otherMessage.id)) {
			return false;
		}

		if (!(body == otherMessage.body)) {
			return false;
		}

		if (!(senderId == otherMessage.senderId)) {
			return false;
		}

		if (!(receiverId == otherMessage.receiverId)) {
			return false;
		}

		return true;
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
