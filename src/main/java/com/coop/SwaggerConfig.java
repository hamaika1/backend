package com.coop;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.coop"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {

		return new ApiInfo("Backend", // Nombre de la aplicacion
				"Este es mi backend", // Descripcion de la api
				"1.0.0", // Version de la API
				"En algun lugar", // URL contrato de servico
				new Contact("Mateo Plante", "http://github.com/hamaika1", "Mateo_Plante_xd@hotmail.com"),
				"License of API", // Licensia de la API
				"API License URL", // URL de la licensia
				Collections.emptyList());
	}
}
