package com.tekusource.sabongpro.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.StringUtils;

public class SabongproLogoutFilter extends LogoutFilter {

	private List<LogoutHandler> handlers;

    private LogoutSuccessHandler logoutSuccessHandler;
	
	public SabongproLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler[] handlers){
		super( logoutSuccessHandler, handlers );
        this.handlers = Arrays.asList( handlers );
        this.logoutSuccessHandler = logoutSuccessHandler;
	}
	
	public SabongproLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers){
		super(logoutSuccessUrl, handlers);
        this.handlers = Arrays.asList( handlers );
        SimpleUrlLogoutSuccessHandler urlLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        if (StringUtils.hasText(logoutSuccessUrl)){
            urlLogoutSuccessHandler.setDefaultTargetUrl( logoutSuccessUrl );
        }
        this.logoutSuccessHandler = urlLogoutSuccessHandler;
	}
	
	public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain )
	        throws IOException, ServletException{
	
		System.out.println("==logging out==");
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession( false );

        if(requiresLogout( request, response )){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            System.out.println("==logging out success==");
            
            if(auth != null)
            	session.removeAttribute("user");
            
            
            for(LogoutHandler handler : handlers){
                handler.logout( request, response, auth );
            }

            logoutSuccessHandler.onLogoutSuccess( request, response, auth );

            return;
        }
	}
}
