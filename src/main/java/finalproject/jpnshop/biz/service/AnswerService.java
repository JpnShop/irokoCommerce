package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.AnswerRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.QuestionRepository;
import finalproject.jpnshop.web.dto.ReqAnswer;
import finalproject.jpnshop.web.dto.ReqQuestion;
import finalproject.jpnshop.web.dto.ResAnswer;
import finalproject.jpnshop.web.dto.ResQuestion;
import finalproject.jpnshop.web.dto.ResQuestion.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public List<ResAnswer.Response> getAnswers() {
        List<Answer> answers = answerRepository.findAll();
        List<ResAnswer.Response> answerList = new ArrayList<>();
        for (Answer answer : answers){
            answerList.add(ResAnswer.Response.of(answer));
        }
        return answerList;
    }


    public ResAnswer.Response getAnswer(long answerId){
        return ResAnswer.Response.of(answerRepository.findById(answerId).orElseThrow(
            () -> new CustomException(ErrorCode.ANSWER_NOT_FOUND)));
    }

    @Transactional
    public void insertAnswer(ReqAnswer answerForm, Long questionId){
        answerForm.setMember(memberRepository.findById(1L).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        answerForm.setQuestion(questionRepository.findById(questionId).orElseThrow(
            () -> new CustomException(ErrorCode.QUESTION_NOT_FOUND)));
        Answer answer = answerForm.toEntity();
        answerRepository.save(answer);
    }

    @Transactional
    public void updateAnswer(ReqAnswer answerForm, Long answerId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(
            () -> new CustomException(ErrorCode.ANSWER_NOT_FOUND));
        answer.setContent(answerForm.getContent());
        answerRepository.save(answer);
    }

    @Transactional
    public void deleteAnswer(Long answerId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(
            () -> new CustomException(ErrorCode.ANSWER_NOT_FOUND));
        answerRepository.delete(answer);
    }

}
