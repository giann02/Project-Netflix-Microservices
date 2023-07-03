package com.dh.movie.service;


import com.dh.movie.event.MovieCreatedEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieCreatedEventProducer producer;

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }
    public Movie save(Movie movie) {
        producer.publishCreateMovie(new MovieCreatedEventProducer.Data(movie.getName(), movie.getGenre()));
        return movieRepository.save(movie);
    }

}
