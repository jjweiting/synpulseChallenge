package com.synpulse.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Swagger Configuration
 *
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
	
  Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
	
	
    //read application.swagger 是否啟動屬性
    @Value(value = "${swagger.enabled:true}")
    Boolean swaggerEnabled;
    
    @Value(value = "${swagger.application-name:applicationName}")
    String applicationName;
    
    @Value(value = "${swagger.application-description:appliationDescription}")
    String applicationDescription;
    
    @Value(value = "${swagger.application-version:1.0}")
    String applicationVersion;
    
    @Value(value = "${swagger.contact-name:contactName}")
    String contactName;
    
    @Value(value = "${swagger.contact-email:contactEmail}")
    String contactEmail;
    
    @Value(value = "${swagger.contact-url:contactUrl:contactUrl}")
    String contactUrl;
    
    

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                // 是否開啟swagger
                //.enable(swaggerEnabled).select()
        		.enable(swaggerEnabled).select()
                // 過濾條件，掃描指定路徑下的選項
                .apis(RequestHandlerSelectors.basePackage("com.synpulse"))
                                //只保留/books/*
                .paths(PathSelectors.ant("/synpulse/**"))
                // 指定路徑處理，PathSelectors.any()代表不過濾任何路徑
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationDescription)
                // 開發者資訊
                .contact(new Contact(applicationName, contactUrl, contactEmail))
                .version(applicationVersion)
                .build();
    }
    
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
            List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
            Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
            allEndpoints.addAll(webEndpoints);
            allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
            allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
            String basePath = webEndpointProperties.getBasePath();
            EndpointMapping endpointMapping = new EndpointMapping(basePath);
            boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
            return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
        }


    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
            return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
        }
}