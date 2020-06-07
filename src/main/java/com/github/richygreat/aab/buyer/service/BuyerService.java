package com.github.richygreat.aab.buyer.service;

import com.github.richygreat.aab.buyer.model.BuyerCreationDto;
import com.github.richygreat.aab.client.model.UserDto;
import com.github.richygreat.aab.client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyerService {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public BuyerCreationDto createBuyer(BuyerCreationDto buyerCreationDto) {
        UserDto userDto = new UserDto();
        userDto.setUsername(buyerCreationDto.getEmailAddress());
        userDto.setPassword(passwordEncoder.encode(buyerCreationDto.getPassword()));
        userDto.setRoles(Collections.singletonList("BUYER"));
        userDto = userService.createUser(userDto);
        log.info("createBuyer: Saved with username: {}", userDto.getUsername());
        return buyerCreationDto;
    }
}
