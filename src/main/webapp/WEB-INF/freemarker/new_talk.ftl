<!-- New Talk -->
    <div style="font-weight: bold;
                color: red;
                text-align: center;">
    	<p id="message_box_error"><p>
    </div>
                <div id = "new_talk_container"  style=" padding:  10%;">
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="title">
                        </label>
                        <input name="title" id="title" placeholder="title" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="description">
                        </label>
                        <input name="description" id="description" placeholder="details" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain"style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="datepicker">
                        </label>
                        <input name="date" id="datepicker" placeholder="date" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                 <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="timepicker">
                        </label>
                        <input name="time" id="timepicker" placeholder="time" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="venue">
                        </label>
                        <input name="venue" id="venue" placeholder="where" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div style="margin-top: 10%;">
                    <input type="button" id="new_talk_submit"  data-theme="b" value="Submit" data-mini="true">
                </div>
              </div>

            <script>
                $('#new_talk_submit').ready(function(){
                 $('#new_talk_submit').click(function(){
                 var url = "new_talk_submit.html"+"?title="+$('#title').val()+
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

                                        if(data.indexOf("true") != -1) {
                                            $('#my_talks_button').trigger('click', ['New Talk Successfully Created']);
                                        }else{
                                           $('#message_box_error').html('Please Supply Valid Entries For All Fields');
                                        }
                              });

                 });

                 $("#datepicker").scroller({ preset: 'date' , dateOrder: 'ddmmyy', minDate: new Date()});
                 $("#timepicker").scroller({ preset: 'time' });
                });

            </script>
