
<#escape x as x?html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Feed Forward App</title>
    <link rel="stylesheet" href="static/css/jquery.mobile-1.1.1.min.css"/>
    <link rel="stylesheet" href="static/css/jqm-datebox.min.css"/>
    <script type="text/javascript" src="static/js/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="static/js/jquery.mobile-1.1.1.js"></script>
    <script type="text/javascript" src="static/js/mobiscroll-2.0.2.full.min.js"></script>
    <link rel="stylesheet" href="static/css/mobiscroll-2.0.2.full.min.css"/>
    <script type="text/javascript" src="static/js/home.js"></script>
    <script type="text/javascript" src="static/js/jquery.ba-hashchange.js"></script>
    <style>
        .dw {font-size: 15px;}
        .compulsory {color: red;font-weight: bold;}
    </style>
</head>
<body lang="en">
<div data-role="page" id="page3">
    <div data-theme="d" data-role="header">
            <div style="textalign: center; height: 25px">

                        <h2 style="text-align:center; padding-left: 2%; font-size: 18px;">
                            Welcome ${username}!
                            <a id="logout" href="logout" data-ajax="false" data-role="external" data-theme="b" id="logout"
                               style="float: right; padding-right: 2%; font-size: 15px;">Logout
                            </a>
                        </h2>
            </div>
    </div>
        <div data-role="navbar" data-iconpos="top">
            <ul>
                <li>
                    <a id="talks_button" data-theme="" data-icon="chat" class="ui-btn-active ui-state-persist">
                        Recent Talks
                    </a>
                </li>
                <li>
                    <a id="upcoming_talks_button"  data-theme=""  data-icon="calendar">
                        Upcoming Talks
                    </a>
                </li>
                <li>
                    <a id="my_talks_button" data-theme="" data-icon="person">
                        My Talks
                    </a>
                </li>

            </ul>
        </div>
    <div id="data_container">
    </div>
</div>
</#escape>
</body>
</html>