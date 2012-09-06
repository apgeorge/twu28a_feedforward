<#escape x as x?html>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Feed Forward App</title>
    <link rel="stylesheet" href="static/css/jquery.mobile-1.1.1.min.css"/>
    <link rel="stylesheet" href="static/css/jqm-datebox.min.css" />
    <script src="static/js/jquery.min.js"></script>
    <script src="static/js/jquery.mobile-1.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/mobiscroll-2.0.2.full.min.js"></script>
    <link rel="stylesheet" href="static/css/mobiscroll-2.0.2.full.min.css"/>
    <style >
        .dw{
            font-size: 15px;
        }
        .compulsory{
            color: red;
            font-weight: bold;
        }
    </style>
</head>

<body lang="en">
<!--- Home --->
<div data-role="page" id="page3">

        <div  data-theme="d" data-role="header">
            <div style="textalign: center; height: 25px" >

                <h2 style="text-align:center; padding-left: 2%; font-size: 18px;">
                    Welcome ${username}!
                    <a id="logout" href="logout" data-ajax="false" class="ui-link" id="logout" style="float: right; padding-right: 2%; font-size: 15px;">
                        Logout
                    </a>
                </h2>

            </div>


                <div data-role="navbar" data-iconpos="top">
                    <ul>
                        <li>
                            <a  id="talks_button" data-theme="" data-icon="" class="ui-btn-active ui-state-persist">
                                Recent Talks
                            </a>
                        </li>
                        <li>
                            <a  id="upcoming_talks_button" data-theme="" class="ui-disabled" data-icon="">
                                Upcoming Talks
                            </a>
                        </li>
                        <li>
                            <a  id="my_talks_button" data-theme="" data-icon="">
                                My Talks
                            </a>
                        </li>

                    </ul>
                </div>

        </div>

    <div id="data_container">

    </div>
</div>

</#escape>

<script>


    var current_talk_id = -1;
    var feedback_button_fn = function (){
        $('a[role="talk"]').click(function(){
            $.mobile.showPageLoadingMsg();
            current_talk_id = this.id;
            $.ajax({
                method: "GET",
                url: "talk_details.html?talk_id="+current_talk_id,
                cache: false,
                dataType: "html",
                async: true
            })
                    .done(function(data){
                        $('#data_container').html(data).trigger('create');


                        $.ajax({
                            method: "GET",
                            url: "add_feedback.html?talk_id="+current_talk_id,
                            cache: false,
                            dataType: "html",
                            async: true
                        })
                                .done(function(data){
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
            })
                    .done(function(data){
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
            })
                    .done(function(data){
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
            })
                    .done(function(data){
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
                                })
                                        .done(function(data){
                                            $('#data_container').html(data).trigger('create');
                                            feedback_button_fn();
                                            $.mobile.hidePageLoadingMsg();
                                        });

                            });

                            $.mobile.hidePageLoadingMsg();

                        })  ;


                        $.ajax({
                            method: "GET",
                            url: "my_talks.html",
                            cache: false,
                            dataType: "html",
                            async: true
                        })
                                .done(function(data){
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
</script>
</body>

</html>