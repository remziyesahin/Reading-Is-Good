package com.getircase.readingisgood.application.domain.security;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.application.domain.common.UserRole;
import com.getircase.readingisgood.application.ports.outgoing.CustomerRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepositoryUseCase customerRepositoryUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) {
       Customer user = customerRepositoryUseCase.findByUsername(username)
               .orElseThrow(() ->
                       new UsernameNotFoundException("User not found with username:" + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getUserRole()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<UserRole> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

}
