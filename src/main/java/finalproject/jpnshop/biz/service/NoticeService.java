package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Notice;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.NoticeRepository;
import finalproject.jpnshop.web.dto.ReqNotice;
import finalproject.jpnshop.web.dto.ResNotice;
import finalproject.jpnshop.web.dto.ResNotice.Response;
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
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;


    public List<ResNotice.Response> getNotices() {
        List<Notice> notices = noticeRepository.findAll();
        List<ResNotice.Response> noticeList = new ArrayList<>();
        for (Notice notice : notices){
            noticeList.add(ResNotice.Response.of(notice));
        }
        return noticeList;
    }

    public Response getNotice(Long id) {
        return Response.of(noticeRepository.findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.NOTICE_NOT_FOUND)));
    }

    //todo : 로그인한 회원으로 멤버 저장
    @Transactional
    public void insertNotice(ReqNotice noticeForm){
        noticeForm.setMember(memberRepository.findById(1L).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        Notice notice = noticeForm.toEntity();
        noticeRepository.save(notice);
    }

    @Transactional
    public void updateNotice(ReqNotice noticeForm, Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
            () -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
        notice.setTitle(noticeForm.getTitle());
        notice.setContent(noticeForm.getContent());
        noticeRepository.save(notice);

    }
    @Transactional
    public void deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
        noticeRepository.delete(notice);
    }
}
