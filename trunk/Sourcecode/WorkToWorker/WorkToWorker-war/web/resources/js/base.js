jQuery(document).ready(function() {
    var $ = jQuery;
    $.noConflict();
    $(document).ready(function() {
        if ($.fn.cssOriginal != undefined) {
            $.fn.css = $.fn.cssOriginal;
        }
        $('.banner').revolution({
            delay: 9000,
            startheight: 490,
            startwidth: 890,
            thumbWidth: 100,
            thumbHeight: 50,
            thumbAmount: 5,
            onHoverStop: "on",
            hideThumbs: 200,
            navigationType: "bullet",
            navigationStyle: "square",
            navigationArrows: "verticalcentered",
            touchenabled: "on",
            navOffsetHorizontal: 0,
            navOffsetVertical: 10,
            shadow: 2,
            fullWidth: "off"
        });
    });
});