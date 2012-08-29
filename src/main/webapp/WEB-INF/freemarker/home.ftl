    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://raw.github.com/commadelimited/jQuery-Mobile-Bootstrap-Theme/master/themes/Bootstrap.css"
        />
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap-responsive.css">
        <title>
        </title>
        <link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/jquery.mobile/1.1.1/jquery.mobile-1.1.1.min.css"
        />
        <link rel="stylesheet" href="static/css/my.css" />
        <link rel="stylesheet" href="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.min.css" />

        <style>
        /* App custom styles */
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js">
        </script>
        <script src="https://ajax.aspnetcdn.com/ajax/jquery.mobile/1.1.1/jquery.mobile-1.1.1.min.js">
        </script>
        <script src="static/js/my.js">
        </script>


        <script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.core.min.js"></script>
        <script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.calbox.min.js"></script>
        <script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/i18n/jquery.mobile.datebox.i18n.en_US.utf8.js"></script>
        <script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/1.1.0/jqm-datebox-1.1.0.mode.slidebox.js"></script>
        <script type="text/javascript" src="http://dev.jtsage.com/cdn/datebox/latest/jqm-datebox.mode.flipbox.min.js"></script>


    </head>

        <body lang="en">
        <!-- Home -->
        <div data-role="page" id="page3">
            <div class="row-fluid">
                <div class="span12" data-theme="d" data-role="header">
                    <div style="textalign: center; height: 50px" class="row-fluid">

                        <h2 style="text-align:center; padding-left: 2%;">
                            Welcome Goku!
                            <a href="#" class="ui-link" style="float: right; padding-right: 2%; font-size: 16px;">
                                Logout
                            </a>
                        </h2>

                    </div>
                    <div class="row-fluid">

                        <div class="span12" data-role="navbar" data-iconpos="top">
                            <ul>
                                <li>
                                    <a  id="talks_button" data-theme="" data-icon="" class="ui-btn-active ui-state-persist">
                                        Recent Talks
                                    </a>
                                </li>
                                <li>
                                    <a  id="my_talks_button" data-theme="" data-icon="">
                                        My Talks
                                    </a>
                                </li>
                                <li>
                                    <a  id="upcoming_talks_button" data-theme="" data-icon="">
                                        Upcoming Talks
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
      <div id="data_container"> 
        
        </div>
        </div>
       
        
        
        <script>


            var feedback_button_fn = function (){
                $('a[role="talk"]').click(function(){
                    $.mobile.showPageLoadingMsg();
                    $.ajax({
                        method: "GET",
                        url: "add_feedback.html",
                        cache: false,
                        dataType: "html",
                        async: true
                    })
                            .done(function(data){
                                $('#data_container').html(data).trigger('create');
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


                  $('#my_talks_button').click(function(){

                       $('my_talks_button').click();

                      $.mobile.showPageLoadingMsg();
                    $.ajax({
                        method: "GET",
                        url: "talk_tab.html",
                        cache: false,
                        dataType: "html",
                        async: true
                    })
                            .done(function(data){
                                $('#data_container').html(data);
                                feedback_button_fn();
                                $('#data_container').trigger('create');

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
                                                $('#talk_tab').empty();
                                                $('#talk_container').html(data).trigger('create');


                                            });
                                    $.mobile.hidePageLoadingMsg();
                                });



                            });


                    $.ajax({
                        method: "GET",
                        url: "my_talks.html",
                        cache: false,
                        dataType: "html",
                        async: true
                    })
                            .done(function(data){
                                $('#talk_container').html(data);
                                feedback_button_fn();
                                $('#data_container').trigger('create');
                            });

                      $.mobile.hidePageLoadingMsg();


                });








              });
        </script>
    </body>

</html>
