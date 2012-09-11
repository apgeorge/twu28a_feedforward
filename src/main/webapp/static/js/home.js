var current_talk_id = -1;
var popup_ajax_error_box = function(status){
    $("<div class='ui-loader ui-overlay-shadow ui-body-e ui-corner-all'><strong>Error loading the page. Try again.</strong></div>")
        .css({  "padding":"20px", "border-width": "2px","border-color":"grey", "display":"block", "opacity":0.96, "top": $(window).scrollTop() + 150 })
        .appendTo( $("body") )
        .delay( 5000 )
        .fadeOut( 800, function(){
            $(this).remove();
        });
};
$.ajaxSetup({
    async : 'true',
    cache : 'false',
    dataType :"html",
    successThreshold : '3000',
    timeout:7000
});
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        options.beforeSend = function () {
            $.mobile.showPageLoadingMsg();
            if ($.isFunction(originalOptions.beforeSend))
                originalOptions.beforeSend();
        };
        options.complete = function () {
            feedback_button_fn();
            $.mobile.hidePageLoadingMsg();
            if ($.isFunction(originalOptions.complete))
                originalOptions.complete();
        };
        options.success = function () {
            feedback_button_fn();
            if ($.isFunction(originalOptions.success))
                originalOptions.success();
            feedback_button_fn();
        };
        options.error = function(jqXHR, textStatus){
            popup_ajax_error_box(textStatus);
            if ($.isFunction(originalOptions.complete))
                originalOptions.complete();
        };
});
var feedback_button_fn = function () {
    $('a[role="talk"]').click(function () {
        current_talk_id = this.id;
        $.ajax({
            method:"GET",
            url:"talk_details.html?talk_id=" + current_talk_id
        })
            .done(function (data) {
                $('#data_container').html(data).trigger('create');
                if(data.indexOf("isNotAnUpcomingTalk") != -1){
                    $.ajax({
                        method:"GET",
                        url:"add_feedback.html?talk_id=" + current_talk_id
                    })
                        .done(function (data) {
                            $('#feedback_container').html(data).trigger('create');
                        });
                }
            });
    });
};



$(function () {
    function callback(hash)
    {
        if(hash=="" || hash=="talks_button")
        {
            $('#upcoming_talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#talks_button').addClass("ui-btn-active ui-state-persist");
            $('#my_talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#talks_button').load();
        }
        if(hash=="upcoming_talks_button"){
            $('#upcoming_talks_button').addClass("ui-btn-active ui-state-persist");
            $('#talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#my_talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#upcoming_talks_button').load();

        }
        if(hash=="my_talks_button"){
            $('#upcoming_talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#talks_button').removeClass("ui-btn-active ui-state-persist");
            $('#my_talks_button').addClass("ui-btn-active ui-state-persist");
            $('#my_talks_button').load();
        }


        $('#talks_button').load(function () {
            $.ajax({
                method:"GET",
                url:"talks.html"
            })
                .done(function (data) {
                    $('#data_container').html(data);
                    $('#data_container').html(data).trigger('create');
                });
        });


        $('#upcoming_talks_button').load(function () {
            $.ajax({
                method:"GET",
                url:"upcoming_talks.html"
            })
                .done(function (data) {

                    $('#data_container').html(data);
                    $('#data_container').html(data).trigger('create');
                });
        });



        $('#my_talks_button').bind("load", function (event, message) {
            $.mobile.showPageLoadingMsg();
            $.ajax({
                method:"GET",
                url:"talk_tab.html"
            })
                .done(function (data) {
                    $('#data_container').html(data).trigger('create');
                    $('#message_box_success').html(message);
                    $('#new_talk').ready(function () {
                        $('#new_talk').click(function () {
                            $.mobile.showPageLoadingMsg();
                            $.ajax({
                                method:"GET",
                                url:"new_talk.html"
                            })
                                .done(function (data) {
                                    $('#data_container').html(data).trigger('create');
                                });
                        });
                    });
                    $.ajax({
                        method:"GET",
                        url:"my_talks.html"
                    })
                        .done(function (data) {
                            $('#talk_container').html(data);
                            $('#data_container').trigger('create');
                        });
                });

        });

    }
    $(document).ready(function() {
        $.history.init(callback);
        $('#talks_button').load();
        $("a").click(function(){
            var url = $(this).attr('id');
            url = url.replace(/^.*#/, '');
            $.history.load(url);
            return false;
        });
    });

});


