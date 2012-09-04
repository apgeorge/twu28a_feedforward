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


                <div data-role="fieldcontain" style="width: 100%; padding-top : 1%;">
                    <fieldset data-role="controlgroup">
                        <select name="city" id="city" data-native-menu="false" style="width: 100%;">
                                 <option value="Bangalore">Bangalore</option>
                                 <option value="Chennai">Chennai</option>
                                 <option value="Gurgaon">Gurgaon</option>
                                 <option value="Pune">Pune</option>
                        </select>
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
                      var array = new Array();

                      $('#title').val($.trim($('#title').val()));
                      $('#venue').val($.trim($('#venue').val()));

                      if($('#title').val() == '')
                            array['title'] = 'true';
                      if(!date_pattern.test($('#datepicker').val()))
                            array['datepicker'] = 'true';
                      if(!time_pattern.test($('#timepicker').val()))
                            array['timepicker'] = 'true';
                      if($('#venue').val() == '')
                            array['venue'] = 'true';

                      return array;
                }

                function resetAll(){
                    $('#title').css('-moz-box-shadow',' none');
                    $('#title').css('-webkit-box-shadow', 'none');
                    $('#title').css('box-shadow',' none');

                    $('#datepicker').css('-moz-box-shadow',' none');
                    $('#datepicker').css('-webkit-box-shadow', 'none');
                    $('#datepicker').css('box-shadow',' none');

                    $('#timepicker').css('-moz-box-shadow',' none');
                    $('#timepicker').css('-webkit-box-shadow', 'none');
                    $('#timepicker').css('box-shadow',' none');

                    $('#venue').css('-moz-box-shadow',' none');
                    $('#venue').css('-webkit-box-shadow', 'none');
                    $('#venue').css('box-shadow',' none');
                }





                $('#new_talk_submit').ready(function(){
                 $('#new_talk_submit').click(function(){


                    var array_validation_result = validate_new_talk_form();
                    var flag_error = false;

                    resetAll();
                    for(element in array_validation_result){
                        if(array_validation_result[element] === 'true'){
                            flag_error = true;
                            $('#'+element).css('-moz-box-shadow', '0 0 12px red');
                            $('#'+element).css('-webkit-box-shadow', '0 0 12px red');
                            $('#'+element).css('box-shadow', '0 0 12px red');
                        }
                    }

                    if(flag_error)
                            return false;

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
                                           $('#message_box_error').html('Cannot create talk with duplicate title');
                                        }
                              });

                 });

                 $("#datepicker").scroller({ preset: 'date' , dateOrder: 'ddmmyy', minDate: new Date() , dateFormat: 'dd/mm/yyyy'});
                 $("#timepicker").scroller({ preset: 'time' });
                });

            </script>
