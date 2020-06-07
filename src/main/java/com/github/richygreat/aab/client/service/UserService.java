package com.github.richygreat.aab.client.service;

import com.github.richygreat.aab.client.model.UserDto;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final Environment environment;
    private final RestTemplate restTemplate;

    public UserDto createUser(UserDto userDto) {
        String usersApiUrl = environment.getRequiredProperty("users.api.url");
        log.info("createUser: usersApiUrl: {} userDto: {}", usersApiUrl, userDto);
        ResponseEntity<UserDto> userResponseEntity = restTemplate.postForEntity(usersApiUrl, userDto, UserDto.class);
        if (userResponseEntity.getStatusCode() == HttpStatus.OK && userResponseEntity.getBody() != null) {
            return userResponseEntity.getBody();
        }
        throw new ResponseStatusException(userResponseEntity.getStatusCode(), "User creation failed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String usersApiUrl = environment.getRequiredProperty("users.api.url") + "/" + username;
        log.info("loadUserByUsername: usersApiUrl: {}", usersApiUrl);
        ResponseEntity<UserDto> userResponseEntity = restTemplate.getForEntity(usersApiUrl, UserDto.class);
        if (userResponseEntity.getStatusCode() == HttpStatus.OK && userResponseEntity.getBody() != null) {
            UserDto userDto = userResponseEntity.getBody();
            return new User(userDto.getUsername(), userDto.getPassword(),
                    userDto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        throw new UsernameNotFoundException(username);
    }
}
