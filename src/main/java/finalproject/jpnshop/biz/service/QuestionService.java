package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.domain.properties.Role;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.QuestionRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqQuestion;
import finalproject.jpnshop.web.dto.ResQuestion;
import finalproject.jpnshop.web.dto.ResQuestion.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<ResQuestion.Response> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<ResQuestion.Response> questionList = new ArrayList<>();
        for (Question question : questions){
            questionList.add(ResQuestion.Response.of(question));
        }
        return questionList;
    }

    public List<ResQuestion.Response> getQuestionsByProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        List<Question> questions = questionRepository.findAllByProduct(product);
        List<ResQuestion.Response> questionList = new ArrayList<>();
        for (Question question : questions){
            questionList.add(ResQuestion.Response.of(question));
        }
        return questionList;
    }

    public List<ResQuestion.Response> getQuestionsByMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<Question> questions = questionRepository.findAllByMember(member);
        List<ResQuestion.Response> questionList = new ArrayList<>();
        for (Question question : questions){
            questionList.add(ResQuestion.Response.of(question));
        }
        return questionList;
    }
    public ResQuestion.Response getQuestion(long questionId, int password){
        Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if(question.getPrivateYn().equals(true)) {
            if (member.getRole().equals(Role.ROLE_ADMIN)) {
                return Response.of(question);
            } else if (question.getPassword() != password) {
                throw new CustomException(ErrorCode.PASSWORD_NOT_CORRECT);
            }
        }
        return Response.of(question);
    }

    @Transactional
    public void insertQuestion(ReqQuestion questionForm, Long productId){
        if(questionForm.getPrivateYn().equals(true) && questionForm.getPassword()==0){
        throw new CustomException(ErrorCode.PASSWORD_NOT_FOUND);
    }
        long memberId = SecurityUtil.getCurrentMemberId();
        questionForm.setMember(memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        questionForm.setProduct(productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)));
        Question question = questionForm.toEntity();
        questionRepository.save(question);
    }

    @Transactional
    public void updateQuestion(ReqQuestion questionForm, Long questionId){
        Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
        question.setTitle(questionForm.getTitle());
        question.setContent(questionForm.getContent());
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId){
        Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
        questionRepository.delete(question);
    }

}
