package com.myapp.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.entity.SysTxn;

@Repository
public interface SessionTxnRepository extends CrudRepository<SysTxn, Integer> {

	@Transactional
	@Modifying
	@Query("update SysTxn set endTime = :endTime, errorInfo = :errorInfo where txnId = :txnId")
	int updateEndTimeAndErrorDetails(@Param("endTime") Date endTime, @Param("errorInfo") String errorInfo,
			@Param("txnId") Integer txnId);
}