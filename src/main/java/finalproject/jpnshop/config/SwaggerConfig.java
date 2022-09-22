package finalproject.jpnshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("일본 쇼핑몰")
            .description("상품 판매")
            .contact(new Contact("[2조]", "https://www.notion.so/BE-FE2-UXUI3-_2-3b8c536443b04fffab9cdbea49202e6f", "wndgn456@gmail.com"))
            .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Iroko Commerce")
            .apiInfo(this.apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("finalproject.jpnshop.web.controller"))
            .paths(PathSelectors.any())
            .build();
    }

}
