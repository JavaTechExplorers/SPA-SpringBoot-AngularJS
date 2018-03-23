package com.myapp.service.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.myapp.controller.UiController;
import com.myapp.entity.SysRole;
import com.myapp.entity.SysUser;
import com.myapp.entity.SysUserRoleMap;
import com.myapp.repository.RoleRepository;
import com.myapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOGGER.info("********* UserDetailsServiceImpl:loadUserByUsername(): Username = " + username);

		if (!StringUtils.isEmpty(username)) {

			SysUser userEntity = userRepository.findByUsername(username);

			if (userEntity != null) {

				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

				List<SysUserRoleMap> userRoleMap = userEntity.getSysUserRoleMaps();
				if (userRoleMap != null) {

					for (SysUserRoleMap sysUserRoleMap : userRoleMap) {

						int roleId = sysUserRoleMap.getRoleId();
						SysRole role = roleRepository.findByRoleId(roleId);

						grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
					}
				}

				LOGGER.error("User details found in the database for the user (" + username + ")");

				return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
			}
			LOGGER.error("Username = " + username + " not found in database");

			throw new UsernameNotFoundException("Username not found");
		}

		LOGGER.error("Username is mandatory");
		throw new UsernameNotFoundException("Username is mandatory");
	}
}
