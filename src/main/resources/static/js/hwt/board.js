$(document).ready(function() {

    $(".faq_move").click(function(event){

        event.preventDefault();

        var headerHeight = $("header").outerHeight();
        var href = $(this).attr("href");
        var target = $(href == "#" || href == "" ? "body" : href);
        var position = target.offset().top - headerHeight;

        $('html,body').animate({
            scrollTop: position
//            scrollTop: $(this.hash).offset().top + 1000
//          }, 800);
          }, 800, "swing");
    });
});
