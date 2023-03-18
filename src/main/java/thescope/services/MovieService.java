package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thescope.models.Movie;
import thescope.repositories.ScopeRepository;

import java.util.List;

@Service
public class MovieService {

    private final ScopeRepository scopeRepository;

    @Autowired
    public MovieService(ScopeRepository scopeRepository) {
        this.scopeRepository = scopeRepository;
    }

    public List<Movie> getAllMovies() {
        return scopeRepository.findAllMovies();
    }
}
