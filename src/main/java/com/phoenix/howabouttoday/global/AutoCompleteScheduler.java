package com.phoenix.howabouttoday.global;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AutoCompleteScheduler {

    /*
        cron
        Cron 표현식을 사용한 작업 예약
                cron = "* * * * * *"
        첫번째 부터 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
        출처: https://data-make.tistory.com/699 [Data Makes Our Future:티스토리]
    */

    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul") //매일 자정에 실행 초 분 시 일 달 기준 (ex) 0 0 0 10 1 1월 10일 자정에 실행
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        log.info("예약 상태 업데이트 - {}", now);
    }
}
