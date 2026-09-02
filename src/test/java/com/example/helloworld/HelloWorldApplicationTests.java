package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthenticatedRootRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void authenticatedRootServesHelloWorldPage() throws Exception {
        mockMvc.perform(get("/").with(user("hello")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("index.html"));
    }

    @Test
    void loginPageIsPublic() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/login.html"));
    }

    @Test
    void loginHtmlIsPublic() throws Exception {
        mockMvc.perform(get("/login.html"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Sign in")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("autofocus")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("spellcheck=\"false\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("aria-describedby=\"login-instructions\"")));
    }

    @Test
    void authenticatedHelloWorldPageContainsGreeting() throws Exception {
        mockMvc.perform(get("/index.html").with(user("hello")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Hello, World!")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Signed in")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("role=\"status\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("aria-live=\"polite\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Your session is protected.")));
    }

    @Test
    void helloApiReturnsGreeting() throws Exception {
        mockMvc.perform(get("/api/hello").with(user("hello")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }
}