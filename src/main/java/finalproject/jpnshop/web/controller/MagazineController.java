package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.service.MagazineService;
import finalproject.jpnshop.web.dto.ReqMagazine;
import finalproject.jpnshop.web.dto.ResMagazine;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MagazineController {

    private final MagazineService magazineService;

    @GetMapping("/magazines")
    public List<Magazine> getMagazines(){
        return magazineService.getMagazines();
    }

    @GetMapping("/magazine/{id}")
    public ResMagazine.Response getMagazine(@PathVariable long id){
        return magazineService.getMagazine(id);
    }

    @PostMapping("/magazine/insert")
    public String insertMagazine(@RequestBody ReqMagazine magazineForm){
        magazineService.insertMagazine(magazineForm);
        return "매거진 등록에 성공했습니다.";
    }

    @PutMapping("/magazine/update")
    public String updateMagazine(@RequestBody ReqMagazine magazineForm){
        magazineService.updateMagazine(magazineForm);
        return "매거진 수정이 성공했습니다.";
    }

    @DeleteMapping("/magazine/delete/{id}")
    public String deleteMagazine(@PathVariable long id){
        magazineService.deleteMagazine(id);
        return "매거진이 삭제되었습니다.";
    }
}
