package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblMovie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKmovie;
	
	@Column(name="movie")
	String title;
	
	@Column(name="FKgenre")
	int genre_fk;
	
	@Column(name="rating")
	double rating;
	
	@Column(name="length")
	int length;

	@Column(name="3D")
	private boolean threeD;


	/**contructor**/
	public Movie() {

	}
	public Movie(String title, int genre_fk, double rating, int length, boolean threeD) {
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

//	public String getGenre() {
//		return genre;
//	}
//
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}

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
	
	public int getGenre_fk() {
		return genre_fk;
	}
	
	public void setGenre_fk(int genre_fk) {
		this.genre_fk = genre_fk;
	}
	
	
}
