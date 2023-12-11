package com.webapp.blogDemo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.webapp.blogDemo.models.Account;

import lombok.RequiredArgsConstructor;

@Component("userDetailsService")
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{
	
	private final AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Account> optionalAccount = accountService.findOneByEmail(email);
		if(!optionalAccount.isPresent()) {
			throw new UsernameNotFoundException("Account not found");
		}
		
		Account account = optionalAccount.get();
		List<GrantedAuthority> grantedAuthorities = account
				.getAuthority()
				.stream()
				.map(authority ->  new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),  grantedAuthorities);
	}
	
	

}
