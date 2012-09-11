package com.thoughtworks.twu.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MailServiceTest {

    private MailSender mockMailSender;
    private MailService mailService;

    @Before
    public void setUp() {
        mockMailSender = mock(MailSender.class);
        mailService = new MailService(mockMailSender);
    }

    @Test
    public void shouldSendAMail() {

        // When
        mailService.send("anything@example.com", "Feedback export", "test");

        // Then
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("anything@example.com");
        message.setSubject("Feedback export");
        message.setText("test");
        verify(mockMailSender).send(eq(message));
    }

}
