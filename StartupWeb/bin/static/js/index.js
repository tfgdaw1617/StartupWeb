$(function () {

    $.stellar.positionProperty.apple = {
        setTop: function($el, newTop, originalTop) {
            $el.css({
                'top': originalTop - newTop,
                'left': $el.hasClass('apple') ? originalTop - newTop : 0
            });
        }
    };
    $.stellar({
        horizontalScrolling: false,
        positionProperty: 'apple'
    });
    /*$("#initPageButton").click(function() {
        $('html, body').animate({
            scrollTop: $("#btn1").offset().top-100
        }, 1000);
    });*/
    $(".buttonSlideDown").click(function(){
        a = $('h3').find('.buttonSlideDown');
        if($(this).hasClass('active') === true) {
            $(this).removeClass('active').next('.box').slideUp(500);
        } else if(a.hasClass('active') === false) {
            $(this).addClass('active').next('.box').slideDown(500);
        } else {
            a.removeClass('active').next('.box').slideUp(500);
        }
    });
});