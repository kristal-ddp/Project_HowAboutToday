package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.EventImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    // Board : Event

    List<EventListDTO> findAll_Event(); // 게시판 리스트
    EventDetailDTO findOne_Event(Long eventNum); // 게시판 디테일
    void addEvent(EventAddDTO eventAddDTO, List<MultipartFile> eventImageList) throws Exception; // 게시글 작성

    List<EventImage> addImage(Long eventNum, List<MultipartFile> multipartFiles) throws Exception; // 게시글 이미지 설정
    String saveFilePath(MultipartFile multipartFile, Long eventNum) throws Exception; // 파일 저장 경로 & 저장 파일 이름 설정

}
