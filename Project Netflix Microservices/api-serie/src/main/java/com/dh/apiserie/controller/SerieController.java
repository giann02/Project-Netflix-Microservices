package com.dh.apiserie.controller;

import com.dh.apiserie.model.Serie;
import com.dh.apiserie.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        return ResponseEntity.ok(serieService.getSeriesBygGenre(genre));
    }

    @PostMapping("/save")
    void createNewSerie(@RequestBody Serie serie) {
         serieService.createSerie(serie);
    }

}
