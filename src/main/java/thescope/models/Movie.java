package thescope.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name="tblMovie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long PKmovie;
	@NotBlank		//jakarta validation annotatie, checked vanzelf of er geldige entities worden ingegeven, geen nood voor if/else blokken dus
	@NotNull (message = "Title cannot be null")
	@Column(name="movie")
	private String title;

	@NotBlank(message = "Genre cannot be blank")
	@NotNull (message = "Genre cannot be null")
	@Column(name = "genre")
	private String genre;
	@NotNull (message = "Rating cannot be null")
	@Column(name="rating")
	private double rating;
	@NotNull (message = "Length cannot be null")
	@Column(name="length")
	private int length;
	@NotNull (message = "3D attribute cannot be null")
	@Column(name="3D")
	private boolean threeD;
	@Column(nullable = true, length = 64)
	private String photo;


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

	public Movie(Movie source) {
		this.title = source.title;
		this.genre = source.genre;
		this.rating = source.rating;
		this.length = source.length;
		this.threeD = source.threeD;
	}

	/**get&set**/

	@Transient
	// Get path to the photo, we need this for html
	public String getPhotoImagePath() {
		if (photo == null) {
			return null;
		}
		else
		{
			return "/images/" + PKmovie + "/" + photo;
		}
	}

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getPKmovie() {
		return PKmovie;
	}
}
