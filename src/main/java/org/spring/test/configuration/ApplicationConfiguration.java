package org.spring.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration conf = new CorsConfiguration().applyPermitDefaultValues();
        conf.addAllowedOrigin("http://localhost:3000");
        conf.addAllowedMethod(HttpMethod.GET);
        conf.addAllowedMethod(HttpMethod.POST);
        conf.addAllowedMethod(HttpMethod.PUT);
        conf.addAllowedMethod(HttpMethod.DELETE);
        source.registerCorsConfiguration("/**", conf);
        return source;
    }
}
