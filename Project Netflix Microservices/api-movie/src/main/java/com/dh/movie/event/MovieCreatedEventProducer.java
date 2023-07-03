package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import lombok.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieCreatedEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void publishCreateMovie(Data data){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE_CREATED, data);
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
