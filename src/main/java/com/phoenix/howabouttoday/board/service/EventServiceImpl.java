package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.Event;
import com.phoenix.howabouttoday.board.entity.EventImage;
import com.phoenix.howabouttoday.board.repository.EventImageRepository;
import com.phoenix.howabouttoday.board.repository.EventRepository;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    // Board : Event

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;

    private final MemberRepository memberRepository;

    // 게시판 리스트 (모든 게시글 조회)
    @Override
    public Slice<EventListDTO> findAll_Event(Pageable pageable) {

        // Entity → DTO
        Slice<EventListDTO> lists = eventRepository.findAll(pageable) // Entity
                .map(EventListDTO::new); // DTO
//                .map(event -> new EventListDTO(event)); // DTO

        return lists;
    }

    // 게시판 디테일 (게시글 1개 조회)
    @Override
    public EventDetailDTO findOne_Event(Long eventNum) {

        // Entity → DTO
        return eventRepository.findById(eventNum) // Entity
                .map(EventDetailDTO::new) // DTO
                .orElse(null); // 에러 시 null 반환
    }

    // 게시글 작성
    @Override
    @Transactional
    public void addEvent(EventDTO eventDTO, List<MultipartFile> eventImageList) throws Exception {

        Member member = memberRepository.findById(eventDTO.getMemberNum()).orElse(null);

        Event event = new Event(member, eventDTO);
        Long eventNum = eventRepository.save(event).getEventNum();

        List<EventImage> eventImages = addImage(eventNum, eventImageList);

        for(EventImage eventImage : eventImages) {
            eventImageRepository.save(eventImage);
        }

    }

    // 게시글 수정
    @Override
    @Transactional
    public void editEvent(Long eventNum, EventDTO eventDTO) throws Exception {

        Event event = eventRepository.findById(eventNum).orElse(null);
        event.editEvent(event.getEventNum(), eventDTO);

        List<EventImage> eventImages = addImage(eventNum, eventDTO.getEventImageList());

        for(EventImage eventImage : eventImages) {
            eventImageRepository.save(eventImage);
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void deleteEvent(EventDetailDTO eventDetailDTO) {

        Event event = eventRepository.findById(eventDetailDTO.getEventNum()).orElse(null);
        eventRepository.delete(event);
    }

    // 게시글 이미지 설정
    @Override
    public List<EventImage> addImage(Long eventNum, List<MultipartFile> multipartFiles) throws Exception {

        List<EventImage> eventImageList = new ArrayList<>(); // 반환할 이미지 리스트
        Event event = eventRepository.findById(eventNum).orElse(null);

        for(MultipartFile multipartFile : multipartFiles) {

            if(!multipartFile.isEmpty()) {

                String originFileName = multipartFile.getOriginalFilename();
                String saveFileName = saveFilePath(multipartFile, eventNum); // UUID 변환

                EventImage eventImage = new EventImage(event, originFileName, saveFileName);
                eventImageList.add(eventImage);
            }
        }

        return eventImageList;
    }

    // 파일 저장 경로 & 저장 파일 이름 설정
    @Override
    public String saveFilePath(MultipartFile multipartFile, Long eventNum) throws Exception{

        if(multipartFile.isEmpty()) {
            return null;
        }

        Event event = eventRepository.findById(eventNum).orElse(null);

        String folderPath = String.valueOf(event.getEventNum());
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\hwt\\event\\" + folderPath; // 파일 저장 경로

        if(!new File(imagePath).exists()) {
            new File(imagePath).mkdir();
        }

        String originFileName = multipartFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + "." + ext;

        multipartFile.transferTo(new File(imagePath, saveFileName));

        return saveFileName;
    }

}
