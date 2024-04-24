package com.subskill.controller;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class SubSkillSendMailController {

    @RegisterExtension
    static GreenMailExtension greenMail =
            new GreenMailExtension(ServerSetupTest.SMTP)
                    .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
                    .withPerMethodLifecycle(false);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testSendMailToUser() {

        String payload = "{ \"email\": \"user1@example.com\", \"content\": \"Hello World!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Void> response =
                this.testRestTemplate.postForEntity("http://localhost:8080/api/v1/mail/send", request, Void.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());

        await()
                .atMost(2, SECONDS)
                .untilAsserted(
                        () -> {
                            MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
                            assertEquals(1, receivedMessages.length);

                            MimeMessage receivedMessage = receivedMessages[0];
                            assertEquals("Hello World!", GreenMailUtil.getBody(receivedMessage));
                            assertEquals(1, receivedMessage.getAllRecipients().length);
                            assertEquals("duke@spring.io", receivedMessage.getAllRecipients()[0].toString());
                        });
    }
}