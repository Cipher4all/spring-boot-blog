package com.webapp.blogDemo.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.blogDemo.models.Account;
import com.webapp.blogDemo.models.Authority;
import com.webapp.blogDemo.repositories.AccountRepository;
import com.webapp.blogDemo.repositories.AuthorityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthorityRepository authorityRepository;
	
	public Account save(Account account) {
		if(account.getId() ==  null) {
			if(account.getAuthority().isEmpty()) {
				Set<Authority> authorities =  new HashSet<Authority>();
				authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
				account.setAuthority(authorities);
			}
			account.setCreatedAt(LocalDateTime.now());
		}
		account.setUpdatedAt(LocalDateTime.now());
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return accountRepository.save(account);
	}
	
	public Optional<Account> findOneByEmail(String email){
		return accountRepository.findOneByEmailIgnoreCase(email);
	}

}
