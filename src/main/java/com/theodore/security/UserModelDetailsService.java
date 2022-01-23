package com.theodore.security;

import com.theodore.dao.SystemRoleMapper;
import com.theodore.dao.SystemStaffInfoMapper;
import com.theodore.entity.SystemStaffInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

   @Resource
   private SystemStaffInfoMapper SystemStaffInfoMapper;

   @Resource
   private SystemRoleMapper systemRoleMapper;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String login) {
      log.debug("Authenticating user '{}'", login);

      String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
      return Optional.ofNullable(SystemStaffInfoMapper.selectByStaffIdAndEnabled(lowercaseLogin, true))
              .map(user -> createSpringSecurityUser(lowercaseLogin, user))
              .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
   }

   private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, SystemStaffInfo user) {
      if (!user.getEnabled()) {
         throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
      }
      List<GrantedAuthority> grantedAuthorities = systemRoleMapper.selectByStaffId(user.getStaffId()).stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getName()))
              .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(user.getStaffId(),
              user.getStaffPassword(),
              grantedAuthorities);
   }
}
