package thescope.models;

import jakarta.persistence.*;

@Entity(name="tblMovie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long PKmovie;
	
	@Column(name="movie")
	private String title;
	
	@Embedded
	@Column(name = "genre_fk")
	private Genre genre_fk;
	
	@Column(name="rating")
	private double rating;
	
	@Column(name="length")
	private int length;

	@Column(name="3D")
	private boolean threeD;


	/**contructor**/
	public Movie() {

	}
	public Movie(String title, Genre genre_fk, double rating, int length, boolean threeD) {
		this.title = title;
		this.genre_fk = genre_fk;
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
	
	public Genre getGenre_fk() {
		return genre_fk;
	}
	
	public void setGenre_fk(Genre genre_fk) {
		this.genre_fk = genre_fk;
	}
	
	
}
