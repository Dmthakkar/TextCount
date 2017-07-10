package com.inteliment.springrest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <h1>App Config Class</h1>
 * This class is used to enable web MVC in Spring. No need of web.xml after creating this class
 * @author  Dhaval Thakkar
 * @version 1.0
 * @see AppInitializer
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.inteliment.springrest")
public class AppConfig {

}