package com.tekusource.sabongpro.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tekusource.sabongpro.service.impl.SabongproUserDetailsService;

public class SabongproUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private SabongproUserDetailsService sabongproUserDetailService;

	private AuthenticationSuccessHandler successHandler;
	private AuthenticationFailureHandler failureHandler;

	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
					throws IOException, ServletException {

		System.out.println("==successful login==");

		SecurityContextHolder.getContext().setAuthentication( authResult );

		User user = sabongproUserDetailService.getCurrentUser();
		request.getSession().setAttribute("user", user);

		// Fire event
		if ( this.eventPublisher != null )
		{
			eventPublisher.publishEvent( new InteractiveAuthenticationSuccessEvent( authResult, this.getClass() ) );
		}

		successHandler.onAuthenticationSuccess( request, response, authResult );
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
					throws IOException, ServletException {

		System.out.println("==failed login==");

		SecurityContextHolder.clearContext();

		HttpSession session = request.getSession(false);

		if(session != null || getAllowSessionCreation()){
			request.getSession().setAttribute( WebAttributes.AUTHENTICATION_EXCEPTION, failed );
		}

		request.getSession().removeAttribute("user");
		sabongproUserDetailService.setCurrentUser(null);
		
		failureHandler.onAuthenticationFailure( request, response, failed );
	}
	 
	 public void setAuthenticationSuccessHandler( AuthenticationSuccessHandler successHandler )
	 {
		 super.setAuthenticationSuccessHandler( successHandler );
		 this.successHandler = successHandler;
	 }

	 public void setAuthenticationFailureHandler( AuthenticationFailureHandler failureHandler )
	 {
		 super.setAuthenticationFailureHandler( failureHandler );
		 this.failureHandler = failureHandler;
	 }

	public AuthenticationSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}

	public AuthenticationFailureHandler getFailureHandler() {
		return failureHandler;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}
}
