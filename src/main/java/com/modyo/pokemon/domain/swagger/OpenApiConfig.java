package com.modyo.pokemon.domain.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@PropertySource("classpath:application-swagger.properties")
public class OpenApiConfig {

    @Bean
    @ConditionalOnResource(resources = "${spring.info.build.location:classpath:META-INF/build-info.properties}")
    public OpenAPI customOpenAPI(BuildProperties buildProperties,
                                 @Value("${springdoc.info.description:}") String description,
                                 @Value("${springdoc.server.url:}") String serverUrl) {
        var openAPI = new OpenAPI().info(new Info()
                .title(buildProperties.getName())
                .version(buildProperties.getVersion())
                .description(description));
        if (StringUtils.isNotBlank(serverUrl)) {
            openAPI.servers(List.of(new Server().url(serverUrl)));
        }
        return openAPI;
    }

}