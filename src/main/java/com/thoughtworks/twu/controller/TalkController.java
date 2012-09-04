package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.domain.Talk;
import com.thoughtworks.twu.service.TalkService;
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
    public ModelAndView getTalk(@RequestParam(value = "talk_id", defaultValue = "-1") int talkId) {
        Talk talk = talkService.getTalk(talkId);
        ModelAndView modelAndView = new ModelAndView("talk_details");
        modelAndView.addObject("talk",talk);
        return modelAndView;
    }

    @RequestMapping(value = "/talks.htm*", method = RequestMethod.GET)
    public ModelAndView getRecentTalksPage()
    {
        ModelAndView modelAndView = new ModelAndView("talks");
        List<Talk> listOfRecentTalks = talkService.getListOfRecentTalks();

        modelAndView.addObject("talksList",listOfRecentTalks);
        return modelAndView;
    }

    @RequestMapping(value = "/home.htm*", method = RequestMethod.GET)
    public ModelAndView getHomePage(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("home");
        String username = httpServletRequest.getUserPrincipal().getName();

        modelAndView.addObject("username", username);
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
        if(!talkService.validate(title,venue,date,time)){
            return addFailureMessageToModelAndView(modelAndView);
        }
        Presentation presentation = new Presentation(title,description,request.getUserPrincipal().getName());
        try{

            resultOfInsertion = talkService.createTalkWithNewPresentation(presentation, venue, date, time);
        }
        catch(Exception e){
            return  addFailureMessageToModelAndView(modelAndView);
        }
        if(resultOfInsertion == 0 )
            return  addFailureMessageToModelAndView(modelAndView);

        modelAndView.addObject("status","true") ;
        return modelAndView;

    }

    private ModelAndView addFailureMessageToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("status", "false");
        return modelAndView;
    }

    @RequestMapping(value = "/my_talks.htm*", method = RequestMethod.GET)
    public ModelAndView getMyTalksPage(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("my_talks");
        String user=request.getUserPrincipal().getName();


        modelAndView.addObject("myTalksList",talkService.getListOfMyTalks(user));

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


}