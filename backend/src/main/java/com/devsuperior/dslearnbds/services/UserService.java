package com.devsuperior.dslearnbds.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired private UserRepository userRepository;
	
	private static Logger logger =  LoggerFactory.getLogger(UserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			logger.error("Usuário não encontrado " + username);
			throw new UsernameNotFoundException("Email não encontrado ");
		}
		logger.info("Usuário encontrado " + username);
		return user;
	}

}