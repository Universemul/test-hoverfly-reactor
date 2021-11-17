package com.example.demo.helpers;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import io.specto.hoverfly.junit.core.HoverflyMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HoverflyService {

    @Bean
    public Hoverfly hoverfly() {
        Hoverfly hv = new Hoverfly(HoverflyConfig.localConfigs().proxyLocalHost(), HoverflyMode.SIMULATE);
        hv.start();
        return hv;
    }
}
