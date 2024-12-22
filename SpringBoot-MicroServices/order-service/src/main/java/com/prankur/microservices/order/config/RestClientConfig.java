package com.prankur.microservices.order.config;

import com.prankur.microservices.order.client.InventoryClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
//import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;
import java.util.Map;

@Configuration
public class RestClientConfig
{
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient()
    {
        RestClient restCLient = RestClient.builder()
                                    .baseUrl(inventoryServiceUrl)
//                                    .requestFactory(getClientRequestFactory())
                                    .build();

        var restClientAdapter = RestClientAdapter.create(restCLient);

        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

//    public ClientHttpRequestFactory getClientRequestFactory()
//    {
//        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings
//                                                                                    .defaults()
//                                                                                    .withConnectTimeout(Duration.ofSeconds(3))
//                                                                                    .withReadTimeout(Duration.ofSeconds(3));
//        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
//    }

}
