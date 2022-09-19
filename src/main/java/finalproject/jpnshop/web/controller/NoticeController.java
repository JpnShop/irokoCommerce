package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.NoticeService;
import finalproject.jpnshop.web.dto.ReqNotice;
import finalproject.jpnshop.web.dto.ResNotice.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public List<Response> getNotices(){
        return noticeService.getNotices();
    }

    @GetMapping("/{id}")
    public Response getNotice(@PathVariable Long id){
        return noticeService.getNotice(id);
    }

    @PostMapping
    public String insertNotice(@RequestBody ReqNotice noticeForm){
        noticeService.insertNotice(noticeForm);
        return "작성한 공지글이 게시되었습니다.";
    }

    @PutMapping("/{id}")
    public String updateNotice(@RequestBody ReqNotice noticeForm,
        @PathVariable Long id){
        noticeService.updateNotice(noticeForm, id);
        return "게시글이 수정되었습니다";
    }

    @DeleteMapping("/{id}")
    public String deleteNotice(@PathVariable Long id){
        noticeService.deleteNotice(id);
        return "게시글이 삭제되었습니다.";
    }
}
