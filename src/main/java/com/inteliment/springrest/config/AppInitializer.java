package com.inteliment.springrest.config;

import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.inteliment.springrest.config.AppFilter;

/**
 * <h1>App Initializer Class</h1>
 * Class override all the methods to configure parameters of web.xml and configure Dispatcher Servlet 
 * @author  Dhaval Thakkar
 * @version 1.0
 * @see AppConfig
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/**
	 * It will configure Root class to enable Web MVC
	 * 
	 */
	@Override
	protected Class[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		return null;
	}

	/**
	 * Method is used for Servlet Mapping
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	/**
	 * Method is used to configure Servlet Filter
	 */
	@Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new AppFilter()};
    	return singleton;
    }

}