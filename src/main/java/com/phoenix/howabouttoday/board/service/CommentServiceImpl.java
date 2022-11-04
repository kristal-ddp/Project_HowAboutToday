package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.CommentFormDTO;
import com.phoenix.howabouttoday.board.entity.Comment;
import com.phoenix.howabouttoday.board.entity.Event;
import com.phoenix.howabouttoday.board.repository.CommentRepository;
import com.phoenix.howabouttoday.board.repository.EventRepository;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    // 이벤트 게시판 댓글 작성
    @Override
    public Long addComment(String nickname, Long eventNum, CommentFormDTO commentFormDTO) {

        Event event = eventRepository.findById(eventNum).orElse(null);
        Member member = memberRepository.findByNickname(nickname);

        Comment comment = new Comment(event, member, commentFormDTO);
        commentRepository.save(comment);

        return commentFormDTO.getCommentNum();
    }

    // 이벤트 게시판 댓글 삭제
    @Override
    public void delete(Long commentNum) {

        Comment comment = commentRepository.findById(commentNum).orElse(null);

        commentRepository.delete(comment);
    }
}
