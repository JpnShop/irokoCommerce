package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.PaymentService;
import finalproject.jpnshop.web.dto.ReqTotalOrder;
import finalproject.jpnshop.web.dto.ResPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResPayment.Response> createPayment(ReqTotalOrder reqTotalOrder) {
        paymentService.createPayment(reqTotalOrder);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<ResPayment.Response> cancelPayment(@PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity.ok().build();
    }

}
