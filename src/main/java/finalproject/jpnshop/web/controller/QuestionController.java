package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.QuestionService;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqQuestion;
import finalproject.jpnshop.web.dto.ResQuestion.Response;
import java.util.List;
import java.util.Map;
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
@RequiredArgsConstructor
@RequestMapping("/customers/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<Response> getQuestions(){
        return questionService.getQuestions();
    }

    //todo : ProductController로 기능 이동 필요
    @GetMapping("/products/{productId}/questions")
    public List<Response> getQuestionsByProduct(@PathVariable long productId){
        return questionService.getQuestionsByProduct(productId);
    }

    @GetMapping("/my")
    public List<Response> getQuestionsByMember(){
        long memberId = SecurityUtil.getCurrentMemberId();
        return questionService.getQuestionsByMember(memberId);
    }

    @GetMapping("/{id}")
    public Response getQuestion(@PathVariable long id, int password){
        return questionService.getQuestion(id,password);
    }

    @PostMapping("/{productId}")
    public String insertQuestion(@RequestBody ReqQuestion questionForm,
        @PathVariable long productId){
        questionService.insertQuestion(questionForm,productId);
        return "작성한 문의글이 게시되었습니다.";
    }

    @PutMapping("/{id}")
    public String updateQuestion(@RequestBody ReqQuestion questionForm,
        @PathVariable long id){
        questionService.updateQuestion(questionForm, id);
        return "문의글 수정이 성공했습니다.";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable long id){
        questionService.deleteQuestion(id);
        return "문의글이 삭제되었습니다.";
    }
}
