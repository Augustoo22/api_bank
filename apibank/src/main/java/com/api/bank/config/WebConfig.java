package com.api.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {
/**
 * Registra o filtro HiddenHttpMethodFilter como um bean no contexto do Spring.
 *
 * O HiddenHttpMethodFilter permite que métodos HTTP como PUT, DELETE e PATCH
 * sejam utilizados em formulários HTML, que por padrão suportam apenas os métodos GET e POST.
 */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}

