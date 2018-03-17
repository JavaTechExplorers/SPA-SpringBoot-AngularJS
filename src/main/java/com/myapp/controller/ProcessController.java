package com.myapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.service.EmployeeService;
import com.myapp.service.security.UserServiceInterface;
import com.myapp.service.so.EmployeeSo;
import com.myapp.service.so.UserSo;

@RestController
public class ProcessController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserServiceInterface userServiceInterface;

	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("*** ProcessController *** user ****");
		return user;
	}

	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	public ResponseEntity<List<EmployeeSo>> getAllData(
			@RequestBody EmployeeSo employeeSo) throws Exception {

		System.out.println("*** ProcessController *** getAllData ****");
		List<EmployeeSo> empList = employeeService.getData(employeeSo);
		return new ResponseEntity<List<EmployeeSo>>(empList, HttpStatus.OK);

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<EmployeeSo> saveData(
			@RequestBody EmployeeSo employeeSo) throws Exception {

		System.out.println("*** ProcessController *** saveData ****");
		employeeService.save(employeeSo);
		return new ResponseEntity<EmployeeSo>(employeeSo, HttpStatus.OK);
	}

	@RequestMapping(value = "/createAccount1", method = RequestMethod.POST)
	public ResponseEntity<UserSo> createAccount(@RequestBody UserSo userSo)
			throws Exception {

		System.out.println("*** ProcessController *** createAccount ****");
		userServiceInterface.save(userSo);
		return new ResponseEntity<UserSo>(userSo, HttpStatus.OK);
	}

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<List<EmployeeSo>> deleteData(@RequestBody EmployeeSo employeeSo) throws Exception {
    		employeeService.delete(employeeSo);
    	List<EmployeeSo> empList = employeeService.getData(employeeSo);
	return new ResponseEntity<List<EmployeeSo>>(empList, HttpStatus.OK);
    }
}