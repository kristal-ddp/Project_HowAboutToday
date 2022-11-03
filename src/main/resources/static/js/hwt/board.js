$(function() {

    // FAQ 카테고리 클릭 시
    $('#faqAll').click(function(){
        $('.col-board').css('display', 'block'); // 모든 게시글 보임
    });

    $('.faq').click(function(){

        let id = $(this).attr("id");

        $('.col-board').css('display', 'none'); // 다른 카테고리 게시글 숨김
        $('#' + id  + '-content').css('display', 'block'); // 해당 카테고리 게시글만 보임
    });


    // (Notice, Event, About Us) 게시판 더보기 버튼 클릭 시
    let page = 0;

    $('#loadMore').click(function(){

        page += 1;

        let url = location.pathname;
        let boardUrl;

        if(url.indexOf('notice') > -1){
            boardUrl = "notice-more";
        }else if(url.indexOf('event') > -1){
            boardUrl = "event-more";
        }else if(url.indexOf('aboutUs') > -1){
            boardUrl = "aboutUs-more";
        }

        $.ajax({

            url: boardUrl + '?page=' + page,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

        }).done(function(result){

            if(url.indexOf('notice') > -1){
                listLow = noticeLow(result);
            }else if(url.indexOf('event') > -1){
                listLow = eventLow(result);
            }else if(url.indexOf('aboutUs') > -1){
                listLow = aboutUsLow(result);
            }

//            $('#list-more').append(listLow); // #list-more의 안쪽의 마지막 위치에 출력
            $('#loadMore').before(listLow); // #loadMore의 바깥쪽의 바로 위에 출력

            if(result.content.length < 1){
                alert("게시글이 더 이상 존재하지 않습니다.");
                $('#loadMore').css('display', 'none');
            }
        });
    });

    // Notice 게시판 더보기 Content
    const noticeLow = function (result){

        let board = result.content;
        let newlow = "";

        for (i = 0; i < board.length; i++) {

            newlow += `<div class="col-lg-4 responsive-column col-board">`
            newlow += `<div class="card-item blog-card">`
            newlow += `<input type="hidden" id="${board[i].boardNum}">`
            newlow += `<div class="card-footer d-flex align-items-center justify-content-between hwt-board-list" onclick="location.href='/notice/${board[i].boardNum}'">`
            newlow += `<div class="author-content d-flex hwt-board-title">`
            newlow += `<div class="author-bio"><a class="author__title">${board[i].boardTitle}</a></div>`
            newlow += `<div class="hwt-board-date"><a class="author__title">${board[i].boardCreate}</a></div></div>`
            newlow += `<div class="post-share"><ul><li><a><i class="la la-share icon-element hwt-icon"></i></a></li></ul></div>`
            newlow += `</div></div></div>`
        }

        return newlow;
    }

    // Event 게시판 더보기 Content
    const eventLow = function (result){

        let board = result.content;
        let newlow = "";

        for (i = 0; i < board.length; i++) {

            newlow += `<div class="col-lg-4 responsive-column col-board">`
            newlow += `<div class="card-item blog-card">`
            newlow += `<input type="hidden" id="${board[i].eventNum}">`
            newlow += `<div class="card-footer d-flex align-items-center justify-content-between hwt-board-list" onclick="location.href='/event/${board[i].eventNum}'">`
            newlow += `<div class="author-content d-flex hwt-board-title">`
            newlow += `<div class="author-bio"><a class="author__title">${board[i].eventTitle}</a></div>`
            newlow += `<div class="hwt-board-date"><a class="author__title">${board[i].eventStart} - ${board[i].eventEnd}</a></div></div>`
            newlow += `<div class="post-share"><ul><li><a><i class="la la-share icon-element hwt-icon"></i></a></li></ul></div>`
            newlow += `</div></div></div>`
        }

        return newlow;
    }

    // About Us 게시판 더보기 Content
    const aboutUsLow = function (result){

        let board = result.content;
        let newlow = "";

        for (i = 0; i < board.length; i++) {

            newlow += `<div class="col-lg-4 responsive-column col-board">`
            newlow += `<div class="card-item blog-card">`
            newlow += `<input type="hidden" id="${board[i].boardNum}">`
            newlow += `<div class="card-footer d-flex align-items-center justify-content-between hwt-board-list" onclick="location.href='/aboutUs/${board[i].boardNum}'">`
            newlow += `<div class="author-content d-flex hwt-board-title">`
            newlow += `<div class="author-bio"><a class="author__title">${board[i].boardTitle}</a></div>`
            newlow += `</div>`
            newlow += `<div class="post-share"><ul><li><a><i class="la la-share icon-element hwt-icon"></i></a></li></ul></div>`
            newlow += `</div></div></div>`
        }

        return newlow;
    }

});