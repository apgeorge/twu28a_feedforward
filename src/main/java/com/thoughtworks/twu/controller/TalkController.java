package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
import com.thoughtworks.twu.utils.DateParser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TalkController {
    private TalkService talkService;

    @Autowired
    public TalkController(TalkService talkService) {
        this.talkService = talkService;
    }


    @RequestMapping(value = "/talk_details.htm*", method = RequestMethod.GET)
    public ModelAndView getTalkDetails(HttpServletRequest request, @RequestParam(value = "talk_id", defaultValue = "-1") int talkId) {
        Talk talk = talkService.getTalk(talkId);
        ModelAndView modelAndView = new ModelAndView("talk_details");
        modelAndView.addObject("talk", talk);
        if(talk.isMyTalk(request.getUserPrincipal().getName()))
            modelAndView.addObject("isEditable","true");

        if(talkService.isUpcomingTalk(talk)){
         return modelAndView.addObject("isUpcoming","isAnUpcomingTalk");
        }
        return  modelAndView.addObject("isUpcoming","isNotAnUpcomingTalk");

    }

    @RequestMapping(value = "/talks.htm*", method = RequestMethod.GET)
    public ModelAndView getRecentTalksPage() {
        ModelAndView modelAndView = new ModelAndView("talks");
        List<Talk> recentTalks = talkService.getRecentTalks();
        modelAndView.addObject("talksList", recentTalks);
        return modelAndView;
    }

    @RequestMapping(value = "/upcoming_talks.htm*", method = RequestMethod.GET)
    public ModelAndView getUpcomingTalks() {
        List<Talk> upcomingTalks = talkService.getUpcomingTalks();

        ModelAndView modelAndView = new ModelAndView("talks");
        modelAndView.addObject("talksList", upcomingTalks);
        return modelAndView;
    }

    @RequestMapping(value = "/new_talk_submit.htm*", method = RequestMethod.GET)
    public ModelAndView newTalksFormSubmit(HttpServletRequest request,
                                           @RequestParam(value = "title", defaultValue = "") String title,
                                           @RequestParam(value = "description", defaultValue = "") String description,
                                           @RequestParam(value = "venue", defaultValue = "") String venue,
                                           @RequestParam(value = "date", defaultValue = "") String date,
                                           @RequestParam(value = "time", defaultValue = "") String time) {
        ModelAndView modelAndView = new ModelAndView("message");
        int resultOfInsertion;
        if (!talkService.validate(title, venue, date, time)) {
            return addFailureMessageToModelAndView(modelAndView);
        }
        Presentation presentation = new Presentation(title, description, request.getUserPrincipal().getName().toLowerCase());
        try {

            resultOfInsertion = talkService.createTalkWithNewPresentation(presentation, venue, date, time);
        } catch (Exception e) {
            return addFailureMessageToModelAndView(modelAndView);
        }
        if (resultOfInsertion == 0)
            return addFailureMessageToModelAndView(modelAndView);

        modelAndView.addObject("status", "true");
        return modelAndView;

    }

    private ModelAndView addFailureMessageToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("status", "false");
        return modelAndView;
    }

    @RequestMapping(value = "/my_talks.htm*", method = RequestMethod.GET)
    public ModelAndView getMyTalksPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("my_talks");
        String user = request.getUserPrincipal().getName();


        modelAndView.addObject("myTalksList", talkService.getMyTalks(user));

        return modelAndView;
    }

    @RequestMapping(value = "/new_talk.htm*", method = RequestMethod.GET)
    public ModelAndView getNewTalkPage() {
        return new ModelAndView("new_talk");
    }


    @RequestMapping(value = "/talk_tab.htm*", method = RequestMethod.GET)
    public ModelAndView getTalkTabPage() {
        return new ModelAndView("talk_tab");
    }

    @RequestMapping(value = "/edit_talk.htm*", method = RequestMethod.POST)
    public ModelAndView editTalkDetails(HttpServletRequest request,
                                        @RequestParam(value = "type", defaultValue = "") String type,
                                        @RequestParam(value = "talk_id", defaultValue = "-1") int talkId,
                                        @RequestParam(value = "update_value", defaultValue = "") String updateValue) {
        Talk talk = talkService.getTalk(talkId);
        if(!talk.isMyTalk(request.getUserPrincipal().getName()))
            return null;
        if (type.equals("description")) {
            talkService.editTalkDescription(talkId, updateValue);
        } else if(type.equals("venue")){
            talkService.editTalkVenue(talkId, updateValue);
        } else if(type.equals("date")){
            DateTime newdate = new DateParser(updateValue,"00:00 AM").convertToDateTime();
            newdate = talk.getDateTime().withDate( newdate.getYear(), newdate.getMonthOfYear(), newdate.getDayOfMonth());
            talkService.editTalkDateTime(talkId, newdate);
        } else if(type.equals("time")){
            DateTime newdate = new DateParser("01/10/2001",updateValue).convertToDateTime();
            newdate = talk.getDateTime().withTime(newdate.getHourOfDay(), newdate.getMinuteOfHour(), newdate.getSecondOfMinute(), newdate.getMillisOfSecond());
            talkService.editTalkDateTime(talkId, newdate);
        } else
            return null;

        ModelAndView message = new ModelAndView("message");
        message.addObject("status",updateValue);
        return message;
    }


    public ModelAndView deleteTalk(int talkId) {
        talkService.deleteTalk(talkId);
        int resultOfDeletion;
        ModelAndView modelAndView = new ModelAndView("message");
        resultOfDeletion = talkService.deleteTalk(talkId);
        if(resultOfDeletion == 0){
            addFailureMessageToModelAndView(modelAndView);
        }
        else{
        modelAndView.addObject("status","talkWithId"+talkId+"Deleted");
        }
        return modelAndView;

    }
}