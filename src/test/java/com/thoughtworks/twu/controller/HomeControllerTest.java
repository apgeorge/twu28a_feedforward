package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {
    @Test
    public void shouldRedirectToHomePage() {
        HomeController homeController = new HomeController();

        ModelAndView modelAndView = homeController.homepage();

        assertThat(modelAndView.getViewName(), is("redirect:home.html"));
    }
}
