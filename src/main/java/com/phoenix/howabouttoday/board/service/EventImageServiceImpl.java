package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.EventImageDTO;
import com.phoenix.howabouttoday.board.entity.EventImage;
import com.phoenix.howabouttoday.board.repository.EventImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class EventImageServiceImpl implements EventImageService{

    // Image : Event

    private final EventImageRepository eventImageRepository;

    // 이미지 리스트 (모든 이미지 조회)
    @Override
    public List<EventImageDTO> findAll_Image(Long eventNum) {

        return eventImageRepository.findByEventEventNum(eventNum)
                .stream()
                .map(EventImageDTO::new)
                .collect(Collectors.toList());
    }

    // 이미지 개별 삭제
    @Override
    public void deleteImage(Long eventImageNum) {
        EventImage eventImage = eventImageRepository.findById(eventImageNum).orElse(null);
        eventImageRepository.delete(eventImage);
    }
}
