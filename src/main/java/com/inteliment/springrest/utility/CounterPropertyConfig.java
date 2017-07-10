package com.inteliment.springrest.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.inteliment.springrest.model.CounterProperty;

/**
 * This Class is used to configure application property in CounterProperty Pojo
 * @author Dhaval Thakkar
 * @see CounterProperty
 * @see CounterPropertyReader
 */
@Configuration
@PropertySource("classpath:counter.properties")
public class CounterPropertyConfig {
	
	@Autowired
    Environment env;
	
	/**
	 * It will ready property value based on given key from Environment and set in CounterProperty Pojo
	 * @return CounterProperty
	 */
	@Bean
    public CounterProperty counterProperty() {
		CounterProperty counterProperty= new CounterProperty();
		try{
			counterProperty.setParagraph(env.getProperty("prop.paragraph"));
			counterProperty.setuserName(env.getProperty("prop.username"));
			counterProperty.setPassword(env.getProperty("prop.password"));
		}catch(Exception e){
			e.printStackTrace();
		}
        return counterProperty;
    }
}