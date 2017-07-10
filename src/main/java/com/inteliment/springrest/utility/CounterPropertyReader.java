package com.inteliment.springrest.utility;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.inteliment.springrest.model.CounterProperty;
 
/**
 * This is property Reader to ready property file.
 * @author Dhaval Thakkar
 * @see CounterProperty
 * @see CounterPropertyConfig
 *
 */
public class CounterPropertyReader {

	public static String CLASSNAME = "CounterPropertyReader";
    private static final Logger LOG = LoggerFactory.getLogger(CounterPropertyReader.class);
	
	/**
	 * It will register CounterPropertyConfig in AnnotationConfigApplicationContext to fetch data from Property File
	 * @return CounterProperty
	 */
    public static CounterProperty getCounterProperty() {
    
	    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(CounterPropertyConfig.class);
	    ctx.refresh();
	
	    CounterProperty counterProperty = ctx.getBean(CounterProperty.class);
	    LOG.info("counterProperty is "+ counterProperty);
	    return counterProperty;
    }
}