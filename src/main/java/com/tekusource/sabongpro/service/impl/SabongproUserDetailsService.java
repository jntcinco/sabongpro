package com.tekusource.sabongpro.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tekusource.sabongpro.dao.UserDao;
import com.tekusource.sabongpro.model.UserRole;

@Service("sabongproUserDetailService")
public class SabongproUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	private ThreadLocal<User> currentUser = new ThreadLocal<User>();

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		com.tekusource.sabongpro.model.User user = userDao.getBy("userName", username);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		User oUser = buildUserForAuthentication(user, authorities);
		currentUser.set(oUser);
		
		return oUser;
	}

	private User buildUserForAuthentication(com.tekusource.sabongpro.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	public User getCurrentUser() {
		return currentUser.get();
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser.set(currentUser);
	}

}
