package thescope.models;

import jakarta.persistence.*;

@Entity(name="tblMovie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long PKmovie;
	
	@Column(name="movie")
	private String title;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name="rating")
	private double rating;
	
	@Column(name="length")
	private int length;

	@Column(name="3D")
	private boolean threeD;


	/**contructor**/
	public Movie() {

	}
	public Movie(String title, String genre, double rating, int length, boolean threeD) {
		this.title = title;
		this.genre = genre;
		this.rating = rating; 
		this.length = length;
		this.threeD = threeD;
	}

	/**get&set**/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isThreeD() {
		return threeD;
	}

	public void setThreeD(boolean threeD) {
		this.threeD = threeD;
	}
	
	public String getGenre_fk() {
		return genre;
	}
	
	public void setGenre_fk(String genre) {
		this.genre = genre;
	}
	
	
}
