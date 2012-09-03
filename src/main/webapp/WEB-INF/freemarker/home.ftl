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

        <script type="text/javascript" src="static/js/mobiscroll-2.0.2.full.min.js"></script>
        <script type="text/javascript" src="static/js/home.js"></script>
        <link rel="stylesheet" href="static/css/mobiscroll-2.0.2.full.min.css" />
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
            <div class="row-fluid">
                <div class="span12" data-theme="d" data-role="header">
                    <div style="textalign: center; height: 25px" class="row-fluid">

                        <h2 style="text-align:center; padding-left: 2%; font-size: 18px;">
                            Welcome ${username}!
                            <a href="http://castest.thoughtworks.com/cas/logout" class="ui-link" id="logout" style="float: right; padding-right: 2%; font-size: 15px;">
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
                                    <a  id="upcoming_talks_button" data-theme="" data-icon="">
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
                </div>
            </div>
      <div id="data_container">

    </div>
</div>

</body>

</html>
