package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Operator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private OperatorsService operatorService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Operator operator = operatorService.getOperatorByEmail(email);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));

		return new org.springframework.security.core.userdetails.User(operator.getEmail(), operator.getPassword(),
				grantedAuthorities);

	}
}
