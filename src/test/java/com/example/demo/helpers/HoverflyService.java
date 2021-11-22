package com.example.demo.helpers;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import io.specto.hoverfly.junit.core.HoverflyMode;
import javax.net.ssl.SSLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider.Proxy;

@Configuration
public class HoverflyService {

    @Bean
    public Hoverfly hoverfly() {
        Hoverfly hv = new Hoverfly(HoverflyConfig.localConfigs(), HoverflyMode.SIMULATE);
        hv.start();
        return hv;
    }

    // Override the webclient as netty Httpclient doesn't honour the jvm default proxy and ssl context
    @Bean
    @Primary
    public WebClient helloworldWebClient(Hoverfly hoverfly) throws SSLException {


        // To trust hoverfly CA
        SslContext sslContext = SslContextBuilder.forClient().trustManager(hoverfly.getSslConfigurer().getTrustManager()).build();

        // Set to use Hoverfly as proxy
        HttpClient httpClient = HttpClient.create()
            .proxy(p -> p.type(Proxy.HTTP).host("localhost").port(hoverfly.getHoverflyConfig().getProxyPort()))
            .secure(t -> t.sslContext(sslContext));
        ReactorClientHttpConnector reactorClientHttpConnector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder().clientConnector(reactorClientHttpConnector).baseUrl("https://docs.openaq.org/").build();
    }
}
