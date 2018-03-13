package com.myapp.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.security.core.userdetails.User;

import com.myapp.entity.RoleEntity;
import com.myapp.entity.UserEntity;
import com.myapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("*** UserDetailsServiceImpl *** loadUserByUsername ****");

		if (!StringUtils.isEmpty(username)) {

			UserEntity userEntity = userRepository.findByUsername(username);

			if (userEntity != null) {

				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

				for (RoleEntity roleEntity : userEntity.getRoleEntityList()) {
					grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
				}

				return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
			}
			
			throw new UsernameNotFoundException("Username not found");
		}
		
		throw new UsernameNotFoundException("Username is mandatory");
	}
}
