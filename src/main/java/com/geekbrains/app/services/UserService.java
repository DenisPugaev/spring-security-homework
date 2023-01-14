package com.geekbrains.app.services;

import com.geekbrains.app.entities.Authorities;
import com.geekbrains.app.repositories.UserRepository;
import com.geekbrains.app.entities.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Collection<Authorities> authorities) {
        return authorities.stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }
}