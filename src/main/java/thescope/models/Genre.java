package thescope.models;

import jakarta.persistence.*;

@Entity(name="tblGenres")
@Embeddable
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PKgenre;
	
	@Column(name="genre")
	private String genre;


	public Long getPKgenre() {
		return PKgenre;
	}

	public void setPKgenre(Long PKgenre) {
		this.PKgenre = PKgenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
