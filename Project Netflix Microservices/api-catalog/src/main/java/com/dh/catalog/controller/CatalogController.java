package com.dh.catalog.controller;
import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	@GetMapping("/{genre}")
	public ResponseEntity<CatalogDTO> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.findCatalogByGenre(genre));
	}

	@GetMapping
	public ResponseEntity<List<Movie>> get() {
		return ResponseEntity.ok(catalogService.getMovies());
	}

}
