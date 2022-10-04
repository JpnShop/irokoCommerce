package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MagazineRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqMagazine;
import finalproject.jpnshop.web.dto.ResMagazine;
import finalproject.jpnshop.web.dto.ResMagazine.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MagazineService {

    private final MagazineRepository magazineRepository;
    private final MemberRepository memberRepository;

    public List<Magazine> getMagazines(){
        return magazineRepository.findAll();
    }

    public ResMagazine.Response getMagazine(long id) {
        Magazine magazine = magazineRepository.findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.MAGAZINE_NOT_FOUND));
        return Response.of(magazine);
    }

    @Transactional
    public void insertMagazine(ReqMagazine magazineForm){
        long memberId = SecurityUtil.getCurrentMemberId();
        magazineForm.setMember(memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        ));
        Magazine magazine = magazineForm.toEntity();
        magazineRepository.save(magazine);
    }

    @Transactional
    public void updateMagazine(ReqMagazine magazineForm){
        Magazine magazine = magazineRepository.findById(magazineForm.getId()).orElseThrow(
            () -> new CustomException(ErrorCode.MAGAZINE_NOT_FOUND)
        );
        magazine.setThumnail(magazineForm.getThumnail());
        magazine.setMagazineItems(magazineForm.getMagazineItems());
        magazine.setTitle(magazineForm.getTitle());
        magazine.setContent(magazineForm.getContent());
    }

    @Transactional
    public void deleteMagazine(long id){
        magazineRepository.deleteById(id);
    }
}
