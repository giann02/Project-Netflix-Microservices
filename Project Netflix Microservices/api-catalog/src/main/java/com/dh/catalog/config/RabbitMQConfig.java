package com.dh.catalog.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "examenExchange";
    public static final String TOPIC_MOVIE_CREATED = "movieCreated";
    public static final String TOPIC_SERIE_CREATED = "serieCreated";
    public static final String QUEUE_MOVIES_CREATION = "moviesCreationQueue";
    public static final String QUEUE_SERIES_CREATION = "seriesCreationQueue";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue moviesCreationQueue(){
        return new Queue(QUEUE_MOVIES_CREATION);
    }

    @Bean
    public Queue seriesCreationQueue(){
        return new Queue(QUEUE_SERIES_CREATION);
    }

    @Bean
    public Binding declareBindingMovies(){
        return BindingBuilder.bind(moviesCreationQueue()).to(appExchange()).with(TOPIC_MOVIE_CREATED);
    }

    @Bean
    public Binding declareBindingSeries(){
        return BindingBuilder.bind(seriesCreationQueue()).to(appExchange()).with(TOPIC_SERIE_CREATED);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}