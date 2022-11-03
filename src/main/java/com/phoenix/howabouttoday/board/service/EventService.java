package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.EventImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    // Board : Event

    Slice<EventListDTO> findAll_Event(Pageable pageable); // 게시판 리스트 (모든 게시글 조회)
    EventDetailDTO findOne_Event(Long eventNum); // 게시판 디테일 (게시글 1개 조회)
    
    void addEvent(EventDTO eventDTO, List<MultipartFile> eventImageList) throws Exception; // 게시글 작성
    void editEvent(Long eventNum, EventDTO eventDTO) throws Exception; // 게시글 수정
    void deleteEvent(EventDetailDTO eventDetailDTO); // 게시글 삭제

    List<EventImage> addImage(Long eventNum, List<MultipartFile> multipartFiles) throws Exception; // 게시글 이미지 설정
    String saveFilePath(MultipartFile multipartFile, Long eventNum) throws Exception; // 파일 저장 경로 & 저장 파일 이름 설정

}
