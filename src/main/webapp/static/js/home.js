var display_message;
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
    timeout:7000,
    method: "GET"
});
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        options.beforeSend = function () {
            $.mobile.showPageLoadingMsg()
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
            if ($.isFunction(originalOptions.success))
                originalOptions.success();
        };
        options.error = function(jqXHR, textStatus){
            popup_ajax_error_box(textStatus);
            if ($.isFunction(originalOptions.complete))
                originalOptions.complete();
        };
});
var ajax_call = function(settings, done_callback){
   $.ajax(settings).done(done_callback);
};

var feedback_button_fn = function () {
    $('a[role="talk"]').click(function () {
        window.location.hash="id_"+this.id;
    });
};

function reset_all_talk_tabs(){
    $('#talks_button').removeClass('ui-btn-active ui-state-persist');
    $('#upcoming_talks_button').removeClass('ui-btn-active ui-state-persist');
    $('#my_talks_button').removeClass('ui-btn-active ui-state-persist');

}

$(function(){

    $(window).hashchange(function(){

        var hash=location.hash;
        hash=(hash.replace(/^#/,''));
        var talk_details_page_hash = "id_"+hash.substring(3);

        switch(hash)
        {
            case 'recent_talks':
                                reset_all_talk_tabs();
                                $('#talks_button').addClass('ui-btn-active ui-state-persist');
                                ajax_call({url:"talks.html"},
                                function (data) {
                                    $('#data_container').html(data).trigger('create');
                                });
                                break;

            case 'upcoming_talks':
                                 reset_all_talk_tabs();
                                 $('#upcoming_talks_button').addClass('ui-btn-active ui-state-persist');
                                 ajax_call({url:"upcoming_talks.html"},
                                 function (data) {
                                    $('#data_container').html(data).trigger('create');
                                 });
                                 break;

            case 'my_talks':
                                 reset_all_talk_tabs();
                                 $('#my_talks_button').addClass('ui-btn-active ui-state-persist');
                                 ajax_call({url:"talk_tab.html"},
                                 function (data) {
                                         $('#data_container').html(data).trigger('create');
                                         $('#message_box_success').html(display_message);
                                         $('#new_talk').ready(function () {
                                             $('#new_talk').click(function () {
                                             window.location.hash="new_talk";
                                             });
                                         });
                                         ajax_call({url:"my_talks.html"},
                                                   function (data) {
                                                     $('#talk_container').html(data);
                                                     $('#data_container').trigger('create');
                                                   });
                                     });
                                 break;
            case 'new_talk':
                                reset_all_talk_tabs();
                                 $('#my_talks_button').addClass('ui-btn-active ui-state-persist');
                                 ajax_call({url:"new_talk.html"},
                                        function (data) {
                                          $('#data_container').html(data).trigger('create');
                                        });
                                 break;

            case talk_details_page_hash:
                                 reset_all_talk_tabs();
                                 var current_talk_id=hash.substring(3);
                                 ajax_call({url:"talk_details.html?talk_id=" + current_talk_id},
                                   function (data) {
                                     $('#data_container').html(data).trigger('create');
                                         if(data.indexOf("isNotAnUpcomingTalk") != -1){
                                             ajax_call({url:"add_feedback.html?talk_id=" + current_talk_id},
                                               function (data) {
                                                     $('#feedback_container').html(data).trigger('create');
                                               });
                                             }
                                   });
                                 break;
            default:
                                 reset_all_talk_tabs();
                                 $('#talks_button').addClass('ui-btn-active ui-state-persist');
                                 ajax_call({url:"talks.html"},
                                 function (data) {
                                     $('#data_container').html(data).trigger('create');
                                 });
                                 break;
        }
    })

    $(window).hashchange();

});

$(function () {


    $('#talks_button').click(function () {
        window.location.hash='recent_talks';
    });


    $('#upcoming_talks_button').click(function () {
    window.location.hash='upcoming_talks';
    });

    $('#my_talks_button').bind("click", function (event, message) {
    display_message=message;
    window.location.hash="my_talks";
    });

});