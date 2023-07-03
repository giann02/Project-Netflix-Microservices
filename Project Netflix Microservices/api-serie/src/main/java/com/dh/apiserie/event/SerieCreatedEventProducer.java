package com.dh.apiserie.event;
import com.dh.apiserie.config.RabbitMQConfig;
import com.dh.apiserie.model.Season;
import lombok.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SerieCreatedEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void publishCreateSerie(Data data){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_SERIE_CREATED, data);
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
