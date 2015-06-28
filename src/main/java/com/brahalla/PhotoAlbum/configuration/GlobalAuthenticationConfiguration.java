package com.brahalla.PhotoAlbum.configuration;

import com.brahalla.PhotoAlbum.dao.AccountRepository;
import com.brahalla.PhotoAlbum.domain.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@ComponentScan("com.brahalla.PhotoAlbum.dao")
public class GlobalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  AccountRepository accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
          return new User(
            account.getUsername(),
            account.getPassword(),
            true, true, true, true,
            AuthorityUtils.createAuthorityList("USER")
          );
        } else {
          throw new UsernameNotFoundException("could not find the user '" + username + "'");
        }
      }

    };
  }

}
