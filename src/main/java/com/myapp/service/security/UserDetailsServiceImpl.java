package com.myapp.service.security;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.myapp.entity.SysRole;
import com.myapp.entity.SysUser;
import com.myapp.entity.SysUserRoleMap;
import com.myapp.repository.RoleRepository;
import com.myapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		System.out.println("*** UserDetailsServiceImpl *** loadUserByUsername ****");

		if (!StringUtils.isEmpty(userName)) {

			SysUser userEntity = userRepository.findByUserName(userName);

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

				return new User(userEntity.getUserName(), userEntity.getUserPassword(), grantedAuthorities);
			}

			throw new UsernameNotFoundException("Username not found");
		}

		throw new UsernameNotFoundException("Username is mandatory");
	}
}
