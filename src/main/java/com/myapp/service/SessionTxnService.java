package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.MyServiceCode;
import com.myapp.entity.SysTxn;
import com.myapp.repository.SessionTxnRepository;
import com.myapp.service.so.TransactionSo;

@Component
public class SessionTxnService {

	@Autowired
	private SessionTxnRepository sessionTxnRepository;

	@MyServiceCode(serviceCode = 10100)
	public List<TransactionSo> getData(TransactionSo transactionSo) throws Exception {

		List<TransactionSo> target = new ArrayList<>();

		Iterable<SysTxn> empIterable = sessionTxnRepository.findAll();
		for (SysTxn employeeEntity : empIterable) {

			TransactionSo so = new TransactionSo();
			BeanUtils.copyProperties(so, employeeEntity);

			target.add(so);
		}

		return target;
	}
}
