/* 지도 */
var mapContainer = document.getElementById('map'),
    mapOptions = {
        center: new kakao.maps.LatLng(37.4988, 127.0317), // 지도 중심좌표
        level: 3 // 지도 확대, 축소 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOptions); // 지도 생성


/* 마커 */
var mapCenter  = new kakao.maps.LatLng(37.4988, 127.0317); // 마커 위치
var marker = new kakao.maps.Marker({ // 마커 생성
    position: mapCenter,
    map: map // 지도에 마커 추가
});

// 마커 인포윈도우 생성
var iwContent = '<div style="text-align:center; font-size:14px; font-weight: 500;">오늘어때</div>' +
                '<div style="margin-left:4px; text-align:center; font-size:13px;">서울 강남구 테헤란로 124</div>';
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent
});

// 마커 마우스오버 이벤트
kakao.maps.event.addListener(marker, 'mouseover', function() {
    infowindow.open(map, marker);
});

// 마커 마우스아웃 이벤트
kakao.maps.event.addListener(marker, 'mouseout', function() {
    infowindow.close();
});


/* 맵, 줌 컨트롤 */
var mapTypeControl = new kakao.maps.MapTypeControl(); // 맵 컨트롤 생성
var zoomControl = new kakao.maps.ZoomControl(); // 줌 컨트롤 생성

map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT); // 맵 컨트롤 추가
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT); // 줌 컨트롤 추가


/* 이벤트 핸들러 */
//카카오맵 이동
function moveKakaoMap(self){

    var center = map.getCenter(),
        lat = center.getLat(),
        lng = center.getLng();

    self.href = 'https://map.kakao.com/link/map/' + encodeURIComponent('오늘어때') + ',' + lat + ',' + lng;
}

//지도 초기화
function resetKakaoMap(){
    map.setCenter(mapCenter);
    map.setLevel(mapOption.level);
}


/* 카카오톡 채널 채팅 */
Kakao.init('41285ae46bc998ac5e0756fff1675a3c');

function kakaoChatStart() {

    // 카카오톡 채널 채팅
    Kakao.Channel.chat({
        channelPublicId: '_NBxmxjxj'
    });

    // 카카오톡 채널 추가
    Kakao.Channel.addChannel({
      channelPublicId: '_NBxmxjxj'
    });
}