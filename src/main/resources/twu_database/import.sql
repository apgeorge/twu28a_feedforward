insert into user (user_id, name) values (1, 'bill');

insert into presentation (id, title, time_stamp, description, owner)
                  values (1, 'title test','2005-01-02 01:02:03.12', 'description test', 'owner test');

insert into talk (talk_id, presentation_id, venue, talk_date, talk_time)
          values (1, 1, 'test venue', '01 Jan 2011', '2pm');

insert into feedback (feedback_id, feedback_comment, talk_id, attendee, attendee_mail, time_at_creation)
              values (1, 'feedback comment test', 1, 'Attendee Name', 'attendee_mail@example.com', '2012-04-12 13:23:34.45');

insert into feedback (feedback_id, feedback_comment, talk_id, attendee, attendee_mail, time_at_creation)
              values (2, 'feedback comment test 2', 1, 'Attendee Name', 'attendee_mail@example.com', '2012-04-12 13:23:34.45');

insert into feedback (feedback_id, feedback_comment, talk_id, attendee, attendee_mail, time_at_creation)
              values (3, 'feedback comment test 3', 1, 'Attendee Name', 'attendee_mail@example.com', '2012-04-12 13:23:34.45');


