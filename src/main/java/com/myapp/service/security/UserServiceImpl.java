package com.myapp.service.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

import com.myapp.entity.SysUser;
import com.myapp.repository.UserRepository;
import com.myapp.service.so.UserSo;
import com.myapp.service.validator.UserValidator;

@Component
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserSo findByUsername(String userName) {

		SysUser userEntity = userRepository.findByUserName(userName);
		if (userEntity != null) {

			UserSo userSo = new UserSo();
			userSo.setUserName(userName);
			return userSo;
		}
		return null;
	}

	@Override
	public UserSo save(UserSo userSo){

		// TODO: what to do validations ?

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(userSo, "userSo");
		ValidationUtils.invokeValidator(userValidator, userSo, errors);
		if (errors.getErrorCount() > 0) {
			System.out.println("Validations error occurred. Account is not created.");
			return null;
		}

		if (userSo != null && !StringUtils.isEmpty(userSo.getUserName())
				&& !StringUtils.isEmpty(userSo.getUserPassword())) {

			SysUser userEntity = new SysUser();
			userEntity.setUserName(userSo.getUserName());
			userEntity.setUserPassword(bCryptPasswordEncoder.encode(userSo.getUserPassword()));
			userEntity.setFirstName(userSo.getFirstName());
			userEntity.setLastName(userSo.getLastName());
			userEntity.setMailId(userSo.getMailId());
			userEntity.setPhoneNum(userSo.getPhoneNum());
			userEntity.setCreatedBy("GUEST");
			userEntity.setCreatedDate(new Date());
						
			// List<SysUserRoleMap> sysUserRoleMaps = new ArrayList<>();
			// SysUserRoleMap map = new SysUserRoleMap();
			// map.setRoleId();
			// SysRole roleEntity = new SysRole();
			// roleEntity.setRoleName("ADMIN");
			// roleEntity.setUserEntity(userEntity);
			//
			// List<SysRole> roleEntityList = new ArrayList<>();
			// roleEntityList.add(roleEntity);
			//
			// userEntity.setRoleEntityList(roleEntityList);

			userEntity = userRepository.save(userEntity);

		}
		return userSo;
	}

}
