//package finalproject.jpnshop.biz.service;
//
//import finalproject.jpnshop.biz.repository.TermsRepository;
//import finalproject.jpnshop.web.dto.ResTerms.Response;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class TermsService {
//
//    private final TermsRepository termsRepository;
//
//    @Transactional(readOnly = true)
//    public String getServiceContent() {
//        Response terms = termsRepository.findById(1L)
//            .map(Response::of)
//            .orElseThrow(() -> new RuntimeException("이용약관이 존재하지 않습니다."));
//
//        return terms.getServiceContent();
//    }
//
//    @Transactional(readOnly = true)
//    public String getPersonalContent() {
//        Response terms = termsRepository.findById(1L)
//            .map(Response::of)
//            .orElseThrow(() -> new RuntimeException("개인 정보 처리 방침이 존재하지 앟습니다."));
//
//        return terms.getPersonalContent();
//    }
//}
