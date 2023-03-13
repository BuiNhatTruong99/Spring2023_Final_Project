package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datamining.entity.Account;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Integer> {

	@Query("select u from Account u where u.username = :username ")
	public Account findByTk(@Param("username")String username);

	@Query (value = "select  distinct ar.account_id from Authorities ar where  ar.role_id in (1,2) ",nativeQuery = true)
    List<Account> getAdministors();
}
