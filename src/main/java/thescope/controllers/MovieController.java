package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thescope.repositories.ScopeRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final ScopeRepository scopeRepository;

    @Autowired
    public MovieController(ScopeRepository scopeRepository) {
        this.scopeRepository = scopeRepository;
    }
}
