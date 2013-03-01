if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });
    })(jQuery);
}

var timer
function fadeMeOut() {
    timer = window.setInterval(function () {
        hideMe()
    }, 3500);
}

function hideMe() {
    $(".alert.alert-info").slideUp(600);
    window.clearInterval(timer)
}