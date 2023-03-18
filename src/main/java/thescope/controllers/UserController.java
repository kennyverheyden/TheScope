package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thescope.repositories.ScopeRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ScopeRepository scopeRepository;

    @Autowired
    public UserController(ScopeRepository scopeRepository) {
        this.scopeRepository = scopeRepository;
    }

}
