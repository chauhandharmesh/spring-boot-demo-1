package com.dc.springbootapp1.config;

import com.dc.springbootapp1.util.AppConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue queue() {
        return new Queue(AppConstant.QUEUE_NAME, false);
    }

    @Bean
    Queue employeeQueue() {
        return new Queue(AppConstant.EMPLOYEE_QUEUE, false);
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.directExchange(AppConstant.DIRECT_EXCHANGE_NAME).build();
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.key.1");
    }

    @Bean
    Binding employeeBinding(Queue employeeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(employeeQueue).to(exchange).with("routing.key.2");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
