package com.inteliment.springrest.security;
 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Method is used as Authentication Entry Point and call the method if credential not matched
 * @author Dhaval Thakkar
 *
 */
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
 
	public static String CLASSNAME = "CustomBasicAuthenticationEntryPoint";
    private final Logger LOG = LoggerFactory.getLogger(CustomBasicAuthenticationEntryPoint.class);

	
	/**
	 * Method will called if Authentication not matched. 
	 * If username and password is incorrect then it will display below Error
	 * Authentication failed, send error response.
	 */
    @Override
    public void commence(final HttpServletRequest request, 
            final HttpServletResponse response, 
            final AuthenticationException authException) throws IOException, ServletException {

    	LOG.info(CLASSNAME + " commence Calling...");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
         
        PrintWriter writer = response.getWriter();
//        LOG.info("commence Calling... Before Write.println");
        writer.println("HTTP Status 401 : " + authException.getMessage());
    }
     
    @Override
    public void afterPropertiesSet() throws Exception {
    	LOG.info(CLASSNAME + " afterPropertiesSet Calling...");
        setRealmName("WORD_COUNT_REALM");
        super.afterPropertiesSet();
    }
}