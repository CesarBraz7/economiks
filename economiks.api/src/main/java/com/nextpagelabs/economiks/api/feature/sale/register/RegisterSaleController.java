package com.nextpagelabs.economiks.api.feature.sale.register;

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
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class RegisterSaleController {

    private final RegisterSaleService registerSaleService;

    @PostMapping
    public ResponseEntity<RegisteredSaleResponse> register(@Valid @RequestBody RegisterSaleRequest request) {

        RegisteredSaleResponse response = registerSaleService.execute(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}

