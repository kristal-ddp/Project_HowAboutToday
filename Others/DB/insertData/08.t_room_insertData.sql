CREATE TABLE T_ROOM (
  accomNum int,
  roomNum int auto_increment,
  roomName varchar(50) NOT NULL,
  defaultGuest int NOT NULL,
  maxGuest int NOT NULL,
  price int NOT NULL,
  restStartTime varchar(20) NOT NULL,
  restEndTime varchar(20) NOT NULL,
  roomInfo varchar(1000) NOT NULL,
  PRIMARY KEY (`roomNum`),
  CONSTRAINT FK_26 FOREIGN KEY (accomNum) REFERENCES T_ACCOMMODATION(accomNum)on delete cascade
)AUTO_INCREMENT=1;

INSERT INTO `T_ROOM` (`accomNum`,`roomName`,`defaultGuest`,`maxGuest`,`price`,`restStartTime`,`restEndTime`,`roomInfo`)
VALUES
(1,"1실 1주차 A ",2,2,30000,"22:00","12:00","2인 /최대 2인,퀸 침대,금연객실"),
(1,"1실 1주차 B ",2,2,30000,"22:00","12:00","2인 /최대 2인,퀸 침대,금연객실"),
(1,"파티룸 B  2인실 ",2,2,60000,"17:00","12:00","2인 /최대 2인,퀸 침대,금연객실"),
(1,"파티룸 A  2인실",2,2,45000,"17:00","12:00","2인 /최대 2인,퀸 침대,금연객실"),
(2,"Deluxe",2,2,69800,"20:00","12:00","2인 /최대 2인,더블 침대,금연객실"),
(2,"Deluxe544",2,2,79800,"20:00","12:00","2인 /최대 2인,퀸 침대,금연객실"),
(2,"Premium",2,2,89800,"20:00","12:00","2인 /최대 2인,더블 침대,금연객실"),
(2,"Suite",2,2,149800,"20:00","12:00","2인 /최대 2인,킹 침대,금연객실"),
(2,"Royal Suite",2,2,169800,"20:00","12:00","2인 /최대 2인,킹 침대,금연객실"),
(3,"당일특가",2,2,55000,"22:00","15:00","2인 /최대 2인,퀸 침대,금연객실"),
(3,"무비룸 오픈특가",2,2,75000,"18:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"디럭스 오픈특가",2,2,65000,"20:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"스탠다드 오픈특가",2,2,50000,"20:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"디럭스 트윈 오픈특가",2,2,65000,"18:00","15:00","2인 /최대 2인,퀸 침대,싱글 침대"),
(3,"스탠다드 트윈 오픈특가",2,2,65000,"18:00","15:00","2인 /최대 2인"),
(3,"스탠다드 롱타임",2,2,75000,"12:00","15:00","2인 /최대 2인,금연객실"),
(3,"스탠다드 게임룸",2,2,55000,"18:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"트윈 스위트 오픈특가",2,3,80000,"18:00","15:00","2인 /최대 3인,퀸 침대"),
(3,"프리미엄 디럭스 오픈특가",2,2,70000,"18:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"프리미엄 스위트 3PC",2,2,130000,"18:00","15:00","2인 /최대 2인,퀸 침대"),
(3,"프리미엄 스위트 오픈특가",2,2,80000,"18:00","15:00","2인 /최대 2인,퀸 침대"),
(4,"3인 길드룸 PC",3,4,80000,"14:00","14:00","3인 /최대 4인,퀸 침대"),
(4,"VIP 커플PC",2,2,30000,"7시간","10:00 ~ 23:00","2인 /최대 2인,퀸 침대"),
(4,"디럭스 고사양 1인PC",2,2,45000,"14:00","14:00","2인 /최대 2인,퀸 침대"),
(4,"스위트B",5,6,180000,"16:00","14:00","5인 /최대 6인,퀸 침대"),
(4,"미니룸 고사양1PC",2,2,15000,"6시간","10:00 ~ 21:00","2인 /최대 2인,퀸 침대"),
(4,"스위트A",5,6,65000,"7시간","12:00 ~ 22:00","5인 /최대 6인,퀸 침대"),
(4,"스탠다드 고사양 1인PC",2,2,20000,"8시간","10:00 ~ 23:00","2인 /최대 2인,퀸 침대"),
(4,"안마특실고사양1PC",2,2,55000,"23:00","14:00","2인 /최대 2인,퀸 침대"),
(4,"프리미엄 커플PC A",2,2,27000,"7시간","10:00 ~ 23:00","2인 /최대 2인,퀸 침대"),
(4,"이벤트 할인특가 랜덤배정",2,2,25000,"9시간","12:00 ~ 23:00","2인 /최대 2인,퀸 침대"),
(4,"프리미엄 커플PC B",2,2,50000,"14:00","14:00","2인 /최대 2인,퀸 침대"),
(5,"DELUXE",2,2,50000,"17:00","12:00","2인 /최대 2인,더블 침대,금연객실"),
(5,"STANDARD",2,2,40000,"17:00","12:00","2인 /최대 2인,금연객실"),
(5,"STANDARD133",2,2,45000,"17:00","12:00","2인 /최대 2인,더블 침대,금연객실"),
(5,"스위트698",2,2,60000,"17:00","12:00","2인 /최대 2인,금연객실"),
(5,"SUITE TWIN",2,2,60000,"17:00","12:00","2인 /최대 2인,퀸 침대,싱글 침대,금연객실"),
(5,"레이트체크인EVENT",2,2,35000,"20:00","12:00","2인 /최대 2인,금연객실"),
(5,"얼리체크인EVENT",2,2,50000,"17:00","12:00","2인 /최대 2인,금연객실"),
(5,"스위트",2,2,65000,"17:00","12:00","2인 /최대 2인,더블 침대,금연객실"),
(5,"DELUXE734",2,2,50000,"17:00","12:00","2인 /최대 2인,금연객실"),
(6," 내맘대로 12시간 STAY 디럭스 프리미엄",2,2,88000,"10:00","00:00","2인 /최대 2인,금연객실"),
(6," 내맘대로 12시간 STAY 점보 트윈 ",2,3,85000,"10:00","00:00","2인 /최대 3인,금연객실"),
(6," 점보 트윈 ",2,3,154000,"15:00","12:00","2인 /최대 3인,금연객실"),
(6," 디럭스 더블",2,2,154000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6," 헐리웃 더블815",2,2,110000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6,"디럭스 더블",2,2,137500,"15:00","12:00","2인 /최대 2인,금연객실"),
(6," 디럭스 트윈",2,2,121000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6," 헐리웃 더블",2,2,91350,"15:00","12:00","2인 /최대 2인,금연객실"),
(6," 스탠다드 더블",2,2,143000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6,"디럭스 프리미엄",2,2,135000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6,"스탠다드 더블",2,2,132000,"15:00","12:00","2인 /최대 2인,금연객실"),
(6,"점보 트윈 ",2,3,137500,"15:00","12:00","2인 /최대 3인,금연객실"),
(7,"Movie & 더블",2,2,46000,"15:00","11:00","2인 /최대 2인,금연객실"),
(7,"디럭스 더블",2,2,23000,"4시간","10:00 ~ 19:00","2인 /최대 2인,퀸 침대,금연객실"),
(7,"Movie & 패밀리 트윈",3,5,66000,"15:00","11:00","3인 /최대 5인,금연객실"),
(7,"디럭스 트윈",2,4,50000,"15:00","11:00","2인 /최대 4인,금연객실"),
(7,"디럭스 더블 24시간 스테이",2,2,46000,"13:00","13:00","2인 /최대 2인,금연객실"),
(7,"머물다 가요 특가 day Use 6시간 랜덤 배정",2,2,30000,"6시간","10:00 ~ 19:00","2인 /최대 2인,금연객실"),
(7,"제주항 특가 꿀잠 4시간 스테이 랜덤 배정",2,2,23000,"4시간","10:00 ~ 19:00","2인 /최대 2인,금연객실"),
(7,"Movie & 프리미엄 스위트",3,5,63900,"15:00","11:00","3인 /최대 5인,퀸 침대,금연객실"),
(7,"제주공항 픽업서비스",2,2,10000,"18:00","22:00","2인 /최대 2인,금연객실"),
(8,"Standard A",2,2,75000,"23:00","12:00","2인 /최대 2인,금연객실"),
(8,"Standard 도보전용",2,2,65000,"22:00","12:00","2인 /최대 2인,퀸 침대"),
(8,"Standard B",2,2,80000,"22:00","12:00","2인 /최대 2인,금연객실"),
(9,"디럭스",2,3,120000,"14:00","12:00","2인 /최대 3인,금연객실"),
(9,"스탠다드",2,3,90000,"14:00","12:00","2인 /최대 3인,금연객실"),
(9,"스위트",2,4,160000,"14:00","12:00","2인 /최대 4인,금연객실"),
(10,"디럭스 더블",2,2,85000,"15:00","11:00","2인 /최대 2인,금연객실"),
(10,"로얄 그랜드 스위트",4,4,330000,"15:00","11:00","4인 /최대 4인,금연객실"),
(10,"디럭스 트리플",3,4,113000,"15:00","11:00","3인 /최대 4인,금연객실"),
(10,"디럭스 패밀리 트윈",2,3,102000,"15:00","11:00","2인 /최대 3인,금연객실"),
(10,"로얄 탑스텐 스위트",4,4,390000,"15:00","11:00","4인 /최대 4인,금연객실"),
(10,"로얄 슈페리어 스위트",4,4,300000,"15:00","11:00","4인 /최대 4인,금연객실"),
(10,"로얄 컴포트 스위트",6,6,420000,"15:00","11:00","6인 /최대 6인,금연객실"),
(10,"패밀리 스위트",6,8,210000,"15:00","11:00","6인 /최대 8인,금연객실"),
(10,"프리미어 스위트",4,6,164000,"15:00","11:00","4인 /최대 6인,금연객실"),
(10,"주니어 스위트",4,5,137000,"15:00","11:00","4인 /최대 5인,금연객실"),
(11,"A201",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"B102",2,8,99000,"15:00","11:00","2인 /최대 8인,퀸 침대,금연객실"),
(11,"A204",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"A102",2,8,99000,"15:00","11:00","2인 /최대 8인,퀸 침대,금연객실"),
(11,"A203",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"B202",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"B101",2,8,99000,"15:00","11:00","2인 /최대 8인,퀸 침대,금연객실"),
(11,"B203",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"B201",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"B204",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(11,"A202",2,4,89000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(12,"102호 에메랄드의성",5,6,100000,"15:00","11:00","5인 /최대 6인,금연객실"),
(12,"203호 나무꾼의심장",5,6,100000,"15:00","11:00","5인 /최대 6인,금연객실"),
(12,"201호 오즈의길",2,3,60000,"15:00","11:00","2인 /최대 3인,금연객실"),
(12,"202호 캔자스농장",4,5,80000,"15:00","11:00","4인 /최대 5인,금연객실"),
(12,"301호 사자의 용기",2,3,60000,"15:00","11:00","2인 /최대 3인,금연객실"),
(12,"204호 착한마녀",4,4,60000,"15:00","11:00","4인 /최대 4인,금연객실"),
(12,"103호 토토의시간",4,4,60000,"15:00","11:00","4인 /최대 4인,금연객실"),
(12,"302호 무지개파티",4,5,80000,"15:00","11:00","4인 /최대 5인,금연객실"),
(12,"303호 노란벽돌",1,0,200000,"15:00","11:00","10인 /최대 12인,금연객실"),
(12,"304호 허수아비의지혜",8,9,150000,"15:00","11:00","8인 /최대 9인,금연객실"),
(13,"13평",2,3,55300,"15:00","11:00","2인 /최대 3인,금연객실"),
(13,"20평",4,5,90000,"15:00","11:00","4인 /최대 5인,금연객실"),
(13,"23평",5,6,100000,"15:00","11:00","5인 /최대 6인,금연객실"),
(14,"301호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(14,"302호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(14,"303호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(14,"403호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(14,"402호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(14,"401호",2,3,90000,"15:00","11:00","2인 /최대 3인,금연객실"),
(15,"A101",2,2,59400,"15:00","11:00","2인 /최대 2인,금연객실"),
(15,"A202",2,4,72900,"15:00","11:00","2인 /최대 4인,금연객실"),
(15,"A201",2,4,75000,"15:00","11:00","2인 /최대 4인,금연객실"),
(15,"A102",2,4,62100,"15:00","11:00","2인 /최대 4인,금연객실"),
(15,"VIP301",2,6,138000,"15:00","11:00","2인 /최대 6인,금연객실"),
(15,"B101",2,4,89100,"15:00","11:00","2인 /최대 4인,금연객실"),
(15,"B201",2,4,83700,"15:00","11:00","2인 /최대 4인,금연객실"),
(16,"A동 -NO4",2,4,50000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(16,"A동-NO3",2,2,60000,"15:00","11:00","2인 /최대 2인,퀸 침대,금연객실"),
(16,"A동-NO5",2,3,50000,"15:00","11:00","2인 /최대 3인,퀸 침대,금연객실"),
(16,"A동-NO2",2,2,60000,"15:00","11:00","2인 /최대 2인,퀸 침대,금연객실"),
(16,"A동-NO5-1",2,3,60000,"15:00","11:00","2인 /최대 3인,퀸 침대,금연객실"),
(16,"B동-No4-2",2,4,50000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(16,"B동- NO1",2,3,45000,"15:00","11:00","2인 /최대 3인,퀸 침대,금연객실"),
(16,"A동-NO2-1",2,3,60000,"15:00","11:00","2인 /최대 3인,퀸 침대,금연객실"),
(16,"B동-카페월풀A",2,2,70000,"15:00","11:00","2인 /최대 2인,퀸 침대,금연객실"),
(16,"R동 로뎀커플-1",2,4,50000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(16,"R동 로뎀커플-3",2,4,50000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(16,"R동 로뎀커플-2",2,4,50000,"15:00","11:00","2인 /최대 4인,퀸 침대,금연객실"),
(17,"3인실",3,3,70000,"15:00","11:00","3인 /최대 3인,금연객실"),
(17,"4인실",4,4,80000,"15:00","11:00","4인 /최대 4인,금연객실"),
(17,"디럭스트윈룸",2,2,35000,"15:00","11:00","2인 /최대 2인,싱글 침대,금연객실"),
(17,"더블룸",2,2,34000,"15:00","11:00","2인 /최대 2인,더블 침대,금연객실"),
(17,"싱글룸",1,1,29000,"15:00","11:00","1인 /최대 1인,싱글 침대,금연객실"),
(17,"벙크트윈룸",2,2,33000,"15:00","11:00","2인 /최대 2인,2층 침대,금연객실"),
(17,"여성 도미토리",1,1,20000,"15:00","11:00","1인 /최대 1인,금연객실"),
(17,"혼성 도미토리",1,1,20000,"15:00","11:00","1인 /최대 1인,금연객실"),
(17,"패밀리룸",3,3,65000,"15:00","11:00","3인 /최대 3인,금연객실"),
(18,"2인동시결제-남자6인도미토리",2,2,50000,"17:00","12:00","2인 /최대 2인,2층 침대,금연객실"),
(18,"남자 4인 도미토리",1,2,25000,"17:00","12:00","1인 /최대 2인,2층 침대,금연객실"),
(18,"3인동시결제-남자6인도미토리",3,3,75000,"17:00","12:00","3인 /최대 3인,2층 침대,금연객실"),
(18,"2인동시결제-여자 6인도미토리",2,2,50000,"17:00","12:00","2인 /최대 2인,2층 침대,금연객실"),
(18,"남자 6인 도미토리",1,6,25000,"17:00","12:00","1인 /최대 6인,2층 침대,금연객실"),
(18,"야외요가 + 여자 6인 도미토리",1,6,95000,"17:00","12:00","1인 /최대 6인,2층 침대,금연객실"),
(18,"4인동시결제-남자6인도미토리",4,4,100000,"17:00","12:00","4인 /최대 4인,2층 침대,금연객실"),
(18,"야외요가 + 남자 6인 도미토리",1,6,95000,"17:00","12:00","1인 /최대 6인,2층 침대,금연객실"),
(19,"5인 패밀리룸",5,5,75000,"14:00","11:00","5인 /최대 5인,금연객실"),
(19,"남성 3인 도미토리",1,1,18000,"14:00","11:00","1인 /최대 1인,금연객실"),
(19,"남성 5인 도미토리",1,1,15000,"14:00","11:00","1인 /최대 1인,금연객실"),
(19,"싱글룸",1,1,35000,"14:00","11:00","1인 /최대 1인,싱글 침대,금연객실"),
(19,"더블룸",2,2,42000,"14:00","11:00","2인 /최대 2인,더블 침대,금연객실"),
(19,"여성 5인 도미토리",1,1,15000,"14:00","11:00","1인 /최대 1인,금연객실"),
(19,"트리플룸",3,3,54000,"14:00","11:00","3인 /최대 3인,금연객실"),
(19,"트윈룸",2,2,42000,"14:00","11:00","2인 /최대 2인,싱글 침대,금연객실"),
(19,"트윈룸 2층침대",2,2,30000,"14:00","11:00","2인 /최대 2인,금연객실"),
(19,"여성 3인 도미토리",1,1,18000,"14:00","11:00","1인 /최대 1인,금연객실"),
(19,"싱글룸 2층침대",1,1,25000,"14:00","11:00","1인 /최대 1인,금연객실"),
(20,"2인 더블",2,2,50000,"15:00","11:00","2인 /최대 2인,킹 침대,금연객실"),
(20,"2인 트윈",2,2,50000,"15:00","11:00","2인 /최대 2인,슈퍼싱글 침대,금연객실"),
(20,"남 4인 도미토리",1,1,22000,"15:00","11:00","1인 /최대 1인,2층 침대,금연객실"),
(20,"여 4인 도미토리",1,1,22000,"15:00","11:00","1인 /최대 1인,2층 침대,금연객실"),
(20,"남 6인 도미토리",1,1,19000,"15:00","11:00","1인 /최대 1인,2층 침대,금연객실"),
(20,"여 6인 도미토리",1,1,19000,"15:00","11:00","1인 /최대 1인,2층 침대,금연객실"),