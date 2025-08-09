package com.StarkIndustries.UserMicroService.configuration;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public static BeanFactoryPostProcessor disableRxJava3Fallback() {
//        return (ConfigurableListableBeanFactory beanFactory) -> {
//            if (beanFactory instanceof BeanDefinitionRegistry registry) {
//                if (registry.containsBeanDefinition("rxJava3FallbackDecorator")) {
//                    registry.removeBeanDefinition("rxJava3FallbackDecorator");
//                }
//            }
//        };
//    }
}
