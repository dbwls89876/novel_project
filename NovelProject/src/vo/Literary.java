package vo;

import java.util.Date;

public class Literary {
	private int id; //유저고유번호
	private int literaryID; //작품고유번호
	private String nickname;
	private String title;
	private String content;
	private String genre;
	private String image;
	Date date;
	
	public Literary() {}
	
	public Literary(int id, int literaryID, String nickname, String title, String content, String genre, String image, Date date) {
		super();
		this.id = id;
		this.literaryID = literaryID;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.genre = genre;
		this.image = image;
		this.date = date;
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
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
