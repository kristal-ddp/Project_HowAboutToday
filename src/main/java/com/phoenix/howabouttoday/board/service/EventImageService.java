package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.EventImageDTO;

import java.util.List;

public interface EventImageService {

    // Image : Event

    List<EventImageDTO> findAll_Image(Long eventNum); // 이미지 리스트 (모든 이미지 조회)
    void deleteImage(Long eventImageNum); // 이미지 개별 삭제

}
