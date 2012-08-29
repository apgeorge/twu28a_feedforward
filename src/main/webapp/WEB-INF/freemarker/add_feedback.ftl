 <!-- A presentation -->
        <div data-role="page" id="page5">
            <div data-theme="b" data-role="header">
                <h1>
                    Welcome Goku!
                </h1>
                <div data-role="navbar" data-iconpos="top">
                    <ul>
                        <li>
                            <a href="#page3" data-theme="" data-icon="" class="ui-btn-active ui-state-persist">
                                Talks
                            </a>
                        </li>
                        <li>
                            <a href="#page7" data-theme="" data-icon="">
                                My Talks
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div data-role="content" style="padding: 15px">
                <div data-role="collapsible-set" data-theme="" data-content-theme="">
                    <div data-role="collapsible" data-collapsed="true">
                        <h3>
                            Event name (details)
                        </h3>
                        <div>
                            <p>
                                <b>
                                    title...
                                </b>
                            </p>
                            <p>
                                <b>
                                    who...
                                </b>
                            </p>
                            <p>
                                <b>
                                    when...
                                </b>
                            </p>
                            <p>
                                <b>
                                    where...
                                </b>
                            </p>
                        </div>
                    </div>
                </div>
                <div data-role="fieldcontain">
                    <fieldset data-role="controlgroup">
                        <label for="textinput9">
                        </label>
                        <input name="feedback" id="textinput9" placeholder="add feedback" value=""
                        type="text">
                    </fieldset>
                </div>
                <div data-role="navbar" data-iconpos="top">
                    <ul>
                        <li>
                            <a href="#page5" data-theme="" data-icon="">
                                Like
                            </a>
                        </li>
                        <li>
                            <a href="#page5" data-theme="" data-icon="">
                                Dislike
                            </a>
                        </li>
                        <li>
                            <a href="#page5" data-theme="" data-icon="">
                                Ideas
                            </a>
                        </li>
                        <li>
                            <a href="#page5" data-theme="" data-icon="">
                                Appreciation
                            </a>
                        </li>
                    </ul>
                </div>
                <input type="submit" data-inline="true" data-theme="b" value="Submit"
                data-mini="true">
                <div data-role="collapsible-set" data-theme="" data-content-theme="">
                    <div data-role="collapsible" data-collapsed="false">
                        <h3>
                            Past Feedback
                        </h3>
                        <ul data-role="listview" data-divider-theme="b" data-inset="true">
                            <li data-role="list-divider" role="heading">
                                Like
                            </li>
                            <li data-theme="c">
                                <a href="#page3" data-transition="slide">
                                    I like this presentation because it is awesome
                                </a>
                            </li>
                            <li data-role="list-divider" role="heading">
                                Dislike
                            </li>
                            <li data-theme="c">
                                <a href="#page3" data-transition="slide">
                                    The presentation needs more pictures
                                </a>
                            </li>
                            <li data-role="list-divider" role="heading">
                                Ideas
                            </li>
                            <li data-role="list-divider" role="heading">
                                Feedback
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>