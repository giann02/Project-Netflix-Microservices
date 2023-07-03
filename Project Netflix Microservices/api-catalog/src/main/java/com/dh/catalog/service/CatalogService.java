package com.dh.catalog.service;
import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.controller.CatalogDTO;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final ObjectMapper mapper;
    @Autowired
    @Lazy
    private CatalogService self;
    public CatalogDTO findCatalogByGenre(String genre){
        var movies = self.getMoviesByGenre(genre);
        var series = self.getSeriesByGenre(genre);
        return CatalogDTO.builder()
                .movies(movies)
                .series(series)
                .build();
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }
    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getMoviesByGenreFallback")
    public List<MovieServiceClient.MovieDto> getMoviesByGenre(String genre){
        return movieServiceClient.getMovieByGenre(genre);
    }

    public List<Movie> getMoviesByGenreFallback(String genre, Throwable t) throws Exception {
        return movieRepository.findAllByGenre(genre);
    }
    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getSeriesByGenreFallback")
    public List<SerieServiceClient.SerieDTO> getSeriesByGenre(String genre){
        return serieServiceClient.getSeriesByGenre(genre);
    }

    public List<SerieServiceClient.SerieDTO> getSeriesByGenreFallback(String genre, Throwable t) throws Exception {
        return serieRepository.findAllByGenre(genre).stream()
                .map(serie -> mapper.convertValue(serie, SerieServiceClient.SerieDTO.class)).collect(Collectors.toList());
    }

}
