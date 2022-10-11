package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.TermsService;
import finalproject.jpnshop.web.dto.ResTerms.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @GetMapping("/serviceTerms")
    public ResponseEntity<String> getServiceContent() {
        return ResponseEntity.ok(termsService.getServiceContent());
    }

    @GetMapping("/personalTerms")
    public ResponseEntity<String> getPersonalContent() {
        return ResponseEntity.ok(termsService.getPersonalContent());
    }
}
