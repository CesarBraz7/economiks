package com.nextpagelabs.economiks.api.feature.customer.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class RegisterCustomerController {

    private final RegisterCustomerService registerCustomerService;

    @PostMapping
    public ResponseEntity<RegisteredCustomerResponse> register(@Valid @RequestBody RegisterCustomerRequest registerCustomerRequest){

        RegisteredCustomerResponse response = registerCustomerService.register(registerCustomerRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
