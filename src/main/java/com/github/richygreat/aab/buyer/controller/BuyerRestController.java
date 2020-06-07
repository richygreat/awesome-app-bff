package com.github.richygreat.aab.buyer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.richygreat.aab.buyer.model.BuyerCreationDto;
import com.github.richygreat.aab.buyer.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/buyers")
@RequiredArgsConstructor
public class BuyerRestController {
    private final BuyerService buyerService;
    private final ObjectMapper objectMapper;

    @PostMapping
    @SneakyThrows(JsonProcessingException.class)
    public ResponseEntity<BuyerCreationDto> createBuyer(@RequestBody @Valid BuyerCreationDto buyerCreationDto) {
        log.info("createBuyer: Entering buyerCreationDto: {}", objectMapper.writeValueAsString(buyerCreationDto)
                .replace(buyerCreationDto.getPassword(), DigestUtils.sha256Hex(buyerCreationDto.getPassword())));
        return ResponseEntity.ok(buyerService.createBuyer(buyerCreationDto));
    }
}
