package com.myapp.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.service.EmployeeService;
import com.myapp.service.SessionTxnService;
import com.myapp.service.security.UserServiceInterface;
import com.myapp.service.so.EmployeeSo;
import com.myapp.service.so.TransactionSo;
import com.myapp.service.so.UserSo;
import com.myapp.service.validator.MyValidationException;

@RestController
public class ProcessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserServiceInterface userServiceInterface;

	@Autowired
	private SessionTxnService sessionTxnService;

	@RequestMapping("/user")
	public Principal user(Principal user) {
		LOGGER.info("*** ProcessController *** user ****");
		return user;
	}

	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	public ResponseEntity<List<EmployeeSo>> getAllData(@RequestBody EmployeeSo employeeSo) throws Exception {

		LOGGER.info("*** ProcessController *** getAllData ****");
		List<EmployeeSo> empList = employeeService.getData(employeeSo);
		return new ResponseEntity<List<EmployeeSo>>(empList, HttpStatus.OK);

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<EmployeeSo> saveData(@RequestBody EmployeeSo employeeSo) throws Exception {

		LOGGER.info("*** ProcessController *** saveData ****");
		employeeService.save(employeeSo);
		return new ResponseEntity<EmployeeSo>(employeeSo, HttpStatus.OK);
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ResponseEntity<UserSo> createAccount(@RequestBody UserSo userSo) throws MyValidationException {

		LOGGER.info("*** ProcessController *** createAccount ****");
		userServiceInterface.save(userSo);

		return new ResponseEntity<UserSo>(userSo, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<List<EmployeeSo>> deleteData(@RequestBody EmployeeSo employeeSo) throws Exception {
		employeeService.delete(employeeSo);
		List<EmployeeSo> empList = employeeService.getData(employeeSo);
		return new ResponseEntity<List<EmployeeSo>>(empList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getTxnData", method = RequestMethod.POST)
	public ResponseEntity<List<TransactionSo>> getSessionTxnData(@RequestBody TransactionSo transactionSo)
			throws Exception {

		LOGGER.info("*** ProcessController *** getAllData ****");
		List<TransactionSo> txnList = sessionTxnService.getData(transactionSo);
		return new ResponseEntity<List<TransactionSo>>(txnList, HttpStatus.OK);

	}

}