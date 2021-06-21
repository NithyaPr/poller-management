package se.nithya.poller.management.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public class ApplicationConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI apiDoc(Environment env) {

        String springActiveProfile = " (" + String.join(",", env.getActiveProfiles()) + ")";
        Contact contact = new Contact().email("xxx.yyy@gmail.com").name("omega");
        Info info = new Info().title(applicationName)
                .version(String.format("%s", springActiveProfile))
                .contact(contact);
        return new OpenAPI().info(info);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("POST", "GET", "PUT", "OPTIONS");
            }
        };
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
