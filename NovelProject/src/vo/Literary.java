package vo;

import java.util.Date;

public class Literary {
	private int id;
	private int literaryID;
	private String title;
	private String content;
	private String genre;
	private double score;
	private String image;
	Date date;
	
	public Literary() {}
	
	public Literary(int id, int literaryID, String name, String content, String genre, double score, String image,
			Date date) {
		super();
		this.id = id;
		this.literaryID = literaryID;
		this.title = name;
		this.content = content;
		this.genre = genre;
		this.score = score;
		this.image = image;
		this.date = date;
	}
	public Literary(int id, int literaryID, String name, String content, String genre, double score, String image) {
		super();
		this.id = id;
		this.literaryID = literaryID;
		this.title = name;
		this.content = content;
		this.genre = genre;
		this.score = score;
		this.image = image;

	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLiteraryID() {
		return literaryID;
	}
	public void setLiteraryID(int literaryID) {
		this.literaryID = literaryID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
