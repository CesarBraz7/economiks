package com.nextpagelabs.economiks.api.feature.sale.receivables.list;

import com.nextpagelabs.economiks.api.feature.sale.receivables.ReceivableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales/receivables")
@RequiredArgsConstructor
public class ListReceivablesController {

    private final ListReceivablesService listReceivablesService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<List<ReceivableResponse>> listReceivables() {
        List<ReceivableResponse> receivables = listReceivablesService.list();
        return ResponseEntity.ok(receivables);
    }
}

