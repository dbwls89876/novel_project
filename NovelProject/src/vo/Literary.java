package vo;

public class Literary {
	private int id;
	private int literaryID;
	private String name;
	private String content;
	private String genre;
	private double score;
	
	public Literary(int id, int literaryID, String name, String content, String genre, double score) {
		super();
		this.id = id;
		this.literaryID = literaryID;
		this.name = name;
		this.content = content;
		this.genre = genre;
		this.score = score;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
