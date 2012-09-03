<!-- New Talk -->
    <div style="font-weight: bold;
                color: red;
                text-align: center;">
    	<p id="message_box_error"><p>
    </div>
                <div id = "new_talk_container"  style=" padding: 5% 10% 10% 10%;">
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="title">
                        </label>
                        <input name="title" id="title" placeholder="Title *" maxlength="50"  value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="description">
                        </label>
                        <input name="description" id="description" maxlength="500" placeholder="Description" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain"style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="datepicker">
                        </label>
                        <input name="date" id="datepicker" placeholder="Date *" maxlength="50" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                 <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="timepicker">
                        </label>
                        <input name="time" id="timepicker" placeholder="Time *" maxlength="50" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div data-role="fieldcontain" style="width: 100%;">
                    <fieldset data-role="controlgroup">
                        <label for="venue">
                        </label>
                        <input name="venue" id="venue" placeholder="Venue *" maxlength="50" value="" type="text" style="width: 100%;">
                    </fieldset>
                </div>
                <div style="margin-top: 10%;">
                    <input type="button" id="new_talk_submit"  data-theme="b" value="Submit" data-mini="false">
                </div>
              </div>

            <script>

                function validate_new_talk_form(){
                      var date_pattern=new RegExp("[0-3][0-9]\/[0-1][0-9]\/[0-9]{4}");
                      var time_pattern=new RegExp("[0-1][0-9]:[0-6][0-9] ((AM)|(PM))");
                      if($('#title').val() == '') return 'title';
                      if(!date_pattern.test($('#datepicker').val()))  return 'date';
                      if(!time_pattern.test($('#timepicker').val()))  return 'time';
                      if($('#venue').val() == '')  return 'venue';

                      return 'valid';
                }

                function resetTextBox(element){
                    element.css('-moz-box-shadow',' none');
                    element.css('-webkit-box-shadow', 'none');
                    element.css('box-shadow',' none');
                }



                $('#new_talk_submit').ready(function(){
                 $('#new_talk_submit').click(function(){

                    switch(validate_new_talk_form())
                    {
                        case "title":   $('#title').css('-moz-box-shadow', '0 0 12px red');
                                        $('#title').css('-webkit-box-shadow', '0 0 12px red');
                                        $('#title').css('box-shadow', '0 0 12px red');
                                        return false;
                                        break;

                        case 'date':
                                        resetTextBox($('#title'));
                                        $('#datepicker').css('-moz-box-shadow', '0 0 12px red');
                                        $('#datepicker').css('-webkit-box-shadow', '0 0 12px red');
                                        $('#datepicker').css('box-shadow', '0 0 12px red');
                                        return false;
                                        break;
                        case 'time':    resetTextBox($('#title'));
                                        resetTextBox($('#datepicker'));
                                        $('#timepicker').css('-moz-box-shadow', '0 0 12px red');
                                        $('#timepicker').css('-webkit-box-shadow', '0 0 12px red');
                                        $('#timepicker').css('box-shadow', '0 0 12px red');
                                        return false;
                                        break;
                        case "venue":   resetTextBox($('#title'));
                                        resetTextBox($('#datepicker'));
                                        resetTextBox($('#timepicker'));
                                        $('#venue').css('-moz-box-shadow', '0 0 12px red');
                                        $('#venue').css('-webkit-box-shadow', '0 0 12px red');
                                        $('#venue').css('box-shadow', '0 0 12px red');
                                        return false;
                                        break;
                        case 'valid': break;
                    }





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

                 $("#datepicker").scroller({ preset: 'date' , dateOrder: 'ddmmyy', minDate: new Date() , dateFormat: 'dd/mm/yyyy'});
                 $("#timepicker").scroller({ preset: 'time' });
                });

            </script>
