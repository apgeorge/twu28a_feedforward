package com.thoughtworks.twu.controller;

import com.sun.security.auth.UserPrincipal;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomeControllerTest {

    private HomeController homeController;

    @Before
    public void setUp() throws Exception {
        homeController = new HomeController();
    }

    @Test
    public void shouldRedirectToHomePage() {

        ModelAndView modelAndView = homeController.homepage();

        assertThat(modelAndView.getViewName(), is("redirect:home.html"));
    }


    @Test
    public void shouldLoadHomePageWhenAtHome() throws Exception {
        UserPrincipal userPrincipal = new UserPrincipal("test.twu");
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setUserPrincipal(userPrincipal);

        ModelAndView result = homeController.getHomePage(mockHttpServletRequest);

        assertThat(result.getViewName(), CoreMatchers.is("home"));
        assertThat((String)result.getModel().get("username"), CoreMatchers.is("test.twu"));
    }

    @Test
    public void shouldRedirectToCasServerAndInvalidateSessionOnLogout() throws Exception {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        HttpSession mockHttpSession = mock(HttpSession.class);
        mockHttpServletRequest.setSession(mockHttpSession);

        ModelAndView modelAndView = homeController.logoutPage(mockHttpServletRequest);

        assertThat(modelAndView.getViewName(), is("redirect:http://castest.thoughtworks.com/cas/logout"));
        verify(mockHttpSession).invalidate();
    }
}
