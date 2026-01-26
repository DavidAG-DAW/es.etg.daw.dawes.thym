package es.etg.daw.dawes.thym.productos.infraestructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    //Recupero el valor del fichero application.properties
    @Value("${restclient.productos.baseurl}")
    private String productosBaseUrl;
    
    @Bean
    public RestClient productoRestClient() {
        return RestClient.builder()
                .baseUrl(productosBaseUrl)
                .build();
    }
}
