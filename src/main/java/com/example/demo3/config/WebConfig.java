//package com.example.demo3.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan("com.example.demo3")
//public class WebConfig implements WebMvcConfigurer {
//
//    private final ApplicationContext applicationContext;
//
//    public WebConfig(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//    // JSP - config!
////    @Bean
////    public InternalResourceViewResolver setupViewResolver() {
////        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
////        resolver.setPrefix("/WEB-INF/pages/");
////        resolver.setSuffix(".jsp");
////        resolver.setViewClass(JstlView.class);
////
////        return resolver;
////    }
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setApplicationContext(applicationContext);
//        templateResolver.setPrefix("/WEB-INF/pages/");
//        templateResolver.setSuffix(".html");
//        return templateResolver;
//    }
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setEnableSpringELCompiler(true);
//        return templateEngine;
//    }
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        registry.viewResolver(resolver);
//    }
//}