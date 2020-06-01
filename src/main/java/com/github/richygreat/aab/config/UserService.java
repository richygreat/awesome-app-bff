package com.github.richygreat.aab.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final Environment environment;
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String usersApiUrl = environment.getProperty("user.api.url") + "/" + username;
        log.info("loadUserByUsername: usersApiUrl: {}", usersApiUrl);
        ResponseEntity<UserModel> userResponseEntity = restTemplate.getForEntity(usersApiUrl, UserModel.class);
        if (userResponseEntity.getStatusCode() == HttpStatus.OK && userResponseEntity.getBody() != null) {
            UserModel userModel = userResponseEntity.getBody();
            return new User(userModel.getUsername(), userModel.getPassword(),
                    userModel.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        throw new UsernameNotFoundException(username);
    }

    @Data
    private static class UserModel {
        private String id;
        private String username;
        private String password;
        private List<String> roles;
    }
}
