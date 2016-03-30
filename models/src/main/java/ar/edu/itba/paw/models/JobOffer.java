package ar.edu.itba.paw.models;

public class JobOffer {
	private String title;
	private String description;
	private int userId;
	
	public JobOffer(String title, String description, int userId) {
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
