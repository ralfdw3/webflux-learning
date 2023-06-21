package academy.devdojo.springwebfluxessentials.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public WebProperties.Resources webPropertiesResources() {
        return new WebProperties.Resources();
    }
}
