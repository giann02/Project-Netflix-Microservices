package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Season;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SerieCreatedEventConsumer {

    private final SerieRepository serieRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIES_CREATION)
    public void listen(Data data){

        var serieToSave = Serie.builder()
                .id(UUID.randomUUID().toString())
                .name(data.name)
                .genre(data.genre)
                .seasons(data.seasons)
                .build();

        serieRepository.save(serieToSave);

    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String name;
        private String genre;
        private List<Season> seasons;
    }

}
