package com.fzhunio.school.config;

import com.fzhunio.school.user.UserRepository;
import com.fzhunio.school.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Collection<? extends GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }
}
