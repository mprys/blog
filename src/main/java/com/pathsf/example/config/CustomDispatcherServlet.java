package com.pathsf.example.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomDispatcherServlet extends DispatcherServlet {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public CustomDispatcherServlet(WebApplicationContext servletAppContext) {
		super(servletAppContext);
	}
	
	
	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(request.toString());
		super.doDispatch(request, response);
	}
}
