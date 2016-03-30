package ar.edu.itba.paw.models;

public class Post {
	private String title;
	private String description;
	private int userId;
	
	public Post(String title, String description, int userId) {
		super();
		this.title = title;
		this.description = description;
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getUserId() {
		return userId;
	}
}
