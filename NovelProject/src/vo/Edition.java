package vo;

import java.util.Date;

public class Edition {
	private int id; //유저고유번호
	private int literaryID; //작품고유번호
	private int editionID; //회차고유번호
	private String title;
	private String content;
	Date date;
	private int count;
	
	
	
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
	public int getEditionID() {
		return editionID;
	}
	public void setEditionID(int editionID) {
		this.editionID = editionID;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
