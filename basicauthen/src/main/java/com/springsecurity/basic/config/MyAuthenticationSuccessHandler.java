package com.springsecurity.basic.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.basic.Util.UserPrincipal;

@Component
public class MyAuthenticationSuccessHandler  implements AuthenticationSuccessHandler   {
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	            Authentication authentication) throws IOException, ServletException {
	    	ObjectMapper mapper = new ObjectMapper();
	    	String jsonInString=mapper.writeValueAsString(((UserPrincipal)authentication.getPrincipal()).getUser());			
	    	response.setStatus(200);
	    	response.getWriter().println("{\"successlogin\":"+(new Boolean(true).booleanValue())+",\"data\":"
	    			+ jsonInString+
	    			"}");
	    }


	
	}