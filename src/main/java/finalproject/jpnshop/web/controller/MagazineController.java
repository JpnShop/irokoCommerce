package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.service.MagazineService;
import finalproject.jpnshop.web.dto.ResCartItem.Response;
import finalproject.jpnshop.web.dto.ResMagazine;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void getMagazine(@PathVariable long id){
        magazineService.getMagazine(id);
    }
}
