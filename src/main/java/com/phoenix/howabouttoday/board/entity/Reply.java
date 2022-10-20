package com.phoenix.howabouttoday.board.entity;

import com.phoenix.howabouttoday.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Reply {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reply_num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_num")
    private Review review;

    private LocalDateTime replyCreatedDate;
    private LocalDateTime replyModifyDate;

    private String content;

}