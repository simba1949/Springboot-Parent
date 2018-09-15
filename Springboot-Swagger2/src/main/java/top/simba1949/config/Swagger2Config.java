package top.simba1949.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author simba1949@outlook.com
 * @date 2018/7/11 9:19
 * @EnableSwagger2 用于启动swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * swagger2 启动后，通过createRestApi创建一个docket的bean
     * apiInfo 创建api的基本信息
     * select() 函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("top.simba1949.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Springboot 集成 Swagger2")
                .description("项目API如下")
                .termsOfServiceUrl("http://localhost:8888")
                .version("1.0")
                .build();
    }
}
