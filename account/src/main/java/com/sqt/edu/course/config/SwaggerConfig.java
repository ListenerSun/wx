package com.sqt.edu.course.config;

import com.sqt.edu.core.constant.AuthConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 配置
 *
 * @author sqt
 * @date 19-7-1
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder userIdPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        //添加头信息
        tokenPar.name(AuthConstants.HTTP_HEADER_TOKEN).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false);
        userIdPar.name(AuthConstants.HTTP_HEADER_USERID).description("userId").modelRef(new ModelRef("string")).parameterType("header").required(false);
        pars.add(tokenPar.build());
        pars.add(userIdPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sqt.edu"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户服务")
                .description("用户服务接口文档")
                .termsOfServiceUrl("https://www.baidu.com/")
                .version("1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("**/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("**/swagger-ui.html/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }


}
