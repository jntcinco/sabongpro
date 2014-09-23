package com.tekusource.sabongpro.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class SabongproLogoutFilter extends LogoutFilter {
	
	public SabongproLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers){
		super(logoutSuccessUrl, handlers);
	}
	
	@Override
    protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("==logging out==");
		
		// Normal logout processing (i.e. detect logout URL)
        if (super.requiresLogout(request, response))
            return true;
        
        HttpSession session = request.getSession();
        session.invalidate();
        //session.removeAttribute("user");
        
        return false;
    }
}
