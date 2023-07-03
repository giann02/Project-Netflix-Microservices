package com.dh.apiserie.service;

import com.dh.apiserie.event.SerieCreatedEventProducer;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService {
    private final SerieRepository repository;
    private final SerieCreatedEventProducer producer;
    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }
    public Serie createSerie(Serie serieDto) {
        producer.publishCreateSerie(new SerieCreatedEventProducer.Data(serieDto.getName(), serieDto.getGenre(), serieDto.getSeasons()));
        return repository.save(serieDto);
    }
}
