package com.imeira.product.purchase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Primary
//    @Bean
//    public LinkDiscoverers discoverers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.imeira.product.purchase.controller"))
                .paths(PathSelectors.any())
                .build()
                //daqui pra baixo eh customizacao da api opcional
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext())).pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title ("Documentação da API")
                .description ("Essa é a API de documentação.")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("/service.html")
                .version("1.0.0")
                .contact(new Contact("Igor Meira","https://imeira.wordpress.com/", "meira.igor@gmail.com"))
                .build();
    }

    private final ResponseMessage m201 = customMessage1();
    private final ResponseMessage m204put = simpleMessage(204, "Atualização ok");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    private ResponseMessage customMessage1() {
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
        return new ResponseMessageBuilder() .code(201) .message("Recurso criado") .headersWithDescription(map) .build();
    }

    //Security Oauth2 na documentação Swagger

    @Value("${app.client.id}")
    private String clientId;
    @Value("${app.client.secret}")
    private String clientSecret;
    @Value("${host.full.dns.auth.link}")
    private String authLink;

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        authorizationScopeList.add(new AuthorizationScope("read", "Ler tudo"));
        authorizationScopeList.add(new AuthorizationScope("write", "Acessar tudo"));
        List<GrantType> grantTypes = new ArrayList<GrantType>();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authLink+"/oauth/token");
        grantTypes.add(creGrant);
        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "Ler tudo");
        authorizationScopes[1] = new AuthorizationScope("write", "Acessar tudo");
        return Collections.singletonList(new SecurityReference("oauth2schema",
                authorizationScopes));
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/users/**"))
                .build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

}
