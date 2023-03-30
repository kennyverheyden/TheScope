package thescope;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import thescope.services.MovieService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieTest {

    @Autowired
    private MovieService movieService;
    @Test
    public void findMovieByIdTest(){
        Long id = 15L;
        assertNotNull(movieService.findMovieById(id));


    }



}
