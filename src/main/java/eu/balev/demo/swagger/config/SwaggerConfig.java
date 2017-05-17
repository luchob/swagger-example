package eu.balev.demo.swagger.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//students
	@Bean
	public Docket studentsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(studentAPIInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(studentsPaths())
				.build().
				securitySchemes(Arrays.asList(basicAuth()));
	}

	private Predicate<String> studentsPaths() {
		return Predicates.or(
				regex("/api/students.*"),
				regex("/api/courses.*"));
	}

	private ApiInfo studentAPIInfo() {
		return new ApiInfoBuilder().title("Students API")
				.description("The super heroic student API ")
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}

	private SecurityScheme basicAuth() {
		return new BasicAuth("Basic Authentication");
	}
}
