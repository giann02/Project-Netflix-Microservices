package com.dh.catalog.event;
import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.repository.MovieRepository;
import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MovieCreatedEventConsumer {
    private final MovieRepository movieRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIES_CREATION)
    public void listen(Data data){

        var movieToSave = Movie.builder()
                .id(UUID.randomUUID().toString())
                .name(data.name)
                .genre(data.genre)
                .build();

        movieRepository.save(movieToSave);

    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String name;
        private String genre;
    }

}
