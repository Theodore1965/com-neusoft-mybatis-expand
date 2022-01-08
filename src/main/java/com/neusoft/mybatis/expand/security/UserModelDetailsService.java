package com.neusoft.mybatis.expand.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

//   private UserRepository userRepository;
//
//   public UserModelDetailsService(UserRepository userRepository) {
//      this.userRepository = userRepository;
//   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String login) {
      log.debug("Authenticating user '{}'", login);

//      if (new EmailValidator().isValid(login, null)) {
//         return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
//            .map(user -> createSpringSecurityUser(login, user))
//            .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
//      }
//
//      String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
//      return userRepository.findOneWithAuthoritiesByUsername(lowercaseLogin)
//         .map(user -> createSpringSecurityUser(lowercaseLogin, user))
//         .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

      return createSpringSecurityUser();
   }

   private org.springframework.security.core.userdetails.User createSpringSecurityUser(/*String lowercaseLogin, User user*/) {
//      if (!user.isActivated()) {
//         throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//      }
//      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//         .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//         .collect(Collectors.toList());
//      return new org.springframework.security.core.userdetails.User(user.getUsername(),
//         user.getPassword(),
//         grantedAuthorities);
      return new org.springframework.security.core.userdetails.User("test","$2a$10$/PHO1UN9TpsVns.H66hXvOD6B1OVEVpnuR9fRmYmydMtD3/ED6Q46", Collections.emptyList());
   }
}
