package com.datamining.service;

import com.datamining.entity.Account;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Integer id);
    
    Account findByTk(String username);

    Account update(Account account);

   void loginFromOAuth2(OAuth2AuthenticationToken oauth2) ;


    List<Account> getAdministors();
}
