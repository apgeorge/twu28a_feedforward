var feedback_button_fn = function (){
$('a[role="talk"]').click(function(){
    $.mobile.showPageLoadingMsg();
    $.ajax({
        method: "GET",
        url: "talk_details.html?talk_id=0",
        cache: false,
        dataType: "html",
        async: true
    }).done(function(data){
         $('#data_container').html(data).trigger('create');

            $.ajax({
                method: "GET",
                url: "add_feedback.html?talk_id=0",
                cache: false,
                dataType: "html",
                async: true
              }).done(function(data){
                      $('#feedback_container').html(data).trigger('create');
                      $.mobile.hidePageLoadingMsg();
                    });
            });
               $.mobile.hidePageLoadingMsg();

        });
};

$(function(){
$('#talks_button').click(function(){
    $.mobile.showPageLoadingMsg();
    $.ajax({
        method: "GET",
        url: "talks.html",
        cache: false,
        dataType: "html",
        async: true
    }).done(function(data){
        $('#data_container').html(data);
        $('#data_container').html(data).trigger('create');
        feedback_button_fn();
    });
    $.mobile.hidePageLoadingMsg();
});

    $('#talks_button').click();

$('#upcoming_talks_button').click(function(){
      $.mobile.showPageLoadingMsg();
      $.ajax({
          method: "GET",
          url: "upcoming_talks.html",
          cache: false,
          dataType: "html",
          async: true
      }).done(function(data){
              $('#data_container').html(data);
              $('#data_container').html(data).trigger('create');
              feedback_button_fn();
              });
        $.mobile.hidePageLoadingMsg();
  });


$('#my_talks_button').bind("click",  function(event, message){
      $.mobile.showPageLoadingMsg();
        $.ajax({
            method: "GET",
            url: "talk_tab.html",
            cache: false,
            dataType: "html",
            async: true
        }).done(function(data){
                $('#data_container').html(data).trigger('create');
                $('#message_box_success').html(message);
                feedback_button_fn();

$('#new_talk').ready( function() {
        $('#new_talk').click(function(){
            $.mobile.showPageLoadingMsg();
                $.ajax({
                    method: "GET",
                    url: "new_talk.html",
                    cache: false,
                    dataType: "html",
                    async: true
                }).done(function(data){
                     $('#data_container').html(data).trigger('create');
                     feedback_button_fn();
                     $.mobile.hidePageLoadingMsg();
                    });

        });

        $.mobile.hidePageLoadingMsg();

});

$.ajax({
    method: "GET",
    url: "my_talks.html",
    cache: false,
    dataType: "html",
    async: true
    }).done(function(data){
        $('#talk_container').html(data);
        $('#data_container').trigger('create');
        feedback_button_fn();
        $.mobile.hidePageLoadingMsg();
    });

     $.mobile.hidePageLoadingMsg();

    });

  $.mobile.hidePageLoadingMsg();


    });

});