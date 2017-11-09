package com.springsecurity.basic.config;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {


    public Http401UnauthorizedEntryPoint() {
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {

        response.sendError(401, "Access Denied");
    }

	
	
}
