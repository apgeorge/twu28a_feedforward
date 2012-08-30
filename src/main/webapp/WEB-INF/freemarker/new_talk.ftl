<!-- New Talk -->
                <div id = "new_talk_container">
                <div data-role="fieldcontain" style="width: 100%;padding: 1%;">
                    <fieldset data-role="controlgroup">
                        <label for="title">
                        </label>
                        <input name="title" id="title" placeholder="title" value="" type="text">
                    </fieldset>
                </div>
                <div data-role="fieldcontain"style="width: 100%;padding: 1%;">
                    <fieldset data-role="controlgroup">
                        <label for="description">
                        </label>
                        <input name="description" id="description" placeholder="details" value="" type="text">
                    </fieldset>
                </div>
                <div data-role="fieldcontain"style="width: 100%;padding: 1%;">
                    <fieldset data-role="controlgroup">
                        <label for="datepicker">
                        </label>
                        <input name="date" id="datepicker" placeholder="date" value="" type="text" data-role="datebox" data-options='{"mode":"slidebox", "useNewStyle":true}'>
                    </fieldset>
                </div>
                 <div data-role="fieldcontain" style="width: 100%;padding: 1%;">
                    <fieldset data-role="controlgroup">
                        <label for="timepicker">
                        </label>
                        <input name="time" id="timepicker" placeholder="time" value="" type="text" data-role="datebox" data-options='{"mode":"timeflipbox", "useNewStyle":true}'>
                    </fieldset>
                </div>
                <div data-role="fieldcontain" style="width: 100%;padding: 1%;">
                    <fieldset data-role="controlgroup">
                        <label for="venue">
                        </label>
                        <input name="venue" id="venue" placeholder="where" value="" type="text">
                    </fieldset>
                </div>
                <div style="margin-left: 13%; margin-right: 50%;">
                    <input type="submit" id="new_talk"  data-theme="b" value="Submit" data-mini="true">
                </div>
              </div>

            <script>
                $('#new_talk_container').ready(function(){
                 $('#new_talk').click(function(){
                 var url = "new_talk.html"+"?title="+$('#title').val()+
                           "&description="+$('#description').val()+
                           "&venue="+$('#venue').val()+
                           "&date="+ $('#datepicker').val()+
                           "&time="+$('#timepicker').val();
                    $.ajax({
                               method: "GET",
                               url: url,
                               cache: false,
                               dataType: "html",
                               async: true
                               })
                         .done(function(data){
                              $('#data_container').html(data).trigger('create');
                                $.ajax({
                                              method: "GET",
                                              url: "my_talks.html",
                                              cache: false,
                                              dataType: "html",
                                               async: true
                                            }).done(function(data){
                                            $('#talk_container').html(data).trigger('create');
                                            })

                                   });

                 });
                });

            </script>
