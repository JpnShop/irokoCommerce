package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MagazineRepository;
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

    @Transactional
    public List<Magazine> getMagazines(){
        return magazineRepository.findAll();
    }

    public Response getMagazine(long id) {
        Magazine magazine = magazineRepository.findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.MAGAZINE_NOT_FOUND));

    }
}
