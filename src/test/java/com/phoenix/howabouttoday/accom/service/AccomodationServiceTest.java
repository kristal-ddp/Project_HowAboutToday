package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.SearchForm;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class AccomodationServiceTest {


    @Autowired
    private AccomodationService accomodationService;
    
    @Autowired
    private RoomRepository roomRepository;
    @Test
    public void 예약된날의_객실_테스트() throws Exception{

        Long accomNum = 1L;
        SearchForm searchForm = new SearchForm();
        searchForm.setReserveDate("2022/10/21 - 2022/10/19");
        Accommodation byAccomNum = accomodationService.findByAccomNum(accomNum, searchForm);
        List<Room> room = byAccomNum.getRoom();
        for (Room room1 : room) {
            System.out.println("room1.getRoomName() = " + room1.getRoomName());

        }


    }
    
    

}