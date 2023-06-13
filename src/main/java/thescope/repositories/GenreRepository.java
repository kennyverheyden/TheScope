package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.Genre;
import thescope.models.User;

	@Repository
	public interface GenreRepository extends JpaRepository<Genre, Long> {
		Genre findGenreByGenreID(int pkGenre);
		Genre findGenreByGenreIgnoreCase(String genre);
}
