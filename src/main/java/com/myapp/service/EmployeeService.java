package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.MyServiceCode;
import com.myapp.entity.EmployeeEntity;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.so.EmployeeSo;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@MyServiceCode(serviceCode = 10001)
	public List<EmployeeSo> getData(EmployeeSo employeeSo) throws Exception {

		List<EmployeeSo> target = new ArrayList<>();

		Iterable<EmployeeEntity> empIterable = employeeRepository.findAll();
		for (EmployeeEntity employeeEntity : empIterable) {

			EmployeeSo so = new EmployeeSo();
			BeanUtils.copyProperties(so, employeeEntity);

			target.add(so);
		}

		return target;
	}

	@MyServiceCode(serviceCode = 10002)
	public EmployeeSo save(EmployeeSo employeeSo) throws Exception {

		EmployeeEntity entity = new EmployeeEntity();

		BeanUtils.copyProperties(entity, employeeSo);

		employeeRepository.save(entity);

		return employeeSo;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@MyServiceCode(serviceCode = 10003)
	public EmployeeSo delete(EmployeeSo employeeSo) throws Exception {

		// EmployeeEntity entity = new EmployeeEntity();

		// BeanUtils.copyProperties(entity, employeeSo);

		employeeRepository.delete(employeeSo.getEmpId());

		return employeeSo;
	}

}
