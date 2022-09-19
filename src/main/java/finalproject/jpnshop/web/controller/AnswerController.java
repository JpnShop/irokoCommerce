package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.AnswerService;
import finalproject.jpnshop.web.dto.ReqAnswer;
import finalproject.jpnshop.web.dto.ReqQuestion;
import finalproject.jpnshop.web.dto.ResAnswer;
import finalproject.jpnshop.web.dto.ResQuestion.Response;
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
@RequiredArgsConstructor
@RequestMapping("/customers/questions/answers")
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping
    public List<ResAnswer.Response> getAnswers(){
        return answerService.getAnswers();
    }

    @GetMapping("/{id}")
    public ResAnswer.Response getAnswer(@PathVariable long id){
        return answerService.getAnswer(id);
    }

    @PostMapping("/question_id={id}")
    public String insertAnswer(@RequestBody ReqAnswer answerForm,
        @PathVariable long id){
        answerService.insertAnswer(answerForm,id);
        return "작성한 답변이 게시되었습니다.";
    }

    @PutMapping("/{id}")
    public String updateQuestion(@RequestBody ReqAnswer answerForm,
        @PathVariable long id){
        answerService.updateAnswer(answerForm, id);
        return "답변을 수정했습니다.";
    }
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable long id){
        answerService.deleteAnswer(id);
        return "답변이 삭제되었습니다.";
    }

}
