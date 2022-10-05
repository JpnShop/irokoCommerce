package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.AnswerRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.QuestionRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqAnswer;
import finalproject.jpnshop.web.dto.ResAnswer;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
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
        Long memberId = SecurityUtil.getCurrentMemberId();
        answerForm.setMember(memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
        Answer answer = answerForm.toEntity();
        answerRepository.save(answer);
        question.setAnswer(answer);
        question.setAnswerStatus("답변 완료");
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
        Question question = questionRepository.findByAnswer(answer);
        question.setAnswer(null);
        question.setAnswerStatus("미답변");
        answerRepository.delete(answer);
    }

}
